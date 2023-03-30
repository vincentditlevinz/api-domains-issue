package com.acme.apidomains.process_design.mappers;

import com.acme.apidomains.process_design.model.Skill;
import com.acme.domains.process_design.transport.ListSkills;

import java.util.List;

public final class SkillMapper {
    private SkillMapper() {
    }


    public static ListSkills.ListSkillsResponse.Skill toGrpc(Skill skill) {
        return ListSkills.ListSkillsResponse.Skill.newBuilder()
                .setId(skill.getId())
                .setName(skill.getName())
                .setDescription(skill.getDescription())
                .build();
    }

    public static ListSkills.ListSkillsResponse toGrpcResponse(List<ListSkills.ListSkillsResponse.Skill> skill) {
        return ListSkills.ListSkillsResponse.newBuilder()
                .addAllSkills(skill)
                .build();
    }
}
