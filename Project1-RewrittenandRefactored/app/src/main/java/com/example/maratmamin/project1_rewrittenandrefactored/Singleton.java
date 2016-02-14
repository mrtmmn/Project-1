package com.example.maratmamin.project1_rewrittenandrefactored;

import java.util.ArrayList;

/**
 * Created by maratmamin on 2/14/16.
 */
public class Singleton {

    ArrayList<String> mainToDo;
    ArrayList<ArrayList<String>> listOfToDos;

    private static Singleton ourInstance = null;
    //private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        if(ourInstance == null) {
            ourInstance = new Singleton();
        }
        return ourInstance;
    }

    private Singleton() {

    }

    public int findIndex(String item) {
        int index = -1;
        int count = 0;

        for (String strItem : mainToDo) {
            if(strItem.equals(item)){
                break;
            }

            count++;
        }

        return index;

    }
}
