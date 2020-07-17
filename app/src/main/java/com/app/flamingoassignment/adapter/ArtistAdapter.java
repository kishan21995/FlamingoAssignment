package com.app.flamingoassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.flamingoassignment.R;
import com.app.flamingoassignment.response.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.MyViewHolder> {
    private List<Artist> artistList;
    private Context context;

    @NonNull
    @Override
    public ArtistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArtistAdapter.MyViewHolder holder, int position) {

        Artist artist = artistList.get(position);
        holder.titleSong.setText(artist.getName());
        //Picasso.with(context).load(artist.getImage()).error(R.drawable.pops).into(holder.imageView);
       // Picasso.with(context).load(artist.getImage()).error(R.drawable.music).into(holder.imageView);


    }


    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public ArtistAdapter(List<Artist> artistList, Context context) {

        this.artistList = artistList;
        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleSong;

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.music_Image);
            titleSong = itemView.findViewById(R.id.music_name);


        }
    }

}
