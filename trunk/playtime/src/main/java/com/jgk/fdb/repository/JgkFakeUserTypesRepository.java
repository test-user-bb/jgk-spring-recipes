package com.jgk.fdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jgk.fdb.domain.JgkFakeUserTypes;


public interface JgkFakeUserTypesRepository  extends JpaRepository<JgkFakeUserTypes, Integer>, JpaSpecificationExecutor<JgkFakeUserTypes> {

}
