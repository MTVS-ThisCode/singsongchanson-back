package com.singsongchanson.domain.music.command.domain.repository;

import com.singsongchanson.domain.music.command.domain.aggregate.entity.Music;
import com.singsongchanson.domain.music.command.domain.aggregate.vo.GenerateUserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    Optional<Music> findByMusicNo(Long musicNo);

    List<Music> findByGenerateUserVO(GenerateUserVO generateUserVO);
}
