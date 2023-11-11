package com.singsongchanson.domain.room.command.application.dto;

import com.singsongchanson.domain.room.command.domain.aggregate.entity.Room;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class RoomResponseDTO {

    private String roomId;
    private Long userNo;
    private String userName;
    private String userProfileImg;
}
