package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.f1_pilot;
import org.springframework.stereotype.Repository;


@Repository
public interface f1_pilotRepository extends JpaRepository <f1_pilot, Integer> {};
