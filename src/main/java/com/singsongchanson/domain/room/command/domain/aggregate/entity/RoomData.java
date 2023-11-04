package com.singsongchanson.domain.room.command.domain.aggregate.entity;

import com.singsongchanson.domain.room.command.application.dto.UpdateRoomDataRequestDTO;
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
    private String avatar;
    private String userNo;
    private List<Furniture> furniture;

    @Builder
    public RoomData(String id, String avatar, List<Furniture> furniture, String userNo) {
        this.id = id;
        this.avatar = avatar;
        this.furniture = furniture;
        this.userNo = userNo;
    }

    public void update(UpdateRoomDataRequestDTO updateRoomData) {
        this.avatar = updateRoomData.getAvatar();
        this.furniture = updateRoomData.getFurniture();
    }
}
