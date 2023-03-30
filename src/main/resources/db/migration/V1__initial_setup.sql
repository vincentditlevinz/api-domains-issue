CREATE SCHEMA api_domains;

CREATE TABLE IF NOT EXISTS api_domains.skill
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(60)  NOT NULL,
    description VARCHAR(500) NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS api_domains_skill_name_idx ON api_domains.skill(name);