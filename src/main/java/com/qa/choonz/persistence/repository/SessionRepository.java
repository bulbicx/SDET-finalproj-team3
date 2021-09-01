package com.qa.choonz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
	Session findByToken(String token);
	boolean existsByToken(String token);
}
