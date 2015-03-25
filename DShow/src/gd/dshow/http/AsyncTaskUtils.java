package gd.dshow.http;

import gd.dshow.interfaces.IShowView;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncTaskUtils extends AsyncTask<String, String, String> {

	private IShowView ishowView = null;
	private Context context;

	public AsyncTaskUtils(Context contenxt, IShowView ishowView) {
		this.context = contenxt;
		this.ishowView = ishowView;

	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return HttpClient.HttpClientPost(context, params[0]);
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		ishowView.showView(result);
		super.onPostExecute(result);

	}

}
