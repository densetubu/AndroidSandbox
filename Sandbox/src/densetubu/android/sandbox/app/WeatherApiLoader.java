package densetubu.android.sandbox.app;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * {@link #loadInBackground()} 以外でOverrideしているメソッドはお決まりとして共通化しておくと便利
 */
public class WeatherApiLoader extends AsyncTaskLoader<WeatherForecast> {

    private WeatherForecast result;

    public WeatherApiLoader(Context context) {
        super(context);
    }

    @Override
    public WeatherForecast loadInBackground() {
        return new WeatherForecast.Builder()
                .setToday(new WeatherBuilder().setDay("今日").setName("晴れ").build())
                .setTomorrow(new WeatherBuilder().setDay("明日").setName("晴れ").build())
                .setDayAfterTomorrow(new WeatherBuilder().setDay("明後日").setName("曇り").build())
                .build();
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
