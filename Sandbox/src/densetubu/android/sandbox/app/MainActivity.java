package densetubu.android.sandbox.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

/**
 * 検索しているとアクティビティやフラグメントに直接 `implements` しているものが多くヒットするが、それは避けるべき。
 * 別クラス化により、余計な if/switch文 が減らせたり、コードジャンプが出来たり、複数箇所で再利用をしやすくなったり、
 * アクティビティの見通がよくなったりとメリットが多い
 *
 * アクティビティは public にし、かつAndroidManifest.xmlにactivityの定義をしないと実行時にエラーでクラッシュする
 * AndroidStudioの場合、[File] -> [New] から `Activity` を選択すると、
 * アクティビティのクラスファイルとAndroidManifestの定義の自動追加をやってくれる
 *
 * Overrideする場合は、親クラスのメソッドを呼び出さないといけないものもあるので注意
 */
public class MainActivity extends ActionBarActivity {

    /**
     * 基本的にonCreateに処理を書いていけば画面が作れる
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 一番最初にビューをセット
        setContentView(R.layout.activity_main);

        // ボタンにクリックイベントをセットする
        // `setContentView` でセットしたレイアウトファイルに存在しないIDを指定すると、
        // `findViewById` の戻り値はnullになる
        Button contentButton = (Button)findViewById(R.id.activity_main_content_button);
        contentButton.setOnClickListener(new OnClickToContentButton());

        // 天気画面へ遷移するクリックイベントをセットする
        Button weatherButton = (Button)findViewById(R.id.activity_main_weather_button);
        weatherButton.setOnClickListener(new OnWeatherTransitClickListener(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
