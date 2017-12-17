かあさんメンバーによるリポジトリ！


・・・随時執筆中。

#### 実行環境
- JAVA8
- glassfish4.1 (※1)
- MySQL5.7.7

上記以外のライブラリ等はMaven参照

#### 想定開発環境
- NetBeans 8.1 (※2)
- EditorConfig

#### 参考　環境構築手順
##### ①データベースの準備
###### 1.MySQLをインストール
[https://dev.mysql.com/downloads/](https://dev.mysql.com/downloads/)

###### 2.MySQLにrootでログイン
```
mysql -u root -p
```

###### 3.kasanスキーマの作成
```
create database kasan;
create datebase kasan_test;
```

###### 4.kasanユーザーの作成
```
create user 'kasan'@'localhost' identified by 'kasan';
```

###### 5.kasanユーザーに権限付与
```
grant all on kasan.* to 'kasan'@'localhost';
grant all on kasan_test.* to 'kasan'@'localhost';
```

###### 6.kasanユーザーでログイン後、スキーマと権限の確認
```
exit;
mysql -u kasan -p
kasan
show databases;
show grants;
```

###### 7.テーブルの作成
```
source [プロジェクトのルートパス]/src/main/resources/data/Kasan.sql
source [プロジェクトのルートパス]/src/main/resources/data/Kasan_test.sql
```

##### ②NetBeansとサーバーの準備・実行
###### 1.NetBeansとGlassFishをインストール
[https://netbeans.org/downloads/8.1/](https://netbeans.org/downloads/8.1/)
[https://glassfish.java.net/download-archive.html](https://glassfish.java.net/download-archive.html)
###### 2.NetBeansにサーバーを追加
サービスウィンドウ→サーバーを右クリック→GlassFish Server選択→
インストールしたGlassFishのフォルダを選択→ドメイン名等入力→終了
###### 3.サーバーを起動
追加したサーバーを右クリック→起動
###### 4.ドメインにリソースの設定を追加
（コマンドプロンプト・ターミナル）
インストールしたGlassFishのbinディレクトリまで移動後、

```
asadmin add-resources [プロジェクトのルートパス]/src/main/resources/env/localhost/glassfish-resources.xml
```

###### 5.Kasanプロジェクトをビルド
プロジェクトウィンドウ→Kasanプロジェクトを右クリック→依存性でビルド
###### 6.Kasanプロジェクトを実行
プロジェクトウィンドウ→Kasanプロジェクトを右クリック→実行
###### 7.EditorConfigプラグインのインストール
...模索中
- - -
※1 glassfish4.1.1は不具合確認済み(要調査)。
 (glassfish4.1も@Transactionalに不具合があり開発が辛いので、こっそり[payara4.1.1.171](http://www.payara.fish/)を使ってたり。。。)
※2 NetBeans8.2でも問題ないと思われるが、IMEを使用してると画面スクロールに不具合がある模様。
