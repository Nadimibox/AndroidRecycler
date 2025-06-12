package com.nadimibox.androidrecycler.location;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 2018
 * <p>
 * Description: ...
 */
public class RecycleOnScrollListener extends RecyclerView.OnScrollListener{

    private final String TAG = getClass().getName();

    /**
     * Call (@see {@link #onLimitedItem()}) when user scrolled and loaded item number
     * For example if all item numbers is 9,and @see {@link #invokeOverItem} is 6, when user recived to 6 item the
     * @see #onLimitedItem() is invoked
     */
    private int invokeOverItem;


    protected RecycleOnScrollListener() {
        //this.invokeOverItem = ;
    }

    public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if ( !(layoutManager instanceof LinearLayoutManager)){
            return;
        }
        //GridLayoutManager ham farzande LinearLayoutManager ast . pas tafavoti nadarad
        LinearLayoutManager llm = (LinearLayoutManager) layoutManager;
        if (dy > 0){
            scrollBottom(dy);
        } else if (dy < 0){
            scrollTop(dy);
        }
        //check for scroll down
        if (invokeOverItem >= 0 && dy > 0) {
            int totalItemCount_recycleGrid = llm.getItemCount();
            int lastVisibleItem_recycleGrid = llm.findLastVisibleItemPosition();
            if (totalItemCount_recycleGrid - invokeOverItem < lastVisibleItem_recycleGrid) {
                //Do pagination.. i.e. fetch new data
                onLimitedItem();
            }
        }
    }

    /**
     * @see #invokeOverItem
     */
    public void onLimitedItem(){

    }

    public void scrollBottom(int dy){

    }

    public void scrollTop(int dy){

    }

    public void onTop(){

    }

    public void onBottom(){

    }
}

