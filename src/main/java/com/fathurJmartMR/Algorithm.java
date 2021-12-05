package com.fathurJmartMR;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Algorithm untuuk mendefinisikan berbagai algoritma dalam program
 * @author Fathurrahman Irwansa
 * @version 5 November 2021
 *
 */
public class Algorithm {

    /**
     * Method untuk mendifinsikan objek Algorithm bersifat private
     */
    private Algorithm() {
    }

    /**
     * Method untuk mendapatkan berbagai data dari array
     * @param <T>		Any Array
     * @param array		Array Value
     * @param value		Any Value
     * @return list of array
     */
    public static <T> List<T> collect(T[] array, T value) {
        List<T> list = new ArrayList<T>();
        for(T t : array){
            if(t.equals(value)){
                list.add(t);
            }
        }
        return list;
    }
    
    /**
     * Method untuk mendapatkan berbagai data dari iterable
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param value		Any Value
     * @return list of iterable
     */
    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        List<T> list = new ArrayList<T>();
        for(T t : iterable){
            if(t.equals(value)){
                list.add(t);
            }
        }
        return list;
    }
    
    /**
     * Method untuk mendapatkan berbagai data dari iterator
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param value		Any Value
     * @return list of iterator
     */
    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        List<T> list = new ArrayList<T>();
        while(iterator.hasNext()){
            T t = iterator.next();
            if(t.equals(value)){
                list.add(t);
            }
        }
        return list;
    }
    
    
    /**
     * 
     * Method untuk mendapatkan data dari array menggunakan predicate
     * @param <T>		Any Array
     * @param array		Array Value
     * @param pred		Any Predicate
     * @return list of array
     */
    public static <T> List<T> collect(T[] array, Predicate<T> pred) {
        List<T> list = new ArrayList<T>();
        for(T t : array){
            if(pred.predicate(t)){
                list.add(t);
            }
        }
        return list;
    }
    
    /**
     * Method untuk mendapatkan data dari iterable menggunakan predicate
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param pred		Any predicate
     * @return list of iterable
     */
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred) {
        List<T> list = new ArrayList<T>();
        for(T t : iterable){
            if(pred.predicate(t)){
                list.add(t);
            }
        }
        return list;
    }
    
    /**
     * Method untuk mendapatkan data dari iterator menggunakan predicate
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param pred		Any Predicate
     * @return list of iterator
     */
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List<T> list = new ArrayList<T>();
        while(iterator.hasNext()){
            T t = iterator.next();
            if(pred.predicate(t)){
                list.add(t);
            }
        }
        return list;
    }

    
    /**
     * Method untuk menghitung jumlah data dalam array
     * @param <T>		Any Array
     * @param array		Array Value
     * @param value		Any Value
     * @return counter
     */
    public static <T> int count(T[] array, T value) {
        int counter = 0;
        for (T arrayValue : array) {
            if (arrayValue.equals(value)) {
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * Method untuk menghitung jumlah data dengan iterable
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param value		Any value
     * @return counter
     */
    public static <T> int count(Iterable<T> iterable, T value) {
        int counter = 0;
        for (T t : iterable) {
            if (t.equals(value)) {
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * Method untuk menghitung jumlah data dalam iterator
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param value		Any Value
     * @return counter
     */
    public static <T> int count(Iterator<T> iterator, T value) {
        int counter = 0;
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * Method untuk menghitung jumlah data dalam array dan predicate
     * @param <T>		Any Array
     * @param array		Array Value
     * @param pred		Any Predicate
     * @return counter
     */
    public static <T> int count(T[] array, Predicate<T> pred) {
        int counter = 0;
        for (T arrayValue : array) {
            if (pred.predicate(arrayValue)) {
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * Method untuk menghitung jumlah data dengan iterable dan predicate
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param pred		Any Predicate
     * @return counter
     */
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        int counter = 0;
        for (T t : iterable) {
            if (pred.predicate(t)) {
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * Method untuk menghitung jumlah data dengan iterator dan predicate
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param pred		Any Predicate
     * @return counter
     */
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
        int counter = 0;
        while (iterator.hasNext()) {
            if (pred.predicate(iterator.next())) {
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * Method untuk memeriksa apakah data ada dalam array
     * @param <T>		Any Array
     * @param array		Array Value
     * @param value		Any Value
     * @return condition
     */
    public static <T> boolean exists(T[] array, T value) {
        for (T arrayValue : array) {
            if (arrayValue.equals(value)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method untuk memeriksa apakah data ada dalam iterable
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param value		Any Value
     * @return condition
     */
    public static <T> boolean exists(Iterable<T> iterable, T value) {
        for (T t : iterable) {
            if (t.equals(value)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method untuk memeriksa apakah data ada dalam iterator
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param value		Any Value
     * @return condition
     */
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method untuk memeriksa apakah data ada dalam array menggunakan predicate
     * @param <T>		Any Array
     * @param array		Array Value
     * @param pred		Any Predicate
     * @return condition
     */
    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        for (T arrayValue : array) {
            if (pred.predicate(arrayValue)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method untuk memeriksa apakah data ada dengan iterable dan predicate
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param pred		Any Predicate
     * @return condition
     */
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        for (T t : iterable) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method untuk memeriksa apakah data ada dengan iterator dan predicate
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param pred		Any Predicate
     * @return condition
     */
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {
            if (pred.predicate(iterator.next())) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * Method untuk mencari data dalam array
     * @param <T>		Any Array
     * @param array		Array Value
     * @param value		Any Value
     * @return arrayValue
     */
    public static <T> T find(T[] array, T value) {
        for (T arrayValue : array) {
            if (arrayValue.equals(value)) {
                return arrayValue;
            }
        }
        return null;
    }
    
    /**
     * Method untuk mencari data dalam iterable
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param value		Any Value
     * @return value
     */
    public static <T> T find(Iterable<T> iterable, T value) {
        for (T t : iterable) {
            if (t.equals(value)) {
                return t;
            }
        }
        return null;
    }
    
    /**
     * Method untuk mencari data dalam iterator
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param value		Any Value
     * @return value
     */
    public static <T> T find(Iterator<T> iterator, T value) {
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                return value;
            }
        }
        return null;
    }
    
    /**
     * Method untuk mencari data dalam array dengan predicate
     * @param <T>		Any Array
     * @param array		Array Value
     * @param pred		Any Predicate
     * @return value
     */
    public static <T> T find(T[] array, Predicate<T> pred) {
        for (T arrayValue : array) {
            if (pred.predicate(arrayValue)) {
                return arrayValue;
            }
        }
        return null;
    }
    
    /**
     * Method untuk mencari data dalam iterable dengan predicate
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param pred		Any Predicate
     * @return value
     */
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        for (T t : iterable) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }
    
    /**
     * Method untuk mencari data dalam iterator dengan predicate
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param pred		Any Predicate
     * @return value
     */
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (pred.predicate(value)) {
                return value;
            }
        }
        return null;
    }

    
    /**
     * Method untuk mencari nilai maksimal dengan comparable
     * @param <T>		Any Array
     * @param first		Nilai pertama
     * @param second	Nilai kedua
     * @return value
     */
    public static <T extends Comparable<? super T>> T max(T first, T second) {
        if ((first.compareTo(second)) > 0) {
            return first;
        } else {
            return second;
        }
    }
    
    /**
     * Method untuk mencari nilai maksimal dalam array
     * @param <T>		Any Array
     * @param array		Array Value
     * @return value
     */
    public static <T extends Comparable<? super T>> T max(T[] array) {
        T maximum = array[0];
        for (T t : array) {
            if (t.compareTo(maximum) > 0) {
                maximum = t;
            }
        }
        return maximum;
    }
    
    /**
     * Method untuk mencari nilai maksimal dalam iterable
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @return value
     */
    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable) {
        T maximum = iterable.iterator().next();
        for (T t : iterable) {
            if (t.compareTo(maximum) > 0) {
                maximum = t;
            }
        }
        return maximum;
    }
    
    /**
     * Method untuk mencari nilai maksimal dalam iterator
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @return value
     */
    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator) {
        T maximum = iterator.next();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (t.compareTo(maximum) > 0) {
                maximum = t;
            }
        }
        return maximum;
    }

    /**
     * Method untuk mencari nilai maksimal dengan comparator
     * @param <T>		Any Array
     * @param first		Nilai pertama
     * @param second	Nilai kedua
     * @param comparator	Comparator Value
     * @return value
     */
    public static <T> T max(T first, T second, Comparator<? super T> comparator) {
        if (comparator.compare(first, second) > 0) {
            return first;
        } else {
            return second;
        }
    }
    
    /**
     * Method untuk mencari nilai maksimal dengan comparator dalam array
     * @param <T>		Any Array
     * @param array		Array Value
     * @param comparator	Comparator Value
     * @return value
     */
    public static <T> T max(T[] array, Comparator<? super T> comparator) {
        T maximum = array[0];
        for (T t : array) {
            if (comparator.compare(t, maximum) > 0) {
                maximum = t;
            }
        }
        return maximum;
    }
    
    /**
     * Method untuk mencari nilai maksimal dengan comparator dalam iterable
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param comparator	Comparator Value
     * @return value
     */
    public static <T> T max(Iterable<T> iterable, Comparator<? super T> comparator) {
        T maximum = iterable.iterator().next();
        for (T t : iterable) {
            if (comparator.compare(t, maximum) > 0) {
                maximum = t;
            }
        }
        return maximum;
    }
    
    /**
     * Method untuk mencari nilai maksimal dengan comparator dalam iterator
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param comparator	Comparator Value
     * @return value
     */
    public static <T> T max(Iterator<T> iterator, Comparator<? super T> comparator) {
        T maximum = iterator.next();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (comparator.compare(t, maximum) > 0) {
                maximum = t;
            }
        }
        return maximum;
    }

    
    /**
     * Method untuk mencari nilai minimal dengan comparable
     * @param <T>		Any Array
     * @param first		Nilai pertama
     * @param second	Nilai Kedua
     * @return value
     */
    public static <T extends Comparable<? super T>> T min(T first, T second) {
        if ((first.compareTo(second)) > 0) {
            return second;
        } else {
            return first;
        }
    }
    
    
    /**
     * Method untuk mencari nilai minimal dalam array
     * @param <T>		Any Array
     * @param array		Array Value
     * @return value
     */
    public static <T extends Comparable<? super T>> T min(T[] array) {
        T minimum = array[0];
        for (T t : array) {
            if (t.compareTo(minimum) < 0) {
                minimum = t;
            }
        }
        return minimum;
    }
    
    /**
     * Method untuk mencari nilai minimal dalam iterable
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @return value
     */
    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable) {
        T minimum = iterable.iterator().next();
        for (T t : iterable) {
            if (t.compareTo(minimum) < 0) {
                minimum = t;
            }
        }
        return minimum;
    }
    
    
    /**
     * Method untuk mencari nilai minimal dalam iterator
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @return value
     */
    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator) {
        T minimum = iterator.next();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (t.compareTo(minimum) < 0) {
                minimum = t;
            }
        }
        return minimum;
    }

    /**
     * Method untuk mencari nilai minimum dengan comparator
     * @param <T>		Any Array
     * @param first		Nilai Pertama
     * @param second	Nilai Kedua
     * @param comparator	Comparator Value
     * @return value
     */
    public static <T> T min(T first, T second, Comparator<? super T> comparator) {
        if (comparator.compare(first, second) < 0) {
            return first;
        } else {
            return second;
        }
    }
    
    /**
     * Method untuk mencari nilai value dalam array dengan comparator
     * @param <T>		Any Array
     * @param array		Array Value
     * @param comparator	Comparator Value
     * @return value
     */
    public static <T> T min(T[] array, Comparator<? super T> comparator) {
        T minimum = array[0];
        for (T t : array) {
            if (comparator.compare(t, minimum) < 0) {
                minimum = t;
            }
        }
        return minimum;
        
    }
    /**
     * Method untuk mencari nilai value dalam iterable dengan comparator
     * @param <T>		Any Array
     * @param iterable	Iterable value
     * @param comparator	Comparator Value
     * @return value
     */
    public static <T> T min(Iterable<T> iterable, Comparator<? super T> comparator) {
        T minimum = iterable.iterator().next();
        for (T t : iterable) {
            if (comparator.compare(t, minimum) < 0) {
                minimum = t;
            }
        }
        return minimum;
    }
    
    /**
     * Method untuk mencari nilai value dalam iterator dengan comparator
     * @param <T>		Any Array
     * @param iterator	Iterator Value
     * @param comparator	Comparator Value
     * @return value
     */
    public static <T> T min(Iterator<T> iterator, Comparator<? super T> comparator) {
        T minimum = iterator.next();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (comparator.compare(t, minimum) < 0) {
                minimum = t;
            }
        }
        return minimum;
    }
    
    /**
     * Method untuk membuat paginasi dari list array yang sudah ada
     * @param <T>		Any Array
     * @param array		Array Value
     * @param page		Page yang diinta
     * @param pageSize	PageSize yang diminta
     * @param pred		Predicate yang digunakn
     * @return filteredList
     */
    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred){
        List<T> newList = new ArrayList<>();
        for(T t : array){
            newList.add(t);
        }
        try{
            List<T> filteredList = newList.stream().filter(pred::predicate).collect(Collectors.toList());
            int endIndex = (page * pageSize) + pageSize;
            if(endIndex > filteredList.size()){
                endIndex = filteredList.size();
            }
            return filteredList.subList((page * pageSize), endIndex);
        }catch (Exception e){
            return newList.subList(0 ,0);
        }
    }
    
    /**
     * Method untuk membuat paginasi dari list iterable yang sudah ada
     * @param <T>		Any Array
     * @param iterable	Nilai iterable
     * @param page		Page yang diminta
     * @param pageSize	PageSize yang diminta
     * @param pred		Predicate yang digunakan
     * @return filteredList
     */
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred){
        List<T> newList = new ArrayList<>();
        for(T t : iterable){
            newList.add(t);
        }
        try{
            List<T> filteredList = newList.stream().filter(pred::predicate).collect(Collectors.toList());
            int endIndex = (page * pageSize) + pageSize;
            if(endIndex > filteredList.size()){
                endIndex = filteredList.size();
            }
            return filteredList.subList((page * pageSize), endIndex);
        }catch (Exception e){
            return newList.subList(0 ,0);
        }
    }
    
    /**
     * Method untuk membuat paginasi dari list iterator yang sudah ada
     * @param <T>		Any Array
     * @param iterator	Iterator value
     * @param page		Page
     * @param pageSize	PageSize yang diminta
     * @param pred		Predicate yang digunakan
     * @return filteredList
     */
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred){
        List<T> newList = new ArrayList<>();
        while(iterator.hasNext()){
            newList.add(iterator.next());
        }
        try{
            List<T> filteredList = newList.stream().filter(pred::predicate).collect(Collectors.toList());
            int endIndex = (page * pageSize) + pageSize;
            if(endIndex > filteredList.size()){
                endIndex = filteredList.size();
            }
            return filteredList.subList((page * pageSize), endIndex);
        }catch (Exception e){
            return newList.subList(0 ,0);
        }
    }
}
