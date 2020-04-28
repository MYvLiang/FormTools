var signin = new Vue({
    el: '#app',
    data: {
        email: "",
        password: "",
        nickname: "",
        code: ""
    },
    methods: {
        getEmailCode : function () {
            axios.get("http://localhost:8080/email-code?"+"email="+this.email,{
                "withCredentials": true
            }).then(function (response) {
                if (response.data.status_code === 0) {
                    alert(response.data.msg);
                } else {
                    alert("已发送验证码");
                }
            }).catch(function (error) {
                console.log(error);
            })
        },
        registerUser : function () {
            axios.post("http://localhost:8080/user",{
                email:this.email,
                password:this.password,
                nickname:this.nickname,
                code:this.code
            },{
                "withCredentials": true
            }).then(function (response) {
                if (response.data.status_code === 0) {
                    alert(response.data.msg);
                } else {
                    alert('注册成功!');
                    location.href='login.html';
                }
            }).catch(function (error) {
                console.log(error);
            })
        }
    }
})