package com.twb.itemrecommender.product;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twb.itemrecommender.R;
import com.twb.itemrecommender.data.domain.Attraction;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final List<Attraction> attractionList;
    private final boolean mTwoPane;

    public ProductListAdapter(List<Attraction> attractionList, boolean twoPane) {
        this.attractionList = attractionList;
        this.mTwoPane = twoPane;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_content, parent, false);
        return new ProductListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductListAdapter.ViewHolder holder, int position) {
        holder.mIdView.setText(String.valueOf(attractionList.get(position).getId()));
        holder.mContentView.setText(attractionList.get(position).getName());

        holder.itemView.setTag(attractionList.get(position));
//        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return attractionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.id_text);
            mContentView = view.findViewById(R.id.content);
        }
    }
}
