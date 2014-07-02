package plant.diseases.android.application;

import android.content.Context;
import com.zyt.android.dbbase.AbsDaoFactory;
import com.zyt.communicate.client.AbsClientConfig;
import com.zyt.communicate.client.AbsWebController;
import com.zyt.communicate.client.IRespBodyValueSetter;
import com.zyt.communicate.client.json.GsonJsonWebController;
import com.zyt.communicate.client.json.JsonRespXmlRespBodyValueSetter;
import com.zyt.communicate.common.json.IReqBody;
import com.zyt.communicate.common.json.IRespBody;
import com.zyt.communicate.common.json.gson.DefaultGsonCreator;
import com.zyt.communicate.common.json.gson.IGsonCreator;
import com.zytproduct.dao.ZytStandardDaoFactory;
import dagger.Module;
import dagger.Provides;
import plant.diseases.android.activity.*;

import javax.inject.Singleton;

/**
 * Created by Timothy on 2014/6/28.
 */
@Module(injects = { DiagnoseActivity.class, DiagnoseResultActivity.class, DiseaseListActivity.class,
		PestListActivity.class, PhotoUploadActivity.class })
public class ApplicationModule {

	private Context context;

	public ApplicationModule(Context context) {
		this.context = context;
	}

	@Provides
	@Singleton
	public AbsDaoFactory provideDaoFactory() {
		AbsDaoFactory daoFactory = new ZytStandardDaoFactory(context);
		return daoFactory;
	}

	/**
	 * 声明具体的Gson对象生成器
	 * @return 具体的Gson对象生成器
	 */
	@Provides
	@Singleton
	public IGsonCreator provideGsonCreator() {
		return new DefaultGsonCreator();
	}

	/**
	 * 声明具体的客户端配置实现
	 * @return 具体的客户端配置实现
	 */
	@Provides
	@Singleton
	public AbsClientConfig<IReqBody, IRespBody> provideClientConfig() {
		return new DiseaseslientConfig<IReqBody, IRespBody>();
	}

	/**
	 * 声明具体的Web请求控制器
	 * @param webContoller 具体的Web请求控制器为GsonJsonWebController
	 * @return 具体的Web请求控制器
	 */
	@Provides
	@Singleton
	public AbsWebController<IReqBody, IRespBody> provideWebController(GsonJsonWebController webContoller) {
		return webContoller;
	}

	/**
	 * 声明具体的通信响应消息体赋值实现
	 * @param valueSetter 具体的Web请求控制器为JsonRespXmlRespBodyValueSetter
	 * @return 具体的通信响应消息体赋值实现
	 */
	@Provides
	@Singleton
	public IRespBodyValueSetter<IReqBody, IRespBody> provideRespBodyValueSetter(
			JsonRespXmlRespBodyValueSetter valueSetter) {
		return valueSetter;
	}

}
