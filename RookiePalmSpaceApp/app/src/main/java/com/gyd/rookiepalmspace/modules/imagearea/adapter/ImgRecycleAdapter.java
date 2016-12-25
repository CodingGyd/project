package com.gyd.rookiepalmspace.modules.imagearea.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.adapter.BaseRecyclerViewAdapter;
import com.gyd.rookiepalmspace.base.entity.ImageInfo;
import com.gyd.rookiepalmspace.base.util.DensityUtils;
import com.gyd.rookiepalmspace.base.util.IntentUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.modules.imagearea.ImgDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by guoyading on 2015-12-22.
 */
public class ImgRecycleAdapter extends BaseRecyclerViewAdapter<ImageInfo, ImgRecycleAdapter.NormalTextViewHolder> {

    public ImgRecycleAdapter(Context context, List<ImageInfo> data) {
        super(context, data);
    }


    //瀑布流
//    private void getRandomHeight(List<ImageInfo> lists){//得到随机item的高度
//        heights = new ArrayList<>();
//        for (int i = 0; i < lists.size(); i++) {
//            heights.add((int)(DensityUtils.dip2px(mContext.getResources(),180)+Math.random()*400));
//        }
//    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.adapter_fragment_img_list_grid_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {

//       ViewGroup.LayoutParams params =  holder.ivImg.getLayoutParams();//得到item的LayoutParams布局参数
//       params.height = heights.get(position);//把随机的高度赋予item布局
//       holder.ivImg.setLayoutParams(params);//把params设置给item布局

        ImageInfo imageInfo = mData.get(position);
        holder.tvImgName.setText(imageInfo.remark);
        holder.ivImgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(mContext, "detail");
            }
        });
        Picasso.with(mContext)
                .load(imageInfo.url)
                .resize(DensityUtils.dip2px(mContext.getResources(), 200), DensityUtils.dip2px(mContext.getResources(), 200))
                .into(holder.ivImg);

    }

    public  class NormalTextViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivImg;
        AppCompatTextView tvImgName;
        AppCompatImageView ivImgDetail;

        public NormalTextViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ImageInfo imageInfo = mData.get(getPosition());

                    Bundle bundle = new Bundle();
                    bundle.putInt("position",getPosition());
                    bundle.putInt("type",imageInfo.type);
                    IntentUtil.toAlpha((BaseActivity) mContext, ImgDetailActivity.class, bundle, null);
                }
            });
            ivImg = (AppCompatImageView) v.findViewById(R.id.iv_img);
            tvImgName = (AppCompatTextView) v.findViewById(R.id.tv_img_name);
            ivImgDetail = (AppCompatImageView) v.findViewById(R.id.iv_img_detail);
        }
    }


}
