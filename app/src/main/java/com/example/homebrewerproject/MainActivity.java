package com.example.homebrewerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchRecipeCreator(View view) {
        Intent intent = new Intent(this, RecipeCreator.class);
        startActivity(intent);
    }

    public void launchRecipeList(View view) {
        Intent intent = new Intent(this, RecipeList.class);
        startActivity(intent);
    }

    public void launchABVCalculator(View view) {
        Intent intent = new Intent(this, ABVCalculator.class);
        startActivity(intent);
    }
}