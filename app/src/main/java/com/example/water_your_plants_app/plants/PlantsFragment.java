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
import com.example.water_your_plants_app.database.relations.UserPlantsWithTypes;
import com.example.water_your_plants_app.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class PlantsFragment extends Fragment {

    PlantsViewAdapter plantsViewAdapter;
    public RecyclerView recyclerView;
    public List<PlantListItem> plantsArrayList = new ArrayList<>();
    public FragmentHomeBinding binding;
    Context context;
    AppDatabase db;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        db = AppDatabase.getDatabase(context);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recyclerView);

        getAllPlants();
        initAdapter();

        return root;
    }
    public void initAdapter() {
        plantsViewAdapter = new PlantsViewAdapter(plantsArrayList, getActivity());
        recyclerView.setAdapter(plantsViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        plantsViewAdapter.notifyItemChanged(0,plantsArrayList.size());
    }
    void getAllPlants(){
        List<UserPlantsWithTypes> listDb = db.dao_userPlant().getAllUserPlantsWithTypes();
        for(int i = 0; i < listDb.size(); i ++){
            plantsArrayList.add(new PlantListItem(listDb.get(i)
            ));
        }
    }
}