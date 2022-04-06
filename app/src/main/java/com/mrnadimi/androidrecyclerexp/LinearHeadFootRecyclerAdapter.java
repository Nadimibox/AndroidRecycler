package com.mrnadimi.androidrecyclerexp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrnadimi.androidrecycler.HeadFootRecyclerLinearAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 26 August 2021
 * <p>
 * Description: ...
 */
public class LinearHeadFootRecyclerAdapter extends HeadFootRecyclerLinearAdapter<LinearHeadFootRecyclerAdapter.Holder, String> {


    public LinearHeadFootRecyclerAdapter() {
    }

    public LinearHeadFootRecyclerAdapter(List<String> data) {
        super(data);
    }

    public LinearHeadFootRecyclerAdapter(View header) {
        super(header);
    }

    public LinearHeadFootRecyclerAdapter(View header, View footer) {
        super(header, footer);
    }

    public LinearHeadFootRecyclerAdapter(View header, View footer, @NonNull @NotNull List<String> data) {
        super(header, footer, data);
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


    public static class Holder extends RecyclerView.ViewHolder{

        TextView textView;

        public Holder(View v){
            super(v);
            textView = v.findViewById(R.id.text);
        }

    }
}
