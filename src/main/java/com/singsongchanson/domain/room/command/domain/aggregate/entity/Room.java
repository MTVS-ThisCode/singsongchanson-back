package com.singsongchanson.domain.room.command.domain.aggregate.entity;

import com.singsongchanson.domain.room.command.domain.aggregate.vo.RoomOwnerVO;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ROOM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Room {

    @Id
    private String roomId;

    @Embedded
    private RoomOwnerVO roomOwnerVO;

    @Builder
    public Room(String roomId, RoomOwnerVO roomOwnerVO) {
        this.roomId = roomId;
        this.roomOwnerVO = roomOwnerVO;
    }
}
