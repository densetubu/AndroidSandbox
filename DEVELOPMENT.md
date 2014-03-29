# DEVELOPMENT

このレポジトリを作成するにあたって実行した事。

より詳細な変更履歴は [コミットログ](https://github.com/ichigotake/AndroidSandbox/commits/master) を参照。

## プロジェクトのセットアップ

1. [ADT](https://developer.android.com/sdk/index.html) で AndroidApplicationProject を作成する
2. [AndroidStudio](http://developer.android.com/sdk/installing/studio.html) / Gradle を利用可能にするために必要なファイルをエクスポートする

    1. [File] -&gt; [Export] を選択する
    2. [Android] を開き、 [Generate Gradle build files] を選択する

## 簡単なお天気アプリを作る

1. [リソースの活用: アプリの概要を表示](https://github.com/densetubu/AndroidSandbox/commit/c086d04cf75e6e06555668cc1b20913aecb6efe8)
2. [画面の実装: ボタンにクリックイベントをセットする](https://github.com/densetubu/AndroidSandbox/commit/1e1c4d9938f710ff99f5df82041bdfef190780be)
3. [画面の実装: 別のアクティビティ(お天気画面)へ遷移する](https://github.com/densetubu/AndroidSandbox/commit/1e1c4d9938f710ff99f5df82041bdfef190780be)
4. [画面の実装: LoaderManagerを利用した一覧表示](https://github.com/densetubu/AndroidSandbox/commit/dd233d332a8885974d62aeb255e7612579868e8b)
5. 画面の実装: livedoorのお天気情報を取得して表示する
6. 画面の実装: 別アクティビティに二次細分区のお天気情報を表示する

## よりよい実装へ

1. Fragmentを利用して画面のパーツを作る
2. 外部ライブラリを利用する準備
3. HTTP通信をライブラリに置き換える
4. AdapterではなくListを操作して画面を更新
5. ライブラリを利用してスライドするメニューを作る

