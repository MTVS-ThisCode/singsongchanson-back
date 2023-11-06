package com.singsongchanson.domain.room.command.application.dto;

import com.singsongchanson.domain.room.command.domain.aggregate.vo.Furniture;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.RoomData;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class RoomDataResponseDTO {

    private String roomId;
    private String avatar;
    private List<Furniture> furniture;
    private String userNo;

    public static RoomDataResponseDTO from(RoomData roomData) {

        return new RoomDataResponseDTO(
                roomData.getId(),
                roomData.getAvatar(),
                roomData.getFurniture(),
                roomData.getUserNo());
    }
}
