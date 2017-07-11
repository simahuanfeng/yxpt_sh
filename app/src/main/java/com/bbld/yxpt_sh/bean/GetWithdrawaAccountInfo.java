package com.bbld.yxpt_sh.bean;

/**
 * 获取提现账户信息（余额和默认银行卡）
 * Created by dell on 2017/7/7.
 */

public class GetWithdrawaAccountInfo {
    /**
     *  "status": 0,
     "mes": "操作成功",
     "accountMoney": "98.00",
     "cardinfo"{}
     */
    private int status;
    private String mes;
    private String accountMoney;
    private GetWithdrawaAccountInfoRes cardinfo;

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

    public String getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }

    public GetWithdrawaAccountInfoRes getActivityInfo() {
        return cardinfo;
    }

    public void setActivityInfo(GetWithdrawaAccountInfoRes activityInfo) {
        cardinfo = activityInfo;
    }

    public static class  GetWithdrawaAccountInfoRes{
        /**
         * "HasCard": 1,
         "BankCardID": 2,
         "BankName": "广发银行",
         "BankLogo": "http://localhost:6114/images/12.png",
         "CardNo": "6225210692223341",
         "OpenBank": "北京总行",
         "Name": "李华"
         */
        private String HasCard;
         private int BankCardID;
        private String BankName;
        private String BankLogo;
        private String CardNo;
        private String OpenBank;
        private String Name;

        public String getHasCard() {
            return HasCard;
        }

        public void setHasCard(String hasCard) {
            HasCard = hasCard;
        }

        public int getBankCardID() {
            return BankCardID;
        }

        public void setBankCardID(int bankCardID) {
            BankCardID = bankCardID;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String bankName) {
            BankName = bankName;
        }

        public String getBankLogo() {
            return BankLogo;
        }

        public void setBankLogo(String bankLogo) {
            BankLogo = bankLogo;
        }

        public String getCardNo() {
            return CardNo;
        }

        public void setCardNo(String cardNo) {
            CardNo = cardNo;
        }

        public String getOpenBank() {
            return OpenBank;
        }

        public void setOpenBank(String openBank) {
            OpenBank = openBank;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }
    }
}
