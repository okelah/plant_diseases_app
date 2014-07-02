package plant.diseases.android.service;

import com.zytproduct.dao.TlDiagnoseItem;
import com.zytproduct.dao.TlDiagnoseItemDao;

import java.util.List;

/**
 * Created by Timothy on 2014/6/28.
 */
public class DiagnoseService {

	/**
	 * 根据parentId查询病害诊断表中满足条件的所有数据
	 * @param parentId
	 * @param itemDao
	 * @return 满足条件的所有数据
	 */
	public static List<TlDiagnoseItem> findDiagnoseItemByParentId(int parentId, TlDiagnoseItemDao itemDao) {
		return itemDao.queryBuilder().where(TlDiagnoseItemDao.Properties.ParentId.eq(parentId))
				.orderAsc(TlDiagnoseItemDao.Properties.DiagnoseId).list();
	}
}
