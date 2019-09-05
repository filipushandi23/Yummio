package com.example.yummio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        String dessertName = intent.getStringExtra("dessertName");
        textView = (TextView) findViewById(R.id.recipe_title);
        textView.setText(dessertName);
    }
}
