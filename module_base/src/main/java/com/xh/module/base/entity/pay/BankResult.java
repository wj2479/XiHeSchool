package com.xh.module.base.entity.pay;

/**
 * 银行认证的接口
 */
public class BankResult {

    /**
     * sequenceNo : 111
     * signature : DB38K1tLL606wRTR9rH8cYRvSFC5IXuZeAZMU+hrMOLMcMTAtuodyNXBAenzg5Q7VFT++srqxVS37w6u634Ia7ignEGHIe7GOhJuN0FzXn1YyasTqiv6+05MPuXUmjzm5mPzbAz/aUQ3vfISNWOd3ftXo2/OAdIsL390Nzt3b0za0AFoyRKHLw9xQX/z2pV1O125eLvNNV455GoRZBE9Q3j4rOfDUPJrUnb+KOOR1JQtcaek2c2f42UpEaLegNjVpzqmOumb08ibo/dKyUewwOENmbw09Vna2v5OVSNE1UV3EMtruQomhnlTb3I/siwkTjAh7RT1f/y7W5IIfP1VaQ==
     * extra : {"appId":"001","applicationId":"00","appChaId":"810001","version":"1"}
     * encryptedData : {"code":"000000","data":{"tokenUrl":"https://1801.ebanktest.com.cn:3801/faop/SmartCampus/#/auth/login?tokenUrl=517214809620200101010101"},"message":"成功"}
     * encryptedKey : 9nYPBjNzZxeG0vTs8DtjPCMK29ucZp9iXc72U6A86Fr9Ptnk0kL2lspzPtSaS5Ieis1wnA+/g2gMlDHuFIMGQT44E3PelKfqlR2qArGz9W6E2MjnF2mXLg2t/DZ01SkeioqMYufIGJJIzBkEOm29VqSZGL6L/l3XJsHOrWUNoQIHPakwQUPa5vvKlawnepr446bIndKJkLmPOMfu7MYsLth9+kU7lhqyOPwRv63FR4swFszyhCJIWaUW59BQee80VGe9asR6l6xqcxi4WPPN3wdJ+bcl69AkdX4a0xeSy8atafXU4Hwtf3kiJ1NEUnawUh1A2lXhOTcB+pA/LEmAbg==
     * timestamp : 20200606173630
     */

    private String sequenceNo;
    private String signature;
    private ExtraBean extra;
    private EncryptedDataBean encryptedData;
    private String encryptedKey;
    private String timestamp;

    public String getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public EncryptedDataBean getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(EncryptedDataBean encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class ExtraBean {
        /**
         * appId : 001
         * applicationId : 00
         * appChaId : 810001
         * version : 1
         */

        private String appId;
        private String applicationId;
        private String appChaId;
        private String version;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(String applicationId) {
            this.applicationId = applicationId;
        }

        public String getAppChaId() {
            return appChaId;
        }

        public void setAppChaId(String appChaId) {
            this.appChaId = appChaId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class EncryptedDataBean {
        /**
         * code : 000000
         * data : {"tokenUrl":"https://1801.ebanktest.com.cn:3801/faop/SmartCampus/#/auth/login?tokenUrl=517214809620200101010101"}
         * message : 成功
         */

        private String code;
        private DataBean data;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public static class DataBean {
            /**
             * tokenUrl : https://1801.ebanktest.com.cn:3801/faop/SmartCampus/#/auth/login?tokenUrl=517214809620200101010101
             */

            private String tokenUrl;

            public String getTokenUrl() {
                return tokenUrl;
            }

            public void setTokenUrl(String tokenUrl) {
                this.tokenUrl = tokenUrl;
            }
        }
    }
}
