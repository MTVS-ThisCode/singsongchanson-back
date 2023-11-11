package com.singsongchanson.domain.room.query.domain.service;

import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public interface RoomQueryDomainService {
    FindUserResponseDTO getUserInfo(Long userNo);
}
