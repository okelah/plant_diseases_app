package com.zytproduct.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zyt.android.dbbase.AbsProductOpenHelper;

/**
 * Created by xuepeng on 2014/4/23.
 */
public class MyProductOpenHelper extends AbsProductOpenHelper {

	public MyProductOpenHelper(Context context, String dbStorePath, SQLiteDatabase.CursorFactory factory,
			boolean isUpgradeFailedCreate, int schemaVersion) {
		super(context, dbStorePath, factory, isUpgradeFailedCreate, schemaVersion);
	}

	@Override
	protected void createAllTables(SQLiteDatabase sqLiteDatabase, boolean ifNotExists) {
		DaoMaster.createAllTables(sqLiteDatabase, ifNotExists);
	}

	@Override
	public void dropAllTables(SQLiteDatabase sqLiteDatabase, boolean ifNotExists) {
		DaoMaster.dropAllTables(sqLiteDatabase, ifNotExists);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 执行数据库重建工作
		dropAllTables(db, true);
		onCreate(db);
	}
}
