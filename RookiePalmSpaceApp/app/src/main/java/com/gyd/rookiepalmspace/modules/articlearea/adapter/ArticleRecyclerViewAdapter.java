package com.gyd.rookiepalmspace.modules.articlearea.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.adapter.BaseRecyclerViewAdapter;
import com.gyd.rookiepalmspace.base.entity.ArticleInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by guoyading on 2015-12-20.
 */
public class ArticleRecyclerViewAdapter extends BaseRecyclerViewAdapter<ArticleInfo, ArticleRecyclerViewAdapter.NormalTextViewHolder>{
    public ArticleRecyclerViewAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.adapter_fragment_article_list_item, parent, false);
        return new NormalTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        ArticleInfo articleInfo = mData.get(position);

        holder.tvArticleTitle.setText(articleInfo.title);
        Long l = Long.parseLong(articleInfo.time);

        holder.tvArticleTime.setText( String.format("%tD",  l));


        holder.tvArticleContent.setText(articleInfo.content);
        holder.tvArticleLocation.setText(articleInfo.location);
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvArticleTitle;
        AppCompatTextView tvArticleTime;
        AppCompatTextView tvArticleContent;
        AppCompatTextView tvArticleLocation;
        NormalTextViewHolder(View view) {
            super(view);
            tvArticleTitle = (AppCompatTextView) view.findViewById(R.id.tv_article_title);
            tvArticleTime = (AppCompatTextView) view.findViewById(R.id.tv_article_time);
            tvArticleContent = (AppCompatTextView) view.findViewById(R.id.tv_article_content);
            tvArticleLocation = (AppCompatTextView) view.findViewById(R.id.tv_article_location_value);

        }
    }

}