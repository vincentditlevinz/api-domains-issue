package com.acme.apidomains.process_design.services;

import com.acme.apidomains.process_design.repositories.SkillRepository;
import com.acme.apidomains.process_design.model.Skill;
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
    @Transactional(readOnly = true)// removing this annotation and then transaction handling is a workaround, but we cannot live with such a limitation
    public Flux<Skill> findAllSkills() {
        if(true) throw new RuntimeException("test");//
        //if(true) return Flux.error(new RuntimeException("test")); // gives the exact same outcome
        return skillRepository.findAll(Sort.by("name"));
    }
}
