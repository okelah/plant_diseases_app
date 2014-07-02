package plant.diseases.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import plant.diseases.android.R;

/**
 * Created by Timothy on 2014/6/27.
 */
public class MainActivity extends Activity implements View.OnClickListener {

	private static long firsBackClickTime = 0;

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

		setContentView(R.layout.activity_main);
		initHeader();
		initView();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			long secondTime = System.currentTimeMillis();
			if (secondTime - firsBackClickTime > 2000) {
				Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
				firsBackClickTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return false;
		} else {
			return super.dispatchKeyEvent(event);
		}
	}

	public void initHeader() {
		TextView tvLeft = (TextView) findViewById(R.id.tvLeft);
		tvLeft.setVisibility(View.GONE);
		TextView tvRight = (TextView) findViewById(R.id.tvRight);
		tvRight.setVisibility(View.GONE);
	}

	private void initView() {
		TextView tvDiagnose = (TextView) findViewById(R.id.tvDiagnose);
		tvDiagnose.setOnClickListener(this);
		TextView tvDiseasePhotos = (TextView) findViewById(R.id.tvDiseasePhotos);
		tvDiseasePhotos.setOnClickListener(this);
		TextView tvPestPhotos = (TextView) findViewById(R.id.tvPestPhotos);
		tvPestPhotos.setOnClickListener(this);
		TextView tvDiseaseView = (TextView) findViewById(R.id.tvDiseaseView);
		tvDiseaseView.setOnClickListener(this);
		TextView tvPestView = (TextView) findViewById(R.id.tvPestView);
		tvPestView.setOnClickListener(this);
		TextView tvPhotoUpload = (TextView) findViewById(R.id.tvPhotoUpload);
		tvPhotoUpload.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvDiagnose:
			Intent intent = new Intent(this, DiagnoseActivity.class);
			startActivity(intent);
			break;
		case R.id.tvDiseasePhotos:
			intent = new Intent(this, DiseasePhotosActivity.class);
			startActivity(intent);
			break;
		case R.id.tvPestPhotos:
			intent = new Intent(this, PestPhotosActivity.class);
			startActivity(intent);
			break;
		case R.id.tvDiseaseView:
			intent = new Intent(this, DiseaseListActivity.class);
			startActivity(intent);
			break;
		case R.id.tvPestView:
			intent = new Intent(this, PestListActivity.class);
			startActivity(intent);
			break;
		case R.id.tvPhotoUpload:
			intent = new Intent(this, PhotoUploadActivity.class);
			startActivity(intent);
			break;
		}
	}
}
