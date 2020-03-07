create SCHEMA IF NOT EXISTS ${schema};

create table IF NOT EXISTS ${schema}.show(
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) not null,
  partial_url VARCHAR(255) not null,
  days VARCHAR(255) not null,
  enable boolean default true,
  registration_time TIMESTAMPTZ not null
);

create sequence IF NOT EXISTS ${schema}.episode_seq;
create table IF NOT EXISTS ${schema}.episode(
  id bigint default nextval('${schema}.episode_seq') PRIMARY KEY,
  name VARCHAR(255) not null,
  url VARCHAR(1024) not null,
  creation_time TIMESTAMP not null,
  modification_time TIMESTAMP not null,
  retry int not null,
  status VARCHAR(255) not null,
  download_in_sec int,
  download_in_mb float,
  duration_in_mins int
);
