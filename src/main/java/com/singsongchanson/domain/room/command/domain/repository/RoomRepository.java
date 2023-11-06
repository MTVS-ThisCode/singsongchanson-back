package com.singsongchanson.domain.room.command.domain.repository;

import com.singsongchanson.domain.room.command.domain.aggregate.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
}
