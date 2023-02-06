package com.example.john_catalog.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.john_catalog.Activities.MainActivity;
import com.example.john_catalog.Model.Fish;
import com.example.john_catalog.R;
import com.squareup.picasso.Picasso;

import java.io.ObjectInputStream;
import java.util.List;

public class FishRecyclerViewAdapter extends RecyclerView.Adapter<FishRecyclerViewAdapter.ViewHolder>
{


    private Context context;
    private List<Fish> fishList;

    public FishRecyclerViewAdapter(List<Fish> fishList, MainActivity mainActivity) {

    }

    @NonNull
    @Override
    public FishRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fish,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull FishRecyclerViewAdapter.ViewHolder holder, int position)
    {
        Fish fish = fishList.get(position);
        String imageLink=fish.getImg_src_set();
        holder.name.setText(fish.getName());
        holder.conservation_status.setText(fish.getConservation_status());
        holder.binomial_name.setText(fish.getBinomial_name());
        Picasso.get()
                .load(imageLink)
                .fit()
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return fishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView conservation_status;
        TextView binomial_name;
        public ViewHolder(@NonNull View itemView, Context context)
        {
            super(itemView);
            context=context;
            name=itemView.findViewById(R.id.fishNameID);
            image=itemView.findViewById(R.id.fishImageID);
            conservation_status=itemView.findViewById(R.id.conservationStatusID);
            binomial_name=itemView.findViewById(R.id.conservationStatusID);
        }
    }
}
