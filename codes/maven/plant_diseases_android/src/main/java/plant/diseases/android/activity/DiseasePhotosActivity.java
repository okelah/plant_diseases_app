package plant.diseases.android.activity;

import android.os.Bundle;
import android.widget.ListView;
import plant.diseases.android.R;
import plant.diseases.android.adapter.DiseasePhotosAdapter;

/**
 * Created by Timothy on 2014/6/28.
 */
public class DiseasePhotosActivity extends BaseActivity {

	private int[] resIds = new int[] { R.drawable.disease1, R.drawable.disease2, R.drawable.disease3,
			R.drawable.disease4, R.drawable.disease5, R.drawable.disease6, R.drawable.disease7, R.drawable.disease8,
			R.drawable.disease9, R.drawable.disease10, R.drawable.disease11, R.drawable.disease12,
			R.drawable.disease13, R.drawable.disease14, R.drawable.disease15, R.drawable.disease16 };
	private String[] photoNames = new String[] { "水稻胡麻斑病病叶", "稻瘟病病叶", "稻瘟病茎叶发病", "稻瘟病叶鞘和穗颈发病", "水稻纹枯病", "水稻烂秧病", "水稻疫",
			"水稻恶苗病", "水稻恶苗病病株茎节上倒\n生根(右)与健株茎(左)比较", "稻叶黑粉病病叶", "稻曲病病穗", "稻小球菌核病", "水稻白叶枯病", "水稻白叶枯病田间症状", "水稻黄矮病",
			"水稻干尖线虫病病叶" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disease_photos);
		initHeader();
		initListView();
	}

	private void initListView() {
		DiseasePhotosAdapter adapter = new DiseasePhotosAdapter(this, resIds, photoNames);
		ListView lvDiseases = (ListView) findViewById(R.id.lvDiseases);
		lvDiseases.setAdapter(adapter);
	}
}
