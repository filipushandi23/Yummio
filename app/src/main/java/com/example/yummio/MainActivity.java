package com.example.yummio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.yummio.Global.Global;
import com.example.yummio.adapter.DessertAdapter;
import com.example.yummio.model.Dessert;
import com.example.yummio.model.Ingredients;
import com.example.yummio.model.Steps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DessertAdapter.DessertAdapterOnClickHandler{

    private RecyclerView recyclerView;
    private DessertAdapter dessertAdapter;
    private List<Dessert> listDessert = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();

        Type listType = new TypeToken<List<Dessert>>() {}.getType();


        try{
            listDessert = gson.fromJson(readFile("data.json"),listType);
        }catch (IOException e){
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.recycler_view);
        dessertAdapter = new DessertAdapter(this);

        //isi data
        dessertAdapter.setListDessert(listDessert);

        //layout manager --> container
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //adapter itu untuk manage data
        recyclerView.setAdapter(dessertAdapter);
    }

    @Override
    public void onClick(Dessert p_dessert) {
//        Toast.makeText(this,"You click = "+name,Toast.LENGTH_SHORT).show();
        Log.v("Baking App", p_dessert.getName());
        //Global.g_loadedDessert = p_dessert;

        Intent intent = new Intent(this,RecipeActivity.class);
        intent.putExtra("dessert", p_dessert);
        startActivity(intent);
    }

    public String readFile(String fileName) throws IOException
    {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName), "UTF-8"));

        String content = "";
        String line;
        while ((line = reader.readLine()) != null)
        {
            content = content + line;
        }

        return content;

    }
}
