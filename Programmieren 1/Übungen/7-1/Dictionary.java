import java.util.HashMap;
import java.util.ArrayList;
public class Dictionary {
	HashMap<String, ArrayList<String>> map;
	public Dictionary()
	{
		map = new HashMap<String, ArrayList<String>>();
	}
	
	public ArrayList<String> translate(String word) throws WordNotFoundException
	{
		if(map.containsKey(word))
		{
			return (ArrayList<String>)map.get(word);
		}
		else
		{
			throw new WordNotFoundException("Word not found");
		}
	}
	
	public void addTranslations(String key, ArrayList<String> value)
	{
		if(map.containsKey(key))
		{
			ArrayList<String> temp = map.get(key);
			for(String e : value)
			{
				temp.add(e);
			}
			map.put(key, temp);
		}
		else
		{
			map.put(key, value);	
		}
	}
	
	public void addTranslations(String key, String[] value)
	{		
		if(map.containsKey(key))
		{
			ArrayList<String> temp = map.get(key);		
			for(String e : value)
			{
				temp.add(e);
			}
			map.put(key, temp);
		}
		else
		{
			ArrayList<String> list = new ArrayList<String>();
			for(String e : value)
			{
				list.add(e);
			}
			map.put(key, list);	
		}
	}
}
