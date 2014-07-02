package plant.diseases.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.zyt.android.dbbase.AbsDaoFactory;
import com.zytproduct.dao.TlDisease;
import com.zytproduct.dao.TlDiseaseDao;
import plant.diseases.android.R;
import plant.diseases.android.adapter.DiseaseListAdapter;
import plant.diseases.android.application.DiseasesApplication;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Timothy on 2014/6/28.
 */
public class DiseaseListActivity extends BaseActivity {

	@Inject
	AbsDaoFactory daoFactory;

	private DiseaseListAdapter diseaseListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disease_list);
		((DiseasesApplication) this.getApplication()).inject(this);
		initHeader();
		initListView();
	}

	private void initListView() {
		TlDiseaseDao diseaseDao = (TlDiseaseDao) daoFactory.getDaoSession().getDao(TlDisease.class);
		List<TlDisease> diseases = diseaseDao.queryBuilder().orderAsc(TlDiseaseDao.Properties.DiseaseId).list();
		diseaseListAdapter = new DiseaseListAdapter(this, diseases);

		ListView lvDiseases = (ListView) findViewById(R.id.lvDiseases);
		lvDiseases.setAdapter(diseaseListAdapter);
		lvDiseases.setOnItemClickListener(new OnItemClick());
	}

	private class OnItemClick implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			TlDisease disease = (TlDisease) parent.getAdapter().getItem(position);
			if (disease != null && disease.getDiseaseUrl() != null) {
				Intent intent = new Intent(DiseaseListActivity.this, DiseaseViewActivity.class);
				intent.putExtra(DiseaseViewActivity.PARAM_ARTICLE_URL, disease.getDiseaseUrl());
				startActivity(intent);
			}
		}
	}
}
