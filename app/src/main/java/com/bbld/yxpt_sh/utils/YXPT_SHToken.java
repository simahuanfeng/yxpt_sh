package com.bbld.yxpt_sh.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

/**
 * SharedPreferences保存、删除token，
 * 删除SharedPreferences文件
 * Created by likey on 2017/5/23.
 */

public class YXPT_SHToken {
    private Context mContext;
    private static final String TOKEN=null;
    private final static String DATA_URL = "/data/data/";

    public YXPT_SHToken(Context mContext){
        super();
        this.mContext=mContext;
    }
    /**
     * SharedPreferences保存token
     * */
    public String getToken(){
        SharedPreferences shared=mContext.getSharedPreferences("YXPT_SHToken",mContext.MODE_PRIVATE);
        String token = shared.getString(TOKEN, null);
        return token;
    }
    /**
     * 删除保存的token
     * */
    public void delToken(){
        SharedPreferences shared=mContext.getSharedPreferences("YXPT_SHToken",mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.remove(TOKEN);
        editor.commit();
    }
    /**
     * 删除保存的帐号密码
     * */
    public void delAP(){
        SharedPreferences shared=mContext.getSharedPreferences("YXPT_SHAP",mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.remove("YXPT_SHACC");
        editor.remove("YXPT_SHPWD");
        editor.commit();
    }
    /**
     * 删除SharedPreferences文件
     * */
    public void delSPFile(){
        File file=new File(DATA_URL + mContext.getPackageName().toString()
                + "/shared_prefs", "YXPT_SHToken.xml");
        if (file.exists()) {
            file.delete();
        }
    }
    /**
     * 删除SharedPreferences文件
     * */
    public void delAPFile(){
        File file=new File(DATA_URL + mContext.getPackageName().toString()
                + "/shared_prefs", "YXPT_SHAP.xml");
        if (file.exists()) {
            file.delete();
        }
    }
}
