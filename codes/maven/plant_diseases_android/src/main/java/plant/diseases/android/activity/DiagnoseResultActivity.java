package plant.diseases.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.zyt.android.dbbase.AbsDaoFactory;
import com.zytproduct.dao.TlDisease;
import plant.diseases.android.R;
import plant.diseases.android.application.DiseasesApplication;
import plant.diseases.android.service.DiseaseCacheService;

import javax.inject.Inject;

/**
 * Created by Timothy on 2014/6/28.
 */
public class DiagnoseResultActivity extends BaseActivity implements View.OnClickListener {

	public static final String PARAM_DISEASE_ID = "diseaseId";

	@Inject
	AbsDaoFactory daoFactory;

	private TlDisease disease;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diagnose_result);
		((DiseasesApplication) this.getApplication()).inject(this);
		initHeader();
		init();
	}

	private void init() {
		int diseaseId = getIntent().getIntExtra(PARAM_DISEASE_ID, 0);
		disease = DiseaseCacheService.getInstance(daoFactory).getDisease(diseaseId);
		if (disease != null) {
			TextView tvDiseaseName = (TextView) findViewById(R.id.tvDiseaseName);
			tvDiseaseName.setText(disease.getDiseaseName());
			tvDiseaseName.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		if (disease != null && disease.getDiseaseUrl() != null) {
			Intent intent = new Intent(this, DiseaseViewActivity.class);
			intent.putExtra(DiseaseViewActivity.PARAM_ARTICLE_URL, disease.getDiseaseUrl());
			startActivity(intent);
		}
	}
}
