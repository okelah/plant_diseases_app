package plant.diseases.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.zyt.android.dbbase.AbsDaoFactory;
import com.zytproduct.dao.TlDiagnoseItem;
import com.zytproduct.dao.TlDiagnoseItemDao;
import plant.diseases.android.R;
import plant.diseases.android.application.DiseasesApplication;
import plant.diseases.android.service.DiagnoseService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Timothy on 2014/6/27.
 */
public class DiagnoseActivity extends BaseActivity implements View.OnClickListener {

	public static final String PARAM_PARENT_ID = "parentId";
	public static final int[] ids = { R.id.rbItem_0, R.id.rbItem_1, R.id.rbItem_2, R.id.rbItem_3, R.id.rbItem_4,
			R.id.rbItem_5, R.id.rbItem_6, R.id.rbItem_7, R.id.rbItem_8, R.id.rbItem_9, R.id.rbItem_10, R.id.rbItem_11,
			R.id.rbItem_12, R.id.rbItem_13, R.id.rbItem_14, R.id.rbItem_15, R.id.rbItem_16, R.id.rbItem_17,
			R.id.rbItem_18, R.id.rbItem_19 };

	@Inject
	AbsDaoFactory daoFactory;
	private List<TlDiagnoseItem> items;
	private RadioGroup rgItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diagnose);
		((DiseasesApplication) this.getApplication()).inject(this);
		initHeader();
		init();
	}

	private void init() {
		// 接收数据
		int parentId = getIntent().getIntExtra(PARAM_PARENT_ID, 0);
		// 初始化缓存
		TlDiagnoseItemDao itemDao = (TlDiagnoseItemDao) daoFactory.getDaoSession().getDao(TlDiagnoseItem.class);
		items = DiagnoseService.findDiagnoseItemByParentId(parentId, itemDao);
		// 初始化RadioGroup
		rgItems = (RadioGroup) findViewById(R.id.rgItems);
		// 动态添加RadioButton
		for (int i = 0; i < items.size(); i++) {
			TlDiagnoseItem item = items.get(i);
			RadioButton rbItemId = new RadioButton(this);
			rbItemId.setText(item.getDiagnoseDesc());
			rbItemId.setId(ids[i]);
			LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
					RadioGroup.LayoutParams.WRAP_CONTENT);
			rgItems.addView(rbItemId, layoutParams);
		}
		Button btnNext = (Button) findViewById(R.id.btnNext);
		btnNext.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNext:
			RadioButton radioButton = (RadioButton) findViewById(rgItems.getCheckedRadioButtonId());
			if (radioButton == null) {
				Toast.makeText(this, "请选择条目", Toast.LENGTH_SHORT).show();
			} else {
				for (int i = 0; i < ids.length; i++) {
					if (radioButton.getId() == ids[i]) {
						TlDiagnoseItem item = items.get(i);
						if (item.getLeafFlag() == 1) {
							// 是叶子，跳到诊断结果页
							Intent intent = new Intent(this, DiagnoseResultActivity.class);
							intent.putExtra(DiagnoseResultActivity.PARAM_DISEASE_ID, item.getDiseaseId().intValue());
							startActivity(intent);
						} else {
							// 不是叶子，继续诊断
							Intent intent = new Intent(this, DiagnoseActivity.class);
							intent.putExtra(PARAM_PARENT_ID, item.getDiagnoseId().intValue());
							startActivity(intent);
						}
						break;
					}
				}
			}
			break;
		}
	}
}
