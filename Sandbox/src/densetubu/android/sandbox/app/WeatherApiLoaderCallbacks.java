package densetubu.android.sandbox.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.ArrayAdapter;

/**
 *
 * AsyncTask vs AsyncTaskLoader(+LoaderCallbacks)
 *
 *  どちらも非同期でバックグラウンド処理が実行されるという点では同じ
 *  違いはフラグメント・アクティビティに紐づくかどうか
 *
 *  アクティビティ・フラグメントでよく実行される処理の場合は、ビューも含めた複数の要素のライフサイクルが複雑に絡み合う
 *  そのため、適切なキャンセル処理をしないと、参照出来ないビューを扱おうとしてクラッシュが発生したり、
 *  無駄な処理が続いたままになってしまうケースがある
 *
 *  AsyncTaskLoaderの場合はLoaderManagerが適切に管理してくれるインターフェースを提供しており、
 *  処理のキャンセル忘れが発生するリスクは低い
 *
 *  サービスクラス等のLoaderCallbacksが利用出来ない環境では、そもそも自身以外のライフサイクルを意識する必要性が薄い
 *  AsyncTaskLoaderが使えないからダメという事はなく、サービスクラスでAsyncTaskを使っていてもなんら問題はない
 *
 *  とりあえずは、アクティビティ・フラグメント上で実行する場合はAsyncTaskLoader(+LoaderCallbacks)、
 *  サービスクラスではAsyncTask、と覚えておくとよい
 */
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
