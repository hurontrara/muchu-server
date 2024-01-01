package com.server.muchu.search.repository;

import com.server.muchu.search.entity.Meme;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchRepository extends JpaRepository<Meme, Long> {

    @Query("select m from Meme m where m.clusterNum = :clusterNum")
    Page<Meme> getMemePageByClusterNum(@Param("clusterNum") int clusterNum, Pageable pageable);

}
