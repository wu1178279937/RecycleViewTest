package utils;

import android.content.Context;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/1 15:53
 */
public class RefreshUtils {

     private RefreshListener mRefreshListener;
     private Builder builder;

    public RefreshUtils() {

    }

    public Builder newBuilder(Context context) {

        builder = new Builder(context);
        return builder;
    }

    private void initRefreshLayout(){

         builder.mRefreshLayout.setLoadMore(builder.canLoadMore);
         builder.mRefreshLayout.setIsOverLay(builder.isOverLay);
         builder.mRefreshLayout.setWaveShow(builder.isWaveShow);
         builder.mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
             @Override
             public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                 if (mRefreshListener!=null){
                     mRefreshListener.refreshData();
                 }
             }

             @Override
             public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                  if (mRefreshListener!=null){
                      mRefreshListener.loadRefreshData();
                  }

             }
         });


    }

     public void  finishRefresh(){
         builder.mRefreshLayout.finishRefresh();
         builder.mRefreshLayout.finishRefreshLoadMore();
     }

    public void finishrefreshSleep() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    builder.mRefreshLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            //结束刷新
                            finishRefresh();

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    builder.mRefreshLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            //结束刷新
                            finishRefresh();

                        }
                    });
                }
            }
        }).start();

    }

    public class  Builder{

        private Context mContext;
        private MaterialRefreshLayout  mRefreshLayout;
        private boolean canLoadMore; //能否加载更多
        private  boolean isOverLay=true;  //是否入侵
        private  boolean isWaveShow=false;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setOverLay(boolean overLay) {
            isOverLay = overLay;
            return  builder;
        }

        public Builder setCanLoadMore(boolean canLoadMore) {
            this.canLoadMore = canLoadMore;
            return builder;
        }

        public Builder setWaveShow(boolean isWaveShow) {
            this.isWaveShow = isWaveShow;
            return builder;
        }

        public Builder setRefreshLayout(MaterialRefreshLayout refreshLayout) {

            this.mRefreshLayout = refreshLayout;
            return builder;
        }
        public void build(RefreshListener refreshListener){
             mRefreshListener=refreshListener;
            valid();
             initRefreshLayout();
        }

        //异常情况
        private void valid() {


            if (this.mContext == null)
                throw new RuntimeException("content can't be null");


            if (this.mRefreshLayout == null)
                throw new RuntimeException("MaterialRefreshLayout can't be  null");
        }

    }



    public interface RefreshListener{
        void  refreshData();
        void  loadRefreshData();
    }
}
