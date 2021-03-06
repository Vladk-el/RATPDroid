package esgi.project.ratpdroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import esgi.project.ratpdroid.db.StopDAO;
import esgi.project.ratpdroid.model.Stop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Intent intent;

	private ImageView buttonSearch;
	private EditText editTextSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.v(TAG, "START MAIN ACTIVITY");
		
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
		editTextSearch = (EditText) search_layout
				.findViewById(R.id.editTextSearch);

		buttonSearch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.v(TAG, "Click sur le bouton search");

				for (Stop stop : Datas.GetInstance().GetStops()) {
					if (stop.getName().contains(
							editTextSearch.getText().toString().toUpperCase())) {
						Datas.GetInstance().SetCurrentStop(stop);

						intent = new Intent(v.getContext(), DetailStation.class);
						startActivity(intent);

						break;
					}
				}
			}
		});
	}

	public void launchRingDialog(View view) {
		final ProgressDialog ringProgressDialog = ProgressDialog
				.show(MainActivity.this,
						"Patientez ...",
						"Merci de patientez pendant la réinitialisation de la base de données ...",
						true);
		ringProgressDialog.setCancelable(true);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					resetBDD();
				} catch (Exception e) {
				}
				ringProgressDialog.dismiss();
			}
		}).start();
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
		Log.v(TAG, "Click sur le bouton BUS");

		intent = new Intent(this, ListTransports.class);
		intent.putExtra("Transport", "BUS");
		startActivity(intent);
	}

	public void onButtonTRAMWAYClick(View view) {
		Log.v(TAG, "Methode onButtonBUSClick");

		intent = new Intent(this, ListTransports.class);
		intent.putExtra("Transport", "BUS");
		startActivity(intent);
	}

	private void resetBDD() {
		try {

			String destPath = "/data/data/" + getPackageName()
					+ "/databases/ratp.db";

			Log.v(TAG, destPath);

			File f = new File(destPath);

			Log.v(TAG, "Replace db");
			InputStream in = getAssets().open("ratp.db");
			OutputStream out = new FileOutputStream(destPath);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Log.v(TAG, "ioexeption");
			e.printStackTrace();
		}
	}

	public void onButtonResetClick(View view) {
		Log.v(TAG, "Methode onButtonResetClick");

		launchRingDialog(view);
	}
}