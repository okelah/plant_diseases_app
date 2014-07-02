package plant.diseases.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Timothy on 2014/5/10.
 */
@Component
public class CommunicateServerConfig {

	/** 用户问题图片资源根路径 */
	@Value("${diseasesServer.photoRootDir}")
	private String photoRootDir;

	public String getPhotoRootDir() {
		return photoRootDir;
	}

	public void setPhotoRootDir(String photoRootDir) {
		this.photoRootDir = photoRootDir;
	}
}
