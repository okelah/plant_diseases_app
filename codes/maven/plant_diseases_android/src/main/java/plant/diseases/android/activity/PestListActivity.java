package plant.diseases.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.zyt.android.dbbase.AbsDaoFactory;
import com.zytproduct.dao.TlPest;
import com.zytproduct.dao.TlPestDao;
import plant.diseases.android.R;
import plant.diseases.android.adapter.PestListAdapter;
import plant.diseases.android.application.DiseasesApplication;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Timothy on 2014/6/28.
 */
public class PestListActivity extends BaseActivity{

	@Inject
	AbsDaoFactory daoFactory;

	private PestListAdapter pestListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pest_list);
		((DiseasesApplication) this.getApplication()).inject(this);
		initHeader();
		initListView();
	}

	private void initListView() {
		TlPestDao pestDao = (TlPestDao) daoFactory.getDaoSession().getDao(TlPest.class);
		List<TlPest> pests = pestDao.queryBuilder().orderAsc(TlPestDao.Properties.PestId).list();
		pestListAdapter = new PestListAdapter(this, pests);

		ListView lvPests = (ListView) findViewById(R.id.lvPests);
		lvPests.setAdapter(pestListAdapter);
		lvPests.setOnItemClickListener(new OnItemClick());
	}

	private class OnItemClick implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			TlPest pest = (TlPest) parent.getAdapter().getItem(position);
			if (pest != null && pest.getPestUrl() != null) {
				Intent intent = new Intent(PestListActivity.this, DiseaseViewActivity.class);
				intent.putExtra(DiseaseViewActivity.PARAM_ARTICLE_URL, pest.getPestUrl());
				startActivity(intent);
			}
		}
	}

}
