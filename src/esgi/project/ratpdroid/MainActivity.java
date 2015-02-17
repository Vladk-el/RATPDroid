package esgi.project.ratpdroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.v(TAG, "START MAIN ACTIVITY");

		try {

			String destPath = "/data/data/" + getPackageName()
					+ "/databases/ratp.db";
			
			Log.v(TAG, destPath);

			File f = new File(destPath);
			if (!f.exists()) {
				Log.v(TAG, "File Not Exist");
				InputStream in = getAssets().open("ratp.db");
				OutputStream out = new FileOutputStream(destPath);

				byte[] buffer = new byte[1024];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
				in.close();
				out.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Log.v(TAG, "ioexeption");
			e.printStackTrace();
		}

		/*
		DBManager dbManager = new DBManager(this);
		Log.v(TAG, "Database is there with version: "
				+ dbManager.getReadableDatabase().getVersion());
		String sql = "select * from prizes";

		SQLiteDatabase db = dbManager.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		Log.v(TAG, "Query Result:" + cursor);

		cursor.close();
		db.close();
		dbManager.close();
		*/
	}

	public void onButtonRERClick(View view) {
		Log.v(TAG, "Click sur le bouton RER");

		intent = new Intent(this, ListTransports.class);
		intent.putExtra("Transport", "RER");
		startActivity(intent);
	}

	public void onButtonMETROClick(View view) {
		Log.v(TAG, "Click sur le bouton METRO");

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
		Log.v(TAG, "Click sur le bouton TRAMWAY");

		intent = new Intent(this, ListTransports.class);
		intent.putExtra("Transport", "TRAMWAY");
		startActivity(intent);
	}
}