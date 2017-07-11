package com.bbld.yxpt_sh.activity;

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
import com.bbld.yxpt_sh.bean.GetShopActivityList;
import com.bbld.yxpt_sh.bean.UpdatePassword;
import com.bbld.yxpt_sh.network.RetrofitService;
import com.bbld.yxpt_sh.utils.YXPT_SHToken;

import java.util.List;

import butterknife.BindView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 活动管理
 * Created by zzy on 2017/7/3.
 */

public class ShopActivityManagementActivity extends BaseActivity{
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.srl_member)
    SwipeRefreshLayout srlMember;
    private int pageIndex=1;
    private int pageSize=10000;
    private List<GetShopActivityList.GetShopActivityListRes> shopactivityList;
    private ShopActivityListAdapter adapter;
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
    private void loadData(final boolean isLoadMore) {
        Call<GetShopActivityList> call= RetrofitService.getInstance().getShopActivityList(new YXPT_SHToken(ShopActivityManagementActivity.this).getToken(), pageIndex+"", pageSize+"");
        call.enqueue(new Callback<GetShopActivityList>() {
            @Override
            public void onResponse(Response<GetShopActivityList> response, Retrofit retrofit) {
                if (response==null){
                    return;
                }
                if (response.body().getStatus()==0){
                    if (isLoadMore){
                        List<GetShopActivityList.GetShopActivityListRes> productsAdd = response.body().getList();
                        shopactivityList.addAll(productsAdd);
                        adapter.notifyDataSetChanged();
                    }else{
                        shopactivityList = response.body().getList();
                        if( shopactivityList .isEmpty()){
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
    private void setListeners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(ShopActivitySettings.class);
            }
        });
    }
    private void setAdapter() {
        adapter=new ShopActivityListAdapter();
        lvList.setAdapter(adapter);
    }
    class ShopActivityListAdapter extends BaseAdapter {
        ShopActivityListHolder holder=null;
        @Override
        public int getCount() {
            return shopactivityList.size();
        }

        @Override
        public GetShopActivityList.GetShopActivityListRes getItem(int i) {
            return shopactivityList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return Long.parseLong(shopactivityList.get(i).getNOID()+"");
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_lv_shopactivitymanagement,null);
                holder=new ShopActivityListHolder();
                holder.tvTitle=(TextView)view.findViewById(R.id.tv_Title);
                holder.tvDiscount=(TextView)view.findViewById(R.id.tv_Discount);
                holder.tvTime=(TextView)view.findViewById(R.id.tv_Time);
                holder.tvRemark=(TextView)view.findViewById(R.id.tv_Remark);
                holder.tvGo=(TextView)view.findViewById(R.id.tv_go);
                view.setTag(holder);
            }
            holder= (ShopActivityListHolder) view.getTag();
            final GetShopActivityList.GetShopActivityListRes list = getItem(i);
            holder.tvTitle.setText(list.getTitle()+"");
            holder.tvDiscount.setText(list.getDiscount()+"");
            holder.tvTime.setText(list.getTitle()+"");
            holder.tvRemark.setText(list.getRemark()+"");
            holder.tvGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("ShopActivityID", list.getNOID());
                    bundle.putString("Title",list.getTitle());
                    bundle.putString("Discount",list.getDiscount());
                    bundle.putString("Remark",list.getRemark());
                    readyGo(UpdateShopActivityActivity.class,bundle);
                }
            });
            return view;
        }
        class ShopActivityListHolder{
            TextView tvTitle;
            TextView tvDiscount;
            TextView tvTime;
            TextView tvRemark;
            TextView tvGo;
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
        return R.layout.activity_shopactivitymanagement;
    }
}
