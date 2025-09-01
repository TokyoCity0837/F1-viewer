package com.example.f1analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.f1analysis.model.Team;

@Repository
public interface TeamRepository extends JpaRepository <Team, Integer> {};
