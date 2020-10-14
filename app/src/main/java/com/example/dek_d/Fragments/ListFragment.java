package com.example.dek_d.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dek_d.Adapter.ListAdapter;
import com.example.dek_d.MainActivity;
import com.example.dek_d.Model.Data;
import com.example.dek_d.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListFragment extends Fragment{

    private List<Data> list;
    private ListAdapter listAdapter;

    private ListView list_view;
    private FloatingActionButton btn_add;

    private AlertDialog.Builder builder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list_view = view.findViewById(R.id.list_view);
        btn_add = view.findViewById(R.id.btn_add);

        builder = new AlertDialog.Builder(getContext());

        list = (List<Data>) getArguments().getSerializable("box");
        listAdapter = new ListAdapter(getContext(), list);
        list_view.setAdapter(listAdapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("item", list.get(i));
                DetailFragment detailFragment = new DetailFragment();
                getFragmentManager().beginTransaction().replace(R.id.container_fm, detailFragment).addToBackStack(null).commit();
                detailFragment.setArguments(bundle);
            }
        });

        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                builder.setTitle("Are you sure ?");
                builder.setMessage("Delete "+list.get(i).getTile()+"\n"+list.get(i).getMessage());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int ix) {
                        list.remove(i);
                        listAdapter.notifyDataSetChanged();
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

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText picture,title,massage;

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View v = inflater.inflate(R.layout.dialog, null);

                picture = v.findViewById(R.id.picture);
                title = v.findViewById(R.id.title);
                massage = v.findViewById(R.id.message);

                builder.setView(v).setTitle("Add items.")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String Picture = picture.getText().toString().trim();
                                String Title = title.getText().toString().trim();
                                String Massage = massage.getText().toString().trim();

                                if(TextUtils.isEmpty(Picture)){
                                    picture.setError("url image require.");
                                    return;
                                }else if(TextUtils.isEmpty(Title)){
                                    title.setError("title require.");
                                    return;
                                }else if(TextUtils.isEmpty(Massage)){
                                    massage.setError("message require.");
                                    return;
                                }else {
                                    Data data = new Data(Picture, Title, Massage);
                                    list.add(0, data);
                                }

                                listAdapter.notifyDataSetChanged();
                            }
                        });
                builder.show();
            }
        });
    }
}