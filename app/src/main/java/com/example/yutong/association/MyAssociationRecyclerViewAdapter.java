package com.example.yutong.association;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Association} and makes a call to the
 * specified {@link/* OnListFragmentInteractionListener}.*/
 /** TODO: Replace the implementation with code for your data type.
 */
public class MyAssociationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Association> mValues;
   /* private final OnListFragmentInteractionListener mListener;*/
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public MyAssociationRecyclerViewAdapter(List<Association> items) {
        mValues = items;
       /* mListener = listener;*/
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_association_item, parent, false);
            return new VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.banner_association, parent, false);
            return new VHHeader(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            ((VHItem) holder).mItem = mValues.get(position);
            ((VHItem) holder).mContentView.setText(mValues.get(position).name);
        /*holder.mIdView.setText(mValues.get(position).style);*/

       /* holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
        } else if (holder instanceof VHHeader) {
            //cast holder to VHHeader and set data for header.
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public int getItemViewType(int position) {
         if (isPositionHeader(position))
             return TYPE_HEADER;

         return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
     return position == 0;
    }

    public class VHItem extends RecyclerView.ViewHolder {
        public final View mView;
        /*public final TextView mIdView;*/
        public final TextView mContentView;
        public Association mItem;

        public VHItem(View view) {
            super(view);
            mView = view;
            /*mIdView = (TextView) view.findViewById(R.id.frag_association_id);*/
            mContentView = (TextView) view.findViewById(R.id.frag_association_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;

         public VHHeader(View view) {
             super(view);
             mView = view;
             mImageView = (ImageView)view.findViewById(R.id.association_banner);
         }
    }
}
