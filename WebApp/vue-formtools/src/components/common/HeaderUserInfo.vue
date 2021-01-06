<template>
    <div class="user-info">
        <div class="user-nickname">SHINY</div>
        <el-dropdown>
            <el-avatar size="small" alt="我的" src="https://portrait.gitee.com/uploads/avatars/user/1796/5390818_MYvLiang_1599898612.png!avatar30"></el-avatar>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>SHINY</el-dropdown-item>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item>我填写的表单</el-dropdown-item>
                <el-dropdown-item>我创建的表单</el-dropdown-item>
                <el-dropdown-item style="color: #f0261f" divided>退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
    export default {
        name: "HeaderUserInfo"
    }
</script>

<style scoped>
    .user-info{
        position:absolute;
        right:15px;
        display: flex;
        align-items: center;
    }
    .user-nickname{
        margin-right: 10px;
        font-size: 14px;
    }
</style>