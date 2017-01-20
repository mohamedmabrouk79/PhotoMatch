package com.example.mohamedmabrouk.photomatch;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WinActivity extends AppCompatActivity {
    public  static String ID="com.example.mohamedmabrouk.photomatch.id";
    private TextView textView ;
    private int count=0;
    private LevelsCount levelsCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        textView=(TextView)findViewById(R.id.wrong);
        Bundle bundle=getIntent().getExtras();
        levelsCount=new LevelsCount();
        count=levelsCount.Read(this);
        levelsCount.write(count++,this);
        Toast.makeText(WinActivity.this, count+"   dddddddd", Toast.LENGTH_SHORT).show();

        textView.setText("Number of Wrong Moves  = " + bundle.getString(ID));
    }

    public void next(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
