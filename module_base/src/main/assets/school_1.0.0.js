(function () {
    if (window.EduJsBridge) {
        // Android加上了这个if判断，如果当前window已经定义了EduJsBridge对象，不再重新加载
        return
    }

    var JSBridge;

    function init(config) {
        JSBridge = config.bridge;
        JSBridge.init(function (msg, callback) {

        })
    }

    var app = {

       /**
         * 打印Android端提示日志
         * @param data
         */
       logi: function (data,callBack) {
           JSBridge.callHandler('logi',data,function (result) {
                callBack(result)
           })
       },

       /**
         * 打印Android端错误日志
         * @param data
         */
       loge: function (data,callBack) {
           JSBridge.callHandler('loge',data,function (result) {
               callBack(result)
           })
       },
    }

    var dialog = {

        /**
         * 显示信息提示对话框
         * @param text
         */
        showInfoDialog: function (text,callBack) {
            JSBridge.callHandler('showInfoDialog',text,function (result) {
                callBack(result)
            })
        },

        /**
         * 显示加载成功提示对话框
         * @param text
         */
        showSuccessDialog: function (text,callBack) {
            JSBridge.callHandler('showSuccessDialog',text,function (result) {
                callBack(result)
            })
        },

        /**
         * 显示加载失败提示对话框
         * @param text
         */
        showFailDialog: function (text,callBack) {
            JSBridge.callHandler('showFailDialog',text,function (result) {
                callBack(result)
            })
        },

        /**
         * 显示加载中提示对话框
         * @param text
         */
        showLoadingDialog: function (text,callBack) {
            JSBridge.callHandler('showLoadingDialog',text,function (result) {
                callBack(result)
            })
        },


        /**
         * 对话框消失
         * @param text
         */
        dismissDialog: function (text,callBack) {
            JSBridge.callHandler('dismissDialog',text,function (result) {
                callBack(result)
            })
        },

    }

    var page = {
        /**
         * 跳转到Android新的页面
         * @param data  网址
         */
        navigate: function (data,callBack) {
            JSBridge.callHandler('navigate',data,function (result) {
                callBack(result)
            })
        },

        /**
         * 关闭当前页面
         * @param data
         */
        close: function (data,callBack) {
            JSBridge.callHandler('finish',data,function (result) {
                callBack(result)
            })
        },
    }

    var pay = {

       /**
        * 支付成功
        * @param data
        */
        success:function (data,callBack){
           JSBridge.callHandler('paysuccess',data,function (result) {
               callBack(result)
           })
        },
        /**
         * 支付失败
         * @param data
         */
        fail: function(data,callBack){
           JSBridge.callHandler('payfail',data,function (result) {
               callBack(result)
           })
        }
    }

    window.EduJsBridge = {
        init: init,
        app: app,
        dialog: dialog,
        page: page,
        pay: pay
    }

    document.addEventListener("WebViewJavascriptBridgeReady", function onReady(ev) {
        EduJsBridge.init({'bridge': ev.bridge})
    })
})();