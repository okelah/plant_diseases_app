package com.zytproduct.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.zytproduct.dao.TlDiagnoseItem;
import com.zytproduct.dao.TlDisease;
import com.zytproduct.dao.TlPest;

import com.zytproduct.dao.TlDiagnoseItemDao;
import com.zytproduct.dao.TlDiseaseDao;
import com.zytproduct.dao.TlPestDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig tlDiagnoseItemDaoConfig;
    private final DaoConfig tlDiseaseDaoConfig;
    private final DaoConfig tlPestDaoConfig;

    private final TlDiagnoseItemDao tlDiagnoseItemDao;
    private final TlDiseaseDao tlDiseaseDao;
    private final TlPestDao tlPestDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        tlDiagnoseItemDaoConfig = daoConfigMap.get(TlDiagnoseItemDao.class).clone();
        tlDiagnoseItemDaoConfig.initIdentityScope(type);

        tlDiseaseDaoConfig = daoConfigMap.get(TlDiseaseDao.class).clone();
        tlDiseaseDaoConfig.initIdentityScope(type);

        tlPestDaoConfig = daoConfigMap.get(TlPestDao.class).clone();
        tlPestDaoConfig.initIdentityScope(type);

        tlDiagnoseItemDao = new TlDiagnoseItemDao(tlDiagnoseItemDaoConfig, this);
        tlDiseaseDao = new TlDiseaseDao(tlDiseaseDaoConfig, this);
        tlPestDao = new TlPestDao(tlPestDaoConfig, this);

        registerDao(TlDiagnoseItem.class, tlDiagnoseItemDao);
        registerDao(TlDisease.class, tlDiseaseDao);
        registerDao(TlPest.class, tlPestDao);
    }
    
    public void clear() {
        tlDiagnoseItemDaoConfig.getIdentityScope().clear();
        tlDiseaseDaoConfig.getIdentityScope().clear();
        tlPestDaoConfig.getIdentityScope().clear();
    }

    public TlDiagnoseItemDao getTlDiagnoseItemDao() {
        return tlDiagnoseItemDao;
    }

    public TlDiseaseDao getTlDiseaseDao() {
        return tlDiseaseDao;
    }

    public TlPestDao getTlPestDao() {
        return tlPestDao;
    }

}
