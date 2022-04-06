package com.mrnadimi.androidrecyclerexp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mrnadimi.androidrecycler.LinearRecycleAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 30 July 2021
 * <p>
 * Description: ...
 */
public class LinearAdapter extends LinearRecycleAdapter<LinearAdapter.Holder, String> {

    public LinearAdapter() {
    }

    public LinearAdapter(List<String> data) {
        super(data);
    }

    @NonNull
    @Override
    public @NotNull LinearAdapter.Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_adapter , parent , false));
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

        public Holder(View v){
            super(v);
            textView = v.findViewById(R.id.text);
        }

    }

}
