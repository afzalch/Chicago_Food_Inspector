package modules;

import java.util.ArrayList;

public class Search {
	public static <T> ArrayList<T> search(ArrayList<T> array, Searcher<T> searcher) {
		ArrayList<T> result = new ArrayList<T>();
		
		for (T t : array)
			if (searcher.matches(t))
				result.add(t);
		
		return result;
	}
}