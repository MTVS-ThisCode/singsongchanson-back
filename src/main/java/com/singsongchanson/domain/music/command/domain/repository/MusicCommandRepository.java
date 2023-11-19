package com.singsongchanson.domain.music.command.domain.repository;

import com.singsongchanson.domain.music.command.domain.aggregate.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicCommandRepository extends JpaRepository<Music, Long> {

}
