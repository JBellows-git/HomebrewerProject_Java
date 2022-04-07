package com.example.homebrewerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeCreator<adapter> extends AppCompatActivity {

    EditText txtRecipeTitle;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText txtLbsHoney;
    TextView txtStartingGravity;
    Spinner spinnerIngredients1;
    EditText etmlNotes;
    Button btnAdd;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_creator);

        txtRecipeTitle = findViewById(R.id.txtRecipeTitle);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        txtLbsHoney = (EditText) findViewById(R.id.txtLbsHoney);
        txtStartingGravity = (TextView) findViewById(R.id.txtStartingGravity);
        spinnerIngredients1 = findViewById(R.id.spinnerIngredients1);
        etmlNotes = findViewById(R.id.etmlNotes);
        btnAdd = findViewById(R.id.btnAdd);
        btnHome = findViewById(R.id.btnHome);
        final int[] gallonMultiplier = new int[1];



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecipeModel recipe;
                try {
                    recipe = new RecipeModel(-1, txtRecipeTitle.getText().toString(), spinnerIngredients1.getSelectedItem().toString(),
                            Double.parseDouble(txtLbsHoney.getText().toString()), Double.parseDouble(txtStartingGravity.getText().toString()),
                            gallonMultiplier[0], etmlNotes.getText().toString());
                    DBHelper dataBaseHelper = new DBHelper(RecipeCreator.this);
                    dataBaseHelper.addOne(recipe);
                    Toast.makeText(RecipeCreator.this, "Recipe Added", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText( RecipeCreator.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    recipe = new RecipeModel(-1, "error", "error", 0.1, 0.1, -1, "error");
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                switch(checkedID){
                    case R.id.radGallon:
                        gallonMultiplier[0] = 1;
                        break;

                    case R.id.radFiveGallon:
                        gallonMultiplier[0] = 5;
                        break;
                }
            }
        });

        //create list of items for the spinner
        String[] ingredients = new String[]{"None/Traditional", "Strawberries", "Blueberries", "Raspberries", "Blackberries"};

        //creates adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ingredients);
        spinnerIngredients1.setAdapter(adapter);

        txtLbsHoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {

                    String honeyPounds = txtLbsHoney.getText().toString();
                    double numHoneyPounds = Double.parseDouble(honeyPounds);
                    double gravityCalc = ((numHoneyPounds * 0.035) / gallonMultiplier[0]) +1;
                    String results = String.format("%.3f", gravityCalc);
                    txtStartingGravity.setText(results);
                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void launchHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}