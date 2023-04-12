package com.example.astronomy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApodRequest request = new ApodRequest("https://go-apod.herokuapp.com/apod", new ApodRequest.ApodCallback() {
            @Override
            public void onSuccess(String date, String description, String photoUrl) {
                // Update the UI with the APOD data
                TextView apodDescription = findViewById(R.id.apod_description);
                apodDescription.setText(description);

                ImageView apodImage = findViewById(R.id.apod_image);
                Picasso.get().load(photoUrl).into(apodImage);
            }


            @Override
            public void onError() {
                // Handle the error here
            }
        });

        request.execute();
    }
}
