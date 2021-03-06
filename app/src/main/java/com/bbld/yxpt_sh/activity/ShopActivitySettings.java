package com.bbld.yxpt_sh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bbld.yxpt_sh.R;
import com.bbld.yxpt_sh.base.BaseActivity;
import com.bbld.yxpt_sh.bean.AddShopActivity;
import com.bbld.yxpt_sh.network.RetrofitService;
import com.bbld.yxpt_sh.utils.YXPT_SHToken;
import com.wuxiaolong.androidutils.library.NetConnectUtil;

import butterknife.BindView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 添加活动设置
 * Created by dell on 2017/7/3.
 */

public class ShopActivitySettings extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_Title)
    EditText etTittle;
    @BindView(R.id.et_Discount)
    EditText etDiscount;
    @BindView(R.id.et_Remark)
    EditText etRemark;
    @BindView(R.id.btn_go)
    Button btnGo;
    private String Title;
    private String Discount;
    private String Remark;
    @Override
    protected void initViewsAndEvents() {
        setListeners();
    }
    private void setListeners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadData();
            }
        });
    }
    private void loadData() {
        Title = etTittle.getText().toString();
        Discount = etDiscount.getText().toString();
        Remark = etRemark.getText().toString();
        if (NetConnectUtil.isNetConnected(getApplicationContext())) {
            Call<AddShopActivity> call = RetrofitService.getInstance().addShopActivity(new YXPT_SHToken(ShopActivitySettings.this).getToken(), Title + "", Discount + "", Remark + "");
            call.enqueue(new Callback<AddShopActivity>() {
                @Override
                public void onResponse(Response<AddShopActivity> response, Retrofit retrofit) {
                    if (Title.equals("") | Discount.equals("")) {
                        showToast("请填写正确数据");
                    } else {
                        if (response == null) {
                            showToast("获取数据失败");
                            return;
                        }
                        if (response.body().getMes().equals("操作成功")) {
                            showToast("保存成功");
                            finish();
                        } else {
                            showToast(response.body().getMes() + "");
                        }
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
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_shopactivitysettings;
    }
}
