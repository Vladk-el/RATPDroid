package esgi.project.ratpdroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UpdateStation extends Activity {

	private static final String TAG = "UpdateStation";

	private TextView textViewUpdateStation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_station);

		Log.v(TAG, "Methode onCreate");
	}

	protected void onStart() {
		super.onStart();

		Log.v(TAG, "Methode onStart");

		Log.v(TAG,
				"Nom de la station : "
						+ getIntent().getStringExtra("NameStation"));

		textViewUpdateStation = (TextView) findViewById(R.id.textViewUpdateStation);

		textViewUpdateStation.setText("Modifier la station "
				+ getIntent().getStringExtra("NameStation"));
	}
}