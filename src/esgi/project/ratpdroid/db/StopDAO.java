package esgi.project.ratpdroid.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import esgi.project.ratpdroid.model.Line;
import esgi.project.ratpdroid.model.Stop;

public class StopDAO {

private SQLiteDatabase bdd;
	
	private DBStopHandler handler;
	
	public StopDAO(Context context){
		handler = new DBStopHandler(context, DBStopHandler.TABLE_STOP, null, DBStopHandler.STOP_VERSION);
	}
	
	public void open(){
		bdd = handler.getWritableDatabase();
	}
	
	public void close(){
		bdd.close();
	}
	
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	public long insert(Stop stop){
		ContentValues values = new ContentValues();
		
		values.put(DBStopHandler.STOP_ID, stop.getId());
		values.put(DBStopHandler.STOP_NAME, stop.getName());
		values.put(DBStopHandler.STOP_LAT, stop.getLat());
		values.put(DBStopHandler.STOP_LON, stop.getLon());
		values.put(DBStopHandler.STOP_NUM, stop.getPosition());
		values.put(DBStopHandler.STOP_IDLINE, stop.getIdLine());
		
		return bdd.insert(DBStopHandler.TABLE_STOP, null, values);
	}
	
	public int update(Stop stop){
		ContentValues values = new ContentValues();
		
		values.put(DBStopHandler.STOP_NAME, stop.getName());
		values.put(DBStopHandler.STOP_LAT, stop.getLat());
		values.put(DBStopHandler.STOP_LON, stop.getLon());
		values.put(DBStopHandler.STOP_NUM, stop.getPosition());
		values.put(DBStopHandler.STOP_IDLINE, stop.getIdLine());
		
		String where = DBStopHandler.STOP_ID + "=" + stop.getId();
		
		return bdd.update(DBStopHandler.TABLE_STOP, values, where, null);
	}
	
	public int remove(Stop stop){
		String where = DBStopHandler.STOP_ID + "=" + stop.getId();
		return bdd.delete(DBStopHandler.TABLE_STOP, where, null);
	}
	
	public Stop getById(int id){
		String where = "WHERE " + DBStopHandler.STOP_ID + "=" + id;
		Cursor c = bdd.query(DBStopHandler.TABLE_STOP, DBStopHandler.STOP_CURSOR_QUERY, where, null, null, null, null);
		return cursorToStop(c);
	}
	
	public List<Stop> getByLine(int id_line){
		List<Stop> all = getAll();
		List<Stop> stops = new ArrayList<Stop>();
		
		for(Stop stop : all){
			if(stop.getIdLine() == id_line){
				stops.add(stop);
			}
		}
		
		Collections.sort(stops);
		
		return stops;
	}
	
	public List<Stop> getAll(){
		String where = "1";
		Cursor c = bdd.query(DBStopHandler.TABLE_STOP, DBStopHandler.STOP_CURSOR_QUERY, where, null, null, null, null);
		return cursorToStops(c);
	}
	
	public Stop cursorToStop(Cursor c){
		if(c.getCount() == 0)
			return null;
		
		c.moveToFirst();
		
		Stop stop = new Stop();
		stop.setId(c.getInt(c.getColumnIndex(DBStopHandler.STOP_ID)));
		stop.setName(c.getString(c.getColumnIndex(DBStopHandler.STOP_NAME)));
		stop.setLat(c.getDouble(c.getColumnIndex(DBStopHandler.STOP_LAT)));
		stop.setLon(c.getDouble(c.getColumnIndex(DBStopHandler.STOP_LON)));
		stop.setPosition(c.getInt(c.getColumnIndex(DBStopHandler.STOP_NUM)));
		stop.setIdLine(c.getInt(c.getColumnIndex(DBStopHandler.STOP_IDLINE)));
		
		c.close();
		
		return stop;
	}
	
	public List<Stop> cursorToStops(Cursor c){
		if(c.getCount() == 0)
			return null;
		
		List<Stop> lines = new ArrayList<Stop>();
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Stop stop = new Stop();
			stop.setId(c.getInt(c.getColumnIndex(DBStopHandler.STOP_ID)));
			stop.setName(c.getString(c.getColumnIndex(DBStopHandler.STOP_NAME)));
			stop.setLat(c.getDouble(c.getColumnIndex(DBStopHandler.STOP_LAT)));
			stop.setLon(c.getDouble(c.getColumnIndex(DBStopHandler.STOP_LON)));
			stop.setPosition(c.getInt(c.getColumnIndex(DBStopHandler.STOP_NUM)));
			stop.setIdLine(c.getInt(c.getColumnIndex(DBStopHandler.STOP_IDLINE)));
			lines.add(stop);
		}
		
		c.close();
		
		return lines;
	}
	
	
	
	
	
}
