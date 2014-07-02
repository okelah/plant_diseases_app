package plant.diseases.serverdb.model;

import com.zyt.dbcore.dbbase.ITableModel;
import com.zyt.dbcore.query.QueryCriterion;
import com.zyt.dbcore.query.QueryExpression;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * the persistence definition of the TlDiseasesUpload entity<br/>
 * <!-- begin-user-doc --><br/>
 * <!-- end-user-doc -->
 * @version 1.0
 * @author: Timothy Ren
 * @generated
 */
public class TlDiseasesUpload implements ITableModel {
	//Constants

	/**
	 * constant for column UPLOAD_ID<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String UPLOAD_ID = "UPLOAD_ID";

	/**
	 * constant for column IMG_NAME<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String IMG_NAME = "IMG_NAME";

	/**
	 * constant for column USER_NAME<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String USER_NAME = "USER_NAME";

	/**
	 * constant for column USER_MOBILE<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String USER_MOBILE = "USER_MOBILE";

	/**
	 * constant for column ISREAD<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String ISREAD = "ISREAD";

	/**
	 * constant for column USER_DESC<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String USER_DESC = "USER_DESC";

	/**
	 * constant for column UPLOADTIME<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String UPLOADTIME = "UPLOADTIME";
	
	//Fields
	/**
	 * uploadId<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */	
	private Long uploadId;

	/**
	 * imgName<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String imgName;

	/**
	 * userName<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String userName;

	/**
	 * userMobile<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String userMobile;

	/**
	 * isread<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Long isread;

	/**
	 * userDesc<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String userDesc;

	/**
	 * uploadtime<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private java.util.Date uploadtime;

	//Property accessors
	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getUploadId(){
		return this.uploadId;
	}

	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUploadId(Long uploadId){
		this.uploadId = uploadId;
	}
	
	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImgName(){
		return this.imgName;
	}	

	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImgName(String imgName){
		this.imgName = imgName;
	}
	
	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUserName(){
		return this.userName;
	}	

	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUserMobile(){
		return this.userMobile;
	}	

	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}
	
	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getIsread(){
		return this.isread;
	}	

	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsread(Long isread){
		this.isread = isread;
	}
	
	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUserDesc(){
		return this.userDesc;
	}	

	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserDesc(String userDesc){
		this.userDesc = userDesc;
	}
	
	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public java.util.Date getUploadtime(){
		return this.uploadtime;
	}	

	/**
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUploadtime(java.util.Date uploadtime){
		this.uploadtime = uploadtime;
	}

	/**
	 * get key QueryCriterion<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @return key QueryCriterion
	 * @generated
	 */
	public QueryCriterion tgetKeyCondition(){
		QueryCriterion qc= QueryExpression.eq(UPLOAD_ID, uploadId);
		return qc;
	}

	/**
	 * get the naming-property map<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @return the naming-property map
	 * @generated
	 */
	public Map<String, Object> tgetNamedParameters(){
		Map<String, Object> map=new LinkedHashMap<String, Object>();
		map.put("UPLOAD_ID", uploadId);
		map.put("IMG_NAME", imgName);
		map.put("USER_NAME", userName);
		map.put("USER_MOBILE", userMobile);
		map.put("ISREAD", isread);
		map.put("USER_DESC", userDesc);
		map.put("UPLOADTIME", uploadtime);
		return map;
	}

	/**
	 * get the default table name<br/>
	 * <!-- begin-user-doc --><br/>
	 * <!-- end-user-doc -->
	 * @return the default table name
	 * @generated
	 */
	public static String tgetDefaultTableName(){
		return "TL_DISEASES_UPLOAD";
	}
}
