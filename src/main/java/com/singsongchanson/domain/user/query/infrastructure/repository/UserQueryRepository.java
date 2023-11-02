package com.singsongchanson.domain.user.query.infrastructure.repository;

import com.singsongchanson.domain.user.command.domain.aggregate.entity.User;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserQueryRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findBySocialIdAndSocialType(String socialId, SocialType socialType);
}
