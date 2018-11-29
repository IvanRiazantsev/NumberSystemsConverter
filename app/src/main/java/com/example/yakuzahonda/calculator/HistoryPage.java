package com.example.yakuzahonda.calculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HistoryPage extends Fragment {

    View view;

    public HistoryPage() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_page, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.historyListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        return view;
    }
}
