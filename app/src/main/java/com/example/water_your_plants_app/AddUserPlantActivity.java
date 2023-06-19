package com.example.water_your_plants_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.water_your_plants_app.database.AppDatabase;
import com.example.water_your_plants_app.database.tables.UserPlant;

import java.util.List;

public class AddUserPlantActivity extends AppCompatActivity {

    Context context;
    Toolbar toolbar;
    AppDatabase db;
    AutoCompleteTextView autocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_plant);

        context = getApplicationContext();

        //setup toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.add_new_species);
        toolbar.setNavigationIcon(R.drawable.logowyp);
        toolbar.setTitle(R.string.add_new_plant);
        setSupportActionBar(toolbar);

        db = AppDatabase.getDatabase(context);

        autocomplete = findViewById(R.id.autoCompleteTextView);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        EditText plantNicknameTextView = findViewById(R.id.plantNicknameTextView);
        ImageButton addUserPlantButton = findViewById(R.id.addPlantButton);

        setUpAutocomplete();
        autocomplete.setOnFocusChangeListener((view14, b) -> autocomplete.showDropDown());

        buttonSubmit.setOnClickListener(view12 -> {
            int newPantID = db.dao_plant().checkIfPlantExistByName(autocomplete.getText().toString());

            if(newPantID==0){//plant does not exist in database
                Toast toast = Toast.makeText(context, "Plant species not found in database", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
            else {
                String name;
                if(plantNicknameTextView.getText().toString().equals("")){
                    name = autocomplete.getText().toString();
                }else{
                    name = plantNicknameTextView.getText().toString();
                }

                UserPlant userPlant = new UserPlant(newPantID, name);
                db.dao_userPlant().insertUserPlant(userPlant);
                finish();
            }
        });
        addUserPlantButton.setOnClickListener(view -> {
            Intent i = new Intent(context, AddPlantSpeciesActivity.class);
            Bundle b = new Bundle();
            b.putString( "speciesName", autocomplete.getText().toString() );
            i.putExtras(b);

            startActivity(i);
        });

    }
    public void setUpAutocomplete(){
        List<String> listDb = db.dao_plant().getAllPlantNames();
        String[] arr = listDb.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.select_dialog_item, arr);
        autocomplete.setThreshold(0);
        autocomplete.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpAutocomplete();
    }
}