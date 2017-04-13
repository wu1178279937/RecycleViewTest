package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bean.RefreshBean;
import butterknife.Bind;
import butterknife.ButterKnife;
import junte.com.recycleviewtest.R;
import weidget.ImageUtils;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/1 11:23
 */
public class TypeHistoryAdapter extends RecyclerView.Adapter<TypeHistoryAdapter.TypeHistoryHolder> {

    private Context mContext;

    private List<RefreshBean.DataBean> mHomehopspot;

    private LayoutInflater inflater;

    public TypeHistoryAdapter(Context mContext, List<RefreshBean.DataBean> mHomehopspot) {
        this.mContext = mContext;
        this.mHomehopspot = mHomehopspot;
        inflater = LayoutInflater.from(mContext);    }

    @Override
    public TypeHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

         return new TypeHistoryHolder(inflater.inflate(R.layout.item_raiders, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeHistoryHolder holder, int position) {
        RefreshBean.DataBean dataBean = mHomehopspot.get(position);
        ImageUtils.load(mContext,dataBean.getCpThree().getImgUrl(),holder.homeReadPivIv);
         holder.homeReadTitle.setText("#:"+dataBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mHomehopspot==null?0:mHomehopspot.size();
    }

    public class TypeHistoryHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.home_read_piv_iv)
        ImageView homeReadPivIv;
        @Bind(R.id.home_read_title)
        TextView homeReadTitle;


        public TypeHistoryHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
