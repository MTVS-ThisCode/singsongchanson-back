package com.singsongchanson.domain.room.command.domain.aggregate.vo;

import com.singsongchanson.domain.room.command.domain.aggregate.vo.Axis;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Furniture {

    private String name;
    private Axis position;
    private Axis rotation;
    private String url;
    private double scale;

    public Furniture(String name, Axis position, Axis rotation, String url, double scale) {
        this.name = name;
        this.position = position;
        this.rotation = rotation;
        this.url = url;
        this.scale = scale;
    }
}
