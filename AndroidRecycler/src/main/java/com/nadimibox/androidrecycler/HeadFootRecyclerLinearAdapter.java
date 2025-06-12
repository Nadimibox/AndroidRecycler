package com.nadimibox.androidrecycler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 26 August 2021
 * <p>
 * Description: All index size for items or ... is List<E> size
 */
public abstract class HeadFootRecyclerLinearAdapter<T extends RecyclerView.ViewHolder , E> extends
        AndroidLiteXRecyclerAdapter<RecyclerView.ViewHolder> {
    protected static final int VIEW_TYPE_ITEM = 1;
    protected static final int VIEW_TYPE_HEADER = 2;
    protected static final int VIEW_TYPE_FOOTER = 3;

    private final List<E> data;


    protected final View header, footer;

    public HeadFootRecyclerLinearAdapter() {
        this(null , null , new ArrayList<>());
    }

    public HeadFootRecyclerLinearAdapter(List<E> data) {
        this(null , null , data);
    }

    public HeadFootRecyclerLinearAdapter(View header){
        this(header , null ,new ArrayList<>());
    }

    public HeadFootRecyclerLinearAdapter(View header , View footer){
        this(header , footer ,new ArrayList<>());
    }

    public HeadFootRecyclerLinearAdapter(View header , View footer , @NonNull List<E> data){
        this.data = data;
        this.header = header;
        this.footer = footer;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType){
        if (viewType == VIEW_TYPE_HEADER){
            return new HeaderHolder(header);
        }else if (viewType == VIEW_TYPE_FOOTER){
            return new FooterHolder(footer);
        }else{
            return onCreateViewHolder(parent);
        }
    }

    @Override
    public final void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerView.ViewHolder holder, int position){
        try{
            if (holder instanceof HeaderHolder || holder instanceof FooterHolder){
                return;
            }
            //noinspection unchecked
            onBindViewHolder((T)holder , position , getData(position));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public final void onViewRecycled(@NonNull RecyclerView.@NotNull ViewHolder holder) {
        try{
            if (holder instanceof HeaderHolder || holder instanceof FooterHolder){
                return;
            }
            //noinspection unchecked
            onViewRecycle((T) holder);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public abstract T onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent);

    public abstract void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull T holder, int position , E data);

    public abstract void onViewRecycle(T holder);

    @Override
    public final int getItemCount() {
        return isHeaderAvailable() && isFooterAvailable() ? data.size() + 2 : isHeaderAvailable() || isFooterAvailable() ? data.size() + 1 : data.size();
    }

    public final int getDataSize(){
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderAvailable() && position == 0 ){
            return VIEW_TYPE_HEADER;
        }else if (isFooterAvailable() && position == getItemCount() - 1 ){
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     * @return The @See {@link LinearLayoutManager} is kill the header and footer width
     * https://stackoverflow.com/questions/35904409/item-in-recyclerview-not-filling-its-width-match-parent
     *
     * We can fix that with https://stackoverflow.com/a/30692398/6098741
     *
     * Note: just {@link LinearLayoutManager} has this bug and {@link androidx.recyclerview.widget.GridLayoutManager} is perfectly work
     *
     */
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
        int count = getDataSize();
        this.data.addAll(data);
        notifyItemRangeInserted(isHeaderAvailable() ? count +1 : count , data.size());
    }

    public void addItem(E data){
        int count = getDataSize();
        this.data.add(data);
        notifyItemRangeInserted(isHeaderAvailable() ? count +1 : count  , 1);
    }

    public void removeItem(int index){
        int count = getDataSize();
        if (index < 0 || index >= count){
            return;
        }
        this.data.remove(index);
        notifyItemRemoved(isHeaderAvailable() ? index +1 : index);
    }

    public void removeItem(E data){
        int index = this.data.indexOf(data);
        if (index == -1){
            return;
        }
        removeItem(index);
    }

    public void removeItems(List<E> data){
        //int count = getItemCount();
        boolean removed = this.data.removeAll(data);
        if (!removed){
            return;
        }
        notifyItemRangeRemoved(isHeaderAvailable() ? 1 : 0 , data.size());
        //notifyDataSetChanged();
    }

    public void removeAll(){
        this.data.clear();
        notifyDataSetChanged();
    }


    public final E getData(int position){
        if (position == 0 && isHeaderAvailable()){
            return null;
        }else if (isFooterAvailable() && position == getItemCount()){
            return null;
        }
        return this.data.get(isHeaderAvailable() ? position -1 : position);
    }



    public void hideHeader(){
        if (header == null ||!isHeaderAvailable()){
            return;
        }
        setHeaderVisibility(false);
    }


    public void showHeader(){
        if (header == null ||isHeaderAvailable()){
            return;
        }
        setHeaderVisibility(true);
    }

    public void toggleHeader(){
        if (isHeaderAvailable()){
            hideHeader();
        }else{
            showHeader();
        }
    }

    public void setHeaderVisibility(boolean isVisible){
        if (isVisible){
            header.setVisibility(View.VISIBLE);
        }else{
            header.setVisibility(View.GONE);
        }
        //Very important, notifyItemChanged or ... got Errors
        notifyDataSetChanged();
    }


    public void hideFooter(){
        if (footer == null ||!isFooterAvailable()){
            return;
        }
        setFooterVisibility(false);
    }

    public void showFooter(){
        if (footer == null || isFooterAvailable()){
            return;
        }
        setFooterVisibility(true);
    }

    public void toggleFooter(){
        if (isFooterAvailable()){
            hideFooter();
        }else{
            showFooter();
        }
    }

    public void setFooterVisibility(boolean isVisible){
        if (isVisible){
            footer.setVisibility(View.VISIBLE);
        }else{
            footer.setVisibility(View.GONE);
        }
        //Very important, notifyItemChanged or ... got Errors
        notifyDataSetChanged();
    }



    private boolean isHeaderAvailable(){
        return header != null && header.getVisibility() != View.GONE;
    }

    private boolean isFooterAvailable(){
        return footer != null && footer.getVisibility() != View.GONE;
    }





    private static class HeaderHolder extends RecyclerView.ViewHolder{

        public HeaderHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

    private static class FooterHolder extends RecyclerView.ViewHolder{

        public FooterHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}



/*
package com.mrnadimi.androidxliterecyclerview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
/*public abstract class HeaderAndFooterLinearAdapter<T extends RecyclerView.ViewHolder , E> extends
        AndroidLiteXRecyclerViewAdapter<RecyclerView.ViewHolder>  {
    protected static final int VIEW_TYPE_ITEM = 1;
    protected static final int VIEW_TYPE_HEADER = 2;
    protected static final int VIEW_TYPE_FOOTER = 3;

    private List<E> data;


    protected final View header, footer;

    public HeaderAndFooterLinearAdapter() {
        this(null , null , new ArrayList<>());
    }

    public HeaderAndFooterLinearAdapter(List<E> data) {
        this(null , null , data);
    }

    public HeaderAndFooterLinearAdapter(View header){
        this(header , null ,new ArrayList<>());
    }

    public HeaderAndFooterLinearAdapter(View header , View footer){
        this(header , footer ,new ArrayList<>());
    }

    public HeaderAndFooterLinearAdapter(View header , View footer ,@NonNull List<E> data){
        this.data = data;
        this.header = header;
        this.footer = footer;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType){
        if (viewType == VIEW_TYPE_HEADER){
            return new HeaderHolder(header);
        }else if (viewType == VIEW_TYPE_FOOTER){
            return new FooterHolder(footer);
        }else{
            return onCreateViewHolder(parent);
        }
    }

    @Override
    public final void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerView.ViewHolder holder, int position){
        try{
            if (holder instanceof HeaderHolder || holder instanceof FooterHolder){
                return;
            }
            //noinspection unchecked
            onBindViewHolder((T)holder , position , getData(position));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public final void onViewRecycled(@NonNull RecyclerView.@NotNull ViewHolder holder) {
        try{
            if (holder instanceof HeaderHolder || holder instanceof FooterHolder){
                return;
            }
            //noinspection unchecked
            onViewRecycle((T) holder);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public abstract T onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent);

    public abstract void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull T holder, int position , E data);

    public abstract void onViewRecycle(T holder);

    @Override
    public final int getItemCount() {
        return header != null && footer!= null ? data.size() + 2 : header != null || footer != null ? data.size() + 1 : data.size();
    }

    public final int getDataSize(){
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (header != null && position == 0 ){
            return VIEW_TYPE_HEADER;
        }else if (footer != null && position == getItemCount() - 1 ){
            return VIEW_TYPE_FOOTER;
        }
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
        this.data = data;
        notifyDataSetChanged();
    }

    public void addItems(List<E> data){
        int count = getDataSize();
        this.data.addAll(data);
        notifyItemRangeInserted(header != null ? count +1 : count , data.size());
    }

    public void addItem(E data){
        int count = getDataSize();
        this.data.add(data);
        notifyItemRangeInserted(header != null ? count +1 : count  , 1);
    }

    public void removeItem(int index){
        int count = getDataSize();
        if (index < 0 || index >= count){
            return;
        }
        this.data.remove(index);
        notifyItemRemoved(header != null ? index +1 : index);
    }

    public void removeItem(E data){
        int index = this.data.indexOf(data);
        if (index == -1){
            return;
        }
        removeItem(index);
    }

    public void removeItems(List<E> data){
        //int count = getItemCount();
        boolean removed = this.data.removeAll(data);
        if (!removed){
            return;
        }
        notifyItemRangeRemoved(header != null ? 1 : 0 , data.size());
        //notifyDataSetChanged();
    }


    public final E getData(int position){
        if (position == 0 && header != null){
            return null;
        }else if (footer != null && position == getItemCount()){
            return null;
        }
        return this.data.get(header!= null ? position -1 : position);
    }





    private static class HeaderHolder extends RecyclerView.ViewHolder{

        public HeaderHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

    private static class FooterHolder extends RecyclerView.ViewHolder{

        public FooterHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}






 */
