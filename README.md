# Kotlin dvd rental

- Kotlin x Spring Boot x Doma2を使ったREST APIのサンプルプロジェクトです
- http://www.postgresqltutorial.com/postgresql-sample-database/ のサンプルデータベースを使います
- swagger-codegenでapiクライアント（ruby gem）を自動生成します
- DBにpostgres、検索にelasticsearchを使っています

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

## APIへのアクセス

全てのloginidとpasswordを初期化

```
// 以下を実行すると全てのuserとstaffのloginidとpasswordが初期化されます
// passwordは全てtestになります
$ curl -sS -XPOST http://localhost:8080/api/v1/sysadmin/init-all-loginid-and-password
```

customer_1のaccess tokenを取得

```
$ curl -sS -XPOST \
    -u customer-api-client:hoge \
    http://localhost:8080/oauth/token \
    -H "Accept: application/json" \
    -d "user_type=customer&username=customer_1&password=test&grant_type=password" \
    | jq -r '"bearer " + .access_token'

// swagger uiの右上にあるauthorizeボタンを押し、上記の標準出力に表示された文字列（例：bearer xxx）をセットすると
// staff apiにアクセスできるようになります
```

staff_1のaccess tokenを取得

```
$ curl -sS -XPOST \
    -u staff-api-client:fuge \
    http://localhost:8080/oauth/token \
    -H "Accept: application/json" \
    -d "user_type=staff&username=staff_1&password=test&grant_type=password" \
    | jq -r '"bearer " + .access_token'
```

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
