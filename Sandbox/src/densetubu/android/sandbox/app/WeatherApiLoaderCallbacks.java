package densetubu.android.sandbox.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.ArrayAdapter;

class WeatherApiLoaderCallbacks implements LoaderManager.LoaderCallbacks<WeatherForecast> {

    private final Context context;
    private final ArrayAdapter<Weather> adapter;

    WeatherApiLoaderCallbacks(Context context, ArrayAdapter<Weather> listView) {
        this.context = context;
        this.adapter = listView;
    }

    @Override
    public Loader<WeatherForecast> onCreateLoader(int i, Bundle bundle) {
        return new WeatherApiLoader(context);
    }

    @Override
    public void onLoadFinished(Loader<WeatherForecast> weatherForecastLoader, WeatherForecast weatherForecast) {
        adapter.add(weatherForecast.getToday());
        adapter.add(weatherForecast.getTomorrow());
        adapter.add(weatherForecast.getDayAfterTomorrow());
    }

    @Override
    public void onLoaderReset(Loader<WeatherForecast> weatherForecastLoader) {

    }
}
