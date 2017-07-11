package com.bbld.yxpt_sh.activity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bbld.yxpt_sh.R;
import com.bbld.yxpt_sh.base.BaseActivity;
import com.bbld.yxpt_sh.bean.GetShopTotial;
import com.bbld.yxpt_sh.network.RetrofitService;
import com.bbld.yxpt_sh.utils.YXPT_SHToken;
import com.bumptech.glide.Glide;
import com.wuxiaolong.androidutils.library.NetConnectUtil;

import butterknife.BindView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 商户中心
 * Created by zzy on 2017/7/1.
 */

public class MerchantCenterActivity extends BaseActivity{
    @BindView(R.id.ll_Gomyaccount)
    LinearLayout llGomyaccount;
    @BindView(R.id.ivShopImg)
    ImageView ivShopImg;
    @BindView(R.id.tvShopName)
    TextView tvShopName;
    @BindView(R.id.ivSetting)
    ImageView ivSetting;
    @BindView(R.id.iv_XXZX)
    ImageView ivXXZX;
    @BindView(R.id.llDDGL)
    LinearLayout llDDGL;
    @BindView(R.id.llHYGL)
    LinearLayout llHYGL;
    @BindView(R.id.llHDGL)
    LinearLayout llHDGL;
    @BindView(R.id.llZXSE)
    LinearLayout llZXSE;
    @BindView(R.id.llJRXSE)
    LinearLayout llJRXSE;
    @BindView(R.id.llYFFJL)
    LinearLayout llYFFJL;
    @BindView(R.id.tv_RewardOrderCount)
    TextView tvRewardOrderCount;
    @BindView(R.id.tv_RewardTotial)
    TextView tvRewardTotial;
    @BindView(R.id.tv_TodayTotialSale)
    TextView tvTodayTotialSale;
    @BindView(R.id.tv_TotialSale)
    TextView tvTotialSale;

    @Override
    protected void initViewsAndEvents() {
        loadData();
        setListeners();
    }

    private void setListeners() {
        llGomyaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(MyAccountActivity.class);
            }
        });
        ivXXZX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(MessageCenterActivity.class);
            }
        });
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(MerchantSettingsActivity.class);
            }
        });
        /*订单管理*/
        llDDGL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(OrderManagementActivity.class);
            }
        });
        /*会员管理*/
        llHYGL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(MemberManagementActivity.class);
            }
        });
        /*活动管理*/
        llHDGL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(ShopActivityManagementActivity.class);
            }
        });
        /*总销售额*/
        llZXSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(TotalSalesActivity.class);
            }
        });
        /*今日销售额*/
        llJRXSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(TodaySalesActivity.class);
            }
        });
        /*已发放奖励*/
        llYFFJL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(GrantSalesActivity.class);
            }
        });
    }

    private void loadData() {
        if (NetConnectUtil.isNetConnected(getApplicationContext())){
           // 检查版本更新
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    checkUpdate();
                }
            });
            Call<GetShopTotial> call= RetrofitService.getInstance().getShopTotial(new YXPT_SHToken(MerchantCenterActivity.this).getToken()+"");
            call.enqueue(new Callback<GetShopTotial>() {
                @Override
                public void onResponse(Response<GetShopTotial> response, Retrofit retrofit) {
                    if (response==null){
                        showToast("获取数据失败");
                        return;
                    }
                    if (response.body().getStatus()==0){
                        GetShopTotial.GetShopTotialRes shopTotial = response.body().getShopTotial();
                        setData(shopTotial);
                    }else{

                    }
                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });
        }else{
            //提示设置网络
            setNetworkMethod();
        }
    }

    private void setData(GetShopTotial.GetShopTotialRes shopTotial) {
        Glide.with(getApplicationContext()).load(shopTotial.getShopImg()).error(R.mipmap.ic_launcher_round).into(ivShopImg);
        tvShopName.setText(shopTotial.getShopName());
        tvRewardOrderCount.setText(shopTotial.getRewardOrderCount()+"单   ");
        tvRewardTotial.setText("￥"+shopTotial.getRewardTotial());
        tvTodayTotialSale.setText("￥"+shopTotial.getTodayTotialSale());
        tvTotialSale.setText("￥"+shopTotial.getTotialSale());

    }

    /**
     * 打开设置网络界面
     * */
    private void setNetworkMethod(){
        //提示对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if(android.os.Build.VERSION.SDK_INT>10){
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                }else{
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                startActivity(intent);
            }
        }).setNegativeButton("已开启", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadData();
                dialog.dismiss();
            }
        }).show();
    }
    /**
     * 更新
     */
    private void checkUpdate() {
//        final String versionName= VersionUtil.getVersionName(getApplicationContext());
//        // TODO 判断网络VersionName和当前app的VersionName
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setTitle("更新").setMessage("是否下载新版本").setPositiveButton("更新", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(MerchantCenterActivity.this, UpdateService.class);
//                intent.putExtra("Key_App_Name",getResources().getString(R.string.app_name));//app名
//                intent.putExtra("Key_Down_Url","下载链接");//网络获取的下载链接
//                startService(intent);
//                showToast("正在后台下载，不会影响您的正常使用");
//                dialogInterface.dismiss();
//            }
//        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        }).setCancelable(false).show();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        loadData();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_merchantcenter;
    }
}
