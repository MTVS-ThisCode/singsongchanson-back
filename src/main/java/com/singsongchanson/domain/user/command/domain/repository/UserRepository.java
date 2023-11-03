package com.singsongchanson.domain.user.command.domain.repository;

import com.singsongchanson.domain.user.command.domain.aggregate.entity.User;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNo(Long userNo);

    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
