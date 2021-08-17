package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.entity.Migration;

@Repository
public interface MigrationRepository extends JpaRepository<Migration, Integer>{

}
