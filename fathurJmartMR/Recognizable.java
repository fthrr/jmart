package fathurJmartMR;


/**
 * Write a description of class Recognizable here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 September 2021
 */
public class Recognizable
{
    public final int id;
    
    protected Recognizable(int id){
        this.id = id;
    }
    
    public boolean equals(Object object){
        if(object instanceof Recognizable){
            Recognizable recognizable = (Recognizable) object;
            if(recognizable.id == this.id){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    
    public boolean equals(Recognizable recognizable){
        if(recognizable.id == this.id){
            return true;
        }
        else{
            return false;
        }
    }
}
