package com.mrnadimi.androidrecycler;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 29 July 2021
 * <p>
 * Description: ...
 */
public abstract class LinearRecycleAdapter<T extends RecyclerView.ViewHolder , E> extends AndroidLiteXRecyclerAdapter<T> {

    private static final int VIEW_TYPE_ITEM = 1;

    private final List<E> data;

    public LinearRecycleAdapter() {
        data = new ArrayList<>();
    }

    public LinearRecycleAdapter(List<E> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(RecyclerView.VERTICAL);
        return llm;
    }

    public void setItems(List<E> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addItems(List<E> data){
        int count = getItemCount();
        this.data.addAll(data);
        notifyItemRangeInserted(count , data.size());
    }

    public void addItem(E data){
        int count = getItemCount();
        this.data.add(data);
        notifyItemRangeInserted(count , 1);
    }

    public void removeItem(int index){
        int count = getItemCount();
        if (index < 0 || index >= count){
            return;
        }
        this.data.remove(index);
        notifyItemRemoved(index);
    }

    public void removeItem(E data){
        int index = this.data.indexOf(data);
        if (index == -1){
            return;
        }
        removeItem(index);
    }

    public void removeItems(List<E> data){
        int count = getItemCount();
        boolean removed = this.data.removeAll(data);
        if (!removed){
            return;
        }
        notifyItemRangeRemoved(count , data.size());
        //notifyDataSetChanged();
    }

    public void removeAll(){
        this.data.clear();
        notifyDataSetChanged();
    }


    public final E getData(int position){
        return this.data.get(position);
    }


    public List<E> getData() {
        return data;
    }
}
