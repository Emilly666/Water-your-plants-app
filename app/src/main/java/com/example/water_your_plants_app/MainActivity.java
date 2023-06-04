package com.example.water_your_plants_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.water_your_plants_app.database.AppDatabase;
import com.example.water_your_plants_app.database.tables.Plant;
import com.example.water_your_plants_app.database.tables.PlantType;
import com.example.water_your_plants_app.database.tables.UserPlant;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter adapter;
    Toolbar toolbar;
    Context context;

    private int selectedTabIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab);
        viewPager2 = findViewById(R.id.viewPager2);
        adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.logowyp);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
            }
        });
        if (savedInstanceState != null) {
            selectedTabIndex = savedInstanceState.getInt("selectedTabIndex", 1); // Default index is 1
            Objects.requireNonNull(tabLayout.getTabAt(selectedTabIndex)).select();
        }else{
            Objects.requireNonNull(tabLayout.getTabAt(1));
            viewPager2.setCurrentItem(1,false);
        }
        //context.deleteDatabase("PLANTS_DATABASE.db");
        AppDatabase db = AppDatabase.getDatabase(context);

        /* //sample data
        db.dao_plantType().insertPlantType(new PlantType("type1", "some soil", "some fertilizer"));
        db.dao_plant().insertPlant(new Plant(1, "nice plant 1", 20, 30, "nice light", 30, 4, 20));
        db.dao_userPlant().insertUserPlant(new UserPlant(1, "my nice plant 1"));
        db.dao_plantType().insertPlantType(new PlantType("type2", "some soil", "some fertilizer"));
        db.dao_plant().insertPlant(new Plant(2, "nice plant 2", 20, 30, "nice light", 30, 4, 20));
        db.dao_userPlant().insertUserPlant(new UserPlant(2, "my nice plant 2"));
        */
        //db.dao_plantType().deletePlantType(db.dao_plantType().getPlantTypeById(1));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.settings) {
            Toast.makeText(getApplicationContext(),"open settings", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedTabIndex", selectedTabIndex);
    }
}