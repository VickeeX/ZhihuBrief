package com.vickee.zhihubrief;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vickee.zhihubrief.NewsResult.NewsLatestResult;

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
        if (viewType == 1) {
            return new LatestNewsOdd(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_latest_news_odd, null));
        } else {
            return new LatestNewsEven(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_latest_news_even, null));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final NewsLatestResult.StoriesBean story = storiesBeen.get(position);
        if (holder instanceof LatestNewsOdd) {
            ((LatestNewsOdd) holder).tvTitle.setText(story.title);
            ((LatestNewsOdd) holder).tvId.setText("[图片" + story.images.size() + "张]");
            Picasso.with(mContext).load(story.images.get(0)).into(((LatestNewsOdd) holder).ivImage);
        } else if (holder instanceof LatestNewsEven) {
            ((LatestNewsEven) holder).tvTitle.setText(story.title);
            ((LatestNewsEven) holder).tvId.setText("[图片" + story.images.size() + "张]");
            Picasso.with(mContext).load(story.images.get(0)).into(((LatestNewsEven) holder).ivImage);
        }
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView,position);
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

    class LatestNewsEven extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvId;
        public ImageView ivImage;

        public LatestNewsEven(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_latest_title_even);
            tvId = (TextView) itemView.findViewById(R.id.tv_latest_id_even);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_latest_image_even);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            // 在数组里，第一个位置为0，对应列表正常数的1 -- 奇数
            return 1;
        } else {
            return 0;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
