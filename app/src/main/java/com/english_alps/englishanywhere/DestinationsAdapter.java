package com.english_alps.englishanywhere;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

public class DestinationsAdapter extends RecyclerView.Adapter<DestinationsAdapter.ViewHolder> {

    public static class DestinationItem {
        final String name;
        final String condition;
        final String waves;
        final String visits;
        final int textColor;
        final int image;
        final List<String> friendPics;

        public DestinationItem(String name, String condition, String waves, String visits, int textColor, int image, List<String> friendPics) {
            this.name = name;
            this.condition = condition;
            this.waves = waves;
            this.visits = visits;
            this.textColor = textColor;
            this.image = image;
            this.friendPics = friendPics;
        }
    }

    private final List<DestinationItem> mValues;

    public DestinationsAdapter() {
        // some sample destinations
        mValues = new ArrayList<DestinationItem>() {{
            add(new DestinationItem("A1-Beginners.", "Ask simple questions", "IELTS 0", "6", R.color.wavesGood, R.drawable.a1, null));
            add(new DestinationItem("A2-Elementary.", "Routine conversation", "IELTS 0", "4", R.color.wavesFair, R.drawable.a2, null));
            add(new DestinationItem("B1-Pre-Intermediate.", "Deal with unpredictable language", "IELTS 4-4.5", "6", R.color.wavesGood, R.drawable.b1, null));
            add(new DestinationItem("B2-Upper-Intermediate.", "Give detailed instructions", "IELTS 5-6.5", "4", R.color.wavesFair, R.drawable.b2, null));
            add(new DestinationItem("C1-Advanced.", "Deal with unfamiliar topics", "IELTS 7-8", "1", R.color.wavesPoor, R.drawable.c1, null));
            add(new DestinationItem("C2-Proficiency.", "Reading as quick as native", "IELTS 8.5-9", "1", R.color.wavesPoor, R.drawable.c2, null));
        }};
    }

    public DestinationsAdapter(ArrayList<String> friendPics) {
        // choose some user pics to display
        int size = friendPics.size();
        final ArrayList<String> pics1 = new ArrayList<>(friendPics.subList(0, Math.min(2, size)));
        final ArrayList<String> pics2 = new ArrayList<>(friendPics.subList(Math.min(2, size), Math.min(5, size)));
        final ArrayList<String> pics3 = new ArrayList<>(friendPics.subList(Math.min(5, size), Math.min(6, size)));

        // some sample destinations
        mValues = new ArrayList<DestinationItem>() {{
            add(new DestinationItem("A1-Beginners.", "Ask simple questions", "IELTS 0", "6", R.color.wavesGood, R.drawable.a1, null));
            add(new DestinationItem("A2-Elementary.", "Routine conversation", "IELTS 0", "4", R.color.wavesFair, R.drawable.a2, null));
            add(new DestinationItem("B1-Pre-Intermediate.", "Deal with unpredictable language", "IELTS 4-4.5", "6", R.color.wavesGood, R.drawable.b1, pics1));
            add(new DestinationItem("B2-Upper-Intermediate.", "Give detailed instructions", "IELTS 5-6.5", "4", R.color.wavesFair, R.drawable.b2, pics2));
            add(new DestinationItem("C1-Advanced.", "Deal with unfamiliar topics", "IELTS 7-8", "1", R.color.wavesPoor, R.drawable.c1, pics3));
            add(new DestinationItem("C2-Proficiency.", "Reading as quick as native", "IELTS 8.5-9", "1", R.color.wavesPoor, R.drawable.c2, null));
        }};
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_destination, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DestinationItem item = mValues.get(position);
        FontHelper.setCustomTypeface(holder.mView);

        holder.mImage.setImageResource(item.image);
        holder.mName.setText(item.name);
        holder.mCondition.setText(item.condition);
        holder.mCondition.setTextColor(
                ContextCompat.getColor(holder.mCondition.getContext(),
                item.textColor));
        holder.mWaves.setText(item.waves);
        holder.mVisits.setText(item.visits);

        if (item.friendPics != null) {
            if (item.friendPics.size() >= 1) {
                displayProfilePic(holder.mFriend1, item.friendPics.get(0));
                holder.mFriend1.setVisibility(View.VISIBLE);
                holder.mVisits.setText("+" + item.visits);
            } else {
                holder.mFriend1.setVisibility(View.GONE);
            }

            if (item.friendPics.size() >= 2) {
                displayProfilePic(holder.mFriend2, item.friendPics.get(1));
                holder.mFriend2.setVisibility(View.VISIBLE);
            } else {
                holder.mFriend2.setVisibility(View.GONE);
            }

            if (item.friendPics.size() >= 3) {
                displayProfilePic(holder.mFriend3, item.friendPics.get(2));
                holder.mFriend3.setVisibility(View.VISIBLE);
            } else {
                holder.mFriend3.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mImage;
        final TextView mName;
        final TextView mCondition;
        final TextView mWaves;
        final TextView mVisits;
        final ImageView mFriend1;
        final ImageView mFriend2;
        final ImageView mFriend3;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImage = (ImageView) view.findViewById(R.id.image);
            mName = (TextView) view.findViewById(R.id.name);
            mCondition = (TextView) view.findViewById(R.id.condition);
            mWaves = (TextView) view.findViewById(R.id.waves);
            mVisits = (TextView) view.findViewById(R.id.visits);
            mFriend1 = (ImageView) view.findViewById(R.id.friend1);
            mFriend2 = (ImageView) view.findViewById(R.id.friend2);
            mFriend3 = (ImageView) view.findViewById(R.id.friend3);
        }
    }

    private void displayProfilePic(ImageView imageView, String url) {
        // helper method to load the profile pic in a circular imageview
        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .borderColor(ContextCompat.getColor(imageView.getContext(), R.color.destBackground))
                .borderWidthDp(1)
                .oval(false)
                .build();
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.icon_profile_empty)
                .transform(transformation)
                .into(imageView);
    }

}
