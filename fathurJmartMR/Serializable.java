package fathurJmartMR;

import java.util.HashMap;

/**
 * Write a description of class Recognizable here.
 *
 * @author Fathurrahman Irwansa
 * @version 6 November 2021
 */
public class Serializable implements Comparable<Serializable>
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<Class<?>, Integer>();
    
    protected Serializable(){
    	Class<?> getClazz = getClass();
    	if(mapCounter.get(getClazz) == null){
            mapCounter.put(getClazz,0);
        }else {
            mapCounter.put(getClazz, mapCounter.get(getClazz) + 1);
        }
        this.id = mapCounter.get(getClazz);
    }
    
    public boolean equals(Object other)
    {
        return other instanceof Serializable && ((Serializable) other).id == id;
    }
    
    public boolean equals(Serializable other)
    {
        return other.id == id;
    }
    
    public static <T extends Serializable> Integer setClosingId(Class<T> clazz, int id) {
    	return mapCounter.replace(clazz, id); 
    }
    
    public static <T extends Serializable> Integer getClosingID(Class<T> clazz) {
    	return mapCounter.get(clazz); 
    }
    
    @Override
    public int compareTo(Serializable other) {
    	return (this.id < other.id) ? -1 : ((this.id == other.id) ? 0 : 1);
    }
}
