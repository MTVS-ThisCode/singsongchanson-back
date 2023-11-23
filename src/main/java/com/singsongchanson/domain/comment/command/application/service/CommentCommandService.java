package com.singsongchanson.domain.comment.command.application.service;

import com.singsongchanson.domain.comment.command.application.dto.CreateCommentRequestDTO;
import com.singsongchanson.domain.comment.command.application.dto.CommentResponseDTO;
import com.singsongchanson.domain.comment.command.application.dto.UpdateCommentRequestDTO;
import com.singsongchanson.domain.comment.command.domain.entity.Comment;
import com.singsongchanson.domain.comment.command.domain.entity.vo.CommentUserVO;
import com.singsongchanson.domain.comment.command.domain.repository.CommentRepository;
import com.singsongchanson.domain.comment.command.domain.service.CommentDomainService;
import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final CommentDomainService commentDomainService;

    public CommentResponseDTO saveComment(UserPrincipal userPrincipal, CreateCommentRequestDTO createCommentRequest) {

        FindUserResponseDTO findUserResponse = commentDomainService.getUserInfo(userPrincipal.getId());

        Comment comment = new Comment(
                createCommentRequest.getContent(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")),
                new CommentUserVO(userPrincipal.getId(), findUserResponse.getNickName(), findUserResponse.getProfileImg()),
                createCommentRequest.getRoomId());

        commentRepository.save(comment);

        return CommentResponseDTO.from(comment);
    }


    public CommentResponseDTO updateComment(Long commentNo, UserPrincipal userPrincipal, UpdateCommentRequestDTO updateCommentRequest) {

        Optional<Comment> optionalComment = commentRepository.findById(commentNo);

        if(optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if(comment.getUserVO().getUserNo() == userPrincipal.getId()) {
                comment.update(updateCommentRequest);
                commentRepository.save(comment);

                return CommentResponseDTO.from(comment);
            }
        }

        return null;    //not found 에러 코드 보내기~!!!
    }

    public boolean deleteComment(Long commentNo, UserPrincipal userPrincipal) {

        Optional<Comment> optionalComment = commentRepository.findById(commentNo);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if (comment.getUserVO().getUserNo() == userPrincipal.getId()) {
                commentRepository.delete(comment);

                return true;
            }
        }
        return false;
    }
}
