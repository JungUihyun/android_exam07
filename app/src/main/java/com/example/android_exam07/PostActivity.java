package com.example.android_exam07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setTitle("글 목록");
    }
}
