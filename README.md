# kotlin-dvd-rental-dev

## 構成

![architecture](architecture.png?raw=true "architecture")

## how to run

1. api（Kotlin APIサーバー）を起動。swagger-codegenでrubyクライアントを生成
2. customer-front（Rails フロントサーバー:顧客画面）を起動
3. admin-front（Rails フロントサーバー:管理画面）を起動

## how to push to all remote repositories

```
$ ./git-remote-add
$ ./git-push-all
```
