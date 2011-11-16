package com.javapda.formarten.dao;

import java.util.List;

import com.javapda.formarten.domain.FootballPlayer;


public interface FootballPlayerDao {

    
    FootballPlayer save(FootballPlayer player);
    List<FootballPlayer> getAllFootballPlayers();
    void delete(FootballPlayer footballPlayer);
    
}
