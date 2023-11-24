package com.singsongchanson.global.configuration;

import com.singsongchanson.global.security.jwt.command.application.service.CustomTokenService;
import com.singsongchanson.global.security.jwt.filter.JwtAuthenticationFilter;
import com.singsongchanson.global.security.oauth2.handler.OAuth2LoginFailureHandler;
import com.singsongchanson.global.security.oauth2.handler.OAuth2LoginSuccessHandler;
import com.singsongchanson.global.security.oauth2.command.domain.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import com.singsongchanson.global.security.oauth2.command.application.service.CustomOAuth2UserService;
import com.singsongchanson.global.security.oauth2.command.application.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
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
    @Order(0)
    public SecurityFilterChain exceptionSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable()
                .requestMatchers((matchers) ->
                        matchers
                                .antMatchers(
                                        "/", "/error","/favicon.ico", "/**/*.png",
                                        "/**/*.gif", "/**/*.svg", "/**/*.jpg",
                                        "/**/*.html", "/**/*.css", "/**/*.js"
                                )
//                                .antMatchers(
//                                        "/api-docs",
//                                        "/v2/api-docs",  "/v3/api-docs","/configuration/ui",
//                                        "/swagger-resources/**", "/configuration/security",
//                                        "/swagger-ui.html", "/webjars/**","/swagger/**",
//                                        "/swagger-ui/**", "/swagger","/webjars/**"
//                                )...
                                .requestMatchers()
                                .antMatchers(
                                        "/login/**", "/api/v1/musics/ranking", "/api/v1/musics/count"
                                )
                                .requestMatchers(new RegexRequestMatcher("/api/v1/musics","GET"))
                )
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());

        return http.build();
    }
    @Bean
    @Order(1)
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
                .requestMatchers(new RegexRequestMatcher("/api/v1/musics","POST")).permitAll()
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
                .userInfoEndpoint().userService(customOAuth2UserService);

        http.addFilterAfter(new JwtAuthenticationFilter(customUserDetailService, customTokenService), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://192.168.0.163:3000", "https://ac63-121-140-234-46.ngrok-free.app", "http://3.35.77.17"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
