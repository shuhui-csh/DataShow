package gd.dshow.ui;

import java.util.List;
import gd.dshow.R;
import gd.dshow.adapter.ListViewAdapter;
import gd.dshow.http.AsyncTaskUtils;
import gd.dshow.http.HttpURL;
import gd.dshow.interfaces.IShowView;
import gd.dshow.utils.JsonTools;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ReportListActivity extends Activity {
	private Context context = ReportListActivity.this;
	private Button back;
	private TextView title;
	private ListView listView;
	private List<String> list;
	private MIshowview mishowview;
	private AsyncTaskUtils task;
	private String URL_Path;

	class MIshowview implements IShowView {
		@Override
		public void showView(String str) {
			// TODO Auto-generatedmethod stub
			String TITLE = JsonTools.getString("title", str);
			List<String> list = JsonTools.getList("data", str);
			title.setText(TITLE);
			ListViewAdapter adapter = new ListViewAdapter(context, list);
			listView.setAdapter(adapter);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reportlist_activity);
		back = (Button) findViewById(R.id.back_second);
		title = (TextView) findViewById(R.id.tv_title_second);
		listView = (ListView) findViewById(R.id.second_list);
		long groupId = getIntent().getExtras().getLong("groupId");
		// URL_Path = HttpURL.BaseReportListURL + (++groupId);
		URL_Path = HttpURL.BaseReportListURL + "1";
		mishowview = new MIshowview();
		task = new AsyncTaskUtils(context, mishowview);
		task.execute(URL_Path);

		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ReportListActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, ReportDetailActivity.class);
				intent.putExtra("URL_Path", URL_Path);
				intent.putExtra("detailId", id);
				startActivity(intent);
			}
		});
	}

}
