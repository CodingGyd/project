package com.gyd.rookiepalmspace.modules.imagearea.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gyd.rookiepalmspace.base.entity.ImageInfo;
import com.gyd.rookiepalmspace.base.util.DensityUtils;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片浏览
 * Created by guoyd on 2016-03-24.
 */
public class ImgPagerAdapter extends PagerAdapter {

    private List<ImageInfo> mImageInfoList;
    private Context mContext;
    private List<ImageView> viewList;
    private static final String TAG = "ImgPagerAdapter";
//    private ImgTouchListener imgTouchListener;
    private MulitPointTouchListener mulitPointTouchListener ;
    public ImgPagerAdapter(Context context, List<ImageInfo> imageInfos) {
        this.mImageInfoList = imageInfos;
        this.mContext = context;

        this.viewList = new ArrayList<>();
        final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < mImageInfoList.size(); i++) {
            ImageInfo imageInfo = mImageInfoList.get(i);
            ImageView iv = new ImageView(mContext);
            mulitPointTouchListener = new MulitPointTouchListener(iv);
            iv.setOnTouchListener(mulitPointTouchListener);
            iv.setLayoutParams(params);
            iv.setTag(imageInfo);
            viewList.add(iv);
        }
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageView = viewList.get(position);
        ImageInfo imageInfo = (ImageInfo) imageView.getTag();

        container.addView(imageView);
        Picasso.with(mContext)
                .load(imageInfo.url)
                .resize(DensityUtils.dip2px(mContext.getResources(), 300), DensityUtils.dip2px(mContext.getResources(),400))
                .into(imageView);


        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

//    private class ImgTouchListener implements View.OnTouchListener {
//        private PointF beginP1;
//        private PointF beginP2;
//        private PointF endP1;
//        private PointF endP2;
//        private double endDis;
//        private double beginDis;
//        private boolean isTouch = false;
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            int actionCode = event.getAction();
//            switch (actionCode) {
//                case MotionEvent.ACTION_DOWN:
//                    if (isTouch) {
//                        endP1 = new PointF(event.getX(), event.getY());//第一个手指第二次落下的位置
//                    } else {
//                        beginP1 = new PointF(event.getX(), event.getY());//第一个手指第一次落下的位置
//                    }
//                    break;
//                case MotionEvent.ACTION_UP:
//                    isTouch = false;
//                    break;
//                case MotionEvent.ACTION_POINTER_DOWN:
//                    if (isTouch) {
//                        endP2 = new PointF(event.getX(), event.getY());//第二个手指落下的位置
//                        endDis = Math.sqrt(Math.pow(endP1.x - endP2.x, 2) + Math.pow(endP1.y - endP2.y, 2));
//                        ToastUtil.show(mContext, "drag");
//                    } else {
//                        beginP2 = new PointF(event.getX(), event.getY());//第二个手指落下的位置
//                        beginDis = Math.sqrt(Math.pow(beginP1.x - beginP1.x, 2) + Math.pow(beginP1.y - beginP2.y, 2));
//                        isTouch = true;
//                    }
//                    break;
//                case MotionEvent.ACTION_POINTER_UP:
//                    isTouch = false;
//                    break;
//                default:
//                    break;
//            }
//            return true;
//        }
//    }

    public class MulitPointTouchListener implements View.OnTouchListener {

        Matrix matrix = new Matrix();
        Matrix savedMatrix = new Matrix();

        public ImageView image;
        static final int NONE = 0;
        static final int DRAG = 1;
        static final int ZOOM = 2;
        int mode = NONE;

        PointF start = new PointF();
        PointF mid = new PointF();
        float oldDist = 1f;


        public MulitPointTouchListener(ImageView image) {
            super();
            this.image = image;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            this.image.setScaleType(ImageView.ScaleType.MATRIX);

            ImageView view = (ImageView) v;
            //        dumpEvent(event);

            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:

                    Log.w("FLAG", "ACTION_DOWN");
                    matrix.set(view.getImageMatrix());
                    savedMatrix.set(matrix);
                    start.set(event.getX(), event.getY());
                    mode = DRAG;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    Log.w("FLAG", "ACTION_POINTER_DOWN");
                    oldDist = spacing(event);
                    if (oldDist > 10f) {
                        savedMatrix.set(matrix);
                        midPoint(mid, event);
                        mode = ZOOM;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    Log.w("FLAG", "ACTION_UP");
                case MotionEvent.ACTION_POINTER_UP:
                    Log.w("FLAG", "ACTION_POINTER_UP");
                    mode = NONE;
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.w("FLAG", "ACTION_MOVE");
                    if (mode == DRAG) {
                        matrix.set(savedMatrix);
                        matrix.postTranslate(event.getX() - start.x, event.getY()
                                - start.y);
                    } else if (mode == ZOOM) {
                        float newDist = spacing(event);
                        if (newDist > 10f) {
                            matrix.set(savedMatrix);
                            float scale = newDist / oldDist;
                            matrix.postScale(scale, scale, mid.x, mid.y);
                        }
                    }
                    break;
            }

            view.setImageMatrix(matrix);
            return true;
        }


        private float spacing(MotionEvent event) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        }

        private void midPoint(PointF point, MotionEvent event) {
            float x = event.getX(0) + event.getX(1);
            float y = event.getY(0) + event.getY(1);
            point.set(x / 2, y / 2);
        }
    }
}
