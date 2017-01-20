package com.example.mohamedmabrouk.photomatch;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Mohamed Mabrouk on 03/07/2016.
 */
public class LevelsCount {
    //*********************** write next  level  into file //
    public void write(int count_level, Context  context){
        try {

            FileOutputStream fOut =context.openFileOutput("levels.txt",context.MODE_PRIVATE);
            OutputStreamWriter writer=new OutputStreamWriter(fOut);
            writer.write(count_level);
            writer.close();
        }catch (Exception e){
        }
    }

    //*************** read current  level from file //
    public int Read(Context context){
        int count=0;
        try {
            FileInputStream fileInputStream=context.openFileInput("levels.txt");
    if (fileInputStream!=null){
        InputStreamReader reader=new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader=new BufferedReader(reader);
        String line;
        while ((line=bufferedReader.readLine())!=null){
         count=Integer.parseInt(line);
        }

    }

        }catch (Exception e){

        }
        return count;
    }

}
