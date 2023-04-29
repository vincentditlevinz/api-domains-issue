package com.acme.apidomains.process_design.mappers;

import static com.acme.domains.process_design.QueryServiceProto.ListSkillsResponse;

import com.acme.apidomains.process_design.model.Skill;

import java.util.List;

public final class SkillMapper {
    private SkillMapper() {}

    public static ListSkillsResponse.Skill toGrpc(Skill skill) {
        return ListSkillsResponse.Skill.newBuilder()
                .setId(skill.getId())
                .setName(skill.getName())
                .setDescription(skill.getDescription())
                .build();
    }

    public static ListSkillsResponse toGrpcResponse(List<ListSkillsResponse.Skill> skill) {
        return ListSkillsResponse.newBuilder().addAllSkills(skill).build();
    }
}
