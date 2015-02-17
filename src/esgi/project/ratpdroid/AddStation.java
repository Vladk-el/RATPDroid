package esgi.project.ratpdroid;

import java.util.Random;

import esgi.project.ratpdroid.db.LineDAO;
import esgi.project.ratpdroid.db.StopDAO;
import esgi.project.ratpdroid.model.Stop;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	private TextView textViewLatitudeAddStation;
	private TextView textViewLongitudeAddStation;

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

		Log.v(TAG, "Value : " + Datas.GetInstance().GetCurrentLine());

		textViewAddStation = (TextView) findViewById(R.id.textViewAddStation);
		textViewLongitudeAddStation = (TextView) findViewById(R.id.textViewLongitudeAddStation);
		textViewLatitudeAddStation = (TextView) findViewById(R.id.textViewLatitudeAddStation);

		textViewAddStation.setText("Ajouter une station "
				+ Datas.GetInstance().GetCurrentLine());
	}

	public void onButtonAddStationsClick(View view) {

		Log.v(TAG, "Methode onButtonAddStationsClick");

		try {
			Stop stop = new Stop();
			stop.setId(randInt(0, 1634));
			stop.setIdLine(Datas.GetInstance().GetCurrentLine().getId());

			if (textViewLatitudeAddStation.getText().toString()
					.matches("[0-9]{1,13}(\\.[0-9]*)?")) {
				stop.setLat(Double.parseDouble(textViewLatitudeAddStation
						.getText().toString()));
			}

			else {
				stop.setLat(0);
			}

			if (textViewLongitudeAddStation.getText().toString()
					.matches("[0-9]{1,13}(\\.[0-9]*)?")) {
				stop.setLon(Double.parseDouble(textViewLongitudeAddStation
						.getText().toString()));
			}

			else {
				stop.setLon(0);
			}

			stop.setName(textViewAddStation.getText().toString());

			StopDAO sdao = new StopDAO(this);
			sdao.open();

			sdao.insert(stop);

			intent = new Intent(this, ListStations.class);
			startActivity(intent);
		} catch (Exception E) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Problème lors de l'ajout")
					.setMessage(
							"Un problème est survenue lors de l'ajout de la station")
					.setIcon(android.R.drawable.ic_dialog_alert).show();
			Log.v(TAG, "CATCH " + E.getMessage());
		}
	}

	public int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

}
