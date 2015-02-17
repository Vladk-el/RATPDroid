package esgi.project.ratpdroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailStation extends Activity {

	private static final String TAG = "DetailStation";

	private TextView textViewNameStation;

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_station);
		
		Log.v(TAG, "Methode onCreate");
	}

	protected void onStart() {
		super.onStart();
		
		Log.v(TAG, "Methode onStart");

		Log.v(TAG,
				"Nom de la station : "
						+ getIntent().getStringExtra("NameStation"));

		intent = new Intent(this, UpdateStation.class);

		textViewNameStation = (TextView) findViewById(R.id.textViewNameStation);

		textViewNameStation.setText(getIntent().getStringExtra("NameStation"));
	}

	public void onButtonUpdateClick(View view) {

		Log.v(TAG, "Methode onButtonUpdateClick");
		
		intent.putExtra("NameStation", getIntent().getStringExtra("NameStation"));
		startActivity(intent);
	}

	public void onButtonDeleteClick(View view) {
		Log.v(TAG, "Methode onButtonDeleteClick");

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Supprimer la station")
				.setMessage("Etes-vous sur de vouloir supprimer la station ?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton("Oui",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Log.v(TAG, "Click sur Oui");
							}
						}).setNegativeButton("Non", null).show();
	}
}