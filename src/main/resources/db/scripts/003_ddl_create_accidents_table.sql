CREATE TABLE accidents(
  id bigserial primary key,
  name varchar,
  text varchar not null,
  address varchar not null,
  type_id bigint references accident_types (id)
);