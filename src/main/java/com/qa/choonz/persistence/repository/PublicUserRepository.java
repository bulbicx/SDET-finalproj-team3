package com.qa.choonz.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.PublicUser;

@Repository
public interface PublicUserRepository extends JpaRepository<PublicUser, Long>{
	Optional<PublicUser> findByUsername(String username);
}
