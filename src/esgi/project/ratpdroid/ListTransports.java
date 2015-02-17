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

	private ListView myList;
	private TextView textViewListTransports;
	private Intent intent;
	private List<Line> lines;

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

		LineDAO ldao = new LineDAO(this);
		ldao.open();

		lines = ldao.getByType(getTransport(getIntent().getStringExtra("Transport")));

		for (Line line : lines) {
			Log.v(TAG, line.toString());
		}

		myList.setAdapter(new ArrayAdapter<Line>(this, R.layout.activity_list,
				lines));

		intent = new Intent(this, ListStations.class);

		myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Line line = lines.get(arg2);
				Log.v(TAG, "Ligne : " + line);
				
				Datas.GetInstance().SetCurrentLine(line);
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

	private Integer getTransport(String transport) {
		if (transport.equalsIgnoreCase("TRAM"))
			return 0;

		else if (transport.equalsIgnoreCase("METRO"))
			return 1;

		else if (transport.equalsIgnoreCase("RER"))
			return 2;

		else
			return 3;
	}

	@Override
	protected void onStart() {
		super.onStart();

		Log.v(TAG, "Methode onStart");

		Log.v(TAG, "Value : " + getIntent().getStringExtra("Transport"));

		events();
	}
}