package esgi.project.ratpdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AddStation extends Activity {

	private static final String TAG = "AddStation";
		
	private TextView textViewAddStation;	
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_station);
		
		Log.v(TAG, "Methode onCreate");
	}
	
	protected void onStart() {
		super.onStart();
		
		Log.v(TAG, "Methode onStart");
		
		Log.v(TAG,"Value : " + getIntent().getStringExtra("Transport"));		
		
		textViewAddStation= (TextView) findViewById(R.id.textViewAddStation);	

		textViewAddStation.setText("Ajouter une station " + getIntent().getStringExtra("Transport"));
	}
}
