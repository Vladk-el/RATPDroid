package esgi.project.ratpdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Intent intent;
	private ImageView buttonSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.v(TAG, "Methode onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();

		Log.v(TAG, "Methode onStart");

		events();
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
	}

	public void onButtonRERClick(View view) {

		Log.v(TAG, "Methode onButtonRERClick");

		intent = new Intent(this, ListTransports.class);
		intent.putExtra("Transport", "RER");
		startActivity(intent);
	}

	public void onButtonMETROClick(View view) {
		Log.v(TAG, "Methode onButtonMETROClick");

		intent = new Intent(this, ListTransports.class);
		intent.putExtra("Transport", "METRO");
		startActivity(intent);
	}

	public void onButtonBUSClick(View view) {
		Log.v(TAG, "Methode onButtonBUSClick");

		intent = new Intent(this, ListTransports.class);
		intent.putExtra("Transport", "BUS");
		startActivity(intent);
	}

	public void onButtonTRAMWAYClick(View view) {
		Log.v(TAG, "Methode onButtonTRAMWAYClick");

		intent = new Intent(this, ListTransports.class);
		intent.putExtra("Transport", "TRAMWAY");
		startActivity(intent);
	}
	
	public void onButtonResetClick(View view) {
		Log.v(TAG, "Methode onButtonResetClick");

		
	}
}