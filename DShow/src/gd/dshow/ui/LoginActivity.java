package gd.dshow.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import gd.dshow.R;
import gd.dshow.http.MyAsyncTaskUtils;
import gd.dshow.interfaces.IShowView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import gd.dshow.http.*;

public class LoginActivity extends Activity {
	private Button login, register, forget;
	private EditText account, password;
	private IShowView mishowview;
	private MyAsyncTaskUtils task;
	private Context context = LoginActivity.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		account = (EditText) findViewById(R.id.et_account);
		password = (EditText) findViewById(R.id.et_password);
		login = (Button) findViewById(R.id.bt_login);
		register = (Button) findViewById(R.id.bt_register);
		forget = (Button) findViewById(R.id.bt_forget);

		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String admin = account.getText().toString();
				String passwordstr = password.getText().toString();
				mishowview = new MIshowview();
				task = new MyAsyncTaskUtils(LoginActivity.this, mishowview);
				String[] str = { admin, passwordstr, HttpURL.Login };
				task.execute(str);
			}
		});

		register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
	}

	class MIshowview implements IShowView {
		@Override
		public void showView(String str) {
			if (str.equals("601")) {
				Toast.makeText(context, "正常登陆", 1000).show();
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			} else if (str.equals("602")) {
				Toast.makeText(context, "用户不存在", 2000).show();
			} else if (str.equals("603")) {
				Toast.makeText(context, "密码错误", 2000).show();
			}
			// TODO Auto-generatedmethod stub
			Log.i("MainActivity", str);
			/*
			 * String title = JsonTools.getString("title", str); List<String>
			 * list = JsonTools.getList("data", str);
			 * listview.setAdapter(MainActivity .setAdapter(MainActivity.this,
			 * list)); textview.setText(title);
			 */
		}
	}
}
