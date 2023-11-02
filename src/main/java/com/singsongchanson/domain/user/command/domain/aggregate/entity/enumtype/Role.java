package com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype;

import lombok.Getter;

@Getter
public enum Role {

    GUEST("ROLE_GUEST"),     // 스프링 시큐리티에서는 권한(Role) 코드에 항상 "ROLE_" 접두사가 붙어야 함!
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String key;

    Role(String key) {
        this.key = key;
    }
}
