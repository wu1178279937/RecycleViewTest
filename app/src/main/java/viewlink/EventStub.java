package viewlink;

import android.support.annotation.NonNull;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/5 16:03
 */
public  abstract  class EventStub<T> implements IEvent<T> {
    protected  IEvent mEventStub;
    private T viewStub;

    public EventStub(IEvent mEventStub, T viewStub) {
        this.mEventStub = mEventStub;
        this.viewStub = viewStub;
    }

    @Override
    public boolean onEvent(@NonNull T obj) {
        boolean b=onEventImpl(obj);
        if (!b&&mEventStub!=null){
            return mEventStub.onEvent(obj);
        }
        return b;
    }


    protected abstract boolean onEventImpl(@NonNull T obj);
}
