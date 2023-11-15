package com.singsongchanson.domain.room.command.domain.aggregate.entity;

import com.singsongchanson.domain.room.command.application.dto.UpdateRoomDataRequestDTO;
import com.singsongchanson.domain.room.command.domain.aggregate.vo.Furniture;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RoomData {

    @Id
    private String id;      // 몽고db objectId
    private List<Furniture> furniture;

    @Builder
    public RoomData(String id, List<Furniture> furniture) {
        this.id = id;
        this.furniture = furniture;
    }

    public void update(UpdateRoomDataRequestDTO updateRoomData) {
        this.furniture = updateRoomData.getFurniture();
    }
}
