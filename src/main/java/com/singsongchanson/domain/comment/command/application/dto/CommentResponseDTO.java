package com.singsongchanson.domain.comment.command.application.dto;

import com.singsongchanson.domain.comment.command.domain.entity.Comment;
import com.singsongchanson.domain.comment.command.domain.entity.vo.CommentUserVO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CommentResponseDTO {

    private Long commentNo;
    private String content;
    private String createdDate;
    private String modifiedDate;
    private CommentUserVO userVO;    // 댓글 작성자
    private String roomNo;    // 댓글이 달린 Room

    public static CommentResponseDTO from(Comment comment) {
        return new CommentResponseDTO(
                comment.getCommentNo(),
                comment.getContent(),
                comment.getCreatedDate(),
                comment.getModifiedDate(),
                comment.getUserVO(),
                comment.getRoomId()
        );
    }
}
