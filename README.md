# kotlin dvd rental

- kotlin x spring boot x doma2
- http://www.postgresqltutorial.com/postgresql-sample-database/ のサンプルデータベースを使います

## 起動

DB起動(dockerを使います）

```
$ ./boot-db.sh
```

webサーバ起動

```
$ ./boot-web.sh
```

http://localhost:8080/api/v1/films

もう一つ別のターミナルを開いて

```
$ ./gradlew -t build
```
