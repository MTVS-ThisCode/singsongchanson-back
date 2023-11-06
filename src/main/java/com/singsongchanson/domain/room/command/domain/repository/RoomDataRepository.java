package com.singsongchanson.domain.room.command.domain.repository;

import com.singsongchanson.domain.room.command.domain.aggregate.entity.RoomData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories
public interface RoomDataRepository extends MongoRepository<RoomData, String> {
}
