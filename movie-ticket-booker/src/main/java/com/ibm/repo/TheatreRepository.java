package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

}
