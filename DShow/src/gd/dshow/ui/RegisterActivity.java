package gd.dshow.ui;

import gd.dshow.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	EditText getEmailAd;
	Button register_1, back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		register_1 = (Button) findViewById(R.id.bt_register_1);
		getEmailAd = (EditText) findViewById(R.id.et_getEmailAd);
		back = (Button) findViewById(R.id.bt_back);

		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RegisterActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});

	}

}
