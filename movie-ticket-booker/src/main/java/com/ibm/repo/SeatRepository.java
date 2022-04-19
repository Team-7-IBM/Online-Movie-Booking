package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
