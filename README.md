
## 使用予定の技術

| 技術名 | 用途 |
| --- | --- |
| Jetpack Compose | UIを構築する |
| Room | データをローカルデータベースに保存 |
| Retrofit | HTTP通信 |

# ステータス

- 期間 : 7/2 ~ 7/8

# 基本方針

本プロジェクトは各作業を以下の順で進める。
同一フェーズ内の作業は並行作業可能である。

## フェーズ1

1-1 Jetpack Composeに移行 (issue#8-1) `branch:migrate-compose`

- dependenciesを更新
- UIをComposeに置き換え
- データ取得はとりあえずモックで代用

## フェーズ2

2-1 基本的なソースコードの修正 (issue#1 issue#2 issue#5) `branch:basic-src-fix`

## フェーズ3

3-1 アーキテクチャの適用 (issue#4 issue#6 issue#3-1) `branch:apply-architecture`

- MVVM + Repositoryパターン でアーキテクチャを適用

## フェーズ4

4-1 モックで代用したデータ取得部分をRetrofitで実装 `branch:implement-retrofit`

4-2 エラーのハンドリング (issue#3-2) `branch:error-handlering`

## フェーズ5

5-1 テストを追加 (issue#7) `branch:add-test`

## フェーズ6

6-1 UIのブラッシュアップ (issue#8) `branch:brushup-ui`

- 画面設計をfigmaで用意する

6-2 新機能の追加 (issue#9) `branch:<feature>`

- 具体的なアイデアについては [新機能を追加](https://github.com/TBSten/yumemi-android-engineer-codecheck-202307/issues/9) のコメントに随時追加していく
- 機能によっては 6-1 と並行作業が不可能になるので注意が必要。

---
---
---

# 株式会社ゆめみ Android エンジニアコードチェック課題

## 概要

本プロジェクトは株式会社ゆめみ（以下弊社）が、弊社に Android エンジニアを希望する方に出す課題のベースプロジェクトです。本課題が与えられた方は、下記の概要を詳しく読んだ上で課題を取り組んでください。

## アプリ仕様

本アプリは GitHub のリポジトリを検索するアプリです。

<img src="docs/app.gif" width="320">

### 環境

- IDE：Android Studio Flamingo | 2022.2.1 Patch 2
- Kotlin：1.6.21
- Java：17
- Gradle：8.0
- minSdk：23
- targetSdk：31

※ ライブラリの利用はオープンソースのものに限ります。
※ 環境は適宜更新してください。

### 動作

1. 何かしらのキーワードを入力
2. GitHub API（`search/repositories`）でリポジトリを検索し、結果一覧を概要（リポジトリ名）で表示
3. 特定の結果を選択したら、該当リポジトリの詳細（リポジトリ名、オーナーアイコン、プロジェクト言語、Star 数、Watcher 数、Fork 数、Issue 数）を表示

## 課題取り組み方法

Issues を確認した上、本プロジェクトを [**Duplicate** してください](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/duplicating-a-repository)（Fork しないようにしてください。必要ならプライベートリポジトリにしても大丈夫です）。今後のコミットは全てご自身のリポジトリで行ってください。

コードチェックの課題 Issue は全て [`課題`](https://github.com/yumemi-inc/android-engineer-codecheck/milestone/1) Milestone がついており、難易度に応じて Label が [`初級`](https://github.com/yumemi-inc/android-engineer-codecheck/issues?q=is%3Aopen+is%3Aissue+label%3A初級+milestone%3A課題)、[`中級`](https://github.com/yumemi-inc/android-engineer-codecheck/issues?q=is%3Aopen+is%3Aissue+label%3A中級+milestone%3A課題+) と [`ボーナス`](https://github.com/yumemi-inc/android-engineer-codecheck/issues?q=is%3Aopen+is%3Aissue+label%3Aボーナス+milestone%3A課題+) に分けられています。課題の必須／選択は下記の表とします。

|                | 初級  | 中級  | ボーナス |
| -------------: | :---: | :---: | :------: |
| 新卒／未経験者 | 必須  | 選択  |   選択   |
|   中途／経験者 | 必須  | 必須  |   選択   |

課題 Issueをご自身のリポジトリーにコピーするGitHub Actionsをご用意しております。  
[こちらのWorkflow](./.github/workflows/copy-issues.yml)を[手動でトリガーする](https://docs.github.com/ja/actions/managing-workflow-runs/manually-running-a-workflow)ことでコピーできますのでご活用下さい。

課題が完成したら、リポジトリのアドレスを教えてください。

## 参考記事

提出された課題の評価ポイントに関しては、[こちらの記事](https://qiita.com/blendthink/items/aa70b8b3106fb4e3555f)に詳しく書かれてありますので、ぜひご覧ください。

## AIサービスの利用について

ChatGPTなどAIサービスの利用は禁止しておりません。

利用にあたって工夫したプロンプトやソースコメント等をご提出頂くことで、加点評価する場合もございます。 (減点評価はありません)

また、弊社コードチェック担当者もAIサービスを利用させていただく場合があります。

AIサービスの利用は差し控えてもらいたいなどのご要望がある場合は、お気軽にお申し出ください。
