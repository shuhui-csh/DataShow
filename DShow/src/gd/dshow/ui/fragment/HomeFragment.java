package gd.dshow.ui.fragment;

import java.util.ArrayList;
import java.util.List;
import gd.dshow.R;
import gd.dshow.adapter.ListViewAdapter;
import gd.dshow.http.AsyncTaskUtils;
import gd.dshow.http.HttpURL;
import gd.dshow.interfaces.IShowView;
import gd.dshow.ui.MainActivity;
import gd.dshow.ui.ReportListActivity;
import gd.dshow.utils.JsonTools;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeFragment extends Fragment {
	private View parentView;
	private ListView listView;
	private IShowView mishowview;
	private gd.dshow.http.AsyncTaskUtils task;

	class MIshowview implements IShowView {
		@Override
		public void showView(String str) {
			List<String> list = JsonTools.getList("data", str);
			ListViewAdapter adapter = new ListViewAdapter(getActivity(), list);
			listView.setAdapter(adapter);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.home_fragment, container, false);
		listView = (ListView) parentView.findViewById(R.id.main_list);
		mishowview = new MIshowview();
		task = new AsyncTaskUtils(getActivity(), mishowview);
		task.execute(HttpURL.ReportGroupURL);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						ReportListActivity.class);
				intent.putExtra("groupId", id);
				startActivity(intent);
			}
		});
		return parentView;
	}

}
