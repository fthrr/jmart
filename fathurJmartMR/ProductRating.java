
package fathurJmartMR;


/**
 * Write a description of class ProductRating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProductRating
{
    private long total;
    private long count;
    
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }
    
    public void insert(int rating){
        total = total + rating;
        count++;
    }
    
    public double getAverage(){
        if(getCount() == 0){
            return 0;
        }
        else{
            return getTotal()/getCount();
        }
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
}
