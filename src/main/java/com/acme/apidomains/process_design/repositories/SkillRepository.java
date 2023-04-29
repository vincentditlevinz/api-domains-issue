package com.acme.apidomains.process_design.repositories;

import com.acme.apidomains.process_design.model.Skill;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends ReactiveSortingRepository<Skill, Integer> {}
