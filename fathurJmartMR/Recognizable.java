package fathurJmartMR;


/**
 * Write a description of class Recognizable here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 September 2021
 */
public abstract class Recognizable
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
}
