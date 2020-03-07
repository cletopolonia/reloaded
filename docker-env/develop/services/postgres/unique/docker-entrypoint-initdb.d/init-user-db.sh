#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER docker;
    CREATE DATABASE kirk_dev;
    CREATE DATABASE kirk_prod;
    CREATE DATABASE kirk_core;
    GRANT ALL PRIVILEGES ON DATABASE kirk_dev TO docker;
EOSQL




