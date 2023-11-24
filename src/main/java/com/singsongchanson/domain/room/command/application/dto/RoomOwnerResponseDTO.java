package com.singsongchanson.domain.room.command.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class RoomOwnerResponseDTO {

    private String roomId;
    private Long userNo;
    private String userName;
    private String userProfileImg;
}
