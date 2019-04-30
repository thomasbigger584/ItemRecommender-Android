package com.twb.itemrecommender.feature.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.twb.itemrecommender.R;
import com.twb.itemrecommender.data.domain.Attraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final List<Attraction> items = new ArrayList<>();
    private ProductClickListener postClickListener;

    ProductListAdapter(ProductClickListener postClickListener) {
        this.postClickListener = postClickListener;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                postClickListener.onClick(getItem(position));
            }
        });
        return viewHolder;
    }

    int addItems(List<Attraction> newItems) {
        int itemsCount = items.size();
        items.addAll(newItems);
        int newItemsCount = items.size();
        notifyItemRangeInserted(itemsCount, newItemsCount);
        return newItemsCount;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductListAdapter.ViewHolder holder, int position) {
        Attraction attraction = getItem(position);
        holder.mIdView.setText(String.valueOf(attraction.getId()));
        holder.mContentView.setText(attraction.getName());
        holder.distance.setText(String.format(Locale.getDefault(), "%.2f km", attraction.getDistanceBigDecimal().doubleValue()));
        holder.itemView.setTag(attraction);
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

    void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public interface ProductClickListener {
        void onClick(Attraction attraction);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;
        final TextView distance;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.id_text);
            mContentView = view.findViewById(R.id.content);
            distance = view.findViewById(R.id.distance);
        }
    }
}
