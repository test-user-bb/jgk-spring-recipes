package com.jgk.spring31hib4.dao;

import com.jgk.spring31hib4.domain.Granny;
import com.jgk.spring31hib4.domain.Jed;

public interface ClampettDao {
    Jed getJedById(Integer id);
    Granny getGrannyById(Integer id);
    void save(Object entity);
}
