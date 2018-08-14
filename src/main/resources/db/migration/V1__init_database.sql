CREATE SCHEMA IF NOT EXISTS userservice;
SET search_path TO userservice;
CREATE SEQUENCE hibernate_sequence START 1000;

CREATE TABLE IF  NOT EXISTS person (
  id serial primary KEY,
  person_id character varying(11) unique not null,
  name character varying (30) not null,
  age integer,
  birth_date date,
  email character varying (30) unique not null
);

CREATE UNIQUE INDEX idx_person_id on person (person_id);




