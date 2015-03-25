package gd.dshow.http;

import gd.dshow.interfaces.IShowView;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.AsyncTask;

public class MyAsyncTaskUtils extends AsyncTask<String, String, String> {
	private IShowView ishowView = null;
	private Context context;

	public MyAsyncTaskUtils(Context contenxt, IShowView ishowView) {
		this.context = context;
		this.ishowView = ishowView;

	}

	@Override
	protected String doInBackground(String... params) {
		String num = params[0];
		String password = params[1];
		String url = params[2];
		NameValuePair nameValuePair = new BasicNameValuePair("loginName", num);
		NameValuePair ageValuePair = new BasicNameValuePair("password",password);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(ageValuePair);
		list.add(nameValuePair);
		return HttpClient.HttpClientPost(context, url, list);
	}

	@Override
	protected void onPostExecute(String result) {
		ishowView.showView(result);
		super.onPostExecute(result);
	}

}
