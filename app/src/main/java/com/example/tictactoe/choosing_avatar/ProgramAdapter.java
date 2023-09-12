package com.example.tictactoe.choosing_avatar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictactoe.R;

import java.util.List;

public class  ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {
    Context context;
    List<Avatar> avatarList;
    private SelectListener listener;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowName;
        ImageView rowImage;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.text1);
            rowImage = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);


        }
    }

    public ProgramAdapter(Context context, List<Avatar> avatarList, SelectListener listener){
        this.context = context;
        this.avatarList = avatarList;
        this.listener = listener;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.avatar_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Avatar avatar = avatarList.get(position);
        holder.rowName.setText(avatar.getName());
        holder.rowImage.setImageResource(avatar.getImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(avatarList.get(holder.getAdapterPosition()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return avatarList.size();
    }
}

