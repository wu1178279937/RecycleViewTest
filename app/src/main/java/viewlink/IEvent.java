package viewlink;

import android.support.annotation.NonNull;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/5 16:03
 */
public interface IEvent<T> {
    public boolean onEvent(@NonNull T obj);
}
