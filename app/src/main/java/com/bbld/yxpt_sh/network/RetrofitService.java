package com.bbld.yxpt_sh.network;


import com.bbld.yxpt_sh.base.Constants;
import com.bbld.yxpt_sh.bean.AddShopActivity;
import com.bbld.yxpt_sh.bean.AddWithdrawa;
import com.bbld.yxpt_sh.bean.GetAccountMoneyLog;
import com.bbld.yxpt_sh.bean.GetBankCardList;
import com.bbld.yxpt_sh.bean.GetMessageList;
import com.bbld.yxpt_sh.bean.GetShopActivityList;
import com.bbld.yxpt_sh.bean.GetShopInfo;
import com.bbld.yxpt_sh.bean.GetShopOrderList;
import com.bbld.yxpt_sh.bean.GetShopReturnOrderList;
import com.bbld.yxpt_sh.bean.GetShopTotial;
import com.bbld.yxpt_sh.bean.GetTodayShopOrderList;
import com.bbld.yxpt_sh.bean.GetUserList;
import com.bbld.yxpt_sh.bean.GetWithdrawaAccountInfo;
import com.bbld.yxpt_sh.bean.Login;
import com.bbld.yxpt_sh.bean.SetShopInfo;
import com.bbld.yxpt_sh.bean.UpdateShopActivity;
import com.bbld.yxpt_sh.bean.VersionAndroid;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by young on 2016/11/6.
 */

public class RetrofitService {
    private static RetrofitService retrofitService = new RetrofitService();
    private static RetrofitInterface retrofitInterface;

    private RetrofitService() {
        initRetrofit();
    }

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            retrofitService = new RetrofitService();
        }
        return retrofitService;
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    /**
     * 测试
     */
    public Call<VersionAndroid> getVersionAndroid(){
        return retrofitInterface.getVersionAndroid();
    }
    /**
     * 登陆
     */
    public Call<Login> login(String acc, String pwd){
        return retrofitInterface.login(acc, pwd);
    }
    /**
     * 获取商户信息
     */
    public Call<GetShopInfo> getShopInfo(String token){
        return retrofitInterface.getShopInfo(token);
    }
    /**
     * 获取首页信息
     */
    public Call<GetShopTotial> getShopTotial(String token){
        return retrofitInterface.getShopTotial(token);
    }
    /**
     * 设置商户信息
     */
    public Call<SetShopInfo> setShopInfo(String token,String LinkName,String LinkPhone,String Contact,String Describe) {
        return retrofitInterface.setShopInfo(token,LinkName, LinkPhone, Contact, Describe);
    }
    /**
     * 获取商户会员列表
     */
    public Call<GetUserList>getUserList(String token,String pageIndex,String pageSize){
        return retrofitInterface.getUserList(token,pageIndex,pageSize);
    }
    /**
     *   获取商户消息列表
     */
    public Call<GetMessageList>getMessageList(String token, String pageIndex, String pageSize) {
        return retrofitInterface.getMessageList(token, pageIndex, pageSize);
    }
    /**
     *   商户活动列表
     */
    public Call<GetShopActivityList>getShopActivityList(String token, String pageIndex, String pageSize) {
        return retrofitInterface.getShopActivityList(token, pageIndex, pageSize);
    }
    /**
     *   添加店铺活动
     */
    public Call<AddShopActivity>addShopActivity(String token, String Title, String Discount,String Remark) {
        return retrofitInterface.addShopActivity(token, Title, Discount, Remark);
    }
    /**
     *   修改店铺活动
     */
    public Call<UpdateShopActivity>updateShopActivity(String token,int ShopActivityID,String Title, String Discount, String Remark) {
        return retrofitInterface.updateShopActivity(token,ShopActivityID, Title, Discount, Remark);
    }
    /**
     *   获取商户全部订单列表
     */
    public Call<GetShopOrderList>getShopOrderList(String token, String pageIndex, String pageSize) {
        return retrofitInterface.getShopOrderList(token, pageIndex, pageSize);
    }
    /**
     *   获取商户今日订单列表
     */
    public Call<GetTodayShopOrderList>getTodayShopOrderList(String token, String pageIndex, String pageSize) {
        return retrofitInterface.getTodayShopOrderList(token, pageIndex, pageSize);
    }
    /**
     *   获取商户已发放订单列表
     */
    public Call<GetShopReturnOrderList>getShopReturnOrderList(String token, String pageIndex, String pageSize) {
        return retrofitInterface.getShopReturnOrderList(token, pageIndex, pageSize);
    }
    /**
     *   获取商户账户余额及收支记录
     */
    public Call<GetAccountMoneyLog>getAccountMoneyLog(String token, String pageIndex, String pageSize) {
        return retrofitInterface.getAccountMoneyLog(token, pageIndex, pageSize);
    }
    /**
     *   获取提现账户信息（余额和默认银行卡）
     */
    public Call<GetWithdrawaAccountInfo>getWithdrawaAccountInfo(String token) {
        return retrofitInterface.getWithdrawaAccountInfo(token);
    }
    /**
     *   获取银行卡列表
     */
    public Call<GetBankCardList>getBankCardList(String token) {
        return retrofitInterface.getBankCardList(token);
    }
    /**
     *   提现
     */
    public Call<AddWithdrawa>addWithdrawa(String token,String BankCardId,String price) {
        return retrofitInterface.addWithdrawa(token,BankCardId,price);
    }
}
