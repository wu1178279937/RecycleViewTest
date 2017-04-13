package weidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/7 16:22
 */
public class MyLayoutInflator extends LayoutInflater {


    protected MyLayoutInflator(Context context) {
        super(context);
    }

    protected MyLayoutInflator(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new MyLayoutInflator(newContext);
    }

    @Override
    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {

        return super.onCreateView(name, attrs);
    }
}
