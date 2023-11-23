package com.singsongchanson.domain.comment.command.domain.repository;

import com.singsongchanson.domain.comment.command.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByRoomId(String roomId);
}
