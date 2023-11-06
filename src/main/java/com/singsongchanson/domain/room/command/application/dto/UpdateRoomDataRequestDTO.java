package com.singsongchanson.domain.room.command.application.dto;

import com.singsongchanson.domain.room.command.domain.aggregate.vo.Furniture;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class UpdateRoomDataRequestDTO {

    private String avatar;
    private List<Furniture> furniture;
}
