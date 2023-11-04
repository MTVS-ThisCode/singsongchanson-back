package com.singsongchanson.domain.room.command.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Axis {

    private double x;
    private double y;
    private double z;

    public Axis(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
