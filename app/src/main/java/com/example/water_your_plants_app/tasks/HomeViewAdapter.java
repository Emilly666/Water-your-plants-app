package com.example.water_your_plants_app.tasks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.water_your_plants_app.R;

import java.util.List;

public class HomeViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM_WATER = 0;
    private final int VIEW_TYPE_ITEM_FERTILIZE = 1;
    private List<Task> mItemList;
    private Context context;
    private final Activity myActivity;

    public HomeViewAdapter(List<Task> itemList, Activity activity) {
        mItemList = itemList;
        myActivity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        populateItemRows((ItemViewHolder) holder, position);
    }

    // getItemCount() method returns the size of the list
    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    public int getItemViewType(int position) {
        return mItemList.get(position).getType();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        Button button4;
        LinearLayout linear;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            button4 = itemView.findViewById(R.id.buttonHomeListItem);
            linear = itemView.findViewById(R.id.plantDescription);

        }
    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {

        viewHolder.linear.setVisibility(View.GONE);

        int icon;
        if(mItemList.get(position).getType() == VIEW_TYPE_ITEM_WATER){
            icon = R.drawable.watering_can_24;
        }else{
            icon = R.drawable.fertilizer_24;
        }

        viewHolder.button4.setCompoundDrawablesWithIntrinsicBounds(icon, 0, R.drawable.baseline_arrow_drop_down_24, 0);
        viewHolder.button4.setOnClickListener(view -> {
            if(mItemList.get(position).isExpanded()){
                viewHolder.linear.setVisibility(View.GONE);
                viewHolder.button4.setCompoundDrawablesWithIntrinsicBounds(icon, 0, R.drawable.baseline_arrow_drop_down_24, 0);
                mItemList.get(position).setExpanded(false);
            }else{
                viewHolder.linear.setVisibility(View.VISIBLE);
                viewHolder.button4.setCompoundDrawablesWithIntrinsicBounds(icon, 0, R.drawable.baseline_arrow_drop_up_24, 0);
                mItemList.get(position).setExpanded(true);
            }
        });
    }
}
