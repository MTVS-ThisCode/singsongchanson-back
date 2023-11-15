package com.singsongchanson.domain.room.command.domain.repository;

import com.singsongchanson.domain.room.command.domain.aggregate.entity.Room;
import com.singsongchanson.domain.room.command.domain.aggregate.vo.RoomOwnerVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    Optional<Room> findByRoomOwnerVO(RoomOwnerVO roomOwnerVO);
}
