package esgi.project.ratpdroid;

import esgi.project.ratpdroid.db.StopDAO;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	private static final String TAG = "MainActivity";
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.v(TAG, "onCreate");
	}

	public void initDatas() {
		Log.v(TAG, "initDatas");
		
		Datas.GetInstance();
		
		StopDAO sdao = new StopDAO(this);
		sdao.open();
		
		Datas.GetInstance().SetStops(sdao.getAll());
		
		sdao.close();
	}
}