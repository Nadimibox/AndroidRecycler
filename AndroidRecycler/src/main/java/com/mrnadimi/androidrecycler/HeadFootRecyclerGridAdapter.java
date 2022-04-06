package com.mrnadimi.androidrecycler;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public abstract class HeadFootRecyclerGridAdapter<T extends RecyclerView.ViewHolder , E> extends HeadFootRecyclerLinearAdapter<T , E> {

    private final int columnNum;

    public HeadFootRecyclerGridAdapter(int columnNum) {
        this(new ArrayList<>() , columnNum);
    }

    public HeadFootRecyclerGridAdapter(List<E> data, int columnNum) {
        this(null , data , columnNum);
    }

    public HeadFootRecyclerGridAdapter(View header , List<E> data, int columnNum) {
        this(header , null , data , columnNum);
    }

    public HeadFootRecyclerGridAdapter(View header , View footer, int columnNum) {
        this(header , footer , new ArrayList<>() , columnNum);
    }

    public HeadFootRecyclerGridAdapter(View header , View footer , List<E> data, int columnNum) {
        super(header , footer , data);
        this.columnNum = columnNum;
    }


    @Override
    public final RecyclerView.LayoutManager getLayoutManager(Context context) {
        final GridLayoutManager llm = new GridLayoutManager(context , columnNum );
        llm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (getItemViewType(position) == VIEW_TYPE_HEADER || getItemViewType(position) == VIEW_TYPE_FOOTER ){
                    return columnNum;
                }
                return 1;
            }
        });
        llm.setOrientation(RecyclerView.VERTICAL);
        return llm;
    }

}
