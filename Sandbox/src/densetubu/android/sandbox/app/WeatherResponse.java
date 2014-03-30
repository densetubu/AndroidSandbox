package densetubu.android.sandbox.app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class WeatherResponse {

    private final static String LOG_TAG = WeatherResponse.class.getSimpleName();

    static WeatherResponse fail() {
        // nullを意識させるような実装は、チェック漏れによるNullPointerException(通称 NPE) による
        // クラッシュリスクがあるので、なるべくならやめるべき
        return new WeatherResponse(null, false);
    }

    static WeatherResponse from(JSONObject response) {
        WeatherForecast.Builder builder = new WeatherForecast.Builder();
        boolean successful = false;

        try {
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
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new WeatherResponse(builder.build(), successful);
    }

    private final WeatherForecast forecast;
    private final boolean isSuccess;

    private WeatherResponse(WeatherForecast forecast, boolean isSuccess) {
        this.forecast = forecast;
        this.isSuccess = isSuccess;
    }

    boolean isSuccess() {
        return isSuccess;
    }

    WeatherForecast getForecast() {
        return forecast;
    }

}
