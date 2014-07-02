package plant.diseases.android.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.*;
import plant.diseases.android.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Timothy on 2014/6/27.
 */
public class BaseActivity extends Activity {

	protected boolean isRegisted = false;

	protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 禁止横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 全屏显示
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置无标题窗口
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在当前的activity中注册广播
		IntentFilter filter = new IntentFilter();
		filter.addAction("ExitToMain");
		this.registerReceiver(this.broadcastReceiver, filter);
		isRegisted = true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (isRegisted) {
			this.unregisterReceiver(this.broadcastReceiver);
		}
	}

	public void initHeader() {
		TextView tvLeft = (TextView) findViewById(R.id.tvLeft);
		tvLeft.setOnClickListener(new OnClickReturn(this));
		TextView tvRight = (TextView) findViewById(R.id.tvRight);
		tvRight.setOnClickListener(new OnClickReturnMain(this));

	}

	public class OnClickReturn implements View.OnClickListener {
		private Activity activity;

		public OnClickReturn(Activity activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View v) {
			activity.finish();
		}
	}

	public class OnClickReturnMain implements View.OnClickListener {
		private Activity activity;

		public OnClickReturnMain(Activity activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setAction("ExitToMain");
			activity.sendBroadcast(intent);
		}
	}

}
