# Kotlin dvd rental

- Kotlin x Spring Boot x Doma2のサンプルプロジェクトです
- http://www.postgresqltutorial.com/postgresql-sample-database/ のサンプルデータベースを使います
- DBにpostgres、検索にelasticsearchを使っています
  - spring boot
  - doma2
  - postgres
  - elasticsearch
  - redis（作成中）

## 起動

DB起動(docker-machineを使います）

```bash
$ docker-machine start
$ eval $(docker-machine env default)
$ docker-compose up
```

webサーバ起動（別のターミナルで）

```bash
$ ./boot-web.sh
```

http://localhost:8080/swagger-ui.html

さらに別のターミナルを開いて以下を実行（continuous build。build後にswagger-codegenでapi clientを自動生成）

```
$ ./gradlew build -x test -continuous
```

## TIPS

### 各Dockerコンテナへの接続

```bash
# postgres
$ docker exec -it dvd-rental-postgres-container psql -U postgres dvdrental

# redis
# $ docker exec -it dvd-rental-redis-container redis-cli
```

### kibana

http://{docker-ip}:5601

- username: elastic
- password: changeme
