package com.bbld.yxpt_sh.bean;

/**
 * 获取商户信息
 * Created by likey on 2017/6/29.
 */

public class GetShopInfo {
    /**"status": 0,
     "mes": "操作成功",
     "ShopInfo": {}*/
    private int status;
    private String mes;
    private ShopInfoRes ShopInfo;

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

    public ShopInfoRes getShopInfo() {
        return ShopInfo;
    }

    public void setShopInfo(ShopInfoRes shopInfo) {
        ShopInfo = shopInfo;
    }

    public static class ShopInfoRes{
        /** "ShopName": "企赢动力的小店",
         "ShopImg": "http://img.zcool.cn/community/01bf1655e514b16ac7251df840273f.jpg",
         "LinkName": "张立武",
         "LinkPhone": "18510310218",
         "Contact": "40012345678",
         "Address": "朝阳北路102号",
         "Latitude": "39.92334",
         "Longitude": "116.512672",
         "Describe": null
         */
        private String  ShopName;
        private String  ShopImg;
        private String  LinkName;
        private String  LinkPhone;
        private String  Contact;
        private String  Address;
        private String  Latitude;
        private String  Longitude;
        private String  Describe;


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

        public String getLinkName() {
            return LinkName;
        }

        public void setLinkName(String linkName) {
            LinkName = linkName;
        }

        public String getLinkPhone() {
            return LinkPhone;
        }

        public void setLinkPhone(String linkPhone) {
            LinkPhone = linkPhone;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String contact) {
            Contact = contact;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String latitude) {
            Latitude = latitude;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String longitude) {
            Longitude = longitude;
        }

        public String getDescribe() {
            return Describe;
        }

        public void setDescribe(String describe) {
            Describe = describe;
        }
    }
}
