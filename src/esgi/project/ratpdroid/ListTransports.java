package esgi.project.ratpdroid;

import java.util.List;

import esgi.project.ratpdroid.db.DBLineHandler;
import esgi.project.ratpdroid.db.LineDAO;
import esgi.project.ratpdroid.model.Line;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListTransports extends Activity {

	private static final String TAG = "ListTransports";

	private String[] lesJoursSemaine = { "lundi", "mardi", "mercredi", "jeudi",
			"vendredi", "samedi", "dimanche" };
	private ListView myList;
	private TextView textViewListTransports;
	private Intent intent;

	private ImageView buttonSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_transports);
		
		Log.v(TAG, "Methode onCreate");
	}

	private void events() {
		
		Log.v(TAG, "Methode events");

		myList = (ListView) findViewById(R.id.listAllTransports);
		textViewListTransports = (TextView) findViewById(R.id.textViewListTransports);

		textViewListTransports.setText("Liste des "
				+ getIntent().getStringExtra("Transport"));
		myList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_list, lesJoursSemaine));

		intent = new Intent(this, ListStations.class);

		myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String item = lesJoursSemaine[arg2];
				Log.v(TAG, "Item : " + item);

				intent.putExtra("TransportName", item);
				startActivity(intent);
			}
		});

		View search_layout = findViewById(R.id.search);
		buttonSearch = (ImageView) search_layout
				.findViewById(R.id.searchButton);

		buttonSearch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.v(TAG, "Click sur le bouton search");

			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Log.v(TAG, "Methode onStart");

		Log.v(TAG, "Value : " + getIntent().getStringExtra("Transport"));
		
		events();
		
		// ==> OK
		LineDAO ldao = new LineDAO(this);
		ldao.open();
		List<Line> lines = ldao.getByType(1);
		for(Line line : lines){
			System.out.println(line);
		}
	}
}