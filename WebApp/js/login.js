// axios全局配置withCredentials也可以获取cookie
// axios.defaults.withCredentials = true;
var app = new Vue({
    el: '#app',
    data: {
        email: "",
        password: "",
        wxLogin:false
    },
    methods: {
        toOtherLogin:function(){
            this.wxLogin=false;
        },
        toWXLogin:function(){
            this.wxLogin=true;
        },
        login: function () {
            axios.get("http://localhost:8080/login?" + "email=" + this.email
                + "&password=" + this.password,
                {
                    "withCredentials": true/*单个axios设置withCredentials*/
                }
            ).then(function (response) {
                if (response.data.status_code === 0) {
                    alert(response.data.msg);
                } else {
                    window.location.href="index.html";
                }
            }).catch(function (error) {
                console.log(error);
            })
        },
        toSignin:function () {
            window.location.href="signin.html";
        },
        toForgotPassword:function () {
            window.location.href="forgot-password.html";
        },
        toYBLogin:function () {
            alert("yiban");
        }
    }
})