package viewlink;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/5 16:06
 */
public class ViewEventStub extends EventStub<View> {

    public ViewEventStub(IEvent mEventStub, View viewStub) {
        super(mEventStub, viewStub);
    }


    @Override
    protected boolean onEventImpl(@NonNull View obj) {
        View tempView=obj;
        if (tempView.getVisibility()==View.VISIBLE){
            tempView.setVisibility(View.INVISIBLE);
            return  true;
        }

        return false;
    }
}
