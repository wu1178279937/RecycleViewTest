package weidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import junte.com.recycleviewtest.R;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/7 15:49
 */
public class RadarView extends View {

    int[]mAttr={R.attr.circlecount};

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);

      TypedArray a= context.obtainStyledAttributes(attrs, mAttr);
            a.getInteger(0,-1);

    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
