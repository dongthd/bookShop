package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.entity.Companie;

@Repository
public interface CompanieRepository extends JpaRepository<Companie, Integer>{

}
