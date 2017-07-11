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

import com.baidu.mapapi.map.Text;
import com.bbld.yxpt_sh.R;
import com.bbld.yxpt_sh.base.BaseActivity;
import com.bbld.yxpt_sh.bean.GetAccountMoneyLog;
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
 * 我的账户
 * Created by dell on 2017/7/7.
 */

public class MyAccountActivity extends BaseActivity{
    @BindView(R.id.tv_accountMoney)
    TextView tvAccountMoney;
    @BindView(R.id.tv_go)
    TextView tvGo;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.srl_member)
    SwipeRefreshLayout srlMember;
    private int pageIndex=1;
    private int pageSize=10000;
    private List<GetAccountMoneyLog.GetAccountMoneyLogRes> accountMoneyLoglist;
    private GetAccountMoneyLogListAdapter adapter;
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
        tvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               readyGo(WithdrawScashActivity.class);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManagerUtil.getInstance().finishActivity(MyAccountActivity.this);
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
        Call<GetAccountMoneyLog> call= RetrofitService.getInstance().getAccountMoneyLog(new YXPT_SHToken(MyAccountActivity.this).getToken(), pageIndex+"", pageSize+"");
        call.enqueue(new Callback<GetAccountMoneyLog>() {
            @Override
            public void onResponse(Response<GetAccountMoneyLog> response, Retrofit retrofit) {
                if (response==null){
                    return;
                }
                if (response.body().getStatus()==0){
                    tvAccountMoney.setText(response.body().getAccountMoney());
                    if (isLoadMore){
                        List<GetAccountMoneyLog.GetAccountMoneyLogRes> productsAdd = response.body().getList();
                        accountMoneyLoglist.addAll(productsAdd);
                        adapter.notifyDataSetChanged();
                    }else{
                        accountMoneyLoglist = response.body().getList();
                        if( accountMoneyLoglist .isEmpty()){
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
        adapter=new GetAccountMoneyLogListAdapter();
        lvList.setAdapter(adapter);
    }

    class GetAccountMoneyLogListAdapter extends BaseAdapter {
        GetAccountMoneyLogListHolder holder=null;
        @Override
        public int getCount() {
            return accountMoneyLoglist.size();
        }

        @Override
        public GetAccountMoneyLog.GetAccountMoneyLogRes getItem(int i) {
            return accountMoneyLoglist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return Long.parseLong(accountMoneyLoglist.get(i).getID()+"");
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_lv_myaccount,null);
                holder=new GetAccountMoneyLogListHolder();
                holder.tvTime=(TextView)view.findViewById(R.id.tv_Time);
                holder.tvRemark=(TextView)view.findViewById(R.id.tv_Remark);
                holder.tvMoney=(TextView)view.findViewById(R.id.tv_Money);
//                holder.ivHeadPortrait=(CircleImageView)view.findViewById(R.id.iv_HeadPortrait);

                view.setTag(holder);
            }
            holder= (GetAccountMoneyLogListHolder) view.getTag();
            final GetAccountMoneyLog.GetAccountMoneyLogRes list = getItem(i);
            holder.tvTime.setText(list.getTime()+"");
            holder.tvRemark.setText(list.getRemark()+"");
            holder.tvMoney.setText("￥"+list.getMoney());
//            Glide.with(getApplicationContext()).load(list.getHeadPortrait()).error(R.drawable.zwt).into(holder.ivHeadPortrait);
            return view;
        }
        class  GetAccountMoneyLogListHolder{
            TextView tvTime;
//            CircleImageView ivHeadPortrait;
            TextView tvRemark;
            TextView tvMoney;
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
        return R.layout.activity_myaccount;
    }
}
