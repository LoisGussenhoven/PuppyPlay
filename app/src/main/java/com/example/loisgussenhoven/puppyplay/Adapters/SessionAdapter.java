package com.example.loisgussenhoven.puppyplay.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loisgussenhoven.puppyplay.Entity.FriendSession;
import com.example.loisgussenhoven.puppyplay.Manager;
import com.example.loisgussenhoven.puppyplay.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Nick van Endhoven, 2119719 on 30-Dec-17.
 */

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {

    public List<FriendSession> sessions;
    Context context;

    public SessionAdapter(List<FriendSession> sessions) {
        this.sessions = sessions;
    }

    @Override
    public SessionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
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

        ImageView layer = holder.ivDog1;
        layer.setColorFilter(Color.parseColor("#" + session.getOther().getColor1()), PorterDuff.Mode.MULTIPLY );
        ImageView layer2 = holder.ivDog2;
        layer2.setColorFilter(Color.parseColor("#" + session.getOther().getColor2()), PorterDuff.Mode.MULTIPLY );

        Picasso.with(context).load("https://maps.googleapis.com/maps/api/" +
                "staticmap?center=" + session.getLocation().getLatitude() + "," + session.getLocation().getLongitude() + "&zoom=16&size=600x400&markers=color:red|label:"
                + session.getOther().getName() + "|" + session.getLocation().getLatitude() + "," + session.getLocation().getLongitude()
        ).into(holder.ivLoc);

    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView tvDogName, tvOwnerName, tvTimeStamp;
        public ImageView ivDog1, ivDog2;
        public ImageView ivLoc;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvDogName = view.findViewById(R.id.list_social_tv_dog_name);
            tvOwnerName =  view.findViewById(R.id.list_social_tv_owner);
            tvTimeStamp =  view.findViewById(R.id.list_social_date_time);

            ivDog1 =  view.findViewById(R.id.list_social_dog_image_1);
            ivDog2 =  view.findViewById(R.id.list_social_dog_image_2);

            ivLoc = view.findViewById(R.id.list_social_iv_location);
        }
    }
}
