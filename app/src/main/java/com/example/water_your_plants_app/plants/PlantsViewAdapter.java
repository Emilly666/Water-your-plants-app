package com.example.water_your_plants_app.plants;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.water_your_plants_app.AddUserPlantActivity;
import com.example.water_your_plants_app.R;
import com.example.water_your_plants_app.database.AppDatabase;
import com.example.water_your_plants_app.database.relations.UserPlantsWithTypes;
import com.example.water_your_plants_app.database.tables.UserPlant;

import java.util.List;

public class PlantsViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
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
    public class AddButtonViewHolder extends RecyclerView.ViewHolder {
        Button addUserPlantButton;
        public AddButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            addUserPlantButton = itemView.findViewById(R.id.addUserPlantButton);
        }
    }
    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        UserPlantsWithTypes userPlant = mItemList.get(position).userPlantsWithTypes;

        viewHolder.buttonPlantItem.setText(userPlant.userPlant.plantNickname);

        viewHolder.linear.setVisibility(View.GONE);
        viewHolder.buttonPlantItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_down_24, 0);

        viewHolder.plantName.setText( userPlant.plantType.typeName + " : " + userPlant.plant.plantName);

        viewHolder.buttonPlantItem.setOnClickListener(view -> {
            if(mItemList.get(position).isExpanded()){
                viewHolder.linear.setVisibility(View.GONE);
                viewHolder.buttonPlantItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_down_24, 0);
                mItemList.get(position).setExpanded(false);
            }else{
                viewHolder.linear.setVisibility(View.VISIBLE);
                viewHolder.buttonPlantItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_up_24, 0);
                mItemList.get(position).setExpanded(true);
            }
        });
    }
    private void addUserPlantItem(AddButtonViewHolder viewHolder, int position) {
        AppDatabase db = AppDatabase.getDatabase(context);

        List<String> listDb = db.dao_plant().getAllPlantNames();
        String[] arr = listDb.toArray(new String[0]);


        viewHolder.addUserPlantButton.setOnClickListener(view -> {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.add_user_plant_popup_layout);

            AutoCompleteTextView autocomplete = dialog.findViewById(R.id.autoCompleteTextView);
            ImageView closeButton = dialog.findViewById(R.id.closeButton);
            Button buttonSubmit = dialog.findViewById(R.id.buttonSubmit);
            EditText plantNicknameTextView = dialog.findViewById(R.id.plantNicknameTextView);
            ImageButton addUserPlantButton = dialog.findViewById(R.id.addPlantButton);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.select_dialog_item, arr);
            autocomplete.setThreshold(0);
            autocomplete.setAdapter(adapter);

            autocomplete.setOnFocusChangeListener((view14, b) -> autocomplete.showDropDown());

            closeButton.setOnClickListener(view1 -> dialog.dismiss());

            buttonSubmit.setOnClickListener(view12 -> {
                //add userPlant here
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

                    mItemList.remove(mItemList.size()-1);
                    UserPlantsWithTypes userPlantsWithTypes = db.dao_userPlant().getLatestUserPlantWithType();
                    mItemList.add(new PlantListItem(userPlantsWithTypes));
                    mItemList.add(null);

                    dialog.dismiss();
                }
            });
            addUserPlantButton.setOnClickListener(view13 -> {
                Intent i = new Intent(myActivity, AddUserPlantActivity.class);
                Bundle b = new Bundle();
                b.putString("speciesName", autocomplete.getText().toString()    );
                i.putExtras(b);
                myActivity.startActivity(i);
            });
            dialog.show();
        });
    }
}
