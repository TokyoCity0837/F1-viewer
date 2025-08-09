package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.f1_team;
import org.springframework.stereotype.Repository;

@Repository
public interface f1_teamRepository extends JpaRepository <f1_team, Integer> {};
