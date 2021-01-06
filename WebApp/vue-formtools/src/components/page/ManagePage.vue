<template>
    <div style="background-color: #ffffff;height:100%;">
        <el-container>
            <el-header>
<!--                <el-image-->
<!--                        style="width: 120px; height: 90px"-->
<!--                        :src="logoImgUrl"-->
<!--                        fit="contain"></el-image>-->
                <h3>通用表单数据收集系统</h3>
                <header-user-info></header-user-info>
            </el-header>
            <el-container>
                <el-aside style="width: 160px;">
                    <el-menu
                            default-active="1">
                        <el-menu-item index="1">
                            <i class="el-icon-document"></i>
                            <span slot="title">我创建的表单</span>
                        </el-menu-item>
                        <el-menu-item index="2">
                            <i class="el-icon-edit"></i>
                            <span slot="title">我填写的表单</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <div class="type-tip">表单类型</div>
                    <ul class="create-type">
                        <li class="create-button">
                            <i class="el-icon-plus create-icon"></i>
                            <span>调查问卷</span>
                        </li>
                        <li class="create-button" style="border-color: #67C23A;">
                            <i class="el-icon-plus create-icon" style="color: #67C23A;"></i>
                            <span>信息登记</span>
                        </li>
                        <li class="create-button" style="border-color: #E6A23C;">
                            <i class="el-icon-plus create-icon" style="color: #E6A23C;"></i>
                            <span>活动报名</span>
                        </li>
                    </ul>
                    <div>
                        <el-tabs v-model="activeName" @tab-click="handleClick">
                            <el-tab-pane label="调查问卷" name="first"></el-tab-pane>
                            <el-tab-pane label="信息登记" name="second"></el-tab-pane>
                            <el-tab-pane label="活动报名" name="third"></el-tab-pane>
                        </el-tabs>
                        <div class="form-list">
                            <div class="form-list-item" v-for="(item,index) in formList">
                                <span>{{ index+1 }}.{{item.title}}</span>
                                <div class="form-list-item-more">
                                    <div :class="status[item.status]">{{statusText[item.status]}}</div>
                                    <div class="create-time">创建时间{{item.time}}</div>
                                    <div class="edit-button">编辑</div>
                                    <el-dropdown>
                                        <i class="el-icon-more"></i>
                                        <el-dropdown-menu slot="dropdown">
                                            <el-dropdown-item>转发填写</el-dropdown-item>
                                            <el-dropdown-item>复制创建</el-dropdown-item>
                                            <el-dropdown-item>停止收集</el-dropdown-item>
                                            <el-dropdown-item style="color: #f0261f" divided>删除表单</el-dropdown-item>
                                        </el-dropdown-menu>
                                    </el-dropdown>
                                </div>
                            </div>
                        </div>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import HeaderUserInfo from '../common/HeaderUserInfo.vue'
    import logo from '../../assets/img/images/logo.jpg'
    export default {
        name: "ManagePage",
        components: {
            HeaderUserInfo
        },
        data() {
            return {
                formList:[{
                    title:'大学生英语在线自主学习调查问卷',
                    status:0,
                    time:'2020-10-28 17:17'
                },{
                    title:'关于大学生游戏消费的调查问卷',
                    status:1,
                    time:'2020-10-28 16:58'
                },{
                    title:'关于“手机媒体对大学生家庭沟通影响”的问卷调查',
                    status:1,
                    time:'2020-10-28 16:39'
                },{
                    title:'关于学生体育活动的信息采集',
                    status:1,
                    time:'2020-10-28 16:34'
                },{
                    title:'马拉松参与者需求调查问卷',
                    status:2,
                    time:'2020-10-28 15:26'
                }],
                logoImgUrl: logo,
                activeName: 'first',
                count: 10,
                status: ["status1", "status2", "status3"],
                statusText: [ "草稿","收集中", "已停止"]
            }
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab.name, event);
            }
        }
    }
</script>

<style scoped>
    .form-list-item-more .status1 {
        color: #087afb;
        font-size: 14px;
        background-color: rgba(184, 216, 219, 0.47);
        border-radius: 10px;
        width: 80px;
        text-align: center;
    }

    .form-list-item-more .status2 {
        color: #ef8d00;
        font-size: 14px;
        background-color: #FFF5E5;
        border-radius: 8px;
        width: 80px;
        text-align: center;
    }

    .form-list-item-more .status3 {
        color: #eb5451;
        font-size: 14px;
        background-color: rgba(240, 85, 82, 0.05);
        border-radius: 8px;
        width: 80px;
        text-align: center;
    }

    .form-list-item-more .create-time {
        color: #999999;
        font-size: 13px;
    }

    .form-list-item-more .edit-button {
        color: #333333;
        font-size: 14px;
    }

    .form-list-item-more {
        color: #999999;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .form-list-item-more div {
        margin: 0 20px;
    }

    .form-list {
        height: calc(100vh - 325px);
        overflow: auto;
        position: relative;
        top: -14px;
    }

    .form-list-item {
        height: 60px;
        background-color: #ffffff;
        border-bottom: 1px solid #dddddd;
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 10px;
        font-size: 16px;
        color: #555555;
    }

    .form-list-item:last-child {
        border-bottom: none;
    }

    .form-list-item:hover {
        background-color: #f9f9f9;

    }

    .type-tip {
        padding-left: 24px;
        padding-bottom: 10px;
    }

    .create-type {
        display: flex;
        margin-bottom: 20px;
    }

    .create-icon {
        font-size: 30px;
        color: #409EFF;
        font-weight: 800;
    }

    .create-button {
        width: 170px;
        height: 120px;
        border: 1px solid #409EFF;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        margin: 5px 20px;
        /*cursor: pointer;鼠标悬停变手指*/
        cursor: pointer;
    }

    .create-button span {
        font-size: 14px;
        margin-top: 10px;
    }

    .el-header {
        display: flex;
        padding: 20px;
        align-items: center;
        background-color: #ffffff;
        box-shadow: 1px 1px 5px 1px #eee;
        margin-bottom: 2px;
    }

    .el-aside {
        background-color: #ffffff;
        margin-right: 2px;
    }

    .el-main {
        background-color: #ffffff;
        padding-bottom: 0;
    }

    .el-container {
        height: 100%;
        background-color: #eeeeee;
    }
</style>