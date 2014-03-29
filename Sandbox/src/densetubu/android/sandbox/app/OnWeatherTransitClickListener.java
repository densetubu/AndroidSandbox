package densetubu.android.sandbox.app;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * 今日の天気画面へ遷移する
 */
class OnWeatherTransitClickListener implements View.OnClickListener {

    private final Activity activity;

    OnWeatherTransitClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, WeatherActivity.class);
        activity.startActivity(intent);
    }
}
