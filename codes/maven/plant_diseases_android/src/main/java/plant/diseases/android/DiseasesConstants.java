package plant.diseases.android;

import java.util.ResourceBundle;

/**
 * Created by Timothy on 2014/6/28.
 */
public class DiseasesConstants {

	private static final ResourceBundle RB_ANDROID = ResourceBundle.getBundle("android");

	/** 此应用所有资源的根路径 */
	private static final String DISEASES_RESOURCES_ROOT_DIR = "/diseases";
	/** 拍照图片存放地址 **/
	private static final String DISEASES_CAMERA_DIR = "/camera_photo";

	/** server端json和文件上传请求URL */
	public static final String DISEASES_JSON_UPLOADFILE_URL = RB_ANDROID.getString("DISEASES_JSON_UPLOADFILE_URL");

	/**
	 * 取得拍照图片本地保存地址
	 * @return
	 */
	public static String getPhotoSaveDir() {
		return DISEASES_RESOURCES_ROOT_DIR + DISEASES_CAMERA_DIR;
	}
}
