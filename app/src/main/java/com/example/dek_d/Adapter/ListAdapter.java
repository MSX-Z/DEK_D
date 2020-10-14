package com.example.dek_d.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.dek_d.Model.Data;
import com.example.dek_d.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<List<Data>> {
    private Context context;
    private List<Data> list;

    private ImageView picture;
    private TextView title,message;

    public ListAdapter(Context context, List<Data> list){
        super(context,0);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public Data getItems(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_list,null,true);

        picture = view.findViewById(R.id.picture);
        title = view.findViewById(R.id.title);
        message = view.findViewById(R.id.message);

        setData(position);

        return view;
    }

    private void setData(int position) {
        Data data = list.get(position);
        Glide.with(context).load(data.getPicture()).into(picture);
        title.setText(data.getTile());
        message.setText(data.getMessage());
    }

}
