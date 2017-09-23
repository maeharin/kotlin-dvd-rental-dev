# kotlin dvd rental

- kotlin x spring boot x doma2
- http://www.postgresqltutorial.com/postgresql-sample-database/ のサンプルデータベースを使います

## 起動

DB起動(dockerを使います）

```
$ ./docker_build_db.sh
```

webサーバ起動

```
$ ./gradlew clean bootRun
```

http://localhost:8080/api/v1/films

もう一つ別のターミナルを開いて

```
$ ./gradlew -t build
```
