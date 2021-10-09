package fathurJmartMR;


/**
 * Write a description of class Recognizable here.
 *
 * @author Fathurrahman Irwansa
 * @version 9 Oktober 2021
 */
public class Recognizable implements Comparable<Recognizable>
{
    public final int id;
    
    protected Recognizable(int id){
        this.id = id;
    }
    
    public boolean equals(Object other)
    {
        return other instanceof Recognizable && ((Recognizable) other).id == id;
    }
    
    public boolean equals(Recognizable other)
    {
        return other.id == id;
    }
    
    public static <T extends Recognizable> int setClosingId(Class<T> clazz, int id) {
    	 if(Class.class.isAssignableFrom(Recognizable.class)){
             return 0;
         }
         else{
             return 1;
         }
    }
    
    public static <T extends Recognizable> int getClosingID(Class<T> clazz) {
    	 if(Class.class.isAssignableFrom(Recognizable.class)){
             return 0;
         }
         else{
             return 1;
         }
    }
    
    @Override
    public int compareTo(Recognizable other) {
    	return Integer.compare(this.id, other.id);
    }
}
