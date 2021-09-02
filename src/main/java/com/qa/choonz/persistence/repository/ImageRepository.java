package com.qa.choonz.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Image;
@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
}
