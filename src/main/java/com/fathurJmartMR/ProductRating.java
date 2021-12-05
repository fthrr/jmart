package com.fathurJmartMR;


/**
 * Product Rating Class untuk menghandle rincian product
 *
 * @author Fathurrahman Irwansa
 * @version 5 Desmber 2021
 */
public class ProductRating
{
    /**
     * Instance variable untuk product class rating
     */
    private long total;
    private long count;
    
    /**
     * Constructor untuk objek dari class ProductRating
     */
    public ProductRating()
    {
        this.total = 0;
        this.count = 0;
    }
    
    /**
     * Method untuk menambahkan rating
     * @param rating product rating
     */
    public void insert(int rating)
    {
        total += rating;
        count++;
    }
    
    
    /**
     * Method untuk menghitung rating rata-rata product
     * @return rating
     */
    public double getAverage()
    {
        if(count == 0){
            return 0.0;
        }else{
            return total/(double)count;
        }
    }
    
    /**
     * Accessor method untuk mendapatkan nilai count
     * @return count
     */
    public long getCount()
    {
        return count;
    }
    
    /**
     * Accessor method untuk mendapatkan nilai total
     * @return total
     */
    public long getTotal()
    {
        return total;
    }
}
