package com.bbld.yxpt_sh.activity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bbld.yxpt_sh.R;
import com.bbld.yxpt_sh.base.BaseActivity;
import com.bbld.yxpt_sh.bean.AddWithdrawa;
import com.bbld.yxpt_sh.bean.GetWithdrawaAccountInfo;
import com.bbld.yxpt_sh.network.RetrofitService;
import com.bbld.yxpt_sh.utils.YXPT_SHToken;
import com.bbld.yxpt_sh.widget.CircleImageView;
import com.bumptech.glide.Glide;


import butterknife.BindView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 提现
 * Created by dell on 2017/7/7.
 */

public class WithdrawScashActivity extends BaseActivity{
    @BindView(R.id.ll_Add)
    LinearLayout llAdd;
    @BindView(R.id.ll_Change)
    LinearLayout llChange;
    @BindView(R.id.iv_BankLogo)
    CircleImageView ivBankLogo;
    @BindView(R.id.tv_BankName)
    TextView tvBankNamev;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_CardNo)
    TextView tvCardNo;
    @BindView(R.id.tv_accountMoney)
    TextView tvAccountMoney;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_GoBank)
    ImageView ivGoBank;
    @BindView(R.id.btn_WithdrawScash)
    Button btnWithdrawScash;
    @BindView(R.id.tv_addall)
    TextView tvAddall;
    private  String BankCardId;
    private  String price;
    @Override
    protected void initViewsAndEvents() {
        loadData();
        setListeners();
    }

    private void setListeners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        llChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               readyGo(BankCardActivity.class);
            }
        });
        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                readyGo(AddBankCardActivity.class);
                showToast("您还没绑定银行卡请联系客服");
            }
        });
        btnWithdrawScash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();

            }
        });
    }
    private void setData(){
        price=etPrice.getText().toString();
        Call<AddWithdrawa> call= RetrofitService.getInstance().addWithdrawa(new YXPT_SHToken(WithdrawScashActivity.this).getToken()+"",BankCardId,price);
        call.enqueue(new Callback<AddWithdrawa>() {
            @Override
            public void onResponse(Response<AddWithdrawa> response, Retrofit retrofit) {
                if (response==null){
                    showToast("获取数据失败");
                    return;
                }
                if (response.body().getStatus()==0){
                    finish();
                    readyGo(MyAccountActivity.class);
                }else{
                showToast(response.body().getMes()+"");
                }
            }
            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
    private void loadData() {

        Call<GetWithdrawaAccountInfo> call= RetrofitService.getInstance().getWithdrawaAccountInfo(new YXPT_SHToken(WithdrawScashActivity.this).getToken()+"");
        call.enqueue(new Callback<GetWithdrawaAccountInfo>() {
            @Override
            public void onResponse(final Response<GetWithdrawaAccountInfo> response, Retrofit retrofit) {
                if (response==null){
                    showToast("获取数据失败");

                    return;
                }
                if (response.body().getStatus()==0){
                    BankCardId=response.body().getActivityInfo().getBankCardID()+"";
                    if(response.body().getActivityInfo()==null){
                        llChange.setVisibility(View.GONE);
                        llAdd.setVisibility(View.VISIBLE);
                        tvAccountMoney.setText("可提现金额0.00元");
                    }else {
                        tvAccountMoney.setText("可提现金额" + response.body().getAccountMoney() + "元");
                        GetWithdrawaAccountInfo.GetWithdrawaAccountInfoRes cardinfo = response.body().getActivityInfo();
                        tvAddall.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                etPrice.setText(response.body().getAccountMoney());
                            }
                        });
                        setData(cardinfo);
                    }
                }else{

                }
            }
  
            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }


    private void setData( GetWithdrawaAccountInfo.GetWithdrawaAccountInfoRes cardinfo) {
        Glide.with(getApplicationContext()).load(cardinfo.getBankLogo()).error(R.mipmap.ic_launcher_round).into(ivBankLogo);
        tvBankNamev.setText(cardinfo.getBankName());
        tvCardNo.setText(cardinfo.getCardNo());
    }



    @Override
    protected void getBundleExtras(Bundle extras) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
    @Override
    public int getContentView() {
        return R.layout.activity_withdrawscash;
    }
}
