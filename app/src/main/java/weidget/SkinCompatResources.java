package weidget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/13 11:37
 */
public class SkinCompatResources {

    private static SkinCompatResources sInstance;
    private final Context mAppContext;
    private Resources resources;
    private String mSkinPkgName;
    private boolean isDefaultSkin;

    private SkinCompatResources(Context context) {
        this.mAppContext = context.getApplicationContext();
        setSkinResource(mAppContext.getResources(),mAppContext.getPackageName());

    }

    public static void  init(Context context){
        if (sInstance==null){
            synchronized (SkinCompatResources.class){
                if (sInstance==null){
                    sInstance=new SkinCompatResources(context);
                }
            }
        }
    }

    public static SkinCompatResources getInstance(){
        return  sInstance;
    }

    public void setSkinResource(Resources resource,String packageName){
        this.resources=resource;
         mSkinPkgName=packageName;
        isDefaultSkin=mAppContext.getPackageName().equals(packageName);
    }

    public Resources getResources() {
        return resources;
    }

    public String getmSkinPkgName() {
        return mSkinPkgName;
    }

    public boolean isDefaultSkin() {
        return isDefaultSkin;
    }

 public int getColor(int resId){
     int originColor= ContextCompat.getColor(mAppContext,resId);
     if (isDefaultSkin){
         return  originColor;
     }
     String resName=mAppContext.getResources().getResourceEntryName(resId);
     int targetId=resources.getIdentifier(resName,"color",mSkinPkgName);
     return  targetId==0?originColor:resources.getColor(targetId);

 }

    public Drawable getDrawable(int resId) {
        Drawable originDrawable = ContextCompat.getDrawable(mAppContext, resId);
        if (isDefaultSkin) {
            return originDrawable;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = resources.getIdentifier(resName, "drawable", mSkinPkgName);

        return targetResId == 0 ? originDrawable : resources.getDrawable(targetResId);
    }

    public Drawable getMipmap(int resId) {
        Drawable originDrawable = ContextCompat.getDrawable(mAppContext, resId);
        if (isDefaultSkin) {
            return originDrawable;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = resources.getIdentifier(resName, "mipmap", mSkinPkgName);

        return targetResId == 0 ? originDrawable : resources.getDrawable(targetResId);
    }

    public ColorStateList getColorStateList(int resId) {
        ColorStateList colorStateList = ContextCompat.getColorStateList(mAppContext, resId);
        if (isDefaultSkin) {
            return colorStateList;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = resources.getIdentifier(resName, "color", mSkinPkgName);

        return targetResId == 0 ? colorStateList : resources.getColorStateList(targetResId);
    }

}
