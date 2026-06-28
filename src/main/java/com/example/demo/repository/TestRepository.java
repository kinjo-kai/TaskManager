package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Task;

public interface TestRepository extends JpaRepository<Task, Long>{
}
