package com.bbld.yxpt_sh.bean;

import java.util.List;

/**
 * Created by dell on 2017/7/1.
 * 获取商户消息列表
 */

public class GetMessageList {
    /**
     "status": 0,
     "mes": "操作成功",
     "count": 1,
     "list": []
     */
    private int status;
    private String mes;
    private  int count;
    private List<GetMessageListRes> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GetMessageListRes> getList() {
        return list;
    }

    public void setList(List<GetMessageListRes> list) {
        this.list = list;
    }

    public static class GetMessageListRes {
        /**
         *  "Title": "支付成功",
         "Content": "您已经成功付款398元，订单号（12398123812），您前面还有201为用户待返还。",
         "Time": "2017-07-01 10:06"
         */
        private String Title;
        private String Content;
        private String Time;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }
    }
}
