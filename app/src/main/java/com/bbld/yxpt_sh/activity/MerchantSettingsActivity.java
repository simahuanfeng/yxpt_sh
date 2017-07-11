package com.bbld.yxpt_sh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbld.yxpt_sh.R;
import com.bbld.yxpt_sh.base.BaseActivity;
import com.bbld.yxpt_sh.bean.GetShopInfo;
import com.bbld.yxpt_sh.bean.SetShopInfo;
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
 * 商户设置
 * Created by zzy on 2017/7/1.
 */

public class MerchantSettingsActivity extends BaseActivity{
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_ShopImg)
    ImageView ivShopImg;
    @BindView(R.id.tv_ShopName)
    TextView tvShopName;
    @BindView(R.id.tv_LinkName)
    EditText etLinkName;
    @BindView(R.id.tv_LinkPhone)
    EditText etLinkPhone;
    @BindView(R.id.tv_Contact)
    EditText etContact;
    @BindView(R.id.tv_Address)
    TextView tvAddress;
    @BindView(R.id.et_Describe)
    EditText etDescribe;
    @BindView(R.id.btn_go)
    Button btnGo;
    private String LinkName;
    private String LinkPhone;
    private String Contact;
    private String Describe;
    @Override
    protected void initViewsAndEvents() {
        loadData();
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setShopInfo();

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setShopInfo(){
        LinkName=etLinkName.getText().toString();
        LinkPhone=etLinkPhone.getText().toString();
        Contact=etContact.getText().toString();
        Describe=etDescribe.getText().toString();
        if (NetConnectUtil.isNetConnected(getApplicationContext())){
            Call<SetShopInfo> call= RetrofitService.getInstance().setShopInfo(new YXPT_SHToken(MerchantSettingsActivity.this).getToken()+"",LinkName, LinkPhone, Contact, Describe);
            call.enqueue(new Callback<SetShopInfo>() {
                @Override
                public void onResponse(Response<SetShopInfo> response, Retrofit retrofit) {
                    if (response==null){
                        showToast("获取数据失败");
                        return;
                    }
                    if (response.body().getMes().equals("操作成功")){
                        showToast("保存成功");
                        readyGo(MerchantCenterActivity.class);
                        finish();
                    }else{
                        showToast(response.body().getMes()+"");
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });
        }else{
            showToast("请检查网络连接");
        }
    }


    private void loadData() {
        if (NetConnectUtil.isNetConnected(getApplicationContext())){
            Call<GetShopInfo> call= RetrofitService.getInstance().getShopInfo(new YXPT_SHToken(MerchantSettingsActivity.this).getToken()+"");
            call.enqueue(new Callback<GetShopInfo>() {
                @Override
                public void onResponse(Response<GetShopInfo> response, Retrofit retrofit) {
                    if (response==null){
                        showToast("获取数据失败");
                        return;
                    }
                    if (response.body().getStatus()==0){
                        GetShopInfo.ShopInfoRes shopInfo = response.body().getShopInfo();
                        setData(shopInfo);
                    }else{

                    }
                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });
        }else{
            showToast("请检查网络连接");
        }
    }

    private void setData(GetShopInfo.ShopInfoRes shopInfo) {
        Glide.with(getApplicationContext()).load(shopInfo.getShopImg()).error(R.drawable.zwt).into(ivShopImg);
        LinkName=shopInfo.getLinkName();
        LinkPhone=shopInfo.getLinkPhone();
        Contact=shopInfo.getContact();
        Describe=shopInfo.getDescribe();
        tvShopName.setText(shopInfo.getShopName());
        etLinkName.setText(LinkName);
        etLinkPhone.setText(LinkPhone);
        etContact.setText(Contact);
        tvAddress.setText(shopInfo.getAddress());
        etDescribe.setText("  "+Describe);


    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_merchantsettings;
    }
}
