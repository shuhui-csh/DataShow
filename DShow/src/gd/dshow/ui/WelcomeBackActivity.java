package gd.dshow.ui;

import gd.dshow.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
/**
 * 此Aty备用，现阶段不需要跳转至该Aty
 * @author Administrator
 *
 */
public class WelcomeBackActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置欢迎界面布局
		setContentView(R.layout.welcome_back);
		final Intent intent = new Intent(WelcomeBackActivity.this, MainActivity.class);
		//设置flags的道理同前
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					getApplicationContext().startActivity(intent);
					finish();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
	}
}