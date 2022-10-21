package com.pburkhardt.inventory_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    Context context;    //used for inflater
    List<inventoryItemModel> itemInventoryList;
    private onItemClickListener itemListener;
    private onFocusChangeListener focusListener;

    public interface onItemClickListener {
        void onItemClick(int itemPos);
        void deleteItem(int itemPos);
        void incrementItemCount(int itemPos);
        void decrementItemCount(int itemPos);
    }

    public interface onFocusChangeListener {
        void itemCountFocusUpdate(int itemPos, int newCount);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        itemListener = listener;
    }

    public void setOnFocusChangeListener(onFocusChangeListener listener) {
        focusListener = listener;
    }

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

        return new RecyclerViewAdapter.viewHolder(view, itemListener, focusListener);
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
        EditText itemCount;
        ImageView addButton;
        ImageView trashButton;

        public viewHolder(@NonNull View itemView, onItemClickListener listener, onFocusChangeListener fListener) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemText);
            subButton = itemView.findViewById(R.id.subtractButton);
            itemCount = itemView.findViewById(R.id.itemCount);
            addButton = itemView.findViewById(R.id.additionButton);
            trashButton = itemView.findViewById(R.id.deleteItemButton);

            // LISTENERS FOR USER/CARD INTERACTIONS //

            //clicking on a card
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int itemPos = getAdapterPosition();
                        if (itemPos != RecyclerView.NO_POSITION) {
                            itemCount.clearFocus();
                            listener.onItemClick(itemPos);
                        }
                    }
                }
            });

            //trash/delete button
            trashButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int itemPos = getAdapterPosition();
                        if (itemPos != RecyclerView.NO_POSITION) {
                            itemCount.clearFocus();
                            listener.deleteItem(itemPos);
                        }
                    }
                }
            });

            // + count increment button
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int itemPos = getAdapterPosition();
                        if (itemPos != RecyclerView.NO_POSITION) {
                            itemCount.clearFocus();
                            listener.incrementItemCount(itemPos);
                        }
                    }
                }
            });

            // - count decrement button
            subButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int itemPos = getAdapterPosition();
                        if (itemPos != RecyclerView.NO_POSITION) {
                            itemCount.clearFocus();
                            listener.decrementItemCount(itemPos);
                        }
                    }
                }
            });

            //updating the counts of items via the editText field
            itemCount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b && fListener != null) {
                        int itemPos = getAdapterPosition();
                        if (itemPos != RecyclerView.NO_POSITION) {
                            fListener.itemCountFocusUpdate(itemPos, Integer.parseInt(itemCount.getText().toString()));
                            itemCount.clearFocus();
                        }

                    }
                }
            });

        }
    }
}
