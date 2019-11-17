package com.kientran.myrecyclerview;

import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kientran.myrecyclerview.LaptopFragment.OnListFragmentInteractionListener;
import com.kientran.myrecyclerview.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyLaptopRecyclerViewAdapter extends RecyclerView.Adapter<MyLaptopRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyLaptopRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_laptop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.priorityTV.setText(mValues.get(position).isPriority ? "!" : "");
        holder.titleTV.setText(mValues.get(position).title);
        holder.dateTV.setText(mValues.get(position).createdDate);
        holder.detailTV.setText(mValues.get(position).details);
        if (mValues.get(position).isFavourite) {
            holder.favouriteTV.setImageResource(R.drawable.ic_star_on);
        } else {
            holder.favouriteTV.setImageResource(R.drawable.ic_star_off);
        }

        holder.favouriteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alternateFavourite(holder, position);
            }
        });


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    void alternateFavourite(ViewHolder holder, int position) {
        mValues.get(position).isFavourite = !mValues.get(position).isFavourite;

        if (mValues.get(position).isFavourite) {
            holder.favouriteTV.setImageResource(R.drawable.ic_star_on);
        } else {
            holder.favouriteTV.setImageResource(R.drawable.ic_star_off);
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView priorityTV;
        public final TextView titleTV;
        public final TextView dateTV;
        public final TextView detailTV;
        public final ImageView favouriteTV;

        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            priorityTV = (TextView) view.findViewById(R.id.tv_priority);
            titleTV = (TextView) view.findViewById(R.id.tv_title);
            dateTV = (TextView) view.findViewById(R.id.tv_date);
            detailTV = (TextView) view.findViewById(R.id.tv_details);
            favouriteTV = (ImageView) view.findViewById(R.id.iv_favourite);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleTV.getText() + "'";
        }
    }
}
