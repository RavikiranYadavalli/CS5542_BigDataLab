package com.example.android.myapplication_maps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by dines on 2/10/2016.
 */
public class photo extends AppCompatActivity{


    int TAKE_PHOTO_CODE = 1991;
    ImageView b3;
    Button b4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        b3=(ImageView)findViewById(R.id.viewphoto);
        b4=(Button)findViewById(R.id.homebutton);
    }
    public void takephotos(View v) {

        Button b2 = (Button) findViewById(R.id.buttonphoto);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PHOTO_CODE);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== TAKE_PHOTO_CODE && resultCode==RESULT_OK)
        {
            Bitmap photo=(Bitmap) data.getExtras().get("data");
            b3.setImageBitmap(photo);
            Log.d("Image successful ", "success");
        }


    }

    public void homeclick(View v)
    {
        Intent redirect = new Intent(photo.this, Home.class);
        startActivity(redirect);

    }

}