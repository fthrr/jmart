package fathurJmartMR;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class JsonTable<T> extends Vector<T>{
	public final String filepath;
	private static final Gson gson = new Gson();
	
	public JsonTable (Class<T> clazz, String filepath) throws IOException {
		this.filepath = filepath;
		try {
			Class<T[]> array = (Class<T[]>) Array.newInstance(clazz,0).getClass();
		    T[] result = JsonTable.readJson(array,this.filepath);
		    if(result != null) {
		    	Collections.addAll(this,result);
		    }
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static <T> T readJson (Class<T> clazz, String filepath) throws FileNotFoundException{
		 T t = null;
		 try {
			 final JsonReader read = new JsonReader(new FileReader(filepath));
			 t = gson.fromJson(read, clazz);
		 }
		 catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }
		 return t;
	}
	
	public void writeJson() throws IOException{
		writeJson(this, this.filepath);
	}
	
	public void writeJson(Object object, String filepath) throws IOException{
		try{
			final FileWriter fileWriter = new FileWriter(filepath, true);
			fileWriter.write(gson.toJson(object));
			fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
	}
}
