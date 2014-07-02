package plant.diseases.protocol;

import com.zyt.communicate.common.UploadMessage;
import com.zyt.communicate.common.json.IReqBody;

/**
 * Created by Timothy on 2014/6/29.
 */
public class PhotoUploadReqBody implements IReqBody {

	/** 用户姓名 */
	private String userName;
	/** 联系电话 */
	private String userMobile;
	/** 照片描述 */
	private String userDesc;
	/** 上传的文件信息 */
	private UploadMessage img;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public UploadMessage getImg() {
		return img;
	}

	public void setImg(UploadMessage img) {
		this.img = img;
	}
}
