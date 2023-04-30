package com.acme.apidomains.process_design.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Table(schema = "api_domains", name = "skill")
public class Skill implements Serializable {

    @Id
    @Column(ID)
    private int id;

    @Column(NAME)
    private String name;

    @Column(DESCRIPTION)
    private String description;

    @Column(CREATED_AT)
    private LocalDateTime createdAt;

    @Column(UPDATED_AT)
    private LocalDateTime updatedAt;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
}
