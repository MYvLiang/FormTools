<template>
    <div>
        <img src="../../assets/img/images/sh1.jpg" class="bg">
        <img src="../../assets/img/images/logo.jpg" class="logo">
        <img src="../../assets/img/images/sh3.jpg" class="pic">
        <h1 class="title">一个在线审核表单</h1>
        <ul class="index">
            <li class="direction"><a class="active" href="#">首页</a></li>
            <li class="direction"><a href="#">我的参与</a></li>
            <li class="direction"><a href="#">关于我们</a></li>
            <li class="direction"><a href="login.html">登录</a></li>
        </ul>
        <img src="../../assets/img/images/sb1.jpg" class="bg">
        <ul class="activity">
            <h1 class="activity">创建活动报名</h1>
            <li class="activity1">活动报名</li>
            <li class="activity2">会议报名</li>
            <li class="activity3">在线申请</li>
            <input type="button" name="activity" value="立即创建" style="background-color: rgba(20,235,237,0);color: #dddddd;width: 150px;height: 35px;position: absolute;top: 300%;left: 25%">
        </ul>
        <ul class="information">
            <h1 class="information">创建信息登记</h1>
            <li class="information1">学生信息</li>
            <li class="information2">员工信息</li>
            <li class="information3">客户信息</li>
            <input type="button" name="information" value="立即创建" style="background-color: rgba(20,235,237,0);color: #dddddd;width: 150px;height: 35px;position: absolute;top: 300%;left: 28%;border-color: #dddddd">
        </ul>
        <ul class="question">
            <h1 class="question">创建问卷调查</h1>
            <li class="question1">市场调查</li>
            <li class="question2">产品调查</li>
            <li class="question3">学生调查</li>
            <input type="button" name="question" value="立即创建" style="background-color: rgba(20,235,237,0);color: #dddddd;width: 150px;height: 35px;position: absolute;top: 300%;left: 28%">
        </ul>
        <img src="../../assets/img/images/sb2.jpg" class="bg">
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
</template>

<script>
    export default {
        name: "Home"
    }
</script>

<style scoped>
    ul.index{
        position: fixed;
        top:18px;
        right:50px;
        width: 35%;
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        border: 1px solid #e7e7e7;
        background-color: #f3f3f3;

    }

    li.direction {
        float: left;
    }

    li.direction a {
        display: block;
        color: #666;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    li.direction a:hover:not(.active) {
        background-color: #ddd;
    }

    li.direction a.active {
        color: white;
        background-color: #4CAF50;
    }
    .logo{
        position: absolute;
        top:18px;
        left:30px;
        width:30%;
        height: 20%;
    }
    .pic {
        width: 20%;
        position: absolute;
        top: 23%;
        left: 38%;
    }
    .title {
        color: white;
        position: absolute;
        top: 65%;
        left: 39%;
    }
    .bg {
        width: 100%;
        height: 100%;
    }
    ul.activity {
        position: absolute;
        top: 136%;
        left: 20%;
        list-style-type: none;
    }
    li.activity1{
        position: absolute;
        left: 36%;
        font-size: x-large;
        color: #dddddd;

    }
    li.activity2{
        position: absolute;
        left: 36%;
        top: 150%;
        font-size: x-large;
        color: #dddddd;

    }
    li.activity3{
        position: absolute;
        left: 36%;
        top: 210%;
        font-size: x-large;
        color: #dddddd;

    }
    h1.activity {
        color: cornflowerblue;
    }
    ul.information {
        position: absolute;
        top: 136%;
        left: 40%;
        list-style-type: none;
    }
    li.information1{
        position: absolute;
        left: 40%;
        font-size: x-large;
        color: #dddddd;

    }
    li.information2{
        position: absolute;
        left: 40%;
        top: 150%;
        font-size: x-large;
        color: #dddddd;

    }
    li.information3{
        position: absolute;
        left: 40%;
        top: 210%;
        font-size: x-large;
        color: #dddddd;

    }
    h1.information {
        color: #dddddd;
    }
    ul.question {
        position: absolute;
        top: 136%;
        left: 61%;
        list-style-type: none;
    }
    li.question1{
        position: absolute;
        left: 38%;
        font-size: x-large;
        color: #dddddd;

    }
    li.question2{
        position: absolute;
        left: 38%;
        top: 150%;
        font-size: x-large;
        color: #dddddd;

    }
    li.question3{
        position: absolute;
        left: 38%;
        top: 210%;
        font-size: x-large;
        color: #dddddd;

    }
    h1.question {
        color: cornflowerblue;
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