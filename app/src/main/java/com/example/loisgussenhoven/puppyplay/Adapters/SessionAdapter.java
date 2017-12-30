package com.example.loisgussenhoven.puppyplay.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loisgussenhoven.puppyplay.Entity.FriendSession;
import com.example.loisgussenhoven.puppyplay.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Nick van Endhoven, 2119719 on 30-Dec-17.
 */

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {

    public List<FriendSession> sessions;

    public SessionAdapter(List<FriendSession> sessions) {
        this.sessions = sessions;
    }

    @Override
    public SessionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_social, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SessionAdapter.ViewHolder holder, int position) {
        FriendSession session = sessions.get(position);

        holder.tvDogName.setText(session.getOther().getName());
        holder.tvOwnerName.setText(session.getOther().getNameOwner());

        SimpleDateFormat df= new SimpleDateFormat("dd MMMM yyyy");
        holder.tvTimeStamp.setText(df.format(session.getDate()) + "\t\t\t" + session.getTime());
        
        //// TODO: 30-Dec-17 Add duration

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 30-Dec-17 Goto detail 
            }
        });
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView tvDogName, tvOwnerName, tvTimeStamp;
        public ImageView ivDog;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvDogName = view.findViewById(R.id.list_social_tv_dog_name);
            tvOwnerName =  view.findViewById(R.id.list_social_tv_owner);
            tvTimeStamp =  view.findViewById(R.id.list_social_date_time);
            ivDog =  view.findViewById(R.id.list_social_dog_image);
        }
    }
}
