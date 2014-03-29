package densetubu.android.sandbox.app;

import android.view.View;
import android.widget.Toast;

class OnClickToContentButton implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), R.string.home_content_button_message, Toast.LENGTH_SHORT).show();
    }
}
