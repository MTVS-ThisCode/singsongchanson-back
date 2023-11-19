package com.singsongchanson.domain.music.command.domain.aggregate.entity.enumtype;

import lombok.Getter;

@Getter
public enum SongWriter {

    USER("user"),
    AI("ai");

    private final String key;

    SongWriter(String key) {
        this.key = key;
    }
}
