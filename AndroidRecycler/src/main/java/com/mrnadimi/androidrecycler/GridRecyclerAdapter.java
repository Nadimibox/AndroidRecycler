package com.mrnadimi.androidrecycler;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 30 July 2021
 * <p>
 * Description: ...
 */
public abstract class GridRecyclerAdapter<T extends RecyclerView.ViewHolder , E> extends LinearRecycleAdapter<T , E> {

    private final int columnNum;

    public GridRecyclerAdapter(int columnNum) {
        this.columnNum = columnNum;
    }

    public GridRecyclerAdapter(List<E> data, int columnNum) {
        super(data);
        this.columnNum = columnNum;
    }


    @Override
    public final RecyclerView.LayoutManager getLayoutManager(Context context) {
        final GridLayoutManager llm = new GridLayoutManager(context , columnNum );
        llm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        llm.setOrientation(RecyclerView.VERTICAL);
        return llm;
    }
}
