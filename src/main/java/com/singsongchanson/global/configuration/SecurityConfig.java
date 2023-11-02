package com.singsongchanson.global.configuration;

import com.singsongchanson.global.security.jwt.command.application.service.CustomTokenService;
import com.singsongchanson.global.security.jwt.filter.JwtAuthenticationFilter;
import com.singsongchanson.global.security.oauth2.handler.OAuth2LoginFailureHandler;
import com.singsongchanson.global.security.oauth2.handler.OAuth2LoginSuccessHandler;
import com.singsongchanson.global.security.oauth2.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import com.singsongchanson.global.security.oauth2.service.CustomOAuth2UserService;
import com.singsongchanson.global.security.oauth2.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity      // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomUserDetailService customUserDetailService;
    private final CustomTokenService customTokenService;

    /* 설명. 정적 자원의 접근에 대해 ‘인가’ 설정을 담당하는 메소드 */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 대해서 Security를 적용하지 않음으로 설정
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()       // csrf 보호 비활성화. restApi라 상태를 저장하지 않으니 해도된다???
                .formLogin().disable()
                .httpBasic().disable()
                .cors()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)     // jwt 사용할거라 세션 생성 안함
                    .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS).permitAll()    // CORS 방지. 로그인요청 url 이전에 OPTIONS 라는 요청을 동시에 보내기 때문에 허용
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/api/v1/musics/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll()       // 나머지 모든 요청은 모든 사용자에게 허용
                    .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                    .successHandler(oAuth2LoginSuccessHandler) // 동의하고 계속하기를 눌렀을 때 Handler 설정
                    .failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 핸들러 설정
                    .userInfoEndpoint().userService(customOAuth2UserService); // Authentication 생성에 필요한 OAuth2User를 반환하는 클래스를 지정

        http.addFilterAfter(new JwtAuthenticationFilter(customUserDetailService, customTokenService), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
