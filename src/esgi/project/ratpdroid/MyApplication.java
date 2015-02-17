package esgi.project.ratpdroid;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	private static final String TAG = "MainActivity";

	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.v(TAG, "onCreate");

		initDatas();
	}

	private void initDatas() {
		Log.v(TAG, "initSingleton");
		
		CurrentData.GetInstance();
	}
}