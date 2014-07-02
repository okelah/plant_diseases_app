package plant.diseases.serverdb.dao.jdbc;

import org.springframework.jdbc.support.KeyHolder;

import plant.diseases.serverdb.dao.TlDiseasesUploadDAO;
import plant.diseases.serverdb.model.TlDiseasesUpload;

import com.zyt.dbcore.dbbase.jdbc.JdbcTableTemplateDAO;

/**
 * DAO interface TlDiseasesUploadDAO implementation by JDBC-ORACLE<br>
 * <!-- begin-user-doc --><br>
 * <!-- end-user-doc -->
 * @version 1.0
 * @author: Timothy Ren
 * @generated
 */
public class JdbcTlDiseasesUploadDAO extends JdbcTableTemplateDAO<TlDiseasesUpload> implements TlDiseasesUploadDAO {

	/**
	 * table name<br>
	 * <!-- begin-user-doc --><br>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final String TABLE_NAME="TL_DISEASES_UPLOAD";
	
	/**
	 * Constructor<br>
	 * <!-- begin-user-doc --><br>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JdbcTlDiseasesUploadDAO() {
		super(TlDiseasesUpload.class, TABLE_NAME, JdbcTableTemplateDAO.KEYGENERATETYPE_ASSIGNED);
	}

	/**
	 * operation before insert<br>
	 * <!-- begin-user-doc --><br>
	 * <!-- end-user-doc -->
	 * @param entity
	 * @generated
	 */
	public void doBeforeInsert(TlDiseasesUpload entity) {
		String sql="select SEQ_DISEASES_UPLOAD.nextval from dual";
		long seqId=this.getJdbcTemplate().queryForObject(sql, Long.class);
		entity.setUploadId(seqId);
	}

	/**
	 * operation after insert<br>
	 * <!-- begin-user-doc --><br>
	 * <!-- end-user-doc -->
	 * @param entity
	 * @param keyHolder
	 * @generated
	 */
	public void doAfterInsert(TlDiseasesUpload entity, KeyHolder keyHolder) {
	}

	/**
	 * get the sql for table insert<br>
	 * <!-- begin-user-doc --><br>
	 * <!-- end-user-doc -->
	 * @param tableName the table name
	 * @return the sql for table insert
	 * @generated
	 */
	public String getInsertSql(String tableName) {    
		String sql="insert into "+tableName+"(UPLOAD_ID,IMG_NAME,USER_NAME,USER_MOBILE,ISREAD,USER_DESC,UPLOADTIME) values(:UPLOAD_ID,:IMG_NAME,:USER_NAME,:USER_MOBILE,:ISREAD,:USER_DESC,:UPLOADTIME)";
		return sql;
	}
	
	/**
	 * get the sql for batch insert<br>
	 * <!-- begin-user-doc --><br>
	 * <!-- end-user-doc -->
	 * @param tableName the table name
	 * @return the sql for batch insert
	 * @generated
	 */
	public String getBatchInsertSql(String tableName) {
		String sql="insert into "+tableName+"(UPLOAD_ID,IMG_NAME,USER_NAME,USER_MOBILE,ISREAD,USER_DESC,UPLOADTIME) values(SEQ_DISEASES_UPLOAD.nextval,:IMG_NAME,:USER_NAME,:USER_MOBILE,:ISREAD,:USER_DESC,:UPLOADTIME)";
		return sql;
	}

	/**
	 * get the sql for update<br>
	 * <!-- begin-user-doc --><br>
	 * <!-- end-user-doc -->
	 * @param tableName the table name
	 * @return the sql for update
	 * @generated
	 */
	public String getUpdateSql(String tableName){
		String sql="update "+tableName+" set IMG_NAME=:IMG_NAME, USER_NAME=:USER_NAME, USER_MOBILE=:USER_MOBILE, ISREAD=:ISREAD, USER_DESC=:USER_DESC, UPLOADTIME=:UPLOADTIME where UPLOAD_ID=:UPLOAD_ID";
		return sql;
	}

	/**
	 * get the sql for find, but not contain the fragment of "where"<br>
	 * e.g. select ### from ###<br>
	 * <!-- begin-user-doc --><br>
	 * <!-- end-user-doc -->
	 * @param tableName the table name
	 * @return the sql for find, but not contain the fragment of "where"
	 * @generated
	 */
	public String getSearchSelectSql(String tableName) {
		String sql="select UPLOAD_ID,IMG_NAME,USER_NAME,USER_MOBILE,ISREAD,USER_DESC,UPLOADTIME from "+tableName;
		return sql;
	}
}
