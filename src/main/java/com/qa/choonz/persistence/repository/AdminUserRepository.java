package com.qa.choonz.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.AdminUser;
import com.qa.choonz.persistence.domain.PublicUser;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long>{
	Optional<AdminUser> findByUsername(String username);
}
