package com.singsongchanson.domain.comment.command.domain.entity;

import com.singsongchanson.domain.comment.command.application.dto.UpdateCommentRequestDTO;
import com.singsongchanson.domain.comment.command.domain.entity.vo.CommentUserVO;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "TBL_COMMENT")
@Getter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;
    @Column
    private String content;
    @Column
    private String createdDate;
    @Column
    private String modifiedDate;
    @Embedded
    private CommentUserVO userVO;
    @Column
    private String roomId;

    public Comment() {
    }

    public Comment(String content, String createdDate, String modifiedDate, CommentUserVO userVO, String roomId) {
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.userVO = userVO;
        this.roomId = roomId;
    }

    public void update(UpdateCommentRequestDTO updateCommentRequest) {
        this.content = updateCommentRequest.getContent();
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
