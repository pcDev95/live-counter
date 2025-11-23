package com.livecounter.lc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livecounter.lc.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
