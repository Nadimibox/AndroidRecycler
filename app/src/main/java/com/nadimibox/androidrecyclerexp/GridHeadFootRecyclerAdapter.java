package com.nadimibox.androidrecyclerexp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.nadimibox.androidrecycler.HeadFootRecyclerGridAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 26 August 2021
 * <p>
 * Description: ...
 */
public class GridHeadFootRecyclerAdapter extends HeadFootRecyclerGridAdapter<GridHeadFootRecyclerAdapter.Holder , String> {


    public GridHeadFootRecyclerAdapter(int columnNum) {
        this(new ArrayList<>() , columnNum);
    }

    public GridHeadFootRecyclerAdapter(List<String> data, int columnNum) {
        this(null , data, columnNum);
    }

    public GridHeadFootRecyclerAdapter(View header, List<String> data, int columnNum) {
        this(header , null, data, columnNum);
    }

    public GridHeadFootRecyclerAdapter(View header, @Nullable View footer, int columnNum) {
        this(header , footer, new ArrayList<>(), columnNum);
    }

    public GridHeadFootRecyclerAdapter(View header, View footer, List<String> data, int columnNum) {
        super(header, footer, data, columnNum);
    }

    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_adapter , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position, String data) {
        holder.textView.setText(data);
    }

    @Override
    public void onViewRecycle(Holder holder) {

    }

    protected static class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View v){
            super(v);
            textView = v.findViewById(R.id.text);
        }
    }
}
