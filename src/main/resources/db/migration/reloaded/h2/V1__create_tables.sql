create SCHEMA IF NOT EXISTS ${schema};

create table IF NOT EXISTS ${schema}.show(
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) not null,
  partial_url VARCHAR(255) not null,
  days VARCHAR(255) not null,
  enabled bool,
  registration_time TIMESTAMP
);

create sequence IF NOT EXISTS ${schema}.episode_seq;

create table IF NOT EXISTS ${schema}.episode(
  id bigint default nextval('${schema}.episode_seq') PRIMARY KEY,
  name VARCHAR(255) not null,
  url VARCHAR(1024) not null,
  download_time TIMESTAMP,
  download_in_sec long,
  download_in_mb long,
  duration_in_mins long
);

