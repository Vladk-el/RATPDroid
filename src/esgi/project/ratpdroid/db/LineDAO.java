package esgi.project.ratpdroid.db;

import java.util.ArrayList;
import java.util.List;

import esgi.project.ratpdroid.model.Line;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LineDAO {

	private SQLiteDatabase bdd;
	
	private DBLineHandler handler;
	
	public LineDAO(Context context){
		handler = new DBLineHandler(context, DBLineHandler.TABLE_LINE, null, DBLineHandler.LINE_VERSION);
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
	
	public long insert(Line line){
		ContentValues values = new ContentValues();
		
		values.put(DBLineHandler.LINE_ID, line.getId());
		values.put(DBLineHandler.LINE_SHORT_NAME, line.getShortName());
		values.put(DBLineHandler.LINE_LONG_NAME, line.getLongName());
		values.put(DBLineHandler.LINE_TYPE, line.getType());
		
		return bdd.insert(DBLineHandler.TABLE_LINE, null, values);
	}
	
	public int update(Line line){
		ContentValues values = new ContentValues();
		
		values.put(DBLineHandler.LINE_SHORT_NAME, line.getShortName());
		values.put(DBLineHandler.LINE_LONG_NAME, line.getLongName());
		values.put(DBLineHandler.LINE_TYPE, line.getType());
		String where = DBLineHandler.LINE_ID + "=" + line.getId();
		
		return bdd.update(DBLineHandler.TABLE_LINE, values, where, null);
	}
	
	public int remove(Line line){
		String where = DBLineHandler.LINE_ID + "=" + line.getId();
		return bdd.delete(DBLineHandler.TABLE_LINE, where, null);
	}
	
	public Line getById(int id){
		String where = "WHERE " + DBLineHandler.LINE_ID + "=" + id;
		Cursor c = bdd.query(DBLineHandler.TABLE_LINE, DBLineHandler.LINE_CURSOR_QUERY, where, null, null, null, null);
		return cursorToLine(c);
	}
	
	public List<Line> getByType(int type){
		List<Line> all = getAll();
		List<Line> lines = new ArrayList<Line>();
		
		for(Line line : all){
			if(line.getType() == type){
				lines.add(line);
			}
		}
		
		return lines;
	}
	
	public List<Line> getByName(String name){
		String like = DBLineHandler.LINE_SHORT_NAME + " LIKE \"" + name + "\"";
		Cursor c = bdd.query(DBLineHandler.TABLE_LINE, DBLineHandler.LINE_CURSOR_QUERY, like, null, null, null, null);
		return cursorToLines(c);
	}
	
	public List<Line> getAll(){
		String where = "1";
		Cursor c = bdd.query(DBLineHandler.TABLE_LINE, DBLineHandler.LINE_CURSOR_QUERY, where, null, null, null, null);
		return cursorToLines(c);
	}
	
	public Line cursorToLine(Cursor c){
		if(c.getCount() == 0)
			return null;
		
		c.moveToFirst();
		
		Line line = new Line();
		line.setId(c.getInt(c.getColumnIndex(DBLineHandler.LINE_ID)));
		line.setShortName(c.getString(c.getColumnIndex(DBLineHandler.LINE_SHORT_NAME)));
		line.setLongName(c.getString(c.getColumnIndex(DBLineHandler.LINE_LONG_NAME)));
		line.setType(c.getInt(c.getColumnIndex(DBLineHandler.LINE_TYPE)));
		
		c.close();
		
		return line;
	}
	
	public List<Line> cursorToLines(Cursor c){
		if(c.getCount() == 0)
			return null;
		
		List<Line> lines = new ArrayList<Line>();
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Line line = new Line();
			line.setId(c.getInt(c.getColumnIndex(DBLineHandler.LINE_ID)));
			line.setShortName(c.getString(c.getColumnIndex(DBLineHandler.LINE_SHORT_NAME)));
			line.setLongName(c.getString(c.getColumnIndex(DBLineHandler.LINE_LONG_NAME)));
			line.setType(c.getInt(c.getColumnIndex(DBLineHandler.LINE_TYPE)));
			lines.add(line);
		}
		
		c.close();
		
		return lines;
	}
	
	
	
	
	
}
