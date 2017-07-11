package com.bbld.yxpt_sh.bean;

import java.util.List;

/**
 * 获取提现记录
 * Created by dell on 2017/7/7.
 */

public class GetWithdrawalList {
    /**
     "status": 0,
     "mes": "操作成功",
     "list": []
     */
    private int status;
    private String mes;
    private List<GetWithdrawalListRes> list;

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

    public List<GetWithdrawalListRes> getList() {
        return list;
    }

    public void setList(List<GetWithdrawalListRes> list) {
        this.list = list;
    }

    public static class GetWithdrawalListRes{
        /**
         *  "BankName": "上海浦东发展银行 ",
         "BankLogo": "http://localhost:6114/images/14.png",
         "CardNo": "6225210692223341",
         "OpenBank": "北沙滩支行",
         "Name": "李华",
         "WithdrawalMoney": "12.22",
         "ServiceMoney": "12.22",
         "IsFinish": "0",
         "Status": "提现处理中",
         "AddDate": "2017-07-06 01:05"
         */
        private String BankName;
        private String BankLogo;
        private String CardNo;
        private String OpenBank;
        private String Name;
        private String WithdrawalMoney;
        private String ServiceMoney;
        private int IsFinish;
        private String Status;
        private String AddDate;

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

        public String getWithdrawalMoney() {
            return WithdrawalMoney;
        }

        public void setWithdrawalMoney(String withdrawalMoney) {
            WithdrawalMoney = withdrawalMoney;
        }

        public String getServiceMoney() {
            return ServiceMoney;
        }

        public void setServiceMoney(String serviceMoney) {
            ServiceMoney = serviceMoney;
        }

        public int getIsFinish() {
            return IsFinish;
        }

        public void setIsFinish(int isFinish) {
            IsFinish = isFinish;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getAddDate() {
            return AddDate;
        }

        public void setAddDate(String addDate) {
            AddDate = addDate;
        }
    }
}
