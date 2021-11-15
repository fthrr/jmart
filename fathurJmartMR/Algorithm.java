package fathurJmartMR;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Algorithm {
	
	private Algorithm() {}
	
	public static <T> List<T> collect(T[] array, T value){
		List<T> tempList = new ArrayList<T>();
		for(T tempArray : array) {
			if(tempArray.equals(value)) {
				tempList.add(tempArray);
			}
			else {
				continue;
			}
		}
		return tempList;
	}
	
	public static <T> List<T> collect(Iterable<T> iterable, T value){
		List<T> tempList = new ArrayList<T>();
		for(T tempArray : iterable) {
			if(tempArray.equals(value)) {
				tempList.add(tempArray);
			}
			else {
				continue;
			}
		}
		return tempList;
	}
	
	public static <T> List<T> collect(Iterator<T> iterator, T value){
		List<T> tempList = new ArrayList<T>();
		while(iterator.hasNext()) {
			if(iterator.next().equals(value)) {
				tempList.add(iterator.next());
			}
			else {
				continue;
			}
		}
		return tempList;
	}
	
	public static <T> List<T> collect(T[] array, Predicate<T> pred){
		List<T> tempList = new ArrayList<T>();
		for(T tempArray : array) {
			if(tempArray.equals(pred)) {
				tempList.add(tempArray);
			}
			else {
				continue;
			}
		}
		return tempList;
	}
	
	public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred){
		List<T> tempList = new ArrayList<T>();
		for(T tempArray : iterable) {
			if(tempArray.equals(pred)) {
				tempList.add(tempArray);
			}
			else {
				continue;
			}
		}
		return tempList;
	}
	
	public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred){
		List<T> tempList = new ArrayList<T>();
		while(iterator.hasNext()) {
			if(iterator.next().equals(pred)) {
				tempList.add(iterator.next());
			}
			else {
				continue;
			}
		}
		return tempList;
	}
	
	public static <T> int count(T[] array, T value) {
		int counter = 0;
        for(T arrayValue : array){
            if (arrayValue.equals(value)){
                counter++;
            }
        }
       return counter;
	}
	
	public static <T> int count(Iterable<T> iterable, T value) {
		int counter = 0;
        for(T t : iterable){
            if(t.equals(value)){
                counter++;
            }
        }
        return counter;
	}
	
	public static <T> int count(Iterator<T> iterator, T value) {
		int counter = 0;
        while(iterator.hasNext()){
            if(iterator.next().equals(value)){
                counter++;
            }
        }
        return counter;
	}
	
	public static <T> int count(T[] array, Predicate<T> pred) {
		int counter = 0;
        for(T arrayValue : array){
            if (arrayValue.equals(pred)){
                counter++;
            }
        }
       return counter;
	}
	
	public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
		int counter = 0;
        for(T t : iterable){
            if(t.equals(pred)){
                counter++;
            }
        }
        return counter;
	}
	
	public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
		int counter = 0;
        while(iterator.hasNext()){
            if(iterator.next().equals(pred)){
                counter++;
            }
        }
        return counter;
	}
	
	public static <T> boolean exist(T[] array, T value) {
		for(T arrayValue : array){
            if (arrayValue.equals(value)){
                return true;
            }
        }
		return false;
	}
	
	public static <T> boolean exist(Iterable<T> iterable, T value) {
		for(T t : iterable){
            if(t.equals(value)){
                return true;
            }
        }
        return false;
	}
	
	public static <T> boolean exist(Iterator<T> iterator, T value) {
		while(iterator.hasNext()){
            if(iterator.next().equals(value)){
                return true;
            }
        }
        return false;
	}
	
	public static <T> boolean exist(T[] array, Predicate<T> pred) {
		for(T arrayValue : array){
            if (arrayValue.equals(pred)){
                return true;
            }
        }
       return false;
	}
	
	public static <T> boolean exist(Iterable<T> iterable, Predicate<T> pred) {
		for(T t : iterable){
            if(t.equals(pred)){
                return true;
            }
        }
        return false;
	}
	
	public static <T> boolean exist(Iterator<T> iterator, Predicate<T> pred) {
		while(iterator.hasNext()){
            if(iterator.next().equals(pred)){
                return true;
            }
        }
        return false;
	}
	
	public static <T> T find(T[] array, T value) {
        for (T arrayValue : array) {
            if (arrayValue.equals(value)) {
                return arrayValue;
            }
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, T value) {
        for (T t : iterable) {
            if (t.equals(value)) {
                return t;
            }
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, T value) {
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                return value;
            }
        }
        return null;
    }
    public static <T> T find(T[] array, Predicate<T> pred) {
        for (T arrayValue : array) {
            if (pred.predicate(arrayValue)) {
                return arrayValue;
            }
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        for (T t : iterable) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (pred.predicate(value)) {
                return value;
            }
        }
        return null;
    }
	
    public static <T extends Comparable<? super T>> T max(T first, T second) {
        if(first.compareTo(second) > 0) return first;
        return second;
    }
    
    public static <T extends Comparable<? super T>> T max(T[] array) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder);
        }
        return temp;
    }
    
    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder);
        }
        return temp;
    }
    
    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator) {
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder);
        }
        return temp;
    }
    
    public static <T> T max(T first, T second, Comparator<? super T> comparator) {
        if(comparator.compare(first, second) > 0) return first;
        return second;
    }
    
    public static <T> T max(T[] array, Comparator<? super T> comparator) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder, comparator);
        }
        return temp;
    }
    
    public static <T> T max(Iterable<T> iterable, Comparator<? super T> comparator) {
        Iterator<T> iterator = iterable.iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder, comparator);
        }
        return temp;
    }
    
    public static <T> T max(Iterator<T> iterator, Comparator<? super T> comparator) {
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder, comparator);
        }
        return temp;
    }
    
    public static <T extends Comparable<? super T>> T min(T first, T second) {
        if(first.compareTo(second) > 0) return first;
        return second;
    }
    
    public static <T extends Comparable<? super T>> T min(T[] array) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder);
        }
        return temp;
    }
    
    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder);
        }
        return temp;
    }
    
    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator) {
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder);
        }
        return temp;
    }
    
    public static <T> T min(T first, T second, Comparator<? super T> comparator) {
        if(comparator.compare(first, second) > 0) return first;
        return second;
    }
    
    public static <T> T min(T[] array, Comparator<? super T> comparator) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder, comparator);
        }
        return temp;
    }
    
    public static <T> T min(Iterable<T> iterable, Comparator<? super T> comparator) {
        Iterator<T> iterator = iterable.iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder, comparator);
        }
        return temp;
    }
    
    public static <T> T min(Iterator<T> iterator, Comparator<? super T> comparator) {
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder, comparator);
        }
        return temp;
    }
    
    public static <T> List<T> paginate (T[] array, int page, int pageSize, Predicate<T> pred){
    	List<T> newList = new ArrayList<>();
        for(T t : array){
            newList.add(t);
        }
        try{
            List<T> filteredList = newList.stream().filter(pred::predicate).collect(Collectors.toList());
            int endIndex = (page * pageSize) + pageSize;
            if(endIndex > filteredList.size()){
                endIndex = filteredList.size();
            }
            return filteredList.subList((page * pageSize), endIndex);
        }catch (Exception e){
            return newList.subList(0 ,0);
        }
    }
    
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred){
        List<T> newList = new ArrayList<>();
        for(T t : iterable){
            newList.add(t);
        }
        try{
            List<T> filteredList = newList.stream().filter(pred::predicate).collect(Collectors.toList());
            int endIndex = (page * pageSize) + pageSize;
            if(endIndex > filteredList.size()){
                endIndex = filteredList.size();
            }
            return filteredList.subList((page * pageSize), endIndex);
        }catch (Exception e){
            return newList.subList(0 ,0);
        }
    }
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred){
        List<T> newList = new ArrayList<>();
        while(iterator.hasNext()){
            newList.add(iterator.next());
        }
        try{
            List<T> filteredList = newList.stream().filter(pred::predicate).collect(Collectors.toList());
            int endIndex = (page * pageSize) + pageSize;
            if(endIndex > filteredList.size()){
                endIndex = filteredList.size();
            }
            return filteredList.subList((page * pageSize), endIndex);
        }catch (Exception e){
            return newList.subList(0 ,0);
        }
    }
}
