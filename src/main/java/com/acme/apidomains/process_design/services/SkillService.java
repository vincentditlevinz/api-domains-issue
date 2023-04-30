package com.acme.apidomains.process_design.services;

import com.acme.apidomains.process_design.model.Skill;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicBoolean;

public interface SkillService {
    AtomicBoolean GatewayContext = new AtomicBoolean(false);

    Flux<Skill> findAllSkills();
}
