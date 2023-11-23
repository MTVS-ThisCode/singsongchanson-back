package com.singsongchanson.domain.room.command.application.dto;

import com.singsongchanson.domain.comment.command.application.dto.CommentResponseDTO;
import com.singsongchanson.domain.room.command.domain.aggregate.vo.Furniture;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class FindRoomDataResponseDTO {

    private String roomId;
    private List<Furniture> furniture;
    private String userName;
    private String userProfileImg;
}
