package gd.dshow.ui;

import gd.dshow.R;
import gd.dshow.http.AsyncTaskUtils;
import gd.dshow.http.HttpURL;
import gd.dshow.interfaces.IShowView;
import gd.dshow.residememu.ResideMenu;
import gd.dshow.residememu.ResideMenuItem;
import gd.dshow.ui.fragment.AccountFragment;
import gd.dshow.ui.fragment.HomeFragment;
import gd.dshow.ui.fragment.SettingsFragment;
import gd.dshow.utils.Exit;
import gd.dshow.utils.JsonTools;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		View.OnClickListener {
	private int i, j = 0;
	private TextView title;
	private boolean isExit;
	private IShowView mishowview;
	private ResideMenu resideMenu;
	private MainActivity mContext;
	private Context context = MainActivity.this;
	private AsyncTaskUtils task;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemAccount;
	private ResideMenuItem itemSettings;
	// 用于双击退出程序的判断时间变量
	private long mExitTime;

	class MIshowview implements IShowView {
		@Override
		public void showView(String str) {
			String TITLE = JsonTools.getString("title", str);
			title.setText(TITLE);
		}
	}

	/**
	 * 在活动首次创建时监听
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏通知栏
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		title = (TextView) findViewById(R.id.tv_title);
		mContext = this;
		mishowview = new MIshowview();
		task = new AsyncTaskUtils(MainActivity.this, mishowview);
		task.execute(HttpURL.ReportGroupURL);
		setUpMenu();
		changeFragment(new HomeFragment());

	}

	private void setUpMenu() {
		// 将侧滑菜单添加到当前的activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		// resideMenu.setMenuListener(menuListener);
		// 设置缩放比例值.
		resideMenu.setScaleValue(0.6f);
		// 创建菜单项;
		itemHome = new ResideMenuItem(this, R.drawable.icon_home, "主页");
		itemAccount = new ResideMenuItem(this, R.drawable.icon_profile, "账户");
		itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "设置");
		// 为每个菜单项创建点击事件
		itemHome.setOnClickListener(this);
		itemAccount.setOnClickListener(this);
		itemSettings.setOnClickListener(this);
		// 设置侧滑菜单是在左侧还是在右侧
		resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemAccount, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
		// 通过设置可以禁用方向 ->
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		// 设置MainActivity上的左右菜单按钮的点击事件
		findViewById(R.id.title_bar_left_menu).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
					}
				});
		findViewById(R.id.title_bar_right_menu).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View view) {
						// exit();
						// 双击退出程序
						Exit myExit = new Exit(context);
						mExitTime = myExit.exit(mExitTime);
					}
				});
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	// 设置点击事件逻辑
	@Override
	public void onClick(View view) {

		// 通过changeFragment()方法替换fragment
		if (view == itemHome) {
			changeFragment(new HomeFragment());
		} else if (view == itemAccount) {
			changeFragment(new AccountFragment());
		} else if (view == itemSettings) {
			changeFragment(new SettingsFragment());
		}
		// 关闭菜单
		resideMenu.closeMenu();
	}

	private void changeFragment(Fragment targetFragment) {
		// 清除忽略视图列表
		resideMenu.clearIgnoredViewList();
		// 通过beginTransaction()方法即可开启并返回FragmentTransaction对象
		getSupportFragmentManager().beginTransaction()
				// replace()方法可以替换Fragment,从id-->targetFragment
				.replace(R.id.home_fragment, targetFragment, "fragment")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

	// 使用onKeyDown()方法监听菜单键和返回键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 监听返回键
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// if (i%2!=0) {
			// changeFragment(new HomeFragment());
			// }
			// else if(i%2==0){
			// exit();
			// 双击退出程序，调用一个退出程序类
			Exit myExit = new Exit(this);
			mExitTime = myExit.exit(mExitTime);
			// }
			return false;
		}
		// 为菜单按钮添加点击事件
		else if (keyCode == KeyEvent.KEYCODE_MENU) {
			// if(i%2!=0&&j%2!=0){
			// changeFragment(new HomeFragment());
			// }else{
			resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			// }
			// i++;j++;
		}
		return true;
	}

	// // ---双击退出程序---
	// public void exit() {
	// // “isExit”作为退出的标志，布尔型
	// if (!isExit) {
	// isExit = true;
	// Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
	// mHandler.sendEmptyMessageDelayed(0, 2000);
	// } else {
	// Intent intent = new Intent(Intent.ACTION_MAIN);
	// intent.addCategory(Intent.CATEGORY_HOME);
	// startActivity(intent);
	// // System.exit(0);
	// android.os.Process.killProcess(android.os.Process.myPid());
	// }
	// }
	//
	// Handler mHandler = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// super.handleMessage(msg);
	// isExit = false;
	// }
	// };

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu() {
		return resideMenu;
	}
}
