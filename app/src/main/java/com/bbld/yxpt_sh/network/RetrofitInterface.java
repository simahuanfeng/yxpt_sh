package com.bbld.yxpt_sh.network;


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
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by young on 2016/11/6.
 */

public interface RetrofitInterface {
    /**
     * 测试
     */
    @GET("GetVersionAndroid.aspx")
    Call<VersionAndroid> getVersionAndroid();
    /**
     * 登陆
     */
    @GET("Login")
    Call<Login>login(@Query("acc") String acc, @Query("pwd") String pwd);
    /**
     * 获取商户信息
     */
    @GET("GetShopInfo")
    Call<GetShopInfo>getShopInfo(@Query("token") String token);
    /**
     * 获取首页信息
     */
    @GET("GetShopTotial")
    Call<GetShopTotial>getShopTotial(@Query("token") String token);
    /**
     * 设置商户信息
     */
    @GET("SetShopInfo")
    Call<SetShopInfo>setShopInfo(@Query("token") String token ,@Query("LinkName") String LinkName, @Query("LinkPhone") String LinkPhone, @Query("Contact") String Contact, @Query("Describe") String Describe);
    /**
     * 获取商户会员列表
     */
    @GET("GetUserList")
    Call<GetUserList>getUserList(@Query("token") String token ,@Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);
    /**
     *   获取商户消息列表
     */
    @GET("GetMessageList")
    Call<GetMessageList>getMessageList(@Query("token") String token , @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);
    /**
     *   商户活动列表
     */
    @GET("GetShopActivityList")
    Call<GetShopActivityList>getShopActivityList(@Query("token") String token , @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);

    /**
     *   添加店铺活动
     */
    @GET("AddShopActivity")
    Call<AddShopActivity>addShopActivity(@Query("token") String token , @Query("Title") String Title, @Query("Discount") String Discount, @Query("Remark") String Remark);
    /**
     *   修改店铺活动
     */
    @GET("UpdateShopActivity")
    Call<UpdateShopActivity>updateShopActivity(@Query("token") String token , @Query("ShopActivityID") int ShopActivityID, @Query("Title") String Title, @Query("Discount") String Discount, @Query("Remark") String Remark);
    /**
     *   获取商户全部订单列表
     */
    @GET("GetShopOrderList")
    Call<GetShopOrderList>getShopOrderList(@Query("token") String token , @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);
    /**
     *   获取商户今日订单列表
     */
    @GET("GetTodayShopOrderList")
    Call<GetTodayShopOrderList>getTodayShopOrderList(@Query("token") String token , @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);
    /**
     *   获取商户已发放订单列表
     */
    @GET("GetShopReturnOrderList")
    Call<GetShopReturnOrderList>getShopReturnOrderList(@Query("token") String token , @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);
    /**
     *   获取商户账户余额及收支记录
     */
    @GET("GetAccountMoneyLog")
    Call<GetAccountMoneyLog>getAccountMoneyLog(@Query("token") String token , @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);
    /**
     *   获取提现账户信息（余额和默认银行卡）
     */
    @GET("GetWithdrawaAccountInfo")
    Call<GetWithdrawaAccountInfo>getWithdrawaAccountInfo(@Query("token") String token );
    /**
     *   获取银行卡列表
     */
    @GET("GetBankCardList")
    Call<GetBankCardList>getBankCardList(@Query("token") String token );
    /**
     *   提现
     */
    @GET("AddWithdrawa")
    Call<AddWithdrawa>addWithdrawa(@Query("token") String token , @Query("BankCardId") String BankCardId, @Query("price") String price);
}
