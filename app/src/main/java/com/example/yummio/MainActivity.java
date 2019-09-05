package com.example.yummio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.yummio.adapter.DessertAdapter;
import com.example.yummio.model.Dessert;

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

        Dessert d1 = new Dessert("Lemon Cake");
        Dessert d2 = new Dessert("Apple Pie");
        Dessert d3 = new Dessert("Pie");

        listDessert.add(d1);
        listDessert.add(d2);
        listDessert.add(d3);
        listDessert.add(d3);
        listDessert.add(d3);
        listDessert.add(d3);
        listDessert.add(d3);
        listDessert.add(d3);
        listDessert.add(d3);
        listDessert.add(d3);listDessert.add(d3);


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
    public void onClick(String name) {
//        Toast.makeText(this,"You click = "+name,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,RecipeActivity.class);
        intent.putExtra("dessertName",name);
        startActivity(intent);
    }
}
