package junte.com.recycleviewtest;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import adapter.HomepagerRecycleAdapter;
import bean.Headerbean;
import bean.HomeCategory;
import bean.RefreshBean;
import butterknife.Bind;
import butterknife.ButterKnife;
import constants.Contants;
import okhttp3.Call;
import okhttp3.Response;
import utils.RefreshUtils;
import weidget.MyStaggerGrildLayoutManger;

public class MainActivity extends AppCompatActivity  implements RefreshUtils.RefreshListener {

    @Bind(R.id.rv_mainactivity)
    RecyclerView rvMainactivity;
    @Bind(R.id.mrl_mainactivity)
    MaterialRefreshLayout mrfMainactivity;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    private RefreshUtils refreshUtils;
    private Context mContext;
    private HomepagerRecycleAdapter homepagerRecycleAdapter;
    private boolean flagFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        initrefresh();
        initdata();



    }

    private void initdata() {

        homepagerRecycleAdapter=new HomepagerRecycleAdapter(mContext);
        rvMainactivity.setAdapter(homepagerRecycleAdapter);
        rvMainactivity.setLayoutManager(new MyStaggerGrildLayoutManger(mContext,2, StaggeredGridLayoutManager.VERTICAL));
         getHeaderData();
        getcategoryData();
        getCenterBean();
        getRefreshData();



    }

    private void getHeaderData() {
        OkGo.get(Contants.API.HOST_SLIDLAYOUT).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                s = "{" + "\"data\":" + s + "}";

                Headerbean headerbean = new Gson().fromJson(s, Headerbean.class);

                if (headerbean != null) {

                    if (headerbean.getData().size() != 0) {
                        homepagerRecycleAdapter.setheaderbean(headerbean);

                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }
        });

    }

    private void getcategoryData() {

        ArrayList<HomeCategory> homeCategories = new ArrayList<>();
        HomeCategory c1 = new HomeCategory(R.mipmap.icon_cart, "购物车");
        HomeCategory c2 = new HomeCategory(R.mipmap.icon_discover, "发现");
        HomeCategory c3 = new HomeCategory(R.mipmap.icon_hot, "热门");
        HomeCategory c4 = new HomeCategory(R.mipmap.icon_user, "寻找");
        homeCategories.add(c1);
        homeCategories.add(c2);
        homeCategories.add(c3);
        homeCategories.add(c4);
        homepagerRecycleAdapter.setCategoryBean(homeCategories);
    }

    private void getCenterBean() {

        OkGo.get(Contants.API.CAMPAIGN_HOME)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        s = "{" + "\"data\":" + s + "}";
                        RefreshBean refreshBean = new Gson().fromJson(s, RefreshBean.class);
                        if (refreshBean != null) {
                            if (refreshBean.getData().size() != 0) {
                                homepagerRecycleAdapter.setCenterBean(refreshBean);
                                refreshUtils.finishrefreshSleep();

                            }
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.e("ssss", "onError: " + e.toString());
                        refreshUtils.finishRefresh();
                    }
                });


    }


    private void getRefreshData() {


        OkGo.get(Contants.API.CAMPAIGN_HOME)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        s = "{" + "\"data\":" + s + "}";
                        RefreshBean refreshBean = new Gson().fromJson(s, RefreshBean.class);
                        if (refreshBean != null) {
                            if (refreshBean.getData().size() != 0) {
                                homepagerRecycleAdapter.setRefreshBean(refreshBean, flagFirst);
                                if (flagFirst) {
                                    refreshUtils.finishrefreshSleep();
                                    flagFirst = false;
                                } else
                                    refreshUtils.finishRefresh();
                            }
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.e("ssss", "onError: " + e.toString());
                        refreshUtils.finishRefresh();
                    }
                });

    }
    private void initrefresh(){
        refreshUtils=new RefreshUtils();
        refreshUtils.newBuilder(mContext).setRefreshLayout(mrfMainactivity)
                .setCanLoadMore(true).build(this);

    }


    @Override
    public void refreshData() {
        flagFirst=true;
        initdata();

    }

    @Override
    public void loadRefreshData() {
          new Thread(new Runnable() {
              @Override
              public void run() {
                  try {
                      Thread.sleep(500);
                      getRefreshData();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  } finally {
                  }
              }
          }).start();
    }
}
