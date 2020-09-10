package com.imdigitalashish.imagesearchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textView = findViewById(R.id.et_search);
    }

    public void search(View view) {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        intent.putExtra("SEARCH", textView.getText().toString());
        startActivity(intent);
    }
}