#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE dvdrental;
EOSQL

pg_restore -U postgres -d dvdrental /tmp/dumpfile/dvdrental.tar

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" dvdrental <<-EOSQL
    create table oauth_access_token (
      authentication_id varchar(256) primary key,
      user_name varchar(256),
      client_id varchar(256),
      token_id varchar(256),
      token bytea,
      authentication bytea,
      refresh_token varchar(256)
    );

    comment on table oauth_access_token is 'アクセストークン(spring security oauthのJdbcTokenStoreに合致するテーブル)';
    comment on column oauth_access_token.authentication_id is 'PK';
    comment on column oauth_access_token.user_name is 'ユーザー名（admin.user_id or consultant.login_id)';
    comment on column oauth_access_token.client_id is 'クライアントアプリケーション名';
    comment on column oauth_access_token.token_id is 'アクセストークンID';
    comment on column oauth_access_token.token is 'アクエストークン本体';
    comment on column oauth_access_token.authentication is '認証情報';
    comment on column oauth_access_token.refresh_token is 'リフレッシュトークン';
EOSQL
