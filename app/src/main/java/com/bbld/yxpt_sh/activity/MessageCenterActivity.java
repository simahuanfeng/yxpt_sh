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
import com.bbld.yxpt_sh.bean.GetMessageList;
import com.bbld.yxpt_sh.network.RetrofitService;
import com.bbld.yxpt_sh.utils.YXPT_SHToken;
import com.bbld.yxpt_sh.widget.CircleImageView;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 消息中心
 * Created by zzy on 2017/7/3.
 */

public class MessageCenterActivity extends BaseActivity{
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.srl_member)
    SwipeRefreshLayout srlMember;
    private int pageIndex=1;
    private int pageSize=10000;
    private List<GetMessageList.GetMessageListRes> messagelist;
    private MessageListAdapter adapter;
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
        Call<GetMessageList> call= RetrofitService.getInstance().getMessageList(new YXPT_SHToken(MessageCenterActivity.this).getToken(), pageIndex+"", pageSize+"");
        call.enqueue(new Callback<GetMessageList>() {
            @Override
            public void onResponse(Response<GetMessageList> response, Retrofit retrofit) {
                if (response==null){
                    return;
                }
                if (response.body().getStatus()==0){
                    if (isLoadMore){
                        List<GetMessageList.GetMessageListRes> productsAdd = response.body().getList();
                        messagelist.addAll(productsAdd);
                        adapter.notifyDataSetChanged();
                    }else{
                        messagelist = response.body().getList();
                        if( messagelist .isEmpty()){
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
    }
    private void setAdapter() {
        adapter=new MessageListAdapter();
        lvList.setAdapter(adapter);
    }
    class MessageListAdapter extends BaseAdapter {
       MessageListHolder holder=null;
        @Override
        public int getCount() {
            return messagelist.size();
        }

        @Override
        public GetMessageList.GetMessageListRes getItem(int i) {
            return messagelist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_lv_messagecenter,null);
                holder=new MessageListHolder();
                holder.tvTitle=(TextView)view.findViewById(R.id.tv_Title);
                holder.tvContent=(TextView)view.findViewById(R.id.tv_Content);
                holder.tvTime=(TextView)view.findViewById(R.id.tv_Time);

                view.setTag(holder);
            }
            holder= (MessageListHolder) view.getTag();
            final GetMessageList.GetMessageListRes list = getItem(i);
            holder.tvTitle.setText(list.getTitle()+"");
            holder.tvContent.setText(list.getContent()+"");
            holder.tvTime.setText(list.getTime()+"");
            return view;
        }
        class  MessageListHolder{
            TextView tvTitle;
            TextView tvContent;
            TextView tvTime;
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
        return R.layout.activity_messagecenter;
    }
}
