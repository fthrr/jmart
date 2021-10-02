package fathurJmartMR;

import java.util.ArrayList;

/**
 * Write a description of class Filter here.
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
public class Filter
{
    public static ArrayList<PriceTag> filterPriceTag(PriceTag[] list, double value, boolean less){
        ArrayList<PriceTag> priceTags = new ArrayList<>();
        for (PriceTag filter : list){
            if (less && filter.getAdjustedPrice() < value || !less && filter.getAdjustedPrice() >= value){
                priceTags.add(filter);
            }
        }
        return priceTags;
    }
    
    public static void filterProductRating(ArrayList<ProductRating> list, double value, boolean less){
        for (int i = 0; i < list.size(); ++i){
            final ProductRating filter = list.get(i);
            if (less && filter.getAverage() < value || !less && filter.getAverage() >= value){
                list.remove(i);
            }
        }
    }
}
