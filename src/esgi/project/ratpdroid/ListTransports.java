package esgi.project.ratpdroid;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListTransports extends Activity {

	private static final String TAG = "ListTransports";
	
	private String[] lesJoursSemaine = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"}; 
	private ListView myList;
	private TextView textViewListTransports;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_transports);		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		Log.v(TAG,"Value : " + getIntent().getStringExtra("Transport"));		
		
		myList = (ListView) findViewById(R.id.listAllTransports);	
		textViewListTransports = (TextView) findViewById(R.id.textViewListTransports);	

		textViewListTransports.setText("Liste des " + getIntent().getStringExtra("Transport"));
		myList.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_list, lesJoursSemaine));
			
		intent = new Intent(this,ListStations.class);
		
		myList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				String item = lesJoursSemaine[arg2];
				Log.v(TAG,"Item : " + item);	
				
				intent.putExtra("TransportName",item);
				startActivity(intent);
			}
			});
	}
}