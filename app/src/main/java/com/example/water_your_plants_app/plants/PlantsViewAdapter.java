package com.example.water_your_plants_app.plants;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.water_your_plants_app.R;
import com.example.water_your_plants_app.database.relations.UserPlantsWithTypes;

import java.util.List;

public class PlantsViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PlantListItem> mItemList;
    private Context context;
    private final Activity myActivity;

    public PlantsViewAdapter(List<PlantListItem> itemList, Activity activity) {
        mItemList = itemList;
        myActivity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.plants_list_item, parent, false);
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

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        Button buttonPlantItem;
        LinearLayout linear;
        TextView plantName;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonPlantItem = itemView.findViewById(R.id.buttonPlantItem);
            linear = itemView.findViewById(R.id.linear);
            plantName = itemView.findViewById(R.id.plantName);
        }
    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        UserPlantsWithTypes userPlantsWithTypes = mItemList.get(position).userPlantsWithTypes;

        viewHolder.buttonPlantItem.setText(userPlantsWithTypes.userPlant.plantNickname);

        viewHolder.linear.setVisibility(View.GONE);
        viewHolder.buttonPlantItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_down_24, 0);

        viewHolder.plantName.setText( userPlantsWithTypes.plantType.typeName + " : " + userPlantsWithTypes.plant.plantName);

        viewHolder.buttonPlantItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mItemList.get(position).isExpanded()){
                    viewHolder.linear.setVisibility(View.GONE);
                    viewHolder.buttonPlantItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_down_24, 0);
                    mItemList.get(position).setExpanded(false);
                }else{
                    viewHolder.linear.setVisibility(View.VISIBLE);
                    viewHolder.buttonPlantItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_up_24, 0);
                    mItemList.get(position).setExpanded(true);
                }
            }
        });
    }
}
