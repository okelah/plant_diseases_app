package plant.diseases.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zytproduct.dao.TlDisease;
import plant.diseases.android.R;

import java.util.List;

/**
 * Created by Timothy on 2014/6/28.
 */
public class DiseaseListAdapter extends BaseAdapter {

	private Context mContext = null;
	private LayoutInflater mInflater = null;
	private List<TlDisease> dataList = null;

	public DiseaseListAdapter(Context mContext, List<TlDisease> dataList) {
		this.mContext = mContext;
		mInflater = LayoutInflater.from(this.mContext);
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.disease_item, null);
			// 初始化组件
			holder.tvDiseaseName = (TextView) convertView.findViewById(R.id.tvDiseaseName);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String diseaseName = dataList.get(position).getDiseaseName();
		holder.tvDiseaseName.setText(diseaseName);
		return convertView;
	}

	class ViewHolder {
		TextView tvDiseaseName;
	}
}
