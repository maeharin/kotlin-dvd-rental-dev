#!/bin/bash

sleep 3

# check server is running
server_status="down"
for i in {0..10}; do
    status_code=`curl -LI http://localhost:8080/v2/api-docs?group=staff-api -o /dev/null -w '%{http_code}\n' -s`
    if [ ${status_code} = "200" ];then
        server_status="up"
        break
    fi
    sleep 1
done

if [ ${server_status} = "down" ];then
    : server is down
    exit 1
fi

RUBY_GENERATED_DIR='./generated/ruby'
TYPESCRIPT_GENERATED_DIR='./generated/typescript'

if [ -e $RUBY_GENERATED_DIR ]; then
    rm -rf $RUBY_GENERATED_DIR
fi

if [ -e $TYPESCRIPT_GENERATED_DIR ]; then
    rm -rf $TYPESCRIPT_GENERATED_DIR
fi

mkdir $RUBY_GENERATED_DIR
mkdir $TYPESCRIPT_GENERATED_DIR

function code_gen() {
  # ruby
  mkdir ${RUBY_GENERATED_DIR}/${1}

  java -jar ./swagger-codegen-cli-2.2.3.jar generate \
      --lang ruby \
      --input-spec http://localhost:8080/v2/api-docs?group=${1} \
      --config ./ruby_config_${1}.json \
      --output ${RUBY_GENERATED_DIR}/${1}


  # typescript
  mkdir ${TYPESCRIPT_GENERATED_DIR}/${1}

  java -jar ./swagger-codegen-cli-2.3.0-SNAPSHOT.jar generate \
      --lang typescript-fetch \
      --input-spec http://localhost:8080/v2/api-docs?group=${1} \
      --config ./typescript_config_${1}.json \
      --output ${TYPESCRIPT_GENERATED_DIR}/${1}

  (cd ${TYPESCRIPT_GENERATED_DIR}/${1} \
      && yarn install \
      && yarn run build \
      && yarn link
  )
}

code_gen staff-api
code_gen customer-api
