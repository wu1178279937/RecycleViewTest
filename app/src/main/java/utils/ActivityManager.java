package utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;


/**
 * activity 管理
 *
 * @author longluliu
 *         Create at: 2014-9-30 下午3:24:02
 * @Filename: ActivityManager.java
 * @Description: TODO
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 */
public class ActivityManager {

    private static Map<String, Activity> mMapActivity = new HashMap<String, Activity>();


    /**
     * @param key 约定使用Class.getSimpleName()
     * @param activity activity
     */
    public static void addActivityToMap(String key, Activity activity) {
        mMapActivity.put(key, activity);
    }

    /**
     * @param key  约定使用Class.getSimpleName()
     */
    public static void removeActivityFromMap(String key) {
        if (mMapActivity.get(key) != null) {
            mMapActivity.get(key).finish();
            mMapActivity.remove(key);
        }
    }

    public static void clearMap() {
        if (mMapActivity != null && mMapActivity.size() > 0) {
            for (Map.Entry<String, Activity> entry : mMapActivity.entrySet()) {
                entry.getValue().finish();
            }
            mMapActivity.clear();
        }
    }

    public static boolean checkActivityExist(Context context, Intent intent){
              if (intent.resolveActivity(context.getPackageManager())!=null){
                   return true;
              }
              return  false;

    }
}
