package com.twb.itemrecommender.feature.product;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twb.itemrecommender.R;
import com.twb.itemrecommender.data.domain.Attraction;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final List<Attraction> items = new ArrayList<>();
    private final boolean mTwoPane;

    public ProductListAdapter(boolean twoPane) {
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
        Attraction attraction = getItem(position);
        holder.mIdView.setText(String.valueOf(attraction.getId()));
        holder.mContentView.setText(attraction.getName());

        holder.itemView.setTag(attraction);
//        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        Attraction attraction = getItem(position);
        if (attraction == null) {
            return super.getItemId(position);
        }
        return attraction.getId();
    }

    private Attraction getItem(int position) {
        return items.get(position);
    }

    public int addItems(List<Attraction> newItems) {
        int itemsCount = items.size();
        items.addAll(newItems);
        int newItemsCount = items.size();
        notifyItemRangeInserted(itemsCount, newItemsCount);
        return newItemsCount;
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
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
