package plant.diseases.android.application;

import android.app.Application;
import dagger.ObjectGraph;

/**
 * Created by Timothy on 2014/6/28.
 */
public class DiseasesApplication extends Application {

	private ObjectGraph objectGraph;

	@Override
	public void onCreate() {
		super.onCreate();
		objectGraph = ObjectGraph.create(new ApplicationModule(this));
	}

	public void inject(Object object) {
		objectGraph.inject(object);
	}

}
