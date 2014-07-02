package plant.diseases.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import plant.diseases.android.R;

/**
 * Created by Timothy on 2014/6/29.
 */
public class DiseasePhotosAdapter extends BaseAdapter {

	private Context mContext = null;
	private LayoutInflater mInflater = null;
	private int[] resIds;
	private String[] photoNames;

	public DiseasePhotosAdapter(Context mContext, int[] resIds, String[] photoNames) {
		this.mContext = mContext;
		mInflater = LayoutInflater.from(this.mContext);
		this.resIds = resIds;
		this.photoNames = photoNames;
	}

	@Override
	public int getCount() {
		return resIds.length;
	}

	@Override
	public Object getItem(int position) {
		return resIds[position];
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
			convertView = mInflater.inflate(R.layout.disease_photo_item, null);
			// 初始化组件
			holder.ivItem = (ImageView) convertView.findViewById(R.id.ivItem);
			holder.tvItem = (TextView) convertView.findViewById(R.id.tvItem);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.ivItem.setImageResource(resIds[position]);
		holder.tvItem.setText(photoNames[position]);
		return convertView;
	}

	class ViewHolder {
		ImageView ivItem;
		TextView tvItem;
	}
}
