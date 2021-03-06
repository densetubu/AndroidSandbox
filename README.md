# AndroidSandbox

Androidアプリの実装サンプル

## 注意点

もし [AndroidStudio](http://developer.android.com/sdk/installing/studio.html) を利用する場合は、ダウンロード時のバージョン (*0.4.6*) から **アップデートしないようにしてください**

## サンプルプロジェクトについて

[DEVELOPMENT.md](DEVELOPMENT.md) を参照

## セットアップ手順

1. ダウンロードする

        例) $ git clone git@github.com:ichigotake/AndroidSandbox.git ~/Development/AndroidSandbox

2. IDE(ADTもしくはAndroidStudio)を起動し、SDKマネージャーから必要なものをダウンロードする

        - Android SDK Build-tools(最新版)
        - Android 4.4.2(API 19)
        - Android 2.1(API 7)
        - Android Support Repository
        - Android Support Library

3. ダウンロードしたプロジェクトをインポートする

4. IDE上部のツールバーから起動ボタン(再生ボタン風のアイコン)を押す

5. アプリが起動すればセットアップ完了！

## お役立ちTips

- 右クリックメニューは積極的に活用していく
    - アクティビティ・リソースの作成やrefactor機能など、新規作成や修正に便利な自動生成をたくさんやってくれる
- コードジャンプでファイル移動の手間を減らす
    - Macの場合はCommand, Windowsの場合はCtrlを押しながらマウス移動をすると、何がコードジャンプ可能か判別出来る
- 名前を変更する場合は右クリックメニューのリファクタ(refactor)機能から行う
    - 変更対象を参照している他の箇所も自動で書き換えてくれる

## 参考リンク

ただ闇雲に検索するよりも一歩先へ進むために必要なキーワードがたくさん散りばめられている

### デザインガイド

公式(英語): https://developer.android.com/design/index.html

非公式(日本語翻訳): http://android-design.teamegg.co.jp/

ほとんどが1機能1ページで簡潔しており、機能を実装する際の注意点もまとまっている。

機能を実装する際に必要な基本的なデザインが揃っているので、一通りチェックしておく事をオススメします。

「読む」までせずとも、まずは「見る」だけでもいいと思います。「読む」のは必要になってからでも遅くはない。

### Training / Guide

http://developer.android.com/develop/index.html

英語の本家ドキュメント。

デザインガイドよりも詳細な実装の注意点がまとまっており、検索して見つけたブログなどの情報にはない抑えるべきポイントも数多く記載されている事は多い。

Androidのドキュメントといえば Javadoc(クラスメソッド一覧) のみだと思っている方が多い印象だが、チュートリアル的なドキュメントもしっかりとある。

英語という事もあり、慣れないうちは中身を完全に理解出来ていなくても、「それっぽい」サンプルコードを流用して試してみるのもいいと思います。

[Googel翻訳](http://translate.google.co.jp/) などの翻訳ツールで概略を掴んでみる事も有効です

**2014年3月31日現在、[翻訳サイト](http://www.techdoctranslator.com/android) は最新の情報に追従していないので注意**

### mixi-inc/AndroidTraining

https://github.com/mixi-inc/AndroidTraining

mixiが提供する、Android開発の入門ドキュメント。

アクティビティのライフサイクルや各種基本実装など、基礎的な所が日本語でまとめられている。

### KeithYokoma/BakusokuAndroid

https://github.com/KeithYokoma/BakusokuAndroid

mixi/AndroidTraningよりも発展的なTipsがまとめられている。

メモリリークをしないための実装や、アプリを作成する際に考慮するとベターな設計など、応用的な内容が掲載されている。

