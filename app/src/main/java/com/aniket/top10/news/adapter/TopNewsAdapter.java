package com.aniket.top10.news.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aniket.top10.R;
import com.aniket.top10.database.topNews.TopNewsEntity;
import com.aniket.top10.databinding.RecyclerviewArticleLayoutBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.ArticleAdapterViewHolder> {
    List<TopNewsEntity> topNewsEntities;
    ArticleAdapterCallback callback;
    public TopNewsAdapter(List<TopNewsEntity> topNewsEntities, ArticleAdapterCallback callback){
        this.topNewsEntities = topNewsEntities;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ArticleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ArticleAdapterViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_article_layout, parent, false),parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapterViewHolder holder, int position) {
        holder.setData(topNewsEntities.get(position));
        holder.binding.readArticle.setOnClickListener(v -> {
            callback.readArticle(topNewsEntities.get(position).Url);
        });
    }

    @Override
    public int getItemCount() {
        return topNewsEntities.size();
    }

    public static class ArticleAdapterViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewArticleLayoutBinding binding;
        Context context;
        public ArticleAdapterViewHolder(RecyclerviewArticleLayoutBinding itemView, Context context) {
            super(itemView.getRoot());
            this.binding = itemView;
            this.context = context;
        }

        public void setData(TopNewsEntity topNewsEntity){
            binding.description.setText(topNewsEntity.description);
            binding.heading.setText(topNewsEntity.title);
            Glide.with(binding.imageHolder).load(topNewsEntity.media)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(binding.imageHolder);
        }
    }

    public interface ArticleAdapterCallback{
        void readArticle(String url);
    }
}
