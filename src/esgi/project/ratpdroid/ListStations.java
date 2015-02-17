package esgi.project.ratpdroid;

import java.util.List;

import esgi.project.ratpdroid.db.LineDAO;
import esgi.project.ratpdroid.db.StopDAO;
import esgi.project.ratpdroid.model.Line;
import esgi.project.ratpdroid.model.Stop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListStations extends Activity {

	private static final String TAG = "ListStations";

	private ListView myList;
	private TextView textViewListStations;
	private Intent intent;

	private ImageView buttonSearch;
	private List<Stop> stops;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_stations);
		
		Log.v(TAG, "Methode onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Log.v(TAG, "Methode onStart");

		events();
		
		Log.v(TAG, "Value : " + Datas.GetInstance().GetCurrentLine());
	}

	private void events() {
		
		Log.v(TAG, "Methode events");

		View search_layout = findViewById(R.id.search);
		buttonSearch = (ImageView) search_layout
				.findViewById(R.id.searchButton);

		buttonSearch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.v(TAG, "Click sur le bouton search");

			}
		});

		myList = (ListView) findViewById(R.id.listAllStations);
		textViewListStations = (TextView) findViewById(R.id.textViewListStations);

		textViewListStations.setText("Liste des stations " + Datas.GetInstance().GetCurrentLine());
		
		StopDAO sdao = new StopDAO(this);
		sdao.open();
		
		stops = sdao.getByLine(Datas.GetInstance().GetCurrentLine().getShortName());
		
		myList.setAdapter(new ArrayAdapter<Stop>(this,R.layout.activity_list, stops));

		intent = new Intent(this, DetailStation.class);

		myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Stop stop = stops.get(arg2);
				Log.v(TAG, "Stop : " + stop);

				Datas.GetInstance().SetCurrentStop(stop);
				startActivity(intent);
			}
		});
	}

	public void onButtonAddStationsClick(View view) {
		
		Log.v(TAG, "Methode onButtonAddStationsClick");
		
		intent = new Intent(this, AddStation.class);
		intent.putExtra("Transport", getIntent()
				.getStringExtra("TransportName"));
		startActivity(intent);
	}
}
