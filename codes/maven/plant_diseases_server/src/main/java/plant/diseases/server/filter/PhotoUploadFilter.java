package plant.diseases.server.filter;

import com.zyt.communicate.common.ResponseCode;
import com.zyt.communicate.common.UploadFile;
import com.zyt.communicate.common.WebControlException;
import com.zyt.communicate.server.json.JsonFileDefaultServerFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plant.diseases.protocol.PhotoUploadReqBody;
import plant.diseases.protocol.PhotoUploadRespBody;
import plant.diseases.server.service.PhotoUploadService;

import java.util.List;

/**
 * Created by Timothy on 2014/6/29.
 */
@Component
public class PhotoUploadFilter extends JsonFileDefaultServerFilter<PhotoUploadReqBody, PhotoUploadRespBody> {

	private static final Logger logger = LoggerFactory.getLogger(PhotoUploadFilter.class);

	@Autowired
	private PhotoUploadService photoUploadService;

	@Override
	public PhotoUploadRespBody processReq(PhotoUploadReqBody reqBody, List<UploadFile> uploadFiles) {
		logger.info("客户端拍照上传请求开始");
		checkReq(reqBody, uploadFiles);
		PhotoUploadRespBody respBody = photoUploadService.photoUpload(reqBody, uploadFiles.get(0));
		logger.info("客户端拍照上传请求结束");
		return respBody;
	}

	@Override
	public Class<PhotoUploadReqBody> getReqBodyClassname() {
		return PhotoUploadReqBody.class;
	}

	private void checkReq(PhotoUploadReqBody reqBody, List<UploadFile> uploadFiles) {
		if (StringUtils.isEmpty(reqBody.getUserName())) {
			logger.warn("客户端拍照上传请求参数错误，用户姓名不能为空：content=" + reqBody.getUserName());
			throw new WebControlException(ResponseCode.CODE_RESP_BUSINESSEXCEPTION, "用户姓名错误");
		}
		if (StringUtils.isEmpty(reqBody.getUserMobile())) {
			logger.warn("客户端拍照上传请求参数错误，联系电话不能为空：content=" + reqBody.getUserMobile());
			throw new WebControlException(ResponseCode.CODE_RESP_BUSINESSEXCEPTION, "联系电话错误");
		}
		if (uploadFiles == null || uploadFiles.size() == 0) {
			logger.warn("客户端拍照上传请求参数错误，没有上传照片");
			throw new WebControlException(ResponseCode.CODE_RESP_BUSINESSEXCEPTION, "没有上传照片");
		}
	}
}
