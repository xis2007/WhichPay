package com.whichpay.whichpay.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.whichpay.whichpay.R;

import java.util.ArrayList;

public class ExplorePagerAdapter extends PagerAdapter {
    private Context mContext;
//    private ArrayList<String> mImagesList;
    private ArrayList<Integer> mImagesList;

    public ExplorePagerAdapter(Context context, ArrayList<Integer> imagesList) {
        mContext = context;
        mImagesList = imagesList;
    }

    @Override
    public int getCount() {
        return mImagesList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pager_content_explore, container, false);
        ImageView pagerImage = view.findViewById(R.id.image_pager);

        Glide.with(mContext).load(mContext.getResources().getDrawable(mImagesList.get(position))).into(pagerImage);

        container.addView(view);
        Log.d("pagerrrrr", "instantiateItem: pager added");
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
