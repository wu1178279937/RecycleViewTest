package weidget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

import junte.com.recycleviewtest.R;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/7 17:16
 */
public class SkinCompatBackgroundHelper extends SkinCompatHelper {

    private final View mView;
    private int mBackgroundResId=INVALID_ID;

    public SkinCompatBackgroundHelper(View view) {
        this.mView = view;
    }

     public void loadFromAttributes(AttributeSet set,int defStyleAttr){

         TintTypedArray a=TintTypedArray.obtainStyledAttributes(mView.getContext(),set, R.styleable.radio_view,defStyleAttr,0);
          try {

              if (a.hasValue(R.styleable.radio_view_bgcolor)){
                  mBackgroundResId=a.getColor(R.styleable.radio_view_bgcolor,INVALID_ID);

              }
          }finally {
              a.recycle();
          }

       applySkin();

     }

        public void onSetBackgroundResource(int resId){
            mBackgroundResId=resId;
                applySkin();
        }

    @Override
    public void applySkin() {

        mBackgroundResId=checkResultId(mBackgroundResId);
        if (mBackgroundResId==INVALID_ID){
            return;
        }
        String typeName=mView.getResources().getResourceTypeName(mBackgroundResId);
         if ("color".equals(typeName)){
             if (Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP){
                   int color=SkinCompatResources.getInstance().getColor(mBackgroundResId);
                  mView.setBackgroundColor(color);
             }else{
                 ColorStateList stateList=SkinCompatResources.getInstance().getColorStateList(mBackgroundResId);
                 Drawable drawable=mView.getBackground();
                 DrawableCompat.setTintList(drawable,stateList);
                 ViewCompat.setBackground(mView,drawable);
             }
         }else if ("drawable".equals(typeName)){
             Drawable drawable=SkinCompatResources.getInstance().getDrawable(mBackgroundResId);
             ViewCompat.setBackground(mView,drawable);

         }else if ("mipmap".equals(typeName)){
             Drawable drawable=SkinCompatResources.getInstance().getMipmap(mBackgroundResId);
             ViewCompat.setBackground(mView,drawable);
         }



    }
}
