package com.pburkhardt.inventory_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    Context context;    //used for inflater
    List<inventoryItemModel> itemInventoryList;

    ///// CONSTRUCTOR /////
    public RecyclerViewAdapter(Context context, List<inventoryItemModel> invItemList) {
        this.context = context;
        this.itemInventoryList = invItemList;
    }

    @NonNull
    @Override
    // gets the layout for the rows
    public RecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.inventory_row, parent, false);

        return new RecyclerViewAdapter.viewHolder(view);
    }

    @Override
    // binds data to the views/cards
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.itemName.setText(itemInventoryList.get(position).getItemName());
        holder.itemCount.setText(String.valueOf(itemInventoryList.get(position).getItemCount()));
    }

    @Override
    public int getItemCount() {
        return this.itemInventoryList.size();
    }

    // gets and holds the cardView/row
    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView itemName;
        ImageView subButton;
        TextView itemCount;
        ImageView addButton;
        ImageView trashButton;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemText);
            subButton = itemView.findViewById(R.id.subtractButton);
            itemCount = itemView.findViewById(R.id.itemCount);
            addButton = itemView.findViewById(R.id.additionButton);
            trashButton = itemView.findViewById(R.id.deleteItemButton);
        }
    }
}
