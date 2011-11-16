package com.javapda.formarten.dao;

import java.util.List;

import com.javapda.formarten.domain.FootballPlayer;

public interface SportsService {
    FootballPlayer saveFootballPlayer(FootballPlayer footballPlayer);
    List<FootballPlayer> getAllFootballPlayers();
    void deleteFootballPlayer(FootballPlayer footballPlayer);
}
