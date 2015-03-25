package gd.dshow.ui;

import gd.dshow.R;
import gd.dshow.http.HttpURL;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
public class ReportDetailActivity extends Activity implements OnClickListener {
	private Button back;
	private TextView title;
	private WebView webView;
	private String url_path;

	private Button tableButton;
	private Button lineButton;
	private Button pieButton;
	private Button barButton;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reportdetail_activity);
		back = (Button) findViewById(R.id.back_third);
		tableButton = (Button) findViewById(R.id.bt_chart_table);
		lineButton = (Button) findViewById(R.id.bt_chart_line);
		pieButton = (Button) findViewById(R.id.bt_chart_pie);
		barButton = (Button) findViewById(R.id.bt_chart_bar);
		// 为每个按钮设置监听器
		tableButton.setOnClickListener(this);
		lineButton.setOnClickListener(this);
		pieButton.setOnClickListener(this);
		barButton.setOnClickListener(this);
		webView = (WebView) findViewById(R.id.webview);
		String path = getIntent().getExtras().getString("URL_Path");
		long detailId = getIntent().getExtras().getLong("detailId");
		// url_path = path + "&detailid=" + (++detailId);
		url_path = HttpURL.BaseURL
				+ "reportlist.action?groupid=1&detailid=1&charttype=table";
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		// 支持放缩
		settings.setBuiltInZoomControls(true);
		// 不显示放缩按钮
		settings.setDisplayZoomControls(false);
		// 图片自适应WB大小
		settings.setUseWideViewPort(true);
		// 在同种分辨率的情况下,屏幕密度不一样的情况下,自动适配页面
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int scale = dm.densityDpi;
		if (scale == 240) {
			webView.getSettings().setDefaultZoom(ZoomDensity.FAR);
		} else if (scale == 160) {
			webView.getSettings().setDefaultZoom(ZoomDensity.MEDIUM);
		} else {
			webView.getSettings().setDefaultZoom(ZoomDensity.CLOSE);
		}

		webView.loadUrl(url_path);

		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ReportDetailActivity.this,
						ReportListActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bt_chart_table:
			Toast.makeText(getApplicationContext(), "请稍等", Toast.LENGTH_SHORT)
					.show();
			webView.loadUrl(url_path);
			break;
		case R.id.bt_chart_line:
			Toast.makeText(getApplicationContext(), "请稍等", Toast.LENGTH_SHORT)
					.show();
			webView.loadUrl(HttpURL.BaseURL + "reportlist.action?groupid=1&detailid=1&charttype=line");
			break;
		case R.id.bt_chart_pie:
			Toast.makeText(getApplicationContext(), "请稍等", Toast.LENGTH_SHORT)
					.show();
			webView.loadUrl(HttpURL.BaseURL + "reportlist.action?groupid=1&detailid=1&charttype=pie");
			break;
		case R.id.bt_chart_bar:
			Toast.makeText(getApplicationContext(), "请稍等", Toast.LENGTH_SHORT)
					.show();
			webView.loadUrl(HttpURL.BaseURL + "reportlist.action?groupid=1&detailid=1&charttype=bar");
			break;
		default:
			break;
		}
	}

	// //加载页面的异步任务
	// class LoadWebView extends AsyncTask<URL, Integer, Void>
	// {
	// Context context;
	// public loadWebView (Context context) {
	// this.context = context;
	// }
	// protected void doInBackground(URL[] params) {
	// webView.load
	// };
	// }
}
