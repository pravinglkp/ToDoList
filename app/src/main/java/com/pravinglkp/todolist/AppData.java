package com.pravinglkp.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AppData {

    static final String FILENAME="appdata.dat";
    public static void storeData(ArrayList<String> list, Context context){

        try {
            FileOutputStream fos =context.openFileOutput(FILENAME,Context.MODE_PRIVATE);
            ObjectOutputStream oos =new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static  ArrayList<String> retriveData(Context context){
        ArrayList<String> list = null;

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list=(ArrayList<String>)ois.readObject();
        } catch (FileNotFoundException e) {
            list= new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            list= new ArrayList<>();
            e.printStackTrace();
        }


        return list;
    }
}
