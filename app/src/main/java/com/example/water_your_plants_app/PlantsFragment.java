package com.example.water_your_plants_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.water_your_plants_app.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class PlantsFragment extends Fragment {

    PlantsViewAdapter plantsViewAdapter;
    public RecyclerView recyclerView;
    public ArrayList<Plant> plantsArrayList = new ArrayList<>();
    public FragmentHomeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        initAdapter();
        plantsArrayList.add(new Plant());
        plantsArrayList.add(new Plant());
        plantsViewAdapter.notifyItemRangeInserted(0,2);

        return root;
    }
    public void initAdapter() {
        plantsViewAdapter = new PlantsViewAdapter(plantsArrayList, getActivity());
        recyclerView.setAdapter(plantsViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}