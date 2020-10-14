package com.example.dek_d.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dek_d.Adapter.DetailAdapter;
import com.example.dek_d.Model.Data;
import com.example.dek_d.R;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

    private Data data;
    private ListView list_view;
    private DetailAdapter detailAdapter;
    private static List<Data> list = new ArrayList<Data>();

    private AlertDialog.Builder builder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list_view = view.findViewById(R.id.list_view);
        detailAdapter = new DetailAdapter(getContext(), list);
        list_view.setAdapter(detailAdapter);

        builder = new AlertDialog.Builder(getContext());

        data = getArguments().getParcelable("item");
        if(data != null) {
            list.add(0,data);
            detailAdapter.notifyDataSetChanged();
        }

        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                builder.setTitle("Are you sure ?");
                builder.setMessage("Delete "+list.get(i).getTile()+"\n"+list.get(i).getMessage());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int ix) {
                        list.remove(i);
                        detailAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int ix) {
                        dialogInterface.dismiss();
                    }
                });

                builder.create().show();
                return true;
            }
        });
    }


}