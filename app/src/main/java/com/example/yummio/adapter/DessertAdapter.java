package com.example.yummio.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yummio.R;
import com.example.yummio.model.Dessert;
import com.squareup.picasso.Picasso;

import java.util.List;


public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.DessertAdapterViewHolder> {

    List<Dessert> listDessert;

    private final DessertAdapterOnClickHandler mClickHandler;

    public interface DessertAdapterOnClickHandler{
        void onClick(Dessert dessert);
    }

    public DessertAdapter(DessertAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }
    @Override
    public DessertAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new DessertAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DessertAdapterViewHolder holder, int i) {
        String dessertName = listDessert.get(i).getName();

        holder.dessertNameView.setText(dessertName);

//        Picasso.with(DessertAdapterViewHolder.itemView.getContext())
//                .load("http://i.imgur.com/DvpvklR.jpg")
//                .resize(50, 50)
//                .into(DessertAdapterViewHolder.movieImageView);
    }

    @Override
    public int getItemCount() {
        if(listDessert.size() != 0){
            return listDessert.size();
        }
        return 0;
    }

    public class DessertAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView dessertNameView;

        public DessertAdapterViewHolder(View view){
            super(view);

            imageView = view.findViewById(R.id.dessert_image);
            dessertNameView = view.findViewById(R.id.dessert_name);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Log.v("Baking App", "Onclick 1");
            mClickHandler.onClick(listDessert.get(adapterPosition));
        }
    }

    public void setListDessert(List<Dessert> listDessert){
        this.listDessert = listDessert;
        notifyDataSetChanged();
    }

}
