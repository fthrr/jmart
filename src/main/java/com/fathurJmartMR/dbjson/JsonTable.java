package com.fathurJmartMR.dbjson;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

/**
 * List with functionality to be written as / loaded from a JSON Array
 * @author Netlab Team
 * @version 0.1
 * @param <T> Type of component elements (may translated as JSON Object)
 */

@SuppressWarnings("serial")
public class JsonTable<T> extends Vector<T>
{
	private static final Gson gson = new Gson();
    public final String filepath;
    
    @SuppressWarnings("unchecked")
    public JsonTable(Class<T> clazz, String filepath) throws IOException
    {
    	this.filepath = filepath;
        try{
            Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
            T[] loaded = readJson(arrayType, filepath);
            if (loaded != null) {
                Collections.addAll(this, loaded);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            File f = new File(filepath);
            File fParent = f.getParentFile();
            if(fParent != null){
                fParent.mkdirs();
            }
            f.createNewFile();
        }
    }
    
    public void writeJson() throws IOException
    {
    	writeJson(this, this.filepath);
    }

    public static void writeJson(Object object, String filepath) throws IOException
    {
    	FileWriter writer = new FileWriter(filepath);
        writer.write(gson.toJson(object));
        writer.close();
    }

    public static <T> T readJson(Class<T> clazz, String filepath) throws FileNotFoundException
    {
    	JsonReader fReader = new JsonReader(new FileReader(filepath));
        return gson.fromJson(fReader, clazz);
    }
}