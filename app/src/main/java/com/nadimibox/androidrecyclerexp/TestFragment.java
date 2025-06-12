package com.nadimibox.androidrecyclerexp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.nadimibox.androidfragment.LiteFragment;

import org.jetbrains.annotations.NotNull;

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
public class TestFragment extends LiteFragment {
    @Override
    public View onCreateRootView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recycle);
        View header = LayoutInflater.from(inflater.getContext()).inflate(R.layout.header_layout, container , false);
        View footer = LayoutInflater.from(inflater.getContext()).inflate(R.layout.footer_layout, container , false);
        LinearHeadFootRecyclerAdapter linearAdapter =
                new LinearHeadFootRecyclerAdapter(header ,footer );

        List<String> aaaa = new ArrayList<>();
        aaaa.add("aaa");
        aaaa.add("bbb");
        aaaa.add("ccc");
        aaaa.add("ddd");
        aaaa.add("eee");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");
        aaaa.add("fff");



        recyclerView.setLayoutManager(linearAdapter.getLayoutManager(requireContext()));
        recyclerView.setAdapter(linearAdapter);





        Button b = v.findViewById(R.id.aaaaaaaaaaaaaaaaaaaaaaaaaaaa);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearAdapter.toggleHeader();
                //linearAdapter.toggleFooter();
                linearAdapter.addItems(aaaa);
            }
        });

        return v;
    }

    @Override
    public void init(ViewGroup rootView) {

    }
}
