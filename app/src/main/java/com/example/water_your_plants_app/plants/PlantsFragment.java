package com.example.water_your_plants_app.plants;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.water_your_plants_app.R;
import com.example.water_your_plants_app.database.AppDatabase;
import com.example.water_your_plants_app.database.tables.Table_Plant;
import com.example.water_your_plants_app.database.tables.Table_PlantType;
import com.example.water_your_plants_app.database.tables.Table_UserPlant;
import com.example.water_your_plants_app.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class PlantsFragment extends Fragment {

    PlantsViewAdapter plantsViewAdapter;
    public RecyclerView recyclerView;
    public ArrayList<Plant> plantsArrayList;
    public FragmentHomeBinding binding;
    Context context;
    AppDatabase db;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        db = AppDatabase.getDatabase(context);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        plantsArrayList = getAllPlants();

        initAdapter();

        return root;
    }
    public void initAdapter() {
        plantsViewAdapter = new PlantsViewAdapter(plantsArrayList, getActivity());
        recyclerView.setAdapter(plantsViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    ArrayList<Plant> getAllPlants(){
        List<Table_UserPlant> listDb = db.dao_userPlant().getAllUserPlants();

        ArrayList<Plant> list = new ArrayList<>();
        for(int i = 0; i < listDb.size(); i++){
            Plant plant = new Plant(listDb.get(i).plantNickname);
            list.add(plant);
        }
        return list;
    }
}