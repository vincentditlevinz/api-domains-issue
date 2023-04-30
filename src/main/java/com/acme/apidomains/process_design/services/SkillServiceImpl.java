package com.acme.apidomains.process_design.services;

import com.acme.apidomains.process_design.model.Skill;
import com.acme.apidomains.process_design.repositories.SkillRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    @Transactional(readOnly = true)
    public Flux<Skill> findAllSkills() {
        return skillRepository.findAll(Sort.by("name"));
    }
}
