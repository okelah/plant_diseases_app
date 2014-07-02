package plant.diseases.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import plant.diseases.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网格布局示例Activity
 */
public class PestPhotosActivity extends BaseActivity implements AdapterView.OnItemSelectedListener,
		AdapterView.OnItemClickListener {

	private ImageView ivPestPhoto;
	private TextView tvPestPhoto;

	private int[] resIds = new int[] { R.drawable.pest1, R.drawable.pest2, R.drawable.pest3, R.drawable.pest4,
			R.drawable.pest5, R.drawable.pest6, R.drawable.pest7, R.drawable.pest8, R.drawable.pest9,
			R.drawable.pest10, R.drawable.pest11, R.drawable.pest12 };
	private String[] photoNames = new String[] { "水稻三化螟幼虫", "稻纵卷叶螟成虫", "稻纵卷叶螟幼虫", "稻弄蝶幼虫", "稻弄蝶蛹", "稻弄蝶成虫", "稻眼蝶幼虫",
			"稻眼蝶成虫", "稻褐飞虱", "稻灰飞虱", "稻蝽", "中华稻蝗" };

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		ivPestPhoto.setImageResource(resIds[position]);
		tvPestPhoto.setText(photoNames[position]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ivPestPhoto.setImageResource(resIds[position]);
		tvPestPhoto.setText(photoNames[position]);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_pest_photos);
		initHeader();
		// 取得网格布局的数据源
		List<Map<String, Object>> gridCells = getGridSources();
		SimpleAdapter adapter = new SimpleAdapter(this, gridCells, R.layout.grid_sample_cell,
				new String[] { "imageview" }, new int[] { R.id.ivItem });
		// 取得网格视图控件
		GridView gridView = (GridView) this.findViewById(R.id.gvPestPhotos);
		gridView.setAdapter(adapter);
		// 取得图片展示视图控件
		ivPestPhoto = (ImageView) this.findViewById(R.id.ivPestPhoto);
		tvPestPhoto = (TextView) this.findViewById(R.id.tvPestPhoto);
		gridView.setOnItemSelectedListener(this);
		gridView.setOnItemClickListener(this);
		ivPestPhoto.setImageResource(resIds[0]);
		tvPestPhoto.setText(photoNames[0]);
	}

	/**
	 * 取得网格布局的数据源
	 * @return 网格布局的数据源
	 */
	private List<Map<String, Object>> getGridSources() {
		List<Map<String, Object>> gridCells = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < resIds.length; i++) {
			Map<String, Object> cell = new HashMap<String, Object>();
			cell.put("imageview", resIds[i]);
			gridCells.add(cell);
		}
		return gridCells;
	}
}
