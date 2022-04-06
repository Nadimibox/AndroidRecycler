package com.mrnadimi.androidrecycler;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 30 July 2021
 * <p>
 * Description: ...
 */
public class MergeRecyclerAdapter extends AndroidLiteXRecyclerAdapter<RecyclerView.ViewHolder> {

    private final List<RecyclerView.Adapter<RecyclerView.ViewHolder>> adapters = new ArrayList<>();
    private final SparseArray<RecyclerView.Adapter<RecyclerView.ViewHolder>> viewTypeMapping=new SparseArray<>();
    private final HashMap<RecyclerView.Adapter<RecyclerView.ViewHolder>, InternalDataObserver> observers=new HashMap<>();

    private final int spanCount;

    public MergeRecyclerAdapter(int spanCount) {
        this.spanCount = spanCount;
    }

    /**
     * Moshkelate adapter ha ra hal kardam be joz in mored be hamin dalil support warning nakardam
     * @param adapter :
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void addAdapter(RecyclerView.Adapter adapter){
        addAdapter(adapters.size(), adapter);
    }

    public void addAdapter(int index, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter){
        if(adapters.contains(adapter))
            throw new IllegalArgumentException("Adapter "+adapter+" is already added!");
        adapters.add(index, adapter);
        InternalDataObserver observer=new InternalDataObserver(adapter);
        adapter.registerAdapterDataObserver(observer);
        observers.put(adapter, observer);
        notifyDataSetChanged();
    }

    @SuppressWarnings({"rawtypes", "ConstantConditions"})
    public void removeAdapter(RecyclerView.Adapter adapter){
        if(adapters.remove(adapter)){
            adapter.unregisterAdapterDataObserver(observers.get(adapter));
            observers.remove(adapter);
            notifyDataSetChanged();
        }
    }

    public void removeAdapterAt(int index){
        removeAdapter(adapters.get(index));
    }

    @SuppressWarnings({ "ConstantConditions"})
    public void removeAllAdapters(){
        for(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter:adapters){
            adapter.unregisterAdapterDataObserver(observers.get(adapter));
            observers.remove(adapter);
        }
        adapters.clear();
        notifyDataSetChanged();
    }

    public RecyclerView.Adapter<RecyclerView.ViewHolder> getAdapterAt(int index){
        return adapters.get(index);
    }

    public int getAdapterCount(){
        return adapters.size();
    }

    public int getAdapterPosition(int pos){
        int count=0;
        for(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter:adapters){
            int c=adapter.getItemCount();
            if(pos>=count && pos<count+c){
                return pos-count;
            }
            count+=c;
        }
        return pos;
    }

    public int getPositionForAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter){
        int pos=0;
        for(RecyclerView.Adapter<RecyclerView.ViewHolder> a:adapters){
            if(a==adapter)
                return pos;
            pos+=a.getItemCount();
        }
        return pos;
    }

    public RecyclerView.Adapter<RecyclerView.ViewHolder> getAdapterForPosition(int pos){
        int count=0;
        for(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter:adapters){
            int c=adapter.getItemCount();
            if(pos>=count && pos<count+c){
                return adapter;
            }
            count+=c;
        }
        return null;
    }

    public int getAdapterIndexForPosition(int pos){
        int count=0;
        int i=0;
        for(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter:adapters){
            int c=adapter.getItemCount();
            if(pos>=count && pos<count+c){
                return i;
            }
            count+=c;
            i++;
        }
        return -1;
    }

    @Override
    public RecyclerView.@NotNull ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return viewTypeMapping.get(viewType).onCreateViewHolder(parent, viewType);
    }


    @Override
    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        GridLayoutManager lmgr= new GridLayoutManager(context , spanCount);
        lmgr.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int i) {
                RecyclerView.Adapter<?> adapterForPosition = getAdapterForPosition(i);
                if (adapterForPosition instanceof GridRecyclerAdapter){
                    return 1;
                }else{
                    return spanCount;
                }
            }
        });
        lmgr.setOrientation(RecyclerView.VERTICAL);
        return lmgr;
    }

    @Override
    public void onBindViewHolder(RecyclerView.@NotNull ViewHolder holder, int position) {
        getAdapterForPosition(position).onBindViewHolder(holder, getAdapterPosition(position));
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.@NotNull ViewHolder holder) {

    }

    @Override
    public int getItemViewType(int position) {
        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter=getAdapterForPosition(position);
        int viewType=adapter.getItemViewType(getAdapterPosition(position));
        viewTypeMapping.put(viewType, adapter);
        return viewType;
    }

    @Override
    public int getItemCount() {
        int count=0;
        for(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter:adapters){
            count+=adapter.getItemCount();
        }
        return count;
    }


    @Override
    public long getItemId(int position) {
        return getAdapterForPosition(position).getItemId(getAdapterPosition(position));
    }

    private class InternalDataObserver extends RecyclerView.AdapterDataObserver{

        private final RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;

        public InternalDataObserver(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter){
            this.adapter=adapter;
        }

        @Override
        public void onChanged(){
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount){
            notifyItemRangeChanged(getPositionForAdapter(adapter)+positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload){
            notifyItemRangeChanged(getPositionForAdapter(adapter)+positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount){
            notifyItemRangeInserted(getPositionForAdapter(adapter)+positionStart, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount){
            notifyItemRangeRemoved(getPositionForAdapter(adapter)+positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount){
            if(itemCount!=1) throw new UnsupportedOperationException("Can't move more than one item");
            int offset=getPositionForAdapter(adapter);
            notifyItemMoved(offset+fromPosition, offset+toPosition);
        }
    }
}