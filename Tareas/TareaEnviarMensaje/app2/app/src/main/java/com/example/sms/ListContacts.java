package com.example.sms;

import com.google.android.material.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

/** A simple two line list item. */
public class ListContacts extends SingleLineItemViewHolder {

    public final TextView secondary;

    public TwoLineItemViewHolder(@NonNull View view) {
        super(view);
        this.secondary = itemView.findViewById(R.id.mtrl_list_item_secondary_text);
    }

    @NonNull
    public static TwoLineItemViewHolder create(@NonNull ViewGroup parent) {
        return new TwoLineItemViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout., parent, false));
    }
}