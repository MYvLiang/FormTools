var forgot = new Vue({
    el: '#app',
    data: {
        form:{
            email:"",
            code:"",
            password:"",
            password_again:""
        }
    },
    methods: {
        getEmailCode: function () {
            axios.get("http://localhost:8080//email-code/reset-password?"+"email="+this.form.email,{
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
        resetPassword: function () {
            axios.post("http://localhost:8080/reset-password",this.form,{
                "withCredentials": true/*单个axios设置withCredentials*/
            }).then(function (response) {
                if (response.data.status_code === 0) {
                    alert(response.data.msg);
                } else {
                    alert("密码修改成功");
                    window.location.href="login.html";
                }
            }).catch(function (error) {
                console.log(error);
            })
        }
    }
})