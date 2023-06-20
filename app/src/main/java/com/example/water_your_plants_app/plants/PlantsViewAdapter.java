package com.example.water_your_plants_app.plants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.water_your_plants_app.AddUserPlantActivity;
import com.example.water_your_plants_app.R;
import com.example.water_your_plants_app.database.AppDatabase;
import com.example.water_your_plants_app.database.relations.UserPlantsWithTypes;

import java.util.List;

public class PlantsViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private List<PlantListItem> mItemList;
    private Context context;
    private final Activity myActivity;
    AppDatabase db;

    public PlantsViewAdapter(List<PlantListItem> itemList, Activity activity) {
        mItemList = itemList;
        myActivity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.plants_list_item, parent, false);
            return new ItemViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.add_user_plant_button, parent, false);
            return new AddButtonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) holder, position);
        } else if (holder instanceof AddButtonViewHolder) {
            addUserPlantItem((AddButtonViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }
    public int getItemViewType(int position) {
        int VIEW_TYPE_ADD_BUTTON = 1;
        return mItemList.get(position) == null ? VIEW_TYPE_ADD_BUTTON : VIEW_TYPE_ITEM;
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        Button buttonPlantItem;
        ConstraintLayout plantDescription;
        TextView plantName, typeName, temperature, light, humidity, soil, fertilizer, waterFrequency;
        ImageView typeIcon, vertMenu;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonPlantItem = itemView.findViewById(R.id.buttonPlantItem);
            plantDescription = itemView.findViewById(R.id.plantDescription);
            plantName = itemView.findViewById(R.id.spiecesName);
            typeName = itemView.findViewById(R.id.typeName);
            temperature = itemView.findViewById(R.id.temperature);
            light = itemView.findViewById(R.id.light);
            humidity = itemView.findViewById(R.id.humidity);
            soil = itemView.findViewById(R.id.soil);
            fertilizer = itemView.findViewById(R.id.fertilizer);
            waterFrequency = itemView.findViewById(R.id.waterFrequency);
            typeIcon = itemView.findViewById(R.id.typeIcon);
            vertMenu = itemView.findViewById(R.id.vertMenu);
        }
    }
    public static class AddButtonViewHolder extends RecyclerView.ViewHolder {
        Button addUserPlantButton;
        public AddButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            addUserPlantButton = itemView.findViewById(R.id.addUserPlantButton);
        }
    }
    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        UserPlantsWithTypes userPlant = mItemList.get(position).userPlantsWithTypes;

        viewHolder.plantDescription.setVisibility(View.GONE);
        viewHolder.buttonPlantItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_down_24, 0);

        viewHolder.buttonPlantItem.setOnClickListener(view -> {
            if(mItemList.get(position).isExpanded()){
                viewHolder.plantDescription.setVisibility(View.GONE);
                viewHolder.buttonPlantItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_down_24, 0);
                mItemList.get(position).setExpanded(false);
            }else{
                viewHolder.plantDescription.setVisibility(View.VISIBLE);
                viewHolder.buttonPlantItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_up_24, 0);
                mItemList.get(position).setExpanded(true);
            }
        });

        viewHolder.buttonPlantItem.setText(userPlant.userPlant.plantNickname);
        viewHolder.plantName.setText(userPlant.plant.plantName);
        viewHolder.typeName.setText(userPlant.plantType.typeName);
        viewHolder.light.setText(userPlant.plant.light);
        viewHolder.soil.setText(userPlant.plantType.soil);

        viewHolder.humidity.setText( String.format(context.getString(R.string.hum), userPlant.plant.humidity) );
        if(userPlant.plant.temperatureFrom == userPlant.plant.temperatureTo){
            viewHolder.temperature.setText( String.format(context.getString(R.string.temp), userPlant.plant.temperatureFrom) );
        }
        else{
            viewHolder.temperature.setText( String.format(context.getString(R.string.temp2), userPlant.plant.temperatureFrom, userPlant.plant.temperatureTo) );
        }
        viewHolder.fertilizer.setText( String.format(context.getResources().getQuantityString(R.plurals.fertilizeEvery, userPlant.plant.fertilizerFrequency, userPlant.plant.fertilizerFrequency)) );
        viewHolder.waterFrequency.setText( String.format(context.getResources().getQuantityString(R.plurals.waterEvery, userPlant.plant.wateringFrequency, userPlant.plant.fertilizerFrequency)) );

        int icon;
        switch ((int) userPlant.plantType.type_id){ // placeholders icons
            case 1:
                icon = R.drawable.baseline_settings_24;
                break;
            case 2:
                icon = R.drawable.home_24;
                break;
            default:
                icon = R.drawable.local_florist_24;
        }
        viewHolder.typeIcon.setBackgroundResource(icon);
        viewHolder.vertMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(context, viewHolder.vertMenu);
                menu.inflate(R.menu.plant_item_popup_menu);
                db = AppDatabase.getDatabase(context);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_delete) {
                            db.dao_userPlant().deleteUserPlantWitTypes((int)userPlant.userPlant.userPlant_id);
                            mItemList.remove(position);
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    menu.setForceShowIcon(true);
                }
                menu.show();
            }
        });
    }
    private void addUserPlantItem(AddButtonViewHolder viewHolder, int position) {
        viewHolder.addUserPlantButton.setOnClickListener(view -> {
            Intent i = new Intent(context, AddUserPlantActivity.class);
            context.startActivity(i);
        });

    }
}
