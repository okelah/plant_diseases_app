package com.zytproduct.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.zytproduct.dao.TlDisease;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TL_DISEASE.
*/
public class TlDiseaseDao extends AbstractDao<TlDisease, Long> {

    public static final String TABLENAME = "TL_DISEASE";

    /**
     * Properties of entity TlDisease.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property DiseaseId = new Property(0, Long.class, "diseaseId", true, "DISEASE_ID");
        public final static Property DiseaseName = new Property(1, String.class, "diseaseName", false, "DISEASE_NAME");
        public final static Property DiseaseUrl = new Property(2, String.class, "diseaseUrl", false, "DISEASE_URL");
    };


    public TlDiseaseDao(DaoConfig config) {
        super(config);
    }
    
    public TlDiseaseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TL_DISEASE' (" + //
                "'DISEASE_ID' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: diseaseId
                "'DISEASE_NAME' TEXT NOT NULL ," + // 1: diseaseName
                "'DISEASE_URL' TEXT);"); // 2: diseaseUrl
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TL_DISEASE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, TlDisease entity) {
        stmt.clearBindings();
 
        Long diseaseId = entity.getDiseaseId();
        if (diseaseId != null) {
            stmt.bindLong(1, diseaseId);
        }
        stmt.bindString(2, entity.getDiseaseName());
 
        String diseaseUrl = entity.getDiseaseUrl();
        if (diseaseUrl != null) {
            stmt.bindString(3, diseaseUrl);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public TlDisease readEntity(Cursor cursor, int offset) {
        TlDisease entity = new TlDisease( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // diseaseId
            cursor.getString(offset + 1), // diseaseName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // diseaseUrl
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, TlDisease entity, int offset) {
        entity.setDiseaseId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDiseaseName(cursor.getString(offset + 1));
        entity.setDiseaseUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(TlDisease entity, long rowId) {
        entity.setDiseaseId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(TlDisease entity) {
        if(entity != null) {
            return entity.getDiseaseId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
