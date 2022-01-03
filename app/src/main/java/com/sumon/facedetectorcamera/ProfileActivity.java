package com.sumon.facedetectorcamera;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    private Button btnChangePhoto;
    private ImageView imgProfile;

    public static final int PROFILE_PHOTO_TAKE_REQUEST_CODE = 1211;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Profile");
        }
        
        imgProfile = findViewById(R.id.imgProfile);
        btnChangePhoto = findViewById(R.id.btnChangePhoto);

        btnChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ImagePicker.Companion.with(ProfileActivity.this)
                        .cropSquare()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();*/

                Intent intent = new Intent(ProfileActivity.this, PhotoActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, PROFILE_PHOTO_TAKE_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: main ");
        if (requestCode == PROFILE_PHOTO_TAKE_REQUEST_CODE && resultCode == RESULT_OK) {
            String photoUri = data.getStringExtra("PHOTO_URI");
            Glide.with(this).load(photoUri).into(imgProfile);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}