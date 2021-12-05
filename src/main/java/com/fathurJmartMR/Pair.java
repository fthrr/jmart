package com.fathurJmartMR;

/**
 * Pair class untuk mendefinisikan Pair pada Jmart
 * 
 * @author Fathurrahman Irwansa
 * @version 5 November 2021
 * 
 */
public class Pair<T, U> {
    /**
     * Instance variable untuk class Pair
     */
    public T first;
    public U second;

    /**
     * Constructor method untuk class pair
     */
    public Pair(){}

    /**
     * Constructor method untuk class pair
     * @param first		nilai pertama
     * @param second	nilai kedua
     */
    public Pair(T first, U second){
        this.first = first;
        this.second = second;
    }
}
