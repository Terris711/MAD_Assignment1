package com.example.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {
    Context context;
    String[] avatarNames;
    String[] avatarDescription;
    int[] avatarImages;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowName;
        TextView rowDescription;
        ImageView rowImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.text1);
            rowDescription = itemView.findViewById(R.id.text2);
            rowImage = itemView.findViewById(R.id.imageView);

        }
    }

    public ProgramAdapter(Context context, String[] avatarNames, String[] avatarDescription, int[] avatarImages){
        this.context = context;
        this.avatarNames = avatarNames;
        this.avatarDescription = avatarDescription;
        this.avatarImages = avatarImages;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.avatars, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.rowName.setText(avatarNames[position]);
    holder.rowDescription.setText((avatarDescription[position]));
    holder.rowImage.setImageResource(avatarImages[position]);
    }

    @Override
    public int getItemCount() {
        return avatarDescription.length;
    }
}
