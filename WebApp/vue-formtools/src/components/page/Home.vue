<template>
    <div>
        <div class="m-header">
            <img src="../../assets/img/images/logo.jpg" class="logo">
            <img src="../../assets/img/images/sh3.jpg" class="pic">
            <h1 class="title">一个在线审核表单</h1>
            <ul class="index">
                <li><a class="active" href="#">首页</a></li>
                <li><a class="direction" href="#">我的填写</a></li>
                <li><a class="direction" href="#">我的创建</a></li>
                <li><a class="direction" href="login.html">登录</a></li>
            </ul>
        </div>
        <div>
            <img src="../../assets/img/images/sb1.jpg" class="bg">
            <ul class="activity">
                <h1 class="activity0">创建活动报名</h1>
                <li class="activity1">活动报名</li>
                <li class="activity2">会议报名</li>
                <li class="activity3">在线申请</li>
                <input type="button" name="activity" value="立即创建" class="but-act">
            </ul>
            <ul class="information">
                <h1 class="information0">创建信息登记</h1>
                <li class="information1">学生信息</li>
                <li class="information2">员工信息</li>
                <li class="information3">客户信息</li>
                <input type="button" name="information" value="立即创建" class="but-act" style="border-color: #fff;color: #fff;left: 45.5%;">
            </ul>
            <ul class="question">
                <h1 class="question0">创建问卷调查</h1>
                <li class="question1">市场调查</li>
                <li class="question2">产品调查</li>
                <li class="question3">学生调查</li>
                <input type="button" name="question" value="立即创建" class="but-act" style="left: 66.5%">
            </ul>
<!--            <img src="../../assets/img/images/sb2.jpg" class="bg1">-->
            <img src="../../assets/img/images/sc.jpg" class="pic3">
            <span class="introduce">
            <h1 class="head" style="color: white">ABOUT TOOLS</h1>
            <p class="text">支持无登录和登录使用，填写问卷类无需登录，为公示信息审核提供私密的环境。<br>
                独特的在线审核功能，在规定时间内可重复修改提交，后台自动更新信息，大大降低了信息收集与整理的工作量，也大大降低了信息的错误率。<br>
            支持临时通讯功能，管理者可随时发出信息错误提醒，表单创建者与填写者交流更方便，具有灵活的可操作性。</p>
        </span>
            <img src="../../assets/img/images/sc3.png" style="width: 28%; height: 60%; position: absolute; top: 285%; left: 55%; z-index: 2">
            <img src="../../assets/img/images/sc2.png" style="width: 28%; height: 60%; position: absolute; top: 352%; left: 25%; z-index: 2">
            <span class="last">
            <h1 style="color: white">专属自定义表单</h1>
            <p class="text">该表单工具应用于调查问卷，生活，工作或者学习的事物信息登记，各种活动的通知发布和收集报名信息，各平台数据互通和统一，且该工具使用完全免费。</p>
            <input type="button" name="registered" value="立即注册" style="border-color: rgba(250,239,239,0.29); color: cornflowerblue; font-size: 20px;width: 250px;height: 70px; position: absolute; top: 300%; left: -12%" >
        </span>
            <span class="foot">
            <ul class="foot">
                <li style="float: left; text-align: center; padding: 14px 16px;"><a href="#" style="text-decoration: none; color: #dddddd"> 首页</a></li>
                <li style="float: left; text-align: center; padding: 14px 16px;"><a href="#" style="text-decoration: none; color: #dddddd">我的管理</a></li>
                <li style="float: left; text-align: center; padding: 14px 16px;"><a href="#" style="text-decoration: none; color: #dddddd">我的参与</a></li>
                <li style="float: left; text-align: center; padding: 14px 16px;"><a href="#" style="text-decoration: none; color: #dddddd">关于我们</a></li>
            </ul>
            <p style="color: #dddddd; position: absolute; top: 525%; left: 2%">工作时间</p>
            <p style="color: #dddddd; position: absolute; top: 535%; left: 2%">地址:</p>
        </span>
        </div>
    </div>

</template>

<script>
    export default {
        name: "Home"
    }
</script>

<style scoped>
    *{
        margin: 0;
        padding: 0;
    }
    .m-header{
        width: 100%;
        height: 55%;
    }
    .m-header:before{
        content: '';
        position: absolute;
        height: 500px;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background-image: url("../../assets/img/images/sh1.jpg");
    }
    ul.index{
        position: absolute;
        display: flex;
        top: 4%;
        right: 15%;
        list-style-type: none;
        overflow: hidden;
        border: 1px solid #e7e7e7;
    }
    .index li{
        position: relative;
    }
    .direction {
        display: block;
        background-color: white;
        color: black;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }
    .direction:after {
        content: "";
        display: block;
        position: absolute;
        bottom: -1px;
        height: 3px;
        left: -1px;
        background-color: #076ce0;
        width: 98%;
        max-width: 0;
        transition: max-width .25s ease-in-out
    }
    .active  {
        display: block;
        background-color: white;
        color: #076ce0;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }
    .active:after {
        content: "";
        display: block;
        position: absolute;
        bottom: -1px;
        height: 3px;
        left: -1px;
        background-color: #076ce0;
        width: 98%;
        transition: max-width .25s ease-in-out
    }
    .direction:hover {
        color: #076ce0;
    }
    .direction:hover:after {
        max-width: 100%
    }
    .logo{
        position: absolute;
        top: 3%;
        left: 15%;
        width: 255px;
        height: 89px;
    }
    .pic {
        width: 102px;
        height: 118px;
        position: absolute;
        top: 15%;
        left: 46%;
    }
    .title {
        color: white;
        position: absolute;
        top: 30%;
        left: 38%;
        font-size: 50px;
    }
    .bg {
        position: absolute;
        top: 500px;
        height: 120%;
        width: 100%;
    }
    .activity {
        position: static;
        list-style-type: none;
    }
    .activity0 {
        position: absolute;
        top: 100%;
        left: 24%;
        color: cornflowerblue;
    }
    .activity1{
        position: absolute;
        top: 106%;
        left: 26.5%;
        font-size: x-large;
        color: #dddddd;

    }
    .activity2{
        position: absolute;
        left: 26.5%;
        top: 111%;
        font-size: x-large;
        color: #dddddd;

    }
    .activity3{
        position: absolute;
        left: 26.5%;
        top: 116%;
        font-size: x-large;
        color: #dddddd;

    }
    .but-act {
        position: absolute;
        top: 130%;
        left: 24.5%;
        border: 1px solid #999999;
        border-radius: 0px 0px 0px 0px;
        background: transparent;
        width: 177px;
        height: 52px;
        line-height: 52px;
        font-size: 16px;
        font-weight: 400;
        color: #fff;
        overflow: hidden;
        text-align: center;
    }
    .information {
        position: static;
        list-style-type: none;
    }
    .information0{
        position: absolute;
        top: 100%;
        left: 45%;
        color: rgba(255, 255, 255, 1);
    }
    .information1{
        position: absolute;
        top: 106%;
        left: 47.5%;
        font-size: x-large;
        color: rgba(255, 255, 255, 1);
    }
    .information2{
        position: absolute;
        top: 111%;
        left: 47.5%;
        font-size: x-large;
        color: rgba(255, 255, 255, 1);
    }
    .information3{
        position: absolute;
        top: 116%;
        left: 47.5%;
        font-size: x-large;
        color: rgba(255, 255, 255, 1);
    }

    .question {
        position: static;
        list-style-type: none;
    }
    .question0{
        position: absolute;
        top: 100%;
        left: 66%;
        color: cornflowerblue;
    }
    .question1{
        position: absolute;
        top: 106%;
        left: 68.5%;
        font-size: x-large;
        color: #dddddd;
    }
    .question2{
        position: absolute;
        top: 111%;
        left: 68.5%;
        font-size: x-large;
        color: #dddddd;

    }
    .question3{
        position: absolute;
        top: 116%;
        left: 68.5%;
        font-size: x-large;
        color: #dddddd;

    }
    .pic3 {
        width: 30%;
        height: 41%;
        position: absolute;
        top: 239%;
        left: 20%;
        z-index: 2;
    }
    .introduce {
        width: 300px;
        position: absolute;
        top: 236%;
        left: 57%;
    }
    .introduce .text{
        font-size: small;
        color: white;
        position: absolute;
        left: -25%;
        top: 110%;
    }
    .last {
        width: 260px;
        position: absolute;
        top: 455%;
        left: 65%;
    }
    .last .text{
        line-height: 30px;
        font-size: medium;
        color: white;
        position: absolute;
        top: 100%;
        left: -13%;
    }

    .foot{
        height: 200px;
        width: 100%;
        background-color: black;
    }
    ul.foot{
        color: #dddddd;
        list-style-type: none;
        margin: 0;
        padding: 0;
    }
</style>