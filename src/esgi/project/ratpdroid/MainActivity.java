package esgi.project.ratpdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onButtonRERClick(View view)
	{
		Log.v(TAG,"Click sur le bouton RER");
		
		intent = new Intent(this,ListTransports.class);
		intent.putExtra("Transport","RER");
		startActivity(intent);
	}
	
	public void onButtonMETROClick(View view)
	{
		Log.v(TAG,"Click sur le bouton METRO");
		
		intent = new Intent(this,ListTransports.class);
		intent.putExtra("Transport","METRO");
		startActivity(intent);
	}
	
	public void onButtonBUSClick(View view)
	{
		Log.v(TAG,"Click sur le bouton BUS");
		
		intent = new Intent(this,ListTransports.class);
		intent.putExtra("Transport","BUS");
		startActivity(intent);
	}
	
	public void onButtonTRAMWAYClick(View view)
	{
		Log.v(TAG,"Click sur le bouton TRAMWAY");
		
		intent = new Intent(this,ListTransports.class);
		intent.putExtra("Transport","TRAMWAY");
		startActivity(intent);
	}
}