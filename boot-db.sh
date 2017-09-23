#!/bin/bash

IMAGE_NAME='postgres-dvd-rental'
CONTAINER_NAME='postgres-dvd-rental-container'
DUMP_FILE_DIR=${PWD}/docker/postgres/dumpfile/

docker stop ${CONTAINER_NAME}

# イメージ作成
docker build -t ${IMAGE_NAME} ./docker/postgres

# コンテナ起動
docker run --rm \
    --name ${CONTAINER_NAME} \
    -p 5432:5432 \
    -v ${DUMP_FILE_DIR}:/tmp/dumpfile/ \
    ${IMAGE_NAME}
