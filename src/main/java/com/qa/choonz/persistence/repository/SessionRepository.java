package com.qa.choonz.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Session;
import com.qa.choonz.persistence.domain.User;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{

}
