package com.vickee.zhihubrief.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vickee.zhihubrief.R;
import com.vickee.zhihubrief.entity.NewsLatestResult;

import java.util.List;

/**
 * Created by Vickee on 2016/7/14.
 */
public class NewsLatestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsLatestResult.StoriesBean> storiesBeen;
    private Context mContext;
    private NewsLatestAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(NewsLatestAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public NewsLatestAdapter(Context context, List<NewsLatestResult.StoriesBean> storiesBeen) {
        this.mContext = context;
        this.storiesBeen = storiesBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LatestNewsOdd(LayoutInflater.from(mContext)
                .inflate(R.layout.item_latest_news_odd, null));

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final NewsLatestResult.StoriesBean story = storiesBeen.get(position);
        ((LatestNewsOdd) holder).tvTitle.setText(story.title);
        ((LatestNewsOdd) holder).tvId.setText("[图片" + story.images.size() + "张]");
        Picasso.with(mContext).load(story.images.get(0)).into(((LatestNewsOdd) holder).ivImage);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return storiesBeen.size();
    }

    class LatestNewsOdd extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvId;
        public ImageView ivImage;

        public LatestNewsOdd(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_latest_title_odd);
            tvId = (TextView) itemView.findViewById(R.id.tv_latest_id_odd);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_latest_image_odd);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
