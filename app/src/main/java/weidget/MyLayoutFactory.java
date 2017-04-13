package weidget;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/7 16:16
 */
public class MyLayoutFactory implements LayoutInflaterFactory {

    MyLayoutInflator layoutInflator;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs)  {
        if (layoutInflator==null){
            layoutInflator=new MyLayoutInflator(context);
        }
        View view=null;

        try {
            view=layoutInflator.onCreateView(name,attrs);
           
        }catch (Exception e){

        }


        return view;
    }
}
