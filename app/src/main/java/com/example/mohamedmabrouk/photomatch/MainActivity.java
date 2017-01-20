package com.example.mohamedmabrouk.photomatch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.Fragment_Container);
        if(fragment==null){
            fragment=new PhotoFragment();
            fragmentManager.beginTransaction().add(R.id.Fragment_Container,fragment).commit();
        }
    }
}
