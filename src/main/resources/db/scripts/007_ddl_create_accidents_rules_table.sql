CREATE TABLE accidents_rules(
    id bigserial primary key,
    accident_id bigint not null references accidents (id),
    rule_id bigint not null references rules (id),
    UNIQUE (accident_id, rule_id)
);