package com.jgk.fdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jgk.fdb.domain.Radimmo5;
import com.jgk.fdb.domain.Radimmo5PK;


public interface Radimmo5Repository  extends JpaRepository<Radimmo5, Radimmo5PK>, JpaSpecificationExecutor<Radimmo5> {

}
