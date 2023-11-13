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
    private List<Furniture> furniture;

    public static RoomDataResponseDTO from(RoomData roomData) {

        return new RoomDataResponseDTO(
                roomData.getId(),
                roomData.getFurniture()
        );
    }
}
