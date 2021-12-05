package com.fathurJmartMR;

/**
 * Interface Predicate
 * 
 * @author lenovo
 * @version 5 Desember 2021
 * 
 */
@FunctionalInterface
public interface Predicate<T> {
    public abstract boolean predicate(T arg);
}
