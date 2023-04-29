package com.acme.apidomains.process_design.services;

import com.acme.apidomains.process_design.model.Skill;

import reactor.core.publisher.Flux;

public interface SkillService {
    Flux<Skill> findAllSkills();
}
