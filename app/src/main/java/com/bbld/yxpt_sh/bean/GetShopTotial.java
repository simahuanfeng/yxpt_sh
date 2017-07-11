package com.bbld.yxpt_sh.bean;


/**
 * 获取首页统计信息
 * Created by dell on 2017/7/1.
 */

public class GetShopTotial {
    /**   "status": 0,
     "mes": "操作成功",
     "ShopTotial":
     **/
    private int status;
    private String mes;
    private GetShopTotialRes ShopTotial;

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

    public GetShopTotialRes getShopTotial() {
        return ShopTotial;
    }

    public void setShopTotial(GetShopTotialRes shopTotial) {
        ShopTotial = shopTotial;
    }

    public static class GetShopTotialRes{
        /**
         "ShopName": "企赢动力的小店",
         "ShopImg": "http://img.zcool.cn/community/01bf1655e514b16ac7251df840273f.jpg",
         "TotialSale": "17.00",
         "TodayTotialSale": "0.00",
         "RewardOrderCount": 2,
         "RewardTotial": "3.00"
         */

        private String ShopName;
        private String ShopImg;
        private String TotialSale;
        private String TodayTotialSale;
        private String RewardOrderCount;
        private String RewardTotial;

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String shopName) {
            ShopName = shopName;
        }

        public String getShopImg() {
            return ShopImg;
        }

        public void setShopImg(String shopImg) {
            ShopImg = shopImg;
        }

        public String getTotialSale() {
            return TotialSale;
        }

        public void setTotialSale(String totialSale) {
            TotialSale = totialSale;
        }

        public String getTodayTotialSale() {
            return TodayTotialSale;
        }

        public void setTodayTotialSale(String todayTotialSale) {
            TodayTotialSale = todayTotialSale;
        }

        public String getRewardOrderCount() {
            return RewardOrderCount;
        }

        public void setRewardOrderCount(String rewardOrderCount) {
            RewardOrderCount = rewardOrderCount;
        }

        public String getRewardTotial() {
            return RewardTotial;
        }

        public void setRewardTotial(String rewardTotial) {
            RewardTotial = rewardTotial;
        }


    }

}
