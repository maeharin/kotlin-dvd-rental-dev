# Kotlin dvd rental

- Kotlin x Spring Boot x Doma2を使ったREST APIのサンプルプロジェクトです
- http://www.postgresqltutorial.com/postgresql-sample-database/ のサンプルデータベースを使います
- swagger-codegenでapiクライアント（ruby gem）を自動生成します
- DBにpostgres、検索にelasticsearchを使っています
  - spring boot
  - doma2
  - postgres
  - elasticsearch

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

テスト実行

```
$ source .env.dev
$ ./gradlew test
```

## 認証APIへのアクセス

```
$ curl -sS -XPOST \
    -u customer-api-client:hoge \
    http://localhost:8080/oauth/token \
    -H "Accept: application/json" \
    -d "username=<TODO>&password=<TODO>&grant_type=password" \
    | jq -r '"bearer " + .access_token'
```

swagger uiの右上にあるauthorizeボタンを押し、
上記の標準出力に表示された文字列（例：bearer TODO）をセット

## TIPS

### 各Dockerコンテナへの接続

```bash
# postgres
$ docker exec -it dvd-rental-postgres-container psql -U postgres dvdrental
```

### kibana

http://{docker-ip}:5601

- username: elastic
- password: changeme
