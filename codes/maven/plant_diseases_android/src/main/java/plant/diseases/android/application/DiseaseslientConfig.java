package plant.diseases.android.application;

import com.zyt.communicate.client.ProtocolRelation;
import com.zyt.communicate.client.json.AbsJsonClientConfig;
import com.zyt.communicate.common.json.IReqBody;
import com.zyt.communicate.common.json.IRespBody;
import com.zyt.communicate.common.json.MapExceptionRespBody;
import plant.diseases.protocol.PhotoUploadReqBody;
import plant.diseases.protocol.PhotoUploadRespBody;

import java.util.ArrayList;
import java.util.List;

public class DiseaseslientConfig<T1 extends IReqBody, T2 extends IRespBody> extends AbsJsonClientConfig<T1, T2> {

	@Override
	protected List<ProtocolRelation<T1, T2>> createProtocolRelations() {
		List<ProtocolRelation<T1, T2>> relations = new ArrayList<ProtocolRelation<T1, T2>>();
		// 首次登录
		relations.add(new ProtocolRelation("0501", "8501", PhotoUploadReqBody.class, PhotoUploadRespBody.class));
		/** 错误异常配置 */
		relations.add(new ProtocolRelation("-1", "-1", null, MapExceptionRespBody.class));
		return relations;
	}
}
