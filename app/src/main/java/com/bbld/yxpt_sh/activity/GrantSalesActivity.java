package com.bbld.yxpt_sh.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bbld.yxpt_sh.R;
import com.bbld.yxpt_sh.base.BaseActivity;
import com.bbld.yxpt_sh.bean.GetShopReturnOrderList;
import com.bbld.yxpt_sh.bean.GetTodayShopOrderList;
import com.bbld.yxpt_sh.network.RetrofitService;
import com.bbld.yxpt_sh.utils.YXPT_SHToken;
import com.bbld.yxpt_sh.widget.CircleImageView;
import com.bumptech.glide.Glide;
import com.wuxiaolong.androidutils.library.ActivityManagerUtil;

import java.util.List;

import butterknife.BindView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 获取商户已发放订单列表
 * Created by zzy on 2017/7/1.
 */

public class GrantSalesActivity extends BaseActivity{
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.srl_member)
    SwipeRefreshLayout srlMember;
    private int pageIndex=1;
    private int pageSize=10000;
    private List<GetShopReturnOrderList.GetShopReturnOrderListRes> shopreturnorderlist;
    private TodayShopOrderlistAdapter adapter;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    srlMember.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void initViewsAndEvents() {
        loadData(false);
        setListeners();
    }
    private void setListeners() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManagerUtil.getInstance().finishActivity(GrantSalesActivity.this);
            }
        });
        srlMember.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex=1;
                        loadData(false);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });
    }
    private void loadData(final boolean isLoadMore) {
        Call<GetShopReturnOrderList> call= RetrofitService.getInstance().getShopReturnOrderList(new YXPT_SHToken(GrantSalesActivity.this).getToken(), pageIndex+"", pageSize+"");
        call.enqueue(new Callback<GetShopReturnOrderList>() {
            @Override
            public void onResponse(Response<GetShopReturnOrderList> response, Retrofit retrofit) {
                if (response==null){
                    return;
                }
                if (response.body().getStatus()==0){
                    if (isLoadMore){
                        List<GetShopReturnOrderList.GetShopReturnOrderListRes> productsAdd = response.body().getList();
                        tvTotal.setText(response.body().getTotal()+"");
                        tvCount.setText(response.body().getCount()+"");
                        shopreturnorderlist.addAll(productsAdd);
                        adapter.notifyDataSetChanged();
                    }else{
                        tvTotal.setText(response.body().getTotal()+"");
                        shopreturnorderlist = response.body().getList();
                        tvCount.setText(response.body().getCount()+"");
                        if(shopreturnorderlist .isEmpty()){
                            setAdapter();
                        }else{
                            setAdapter();
                        }
                    }
                }else{
                    showToast(response.body().getMes()+"");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    private void setAdapter() {
        adapter=new TodayShopOrderlistAdapter();
        lvList.setAdapter(adapter);
    }

    class TodayShopOrderlistAdapter extends BaseAdapter {
        ShopReturnOrderListHolder holder=null;
        @Override
        public int getCount() {
            return shopreturnorderlist.size();
        }

        @Override
        public GetShopReturnOrderList.GetShopReturnOrderListRes getItem(int i) {
            return shopreturnorderlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return Long.parseLong(shopreturnorderlist.get(i).getNOID()+"");
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_lv_3sales,null);
                holder=new ShopReturnOrderListHolder();
                holder.tvNickName=(TextView)view.findViewById(R.id.tv_NickName);
                holder.tvAddDate=(TextView)view.findViewById(R.id.tv_AddDate);
                holder.tvEnterAmount=(TextView)view.findViewById(R.id.tv_EnterAmount);
                holder.ivHeadPortrait=(CircleImageView)view.findViewById(R.id.iv_HeadPortrait);

                view.setTag(holder);
            }
            holder= (ShopReturnOrderListHolder) view.getTag();
            final GetShopReturnOrderList.GetShopReturnOrderListRes list = getItem(i);
            holder.tvNickName.setText(list.getNickName()+"");
            holder.tvAddDate.setText("订单时间："+list.getAddDate());
            holder.tvEnterAmount.setText("￥"+list.getEnterAmount());
            holder.tvEnterAmount.setTextColor(Color.rgb(102,204,255));
            Glide.with(getApplicationContext()).load(list.getHeadPortrait()).error(R.drawable.zwt).into(holder.ivHeadPortrait);
            return view;
        }
        class ShopReturnOrderListHolder{
            TextView tvNickName;
            CircleImageView ivHeadPortrait;
            TextView tvAddDate;
            TextView tvEnterAmount;
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        loadData(false);
    }
    @Override
    protected void getBundleExtras(Bundle extras) {

    }



    @Override
    public int getContentView() {
        return R.layout.activity_grantsales;
    }
}
