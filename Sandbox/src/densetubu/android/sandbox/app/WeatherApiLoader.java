package densetubu.android.sandbox.app;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * {@link #loadInBackground()} 以外でOverrideしているメソッドはお決まりとして共通化しておくと便利
 */
public class WeatherApiLoader extends AsyncTaskLoader<WeatherResponse> {

    private WeatherResponse result;

    public WeatherApiLoader(Context context) {
        super(context);
    }

    @Override
    public WeatherResponse loadInBackground() {
        WeatherResponse response;
        try {
            response = WeatherResponse.from(requestApi());
        } catch (IOException e) {
            e.printStackTrace();
            response = WeatherResponse.fail();
        } catch (JSONException e) {
            e.printStackTrace();
            response = WeatherResponse.fail();
        }

        return response;
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
    public void deliverResult(WeatherResponse result) {
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
