package com.example.dek_d.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dek_d.Model.Data;
import com.example.dek_d.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailFragment extends Fragment {

    private Data data;

    private ImageView picture;
    private TextView title,message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        picture = view.findViewById(R.id.picture);
        title = view.findViewById(R.id.title);
        message = view.findViewById(R.id.message);

        data = getArguments().getParcelable("item");

        setData(data);
    }

    private void setData(Data data) {
        Picasso.get().load(data.getPicture()).into(picture);
        title.setText(data.getTile());
        message.setText(data.getMessage());
    }
}