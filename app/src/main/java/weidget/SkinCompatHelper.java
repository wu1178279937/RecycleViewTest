package weidget;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/7 17:13
 */
public abstract class SkinCompatHelper {
    public static final  String SYSTEM_ID_PREFIX="1";
    public static final  int INVALID_ID=0;

    final  static public int checkResultId(int resId){

        String  hexResId=Integer.toHexString(resId);
        return hexResId.startsWith(SYSTEM_ID_PREFIX)?INVALID_ID:resId;

    }

    abstract  public void applySkin();
}
