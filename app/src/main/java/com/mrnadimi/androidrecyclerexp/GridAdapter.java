package com.mrnadimi.androidrecyclerexp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mrnadimi.androidrecycler.GridRecyclerAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 30 July 2021
 * <p>
 * Description: ...
 */
public class GridAdapter extends GridRecyclerAdapter<GridAdapter.Holder, String> {


    public GridAdapter(int columnNum) {
        super(columnNum);
    }

    @NonNull
    @Override
    public @NotNull GridAdapter.Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view , null , false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        holder.textView.setText(getData(position));
    }

    @Override
    public void onViewRecycled(@NonNull @NotNull Holder holder) {

    }

    public static class Holder extends RecyclerView.ViewHolder{

        TextView textView;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.dddd);
        }
    }
}
