package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.User;

public interface AdminRepository extends JpaRepository<User, Integer> {

}
