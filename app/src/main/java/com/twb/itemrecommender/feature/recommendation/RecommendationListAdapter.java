package com.twb.itemrecommender.feature.recommendation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.twb.itemrecommender.R;
import com.twb.itemrecommender.data.domain.Recommendation;

import java.util.ArrayList;
import java.util.List;

public class RecommendationListAdapter extends RecyclerView.Adapter<RecommendationListAdapter.ViewHolder> {

    private final List<Recommendation> items = new ArrayList<>();
    private RecommendationClickListener postClickListener;

    RecommendationListAdapter(RecommendationClickListener postClickListener) {
        this.postClickListener = postClickListener;
    }

    @NonNull
    @Override
    public RecommendationListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

    int addItems(List<Recommendation> newItems) {
        int itemsCount = items.size();
        items.addAll(newItems);
        int newItemsCount = items.size();
        notifyItemRangeInserted(itemsCount, newItemsCount);
        return newItemsCount;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecommendationListAdapter.ViewHolder holder, int position) {
        Recommendation recommendation = getItem(position);
        holder.mIdView.setText(String.valueOf(recommendation.getItemId()));
        holder.mContentView.setText(recommendation.getAttraction().getName());
        Double percentage = recommendation.getProbability() * 100;
        holder.probability.setText(String.format("%.2f%%", percentage));
        holder.itemView.setTag(recommendation);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        Recommendation recommendation = getItem(position);
        if (recommendation == null) {
            return super.getItemId(position);
        }
        return recommendation.getIndex();
    }

    private Recommendation getItem(int position) {
        return items.get(position);
    }

    void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public interface RecommendationClickListener {
        void onClick(Recommendation recommendation);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;
        final TextView probability;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.id_text);
            mContentView = view.findViewById(R.id.content);
            probability = view.findViewById(R.id.distance);
        }
    }
}
