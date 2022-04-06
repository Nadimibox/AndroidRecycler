package com.mrnadimi.androidrecycler;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 30 July 2021
 * <p>
 * Description: ...
 */
public abstract class AndroidLiteXRecyclerAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>  {

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public abstract T onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull T holder, int position);

    @Override
    public abstract void onViewRecycled(@NonNull @NotNull T holder);

    public abstract RecyclerView.LayoutManager getLayoutManager(Context context);

}
