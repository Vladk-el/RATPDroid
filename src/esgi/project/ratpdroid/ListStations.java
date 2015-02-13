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

public class ListStations extends Activity {

	private static final String TAG = "ListStations";
	
	private String[] lesJoursSemaine = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"}; 
	
	private ListView myList;
	private TextView textViewListStations;	
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_stations);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		Log.v(TAG,"Value : " + getIntent().getStringExtra("TransportName"));		
		
		myList = (ListView) findViewById(R.id.listAllStations);	
		textViewListStations= (TextView) findViewById(R.id.textViewListStations);	

		textViewListStations.setText("Liste des stations " + getIntent().getStringExtra("TransportName"));
		myList.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_list, lesJoursSemaine));
			
		myList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				String item = lesJoursSemaine[arg2];
				Log.v(TAG,"Item : " + item);				
			}
			});
	}
	
	public void onButtonAddStationsClick(View view)
	{
		intent = new Intent(this,AddStation.class);	
		intent.putExtra("Transport",getIntent().getStringExtra("TransportName"));
		startActivity(intent);
	}
}
