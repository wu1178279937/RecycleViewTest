package utils;

import android.content.AsyncTaskLoader;
import android.content.Context;


/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/13 16:29
 */
public class FileLoader extends AsyncTaskLoader<String> {


    public FileLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        return null;
    }

}
