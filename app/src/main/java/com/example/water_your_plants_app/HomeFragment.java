package com.example.water_your_plants_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.water_your_plants_app.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    HomeViewAdapter homeViewAdapter;
    public RecyclerView recyclerView;
    public ArrayList<Task> taskArrayList = new ArrayList<>();
    public FragmentHomeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        initAdapter();
        taskArrayList.add(new Task(0));
        taskArrayList.add(new Task(1));
        homeViewAdapter.notifyItemRangeInserted(0,2);

        return root;
    }
    public void initAdapter() {
        homeViewAdapter = new HomeViewAdapter(taskArrayList, getActivity());
        recyclerView.setAdapter(homeViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}