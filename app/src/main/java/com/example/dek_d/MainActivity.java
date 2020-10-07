package com.example.dek_d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.dek_d.Fragments.ListFragment;
import com.example.dek_d.Model.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ListFragment listFragment;

    private List<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<Data>();

        listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container_fm,listFragment).commit();

        getData();

    }

    private void getData(){
        String json;
        try {
            InputStream inputStream = getAssets().open("Data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Data data = convertJsonObjectToObject(jsonObject);
                list.add(data);
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("box", (Serializable) list);
            listFragment.setArguments(bundle);

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private Data convertJsonObjectToObject(JSONObject jsonObject) {
        Data data = null;
        try {
            String picture = jsonObject.getString("picture");
            String tile = jsonObject.getString("title");
            String message = jsonObject.getString("message");
            data = new Data(picture,tile,message);
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}