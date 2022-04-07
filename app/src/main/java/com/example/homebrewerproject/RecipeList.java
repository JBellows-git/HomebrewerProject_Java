package com.example.homebrewerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class RecipeList extends AppCompatActivity {

    ListView lvRecipeList;
    DBHelper dataBaseHelper;
    ArrayAdapter recipeArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        lvRecipeList = findViewById(R.id.lvRecipeList);

        dataBaseHelper = new DBHelper(RecipeList.this);

        showList();

        lvRecipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecipeModel clickedRecipe = (RecipeModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedRecipe);
                showList();
            }
        });
    }

    private void showList() {
        recipeArrayAdapter = new ArrayAdapter<RecipeModel>(RecipeList.this, android.R.layout.simple_list_item_1, dataBaseHelper.selectAll());
        lvRecipeList.setAdapter(recipeArrayAdapter);
    }
}