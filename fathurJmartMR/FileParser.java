package fathurJmartMR;


/**
 * Write a description of interface FileParser here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 September 2021
 */
public abstract interface FileParser
{
    public boolean read(String content);
    
    default Object write(){
        return null;
    }
    
    public static Object newInstance(String content){
        return null;
    }
}
