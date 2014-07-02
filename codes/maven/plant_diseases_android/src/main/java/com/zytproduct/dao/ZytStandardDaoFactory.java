package com.zytproduct.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zyt.android.dbbase.AbsDaoFactory;
import de.greenrobot.dao.AbstractDaoMaster;

/**
 * Created by xuepeng on 2014/4/23.
 */
public class ZytStandardDaoFactory extends AbsDaoFactory {

	public ZytStandardDaoFactory(Context context) {
		super(context);
	}

	@Override
	protected SQLiteOpenHelper createSQLiteOpenHelper(Context context, String dbPath) {
		return new MyProductOpenHelper(context, dbPath, null, true, DaoMaster.SCHEMA_VERSION);
	}

	@Override
	protected AbstractDaoMaster createDaoMaster(SQLiteDatabase sqLiteDatabase) {
		return new DaoMaster(sqLiteDatabase);
	}
}
