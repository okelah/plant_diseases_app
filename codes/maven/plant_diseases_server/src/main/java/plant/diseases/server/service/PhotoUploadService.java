package plant.diseases.server.service;

import com.zyt.communicate.common.UploadFile;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plant.diseases.protocol.PhotoUploadReqBody;
import plant.diseases.protocol.PhotoUploadRespBody;
import plant.diseases.server.CommunicateServerConfig;
import plant.diseases.serverdb.dao.TlDiseasesUploadDAO;
import plant.diseases.serverdb.model.TlDiseasesUpload;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Timothy on 2014/6/29.
 */
@Component
public class PhotoUploadService {

	private static final Logger logger = LoggerFactory.getLogger(PhotoUploadService.class);

	@Autowired
	private CommunicateServerConfig communicateServerConfig;
	@Autowired
	private TlDiseasesUploadDAO uploadDAO;

	public PhotoUploadRespBody photoUpload(PhotoUploadReqBody reqBody, UploadFile uploadFile) {
		// 创建灾害照片上传记录
		TlDiseasesUpload upload = new TlDiseasesUpload();
		upload.setUserName(reqBody.getUserName());
		upload.setUserMobile(reqBody.getUserMobile());
		upload.setUserDesc(reqBody.getUserDesc());
		upload.setImgName(uploadFile.getFileName());
		upload.setUploadtime(new Date());
		TlDiseasesUpload record = uploadDAO.insert(upload);
		// 为该照片创建资源路径
		String saveDir = getFileSaveDir(record.getUploadId().intValue());
		try {
			FileUtils.copyFileToDirectory(new File(uploadFile.getFilePath()), new File(saveDir));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		// 返回执行结果
		PhotoUploadRespBody respBody = new PhotoUploadRespBody();
		return respBody;
	}

	private String getFileSaveDir(int recordId) {
		String saveDir = communicateServerConfig.getPhotoRootDir() + "/" + recordId;
		new File(saveDir).mkdirs();
		return saveDir;
	}

}
