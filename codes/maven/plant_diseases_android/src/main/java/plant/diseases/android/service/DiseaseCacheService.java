package plant.diseases.android.service;

import com.zyt.android.dbbase.AbsDaoFactory;
import com.zytproduct.dao.TlDisease;
import com.zytproduct.dao.TlDiseaseDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Timothy on 2014/6/28.
 */
public class DiseaseCacheService {

	private static DiseaseCacheService instance;

	private Map<Integer, TlDisease> diseaseMap;

	private DiseaseCacheService(AbsDaoFactory daoFactory) {
		TlDiseaseDao diseaseDao = (TlDiseaseDao) daoFactory.getDaoSession().getDao(TlDisease.class);
		diseaseMap = new HashMap<Integer, TlDisease>();
		List<TlDisease> diseases = diseaseDao.loadAll();
		for (TlDisease disease : diseases) {
			diseaseMap.put(disease.getDiseaseId().intValue(), disease);
		}
	}

	public Map<Integer, TlDisease> getDiseaseMap() {
		return diseaseMap;
	}

	public TlDisease getDisease(int diseaseId) {
		return diseaseMap.get(diseaseId);
	}

	public static DiseaseCacheService getInstance(AbsDaoFactory daoFactory) {
		if (instance == null) {
			instance = new DiseaseCacheService(daoFactory);
		}
		return instance;
	}
}
