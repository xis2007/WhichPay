package com.whichpay.whichpay.recyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SearchResultsItemDecoration extends RecyclerView.ItemDecoration {
    public SearchResultsItemDecoration() {
//        int[] attrs = new int[]{android.R.attr.listDivider};
//        TypedArray a = context.obtainStyledAttributes(attrs);
//        mDivider = a.getDrawable(0);
//        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        drawHorizontal(c, parent, state);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, 20);
    }

//    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = parent.getChildAt(i);
//            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//            int left = child.getLeft() - params.leftMargin;
//            int top = child.getBottom() + params.bottomMargin;
//            int right = child.getRight() + params.rightMargin;
//            int bottom = top + mDivider.getIntrinsicHeight();
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(c);
//        }
//    }
}
