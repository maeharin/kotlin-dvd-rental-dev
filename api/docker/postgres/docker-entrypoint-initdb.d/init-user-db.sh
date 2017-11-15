#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE dvdrental;
EOSQL

pg_restore -U postgres -d dvdrental /tmp/dumpfile/dvdrental.tar

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" dvdrental <<-EOSQL
    BEGIN;

    CREATE TABLE oauth_access_token (
      authentication_id VARCHAR(256) PRIMARY KEY,
      user_name VARCHAR(256),
      client_id VARCHAR(256),
      token_id VARCHAR(256),
      token BYTEA,
      authentication BYTEA,
      refresh_token VARCHAR(256)
    );

    ALTER TABLE customer ADD COLUMN login_id VARCHAR(256);
    ALTER TABLE customer ADD COLUMN password_digest VARCHAR(256);
    CREATE UNIQUE INDEX idx_unq_customer_login_id ON customer(login_id);

    ALTER TABLE staff DROP COLUMN username;
    ALTER TABLE staff DROP COLUMN password;
    ALTER TABLE staff ADD COLUMN login_id VARCHAR(256);
    ALTER TABLE staff ADD COLUMN password_digest VARCHAR(256);
    CREATE UNIQUE INDEX idx_unq_staff_login_id ON staff(login_id);

    COMMIT;
EOSQL
