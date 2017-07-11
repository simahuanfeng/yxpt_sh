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
import com.bbld.yxpt_sh.bean.GetUserList;
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
 * 会员管理
 * Created by zzy on 2017/7/1.
 */

public class MemberManagementActivity extends BaseActivity{
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.srl_member)
    SwipeRefreshLayout srlMember;
    private int pageIndex=1;
    private int pageSize=10000;
    private List<GetUserList.GetUserListRes> userlist;
    private UserListAdapter adapter;
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
                ActivityManagerUtil.getInstance().finishActivity(MemberManagementActivity.this);
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
        Call<GetUserList> call= RetrofitService.getInstance().getUserList(new YXPT_SHToken(MemberManagementActivity.this).getToken(), pageIndex+"", pageSize+"");
        call.enqueue(new Callback<GetUserList>() {
            @Override
            public void onResponse(Response<GetUserList> response, Retrofit retrofit) {
                if (response==null){
                    return;
                }
                if (response.body().getStatus()==0){
                    if (isLoadMore){
                        List<GetUserList.GetUserListRes> productsAdd = response.body().getList();
                        userlist.addAll(productsAdd);
                        adapter.notifyDataSetChanged();
                    }else{
                        userlist = response.body().getList();
                        if( userlist .isEmpty()){
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
        adapter=new UserListAdapter();
        lvList.setAdapter(adapter);
    }

    class UserListAdapter extends BaseAdapter{
        UserListHolder holder=null;
        @Override
        public int getCount() {
            return userlist.size();
        }

        @Override
        public GetUserList.GetUserListRes getItem(int i) {
            return userlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return Long.parseLong(userlist.get(i).getNOID()+"");
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_lv_membermanagement,null);
                holder=new UserListHolder();
                holder.tvNickName=(TextView)view.findViewById(R.id.tv_NickName);
                holder.tvOrderCount=(TextView)view.findViewById(R.id.tv_OrderCount);
                holder.tvOrderPrice=(TextView)view.findViewById(R.id.tv_OrderPrice);
                holder.ivHeadPortrait=(CircleImageView)view.findViewById(R.id.iv_HeadPortrait);

                view.setTag(holder);
            }
            holder= (UserListHolder) view.getTag();
            final GetUserList.GetUserListRes list = getItem(i);
            holder.tvNickName.setText(list.getNickName()+"");
            holder.tvOrderCount.setText(list.getOrderCount()+"");
            holder.tvOrderPrice.setText("￥"+list.getOrderPrice());
            Glide.with(getApplicationContext()).load(list.getHeadPortrait()).error(R.drawable.zwt).into(holder.ivHeadPortrait);
            return view;
        }
        class  UserListHolder{
            TextView tvNickName;
            CircleImageView ivHeadPortrait;
            TextView tvOrderCount;
            TextView tvOrderPrice;
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
        return R.layout.activity_membermanagement;
    }
}
