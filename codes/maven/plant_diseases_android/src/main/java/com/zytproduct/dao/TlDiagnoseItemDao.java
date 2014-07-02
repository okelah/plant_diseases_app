package com.zytproduct.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.zytproduct.dao.TlDiagnoseItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TL_DIAGNOSE_ITEM.
*/
public class TlDiagnoseItemDao extends AbstractDao<TlDiagnoseItem, Long> {

    public static final String TABLENAME = "TL_DIAGNOSE_ITEM";

    /**
     * Properties of entity TlDiagnoseItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property DiagnoseId = new Property(0, Long.class, "diagnoseId", true, "DIAGNOSE_ID");
        public final static Property DiagnoseDesc = new Property(1, String.class, "diagnoseDesc", false, "DIAGNOSE_DESC");
        public final static Property ParentId = new Property(2, long.class, "parentId", false, "PARENT_ID");
        public final static Property LeafFlag = new Property(3, long.class, "leafFlag", false, "LEAF_FLAG");
        public final static Property DiseaseId = new Property(4, Long.class, "diseaseId", false, "DISEASE_ID");
    };


    public TlDiagnoseItemDao(DaoConfig config) {
        super(config);
    }
    
    public TlDiagnoseItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TL_DIAGNOSE_ITEM' (" + //
                "'DIAGNOSE_ID' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: diagnoseId
                "'DIAGNOSE_DESC' TEXT NOT NULL ," + // 1: diagnoseDesc
                "'PARENT_ID' INTEGER NOT NULL ," + // 2: parentId
                "'LEAF_FLAG' INTEGER NOT NULL ," + // 3: leafFlag
                "'DISEASE_ID' INTEGER);"); // 4: diseaseId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TL_DIAGNOSE_ITEM'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, TlDiagnoseItem entity) {
        stmt.clearBindings();
 
        Long diagnoseId = entity.getDiagnoseId();
        if (diagnoseId != null) {
            stmt.bindLong(1, diagnoseId);
        }
        stmt.bindString(2, entity.getDiagnoseDesc());
        stmt.bindLong(3, entity.getParentId());
        stmt.bindLong(4, entity.getLeafFlag());
 
        Long diseaseId = entity.getDiseaseId();
        if (diseaseId != null) {
            stmt.bindLong(5, diseaseId);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public TlDiagnoseItem readEntity(Cursor cursor, int offset) {
        TlDiagnoseItem entity = new TlDiagnoseItem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // diagnoseId
            cursor.getString(offset + 1), // diagnoseDesc
            cursor.getLong(offset + 2), // parentId
            cursor.getLong(offset + 3), // leafFlag
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // diseaseId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, TlDiagnoseItem entity, int offset) {
        entity.setDiagnoseId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDiagnoseDesc(cursor.getString(offset + 1));
        entity.setParentId(cursor.getLong(offset + 2));
        entity.setLeafFlag(cursor.getLong(offset + 3));
        entity.setDiseaseId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(TlDiagnoseItem entity, long rowId) {
        entity.setDiagnoseId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(TlDiagnoseItem entity) {
        if(entity != null) {
            return entity.getDiagnoseId();
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