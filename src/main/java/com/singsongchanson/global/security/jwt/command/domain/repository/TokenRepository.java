package com.singsongchanson.global.security.jwt.command.domain.repository;

import com.singsongchanson.global.security.jwt.command.domain.aggregate.entity.Token;
import com.singsongchanson.global.security.jwt.command.domain.aggregate.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByUserVO(UserVO userVO);

    Optional<Token> findByAccessToken(String accessToken);
}
