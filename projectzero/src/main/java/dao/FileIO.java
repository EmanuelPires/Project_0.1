package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileIO<T> {
	
	private String filename;
	
	
	public FileIO(String filename) {
		this.filename=filename;
	}
	
	
	public void writeObjects(ArrayList<T> objectList) {
		try(ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(filename));){
			out.writeObject(objectList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<T> readObjects()throws IOException, FileNotFoundException, ClassNotFoundException{
		ArrayList<T> ret;
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
		ret = (ArrayList<T>) in.readObject();
		return ret;
	}

}
