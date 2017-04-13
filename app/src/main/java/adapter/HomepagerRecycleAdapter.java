package adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bean.Headerbean;
import bean.HomeCategory;
import bean.RefreshBean;
import butterknife.Bind;
import butterknife.ButterKnife;
import junte.com.recycleviewtest.R;
import weidget.AsHomepageHeaderView;
import weidget.ImageUtils;
import weidget.MyStaggerGrildLayoutManger;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/1 11:36
 */
public class HomepagerRecycleAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<Headerbean.DataBean> headerData;
    private int count = 3;
    private List<RefreshBean.DataBean> refreshbean;
    private List<RefreshBean.DataBean> centerBean;
    private ArrayList<HomeCategory> mHomeCategories;
    private int TYPE_TOP = 1;//头部布局
    private List<Integer> mHeights = new ArrayList<>();
    private int TYPE_CENTER = 2;//
    private int TYPE_CATEGORY = 3;//中间的四个快速入口
    private int TYPE_HEADER = 4;//每个分类的head
    private int REFRESHPOSITION = 5;//下部head的位置
    private int CENTERPOSITION;//中间head的位置
    private int TYPE_REFRESH = 6;//最下面的布局
    private LayoutInflater inflater;
    private RecyclerView recyclerView;
    private MyStaggerGrildLayoutManger mystager;

    public HomepagerRecycleAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        //初始化各我数据源
        headerData = new ArrayList<>();
        refreshbean = new ArrayList<>();
        centerBean = new ArrayList<>();


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if (viewType==TYPE_TOP){
             View viewTop=inflater.inflate(R.layout.adapter_slider,parent,false);
             StaggeredGridLayoutManager.LayoutParams params=(StaggeredGridLayoutManager.LayoutParams)viewTop.getLayoutParams();
             params.setFullSpan(true);
             viewTop.setLayoutParams(params);
             return new TypeTopsliderHolder(viewTop);
         }else if (viewType==TYPE_HEADER){
             View viewTop=inflater.inflate(R.layout.item_homepagertypeheader_type,parent,false);
             StaggeredGridLayoutManager.LayoutParams params=(StaggeredGridLayoutManager.LayoutParams)viewTop.getLayoutParams();
             params.setFullSpan(true);
             viewTop.setLayoutParams(params);
             return new TypeheadHolder(viewTop);

         }else if (viewType==TYPE_CENTER){

             View viewTop=inflater.inflate(R.layout.itam_homepageradapter_rv2,parent,false);
             StaggeredGridLayoutManager.LayoutParams params=(StaggeredGridLayoutManager.LayoutParams)viewTop.getLayoutParams();
             params.setFullSpan(true);
             viewTop.setLayoutParams(params);
             return new TypetypeHolder2(viewTop);
         }else if (viewType == TYPE_CATEGORY){
             View view = inflater.inflate(R.layout.itam_homepageradapter_rv2, parent, false);
             StaggeredGridLayoutManager.LayoutParams params2 =
                     (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
             params2.setFullSpan(true);
             view.setLayoutParams(params2);
             return new TypetypeHolder(view);
         }else if (viewType == TYPE_REFRESH) {
             return new TypeRefresh(inflater.inflate(R.layout.item_raiders2, parent, false));
         }else{
             View viewtop = inflater.inflate(R.layout.adapter_slider, parent, false);
             StaggeredGridLayoutManager.LayoutParams params =
                     (StaggeredGridLayoutManager.LayoutParams) viewtop.getLayoutParams();
             params.setFullSpan(true);
             viewtop.setLayoutParams(params);
             return new TypeTopsliderHolder(viewtop);
         }



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         if (holder instanceof TypeTopsliderHolder&&headerData.size()!=0&&((TypeTopsliderHolder)holder).linearLayout.getChildCount()==0){
                 initslider((TypeTopsliderHolder)holder,headerData);
         } else if (holder instanceof TypetypeHolder&&centerBean.size()!=0){
                initcategory((TypetypeHolder)holder);
         }else if (holder instanceof TypeheadHolder){
             initTop((TypeheadHolder)holder,position);
         }else if (holder instanceof TypetypeHolder2&&centerBean.size()!=0){

             initCenterBean((TypetypeHolder2)holder);
         }else if (holder instanceof TypeRefresh &&refreshbean.size()!=0){
             initrefreshdata(((TypeRefresh) holder), position - REFRESHPOSITION - 1);//加载瀑布流数据源
         }


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
         this.recyclerView=recyclerView;
        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
         if (layoutManager instanceof MyStaggerGrildLayoutManger){
              mystager=(MyStaggerGrildLayoutManger)layoutManager;

         }
         recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                  if (newState==2){
                      Glide.with(mContext).pauseRequests();
                  }else {
                      Glide.with(mContext).resumeRequests();
                  }

             }


         });
    }

    private void initrefreshdata(TypeRefresh holder, int postion) {
          if (mHeights.size()<getItemCount()+2){
              mHeights.add((int)(500+Math.random()*400));
          }
         ViewGroup.LayoutParams params=holder.homeReadPivIv.getLayoutParams();
         if (mHeights.size()>postion){
             params.height=mHeights.get(postion);
         }else {
             params.height=589;
         }

         holder.homeReadPivIv.setLayoutParams(params);
         holder.homeReadPivIv.setScaleType(ImageView.ScaleType.FIT_XY);
         if (refreshbean.size()>postion){
             ImageUtils.load(mContext,refreshbean.get(postion).getCpOne().getImgUrl(),holder.homeReadPivIv);
         }else {
             ImageUtils.load(mContext,refreshbean.get(0).getCpTwo().getImgUrl(),holder.homeReadPivIv);
         }
    }

    private void initslider(TypeTopsliderHolder holder, List<Headerbean.DataBean> data) {
        LinearLayout linearLayout=holder.linearLayout;
        for (int i = 0; i <data.size() ; i++) {

            ImageView imgView=new ImageView(mContext);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.rightMargin=40;
            imgView.setLayoutParams(params);
            ImageUtils.load(mContext,data.get(i).getImgUrl(),imgView);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            linearLayout.addView(imgView);
        }

    }

    private void initCenterBean(TypetypeHolder2 holder) {
        holder.rvtype.setLayoutManager(new GridLayoutManager(mContext, 2));

        TypeHistoryAdapter centerAdapter = new TypeHistoryAdapter(mContext, centerBean);


        holder.rvtype.setAdapter(centerAdapter);


    }
    private void initcategory(TypetypeHolder holder) {

        holder.rvtype.setLayoutManager(new GridLayoutManager(mContext, mHomeCategories.size()));

        TypeCategoryAdapter categoryAdapter = new TypeCategoryAdapter(mContext, mHomeCategories);

        holder.rvtype.setAdapter(categoryAdapter);


    }

    private void initTop(TypeheadHolder holder, int position) {
        if (position == CENTERPOSITION) {
            holder.hview.setTypeName("中间head");

        } else if (position == REFRESHPOSITION) {
            holder.hview.setTypeName("下部head");

        }

    }
    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public int getItemViewType(int position) {
         CENTERPOSITION=mHomeCategories.size()==0?1:2;
         REFRESHPOSITION=centerBean.size()==0?3:4;
         if (position==0) return TYPE_TOP;
          else if (position==CENTERPOSITION||position==REFRESHPOSITION) return TYPE_HEADER;
          else if (position==1) return TYPE_CATEGORY;
          else  if (position==CENTERPOSITION+1) return TYPE_CENTER;
          else return  TYPE_REFRESH;



    }

    //头部Viewpager viewholder
    public class TypeTopsliderHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ll_slider)
        LinearLayout linearLayout;

        public TypeTopsliderHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }


    }

    public class TypeheadHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ashv_homepager)
        AsHomepageHeaderView hview;

        public TypeheadHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            hview.setMoreclicklistenser(new AsHomepageHeaderView.MoreclickListenser() {
                @Override
                public void setmoreclicklistenser() {
                }
            });
        }
    }

    //中间的四个type
    public class TypetypeHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rv_homepageradapter_artist)
        RecyclerView rvtype;


        public TypetypeHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class TypetypeHolder2 extends RecyclerView.ViewHolder {
        @Bind(R.id.rv_homepageradapter_artist)
        RecyclerView rvtype;


        public TypetypeHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static class TypeRefresh extends RecyclerView.ViewHolder {
        @Bind(R.id.home_read_piv_iv)
        ImageView homeReadPivIv;

        TypeRefresh(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setheaderbean(Headerbean headerbean) {
        headerData = headerbean.getData();
        notifyDataSetChanged();
    }

    public void setRefreshBean(RefreshBean refreshBean, boolean flagFirst) {
        refreshbean.addAll(refreshBean.getData());
        int count1 = this.count;
        this.count += refreshBean.getData().size();
        notifyDataSetChanged();
        if (!flagFirst) {
            recyclerView.smoothScrollToPosition(count1 + 2);//加载完以后向上滚动3个条目
        }

      }

    public void setCenterBean(RefreshBean refreshBean) {
        centerBean = refreshBean.getData();
        count++;
        notifyDataSetChanged();
    }

    public void setCategoryBean(ArrayList<HomeCategory> homeCategories) {
        mHomeCategories = homeCategories;
        count++;
        notifyDataSetChanged();

    }

}
