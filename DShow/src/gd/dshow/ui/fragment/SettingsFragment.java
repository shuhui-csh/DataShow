package gd.dshow.ui.fragment;

import java.util.zip.Inflater;

import gd.dshow.R;
import gd.dshow.residememu.ResideMenu;
import gd.dshow.ui.LoginActivity;
import gd.dshow.ui.MainActivity;
import gd.dshow.ui.RegisterActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;

public class SettingsFragment extends Fragment {
	private View parentView;
	private ResideMenu resideMenu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.settings_fragment, container,
				false);
		return parentView;

	}

}
