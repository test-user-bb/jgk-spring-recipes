package com.javapda.formarten.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.javapda.formarten.domain.FootballPlayer;

@Service
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class SportsServiceImpl implements SportsService {

    @Inject FootballPlayerDao footballPlayerDao;
    public FootballPlayerDao getFootballPlayerDao() {
        return footballPlayerDao;
    }
    public void setFootballPlayerDao(FootballPlayerDao footballPlayerDao) {
        this.footballPlayerDao = footballPlayerDao;
    }
    public List<FootballPlayer> getAllFootballPlayers() {
        return footballPlayerDao.getAllFootballPlayers();
    }
    public FootballPlayer saveFootballPlayer(FootballPlayer footballPlayer) {
        return footballPlayerDao.save(footballPlayer);
        
    }
    public void deleteFootballPlayer(FootballPlayer footballPlayer) {
        footballPlayerDao.delete(footballPlayer);
        
    }
    
    
}
