package densetubu.android.sandbox.app;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * {@link #loadInBackground()} 以外でOverrideしているメソッドはお決まりとして共通化しておくと便利
 */
public class WeatherApiLoader extends AsyncTaskLoader<WeatherForecast> {

    private final String LOG_TAG = WeatherApiLoader.class.getName();
    private WeatherForecast result;

    public WeatherApiLoader(Context context) {
        super(context);
    }

    @Override
    public WeatherForecast loadInBackground() {
        WeatherForecast.Builder builder = new WeatherForecast.Builder();
        boolean successful = false;

        try {
            JSONObject response = requestApi();
            // LogCatにデバッグログを仕込んでおくと開発中に便利
            // 参考: http://www.techdoctranslator.com/android/developing/tools/logcat
            // その他: 「logcat eclipse」などで検索
            Log.d(LOG_TAG, "response: " + response.toString(1));

            JSONArray forecasts = response.getJSONArray("forecasts");

            JSONObject today = forecasts.getJSONObject(0);
            builder.setToday(new WeatherBuilder()
                    .setName(today.getString("telop"))
                    .setDay(today.getString("date"))
                    .build()
            );

            JSONObject tomorrow = forecasts.getJSONObject(1);
            builder.setTomorrow(new WeatherBuilder()
                    .setName(tomorrow.getString("telop"))
                    .setDay(tomorrow.getString("date"))
                    .build()
            );

            successful = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // TODO: nullを返さない形がよい
        if (successful) {
            return builder.build();
        } else {
            return null;
        }
    }

    /**
     * Android組込みのHttpComponent（HttpClient）の正しい使い方といくつかのtips - terurouメモ
     * http://terurou.hateblo.jp/entry/20110702/1309541200
     *
     * 自前でHTTPクライアントを実装するよりも、volleyやandroid-async-http、Retrofitなどの
     * HTTP通信ライブラリを利用する方がスッキリ記述でき、かつお手軽にリトライ処理や安定性を確保出来るのでモアベター
     * この実装は必要最低限で、タイムアウト時間の設定や細かい失敗ケースのケアなどはしていない
     */
    private JSONObject requestApi() throws IOException, JSONException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(getContext().getString(R.string.weather_api_endpoint));
        String response = client.execute(request, new BasicResponseHandler());
        return new JSONObject(response);
    }

    @Override
    public void deliverResult(WeatherForecast result) {
        if (isReset()) {
            if (this.result != null) {
                this.result = null;
            }
            return;
        }

        this.result = result;

        if (isStarted()) {
            super.deliverResult(result);
        }
    }

    @Override
    protected void onStartLoading() {
        if (result != null) {
            deliverResult(result);
        }
        if (takeContentChanged() || result == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
    }

}
