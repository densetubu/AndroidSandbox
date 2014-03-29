package densetubu.android.sandbox.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 天気予報を表示する
 *
 * TODO: 各所にTipsコメントを書き込む
 * TODO: livedoorのお天気Webサービスから天気情報を取得して表示する
 *
 * API仕様: http://weather.livedoor.com/weather_hacks/webservice
 */
public class WeatherActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // ListView に Adapter をセットし、Adapter#add() で要素を追加する事で一覧表示が出来る
        ListView weatherListView = (ListView)findViewById(R.id.activity_weather_list);
        ArrayAdapter<Weather> adapter = new ArrayAdapter<Weather>(this, android.R.layout.simple_list_item_1);
        weatherListView.setAdapter(adapter);

        // バックグラウンドで天気APIリクエストを実行する
        // 参考
        //  AsyncTaskLoaderを使ってみる ｜ Developers.IO
        //  http://dev.classmethod.jp/smartphone/asynctaskloader/
        getSupportLoaderManager().restartLoader(0, null, new WeatherApiLoaderCallbacks(this, adapter));
    }

}
