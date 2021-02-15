package com.fatih.report.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatih.report.demo.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

}
