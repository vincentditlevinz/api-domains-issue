package com.acme.apidomains.process_design.services;

import com.acme.apidomains.process_design.model.Skill;
import com.acme.apidomains.process_design.repositories.SkillRepository;
import com.acme.apidomains.process_design.spi.ListSkillCalled;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    private final ApplicationEventPublisher events;

    @Override
    @Transactional(readOnly = true)
    public Flux<Skill> findAllSkills() {
        var skills = skillRepository.findAll(Sort.by("name"));
        // Avoid an artificial loop, in a real world scenario, we won't send and event to call the
        // same service again and again !
        if (!GatewayContext.getAndSet(false)) events.publishEvent(new ListSkillCalled());
        return skills;
    }
}
