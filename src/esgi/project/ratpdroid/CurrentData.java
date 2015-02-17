package esgi.project.ratpdroid;

import esgi.project.ratpdroid.model.Line;
import esgi.project.ratpdroid.model.Stop;

public class CurrentData {

	private static CurrentData _instance;
	private Line currentLine;
	private Stop currentStop;
	
	public static CurrentData GetInstance()
	{
		if (_instance == null)
			_instance = new CurrentData();
		
		return _instance;
	}
	
	public void SetCurrentLine(Line line)
	{
		this.currentLine = line;
	}
	
	public Line GetCurrentLine()
	{
		return currentLine;
	}
	
	public void SetCurrentStop(Stop stop)
	{
		this.currentStop = stop;
	}
	
	public Stop GetCurrentStop()
	{
		return currentStop;
	}
}
