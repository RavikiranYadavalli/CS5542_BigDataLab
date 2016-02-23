package com.example.android.myapplication_maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

        Button button_map, button_photo,button_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        button_map = (Button) findViewById(R.id.mapbutton);
        button_photo = (Button) findViewById(R.id.photobutton);
        button_home=(Button) findViewById(R.id.homebutton);
    }


    public void captures(View v)
    {
        Intent redirect = new Intent(Home.this, photo.class);
        startActivity(redirect);

    }
    public void homes(View v1)
    {
        Intent redirect = new Intent(Home.this, MainActivity.class);
        startActivity(redirect);

    }
    public void mymaps(View v1)
    {
        Intent redirect = new Intent(Home.this, maps.class);
        startActivity(redirect);

    }

}


