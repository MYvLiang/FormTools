<template>
    <div style="background-color: #ffffff;height:100%;">
        <el-container>
            <el-header>
                <el-image
                        style="width: 40px; height: 40px"
                        src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iMzBweCIgaGVpZ2h0PSIzMHB4IiB2aWV3Qm94PSIwIDAgMzAgMzAiIHZlcnNpb249IjEuMSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+CiAgICA8dGl0bGU+MDUg5Zu+5qCHL+agvOW8j+Wbvuaghy/ooajljZUgMzBweC3lk4HniYxAMXg8L3RpdGxlPgogICAgPGRlZnM+CiAgICAgICAgPGxpbmVhckdyYWRpZW50IHgxPSI1MCUiIHkxPSI1NC41MjIxMzM2JSIgeDI9IjgzLjUwMTEzMDMlIiB5Mj0iOTkuMjI5MzkyMSUiIGlkPSJsaW5lYXJHcmFkaWVudC0xIj4KICAgICAgICAgICAgPHN0b3Agc3RvcC1jb2xvcj0iIzBENTlEMyIgb2Zmc2V0PSIwJSI+PC9zdG9wPgogICAgICAgICAgICA8c3RvcCBzdG9wLWNvbG9yPSIjMDA3NUUzIiBvZmZzZXQ9IjEwMCUiPjwvc3RvcD4KICAgICAgICA8L2xpbmVhckdyYWRpZW50PgogICAgICAgIDxwYXRoIGQ9Ik05LjgyNDI0MDA3LDEyLjEgTDE3LjE1NTQwNDcsMC4yNzYzMDU5ODIgTDIyLjM5NTg1MjgsLTMuNTI2NTY1NzFlLTEwIEwyMi4zOTU4NTI4LDIxLjE0OTIxODcgQzIyLjM5NTg1MjgsMjEuNjE5MDkyMyAyMi4wMzMwOTA2LDIyIDIxLjU4NTYwMDcsMjIgTDEuMjA2NTM0MzksMjIgQzAuNzU5MDQ0NTQ2LDIyIDAuMzk2MjgyMzU0LDIxLjYxOTA5MjMgMC4zOTYyODIzNTQsMjEuMTQ5MjE4NyBMMC4zOTU4NTI3NTUsMTIuMSBMOS44MjQyNDAwNywxMi4xIFoiIGlkPSJwYXRoLTIiPjwvcGF0aD4KICAgICAgICA8bGluZWFyR3JhZGllbnQgeDE9Ijk2LjQwMTQ4OTQlIiB5MT0iMzMuMzU3OTg4MiUiIHgyPSIyNS4xNzU4NzA5JSIgeTI9IjY0LjMwODkzNTIlIiBpZD0ibGluZWFyR3JhZGllbnQtNCI+CiAgICAgICAgICAgIDxzdG9wIHN0b3AtY29sb3I9IiMzNEIyRkYiIG9mZnNldD0iMCUiPjwvc3RvcD4KICAgICAgICAgICAgPHN0b3Agc3RvcC1jb2xvcj0iIzAwODFGQyIgb2Zmc2V0PSIxMDAlIj48L3N0b3A+CiAgICAgICAgPC9saW5lYXJHcmFkaWVudD4KICAgICAgICA8bGluZWFyR3JhZGllbnQgeDE9IjEwMy4zMzQ2MDElIiB5MT0iMzQuOTMwNjk1MyUiIHgyPSI2Mi44MTYyOTYzJSIgeTI9IjU0LjY3NDQwMzglIiBpZD0ibGluZWFyR3JhZGllbnQtNSI+CiAgICAgICAgICAgIDxzdG9wIHN0b3AtY29sb3I9IiMxMjc1RUMiIG9mZnNldD0iMCUiPjwvc3RvcD4KICAgICAgICAgICAgPHN0b3Agc3RvcC1jb2xvcj0iIzA3ODdGQyIgb2Zmc2V0PSIxMDAlIj48L3N0b3A+CiAgICAgICAgPC9saW5lYXJHcmFkaWVudD4KICAgIDwvZGVmcz4KICAgIDxnIGlkPSIwNS3lm77moIcv5qC85byP5Zu+5qCHL+ihqOWNlS0zMHB4LeWTgeeJjCIgc3Ryb2tlPSJub25lIiBzdHJva2Utd2lkdGg9IjEiIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPGcgaWQ9Iue8lue7hC0yIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSgtMS4wMDAwMDAsIDQuMDAwMDAwKSI+CiAgICAgICAgICAgIDxnIGlkPSLnvJbnu4QiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDQuNjA0MTQ3LCAwLjAwMDAwMCkiPgogICAgICAgICAgICAgICAgPG1hc2sgaWQ9Im1hc2stMyIgZmlsbD0id2hpdGUiPgogICAgICAgICAgICAgICAgICAgIDx1c2UgeGxpbms6aHJlZj0iI3BhdGgtMiI+PC91c2U+CiAgICAgICAgICAgICAgICA8L21hc2s+CiAgICAgICAgICAgICAgICA8dXNlIGlkPSLot6/lvoQiIGZpbGw9InVybCgjbGluZWFyR3JhZGllbnQtMSkiIHhsaW5rOmhyZWY9IiNwYXRoLTIiPjwvdXNlPgogICAgICAgICAgICAgICAgPGltYWdlIGlkPSLot6/lvoQiIG1hc2s9InVybCgjbWFzay0zKSIgeD0iMCIgeT0iMCIgd2lkdGg9IjIyLjY4IiBoZWlnaHQ9IjIxLjYiIHhsaW5rOmhyZWY9ImRhdGE6aW1hZ2UvcG5nO2Jhc2U2NCxpVkJPUncwS0dnb0FBQUFOU1VoRVVnQUFBQlVBQUFBVUNBWUFBQUJpUzNZekFBQUFCR2RCVFVFQUFMR09mUHRSa3dBQUFFUmxXRWxtVFUwQUtnQUFBQWdBQVlkcEFBUUFBQUFCQUFBQUdnQUFBQUFBQTZBQkFBTUFBQUFCQUFFQUFLQUNBQVFBQUFBQkFBQUFGYUFEQUFRQUFBQUJBQUFBRkFBQUFBQTFlenUrQUFBREdFbEVRVlE0RWFXVXoyNGNSUkRHditxZW5iV3RqZTA0T0pFaUl2a1FjakFTaDJ3azRNZ05DWVVvU0Q3eEdOeTRvTHhHWGlGdndJbG5DRmhDQ2dpUkJPenN2K3p1ek03T2JuZFg4WTJkZU0zTmRscGF6ZlJzOTYrL3F2cXFCUjh3cmgvOHVUV3RaMThCMlRVZ0FRNmx1TlIzVjJXYW1VeVh5MjhNMlJkaXFXdGU3bG5DTGFSV0o3c3FOSC84KzMzVDhBRGlkOGwzTGpxb3BOd01jaVdsTzkrLzJFd2hQUkpwM1JTMWJmVm9neTh3TEVWOGZXbG9FL1prTW44b1R1OG8wbzZLdEVVbEFscUk4K01ZUTNGcGFQdTc1NTh5eEM3TTdjQmt6WmtSYUlXcWpTVHBXeFpzZWlubzdzRmhKNW84Rmk4M1ZkQWhUT0IwNWd3amthd2ZFMGJZWENzdkJSMHQ4SWpWMkNQc2hoUEpHWExnZkVvdkRVUXh4THFmb0ZpZlh4amErdmJYQjdEMHRaai9tQUszWVVwYld1RkZldWEwRjZFRGlCVUk0K1dGb0kzSlZlVUhNZGtUMkVjQ2FhdVRCUlcramFaOUtoK2duYVpZeEJwYldiaVFUNHY1L0VjVDIyY0MyL1MxaWNqTXBWUXdqd09rTUVpSUkzNHZFV1FKN0tjc2UvamJFeFgzQ2J2aWpnSGJsTjVtWk1KRkZDTE5rNDdob01FTnJMRlkxVFNUTTNuRGxqeml5aDdjeG9qblZmaW5DdmdaeXJSd3VkRzR6RG9uN0FkdVlZd25Qek1WUXlJZ2tyb3crSUtoRDdudW1HY2M4WVJlcm40SXJVdThHaTZ4ZjhnTGdEZUJlcHVRTWFUdjJHSlNpeVoyQjYxeWZ0RHg1aEJGZFU1bEkvNzVMenhlMHZMSFZSYkcyTnlwOEZjWjhleEFHWmhsaEJ3bnRtNm1zV1FhT3F4a0Mra2s3Rk9zWi9PQmtXalQzVGJucmpIVXZ4SFIxekc2SG54ZW9PZ3ZzTjlOT0R6ZGtpWG4vdmFLZ2tvNmpIeU53VHA2WmFYVVBKbUprVnN5WnpVdFZhYTBuTUN0RDdDWmo0R3F4UFZ1d0ZPY3FHeXdHWE4vbEJSVHVGYk9qWFFETHhtTEsyaE9wWUZneC9LcEJJU2FWcElLMGMzUXIrYTROMS9nZG5PWk12dnZSb1liN05maFJvRVVNMTVqMUZqUk5HNEZqU3g2TTNOTVFSVVN0bllqeWpxaTB3LzQvTXVBdzJlR3A5MHpZTVBOc0pkWHVFWklzU0dZWllLNlpJRVp5ZnRoSmJCMjYzVFQ3VDhNZTU4cGZtR29uYnZOTjJOeCtGeXBiTFpSdy9sS1Axa3BmQTg5ZS81MFRzMy9JV2RMM3IzOEIrN3RwbDZibCt3MUFBQUFBRWxGVGtTdVFtQ0MiPjwvaW1hZ2U+CiAgICAgICAgICAgIDwvZz4KICAgICAgICAgICAgPHBhdGggZD0iTTI3LDAgTDE4LjE3MzYzNTUsMTQuNjM0NTQxMiBDMTguMDExOTgzMSwxNC45MDI1Njg4IDE3LjcwODA3NDksMTUuMDM0ODM1NiAxNy40MTQyMjc2LDE0Ljk5MjEgTDEuMjUyMjc4LDE0Ljk5MjM5IEMxLjExMjk0ODcxLDE0Ljk5MjM5IDEsMTQuODgwMzA0OCAxLDE0Ljc0MjA0MDcgQzEsMTQuNjk2MTkzOCAxLjAxMjY4NjczLDE0LjY1MTIyNzIgMS4wMzY2NzY0MSwxNC42MTIwNDU1IEw0LjE1NjgwNTQ0LDkuNDQ4NzU4NjcgQzQuMjk0MDY5NjgsOS4yMjQ1Njg4NyA0LjUzOTI2MjAyLDkuMDg3Njk2NTcgNC44MDM2MTAyMiw5LjA4NzY5NjU3IEwxNC42OTk0MzIzLDkuMDg3NDg1NyBMMTkuOTE1NTMxMiwwLjM2OTQxMjY5OSBDMjAuMDUxNjMzMSwwLjE0MDQ4NzkyOCAyMC4yOTk1Mjk1LDAgMjAuNTY3Mzc0NCwwIEwyNywwIFogTTE1Ljc0NDc0ODEsMCBDMTUuODg1NzE5OCwwIDE2LDAuMTA0NjU5MjU5IDE2LDAuMjMzNzYyOTk5IEMxNiwwLjI3MjQ2MDQ3MSAxNS45ODk1MSwwLjMxMDU1MjY1MiAxNS45Njk0NzEzLDAuMzQ0NjIxODIxIEwxMi44NTk5NzAzLDUuNjMxMjg3NDcgQzEyLjcyNjM5NDQsNS44NTgzODg3MyAxMi40Njc0NjYzLDYgMTIuMTg1ODAwNiw2IEw2LjI1NTI1MTk1LDYgQzYuMTE0MjgwMTksNiA2LDUuODk1MzQwNzQgNiw1Ljc2NjIzNyBDNiw1LjcyODExNDYxIDYuMDEwMTgwNjMsNS42OTA1NzA2OCA2LjAyOTY1NDYzLDUuNjU2ODc3MjcgTDkuMDgzNDg5NjcsMC4zNzMyMDk4IEM5LjIxNjE5NDAyLDAuMTQzNjA4MTA3IDkuNDc2NjE4MDgsMCA5Ljc2MDI4MTYyLDAgTDE1Ljc0NDc0ODEsMCBaIiBpZD0i5b2i54q2IiBmaWxsPSJ1cmwoI2xpbmVhckdyYWRpZW50LTQpIj48L3BhdGg+CiAgICAgICAgICAgIDxwYXRoIGQ9Ik00Ljc5MzMzMTQxLDkuMDg3NDg1NyBMMTQuNjk5NDMyMyw5LjA4NzQ4NTcgTDE0LjY5OTQzMjMsOS4wODc0ODU3IEwxMS41NDkyNTk1LDE1IEwxLjMwOTkwMTY3LDE0Ljk5Mzg4MzkgQzEuMTcwMTA0NjIsMTQuOTkzODAwNCAxLjA1Njg0NDQxLDE0Ljg4MDQwNDggMS4wNTY5Mjc5MSwxNC43NDA2MDc3IEMxLjA1Njk1NDk3LDE0LjY5NTMxNjMgMS4wNjkxMzM2OCwxNC42NTA4NjE2IDEuMDkyMTkzODUsMTQuNjExODgwMyBMNC4xMzk3NTQzNSw5LjQ2MDIyNDY4IEM0LjI3NjQyMzE0LDkuMjI5MTk3MDkgNC41MjQ5MDYxOCw5LjA4NzQ4NTcgNC43OTMzMzE0MSw5LjA4NzQ4NTcgWiIgaWQ9IuefqeW9oiIgZmlsbD0idXJsKCNsaW5lYXJHcmFkaWVudC01KSI+PC9wYXRoPgogICAgICAgIDwvZz4KICAgIDwvZz4KPC9zdmc+"
                        fit="contain"></el-image>
                <h3>通用表单工具</h3>
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
                            <div class="form-list-item" v-for="(i,index) in count">
                                <span>{{ i }}.班级信息收集表</span>
                                <div class="form-list-item-more">
                                    <div :class="status[index%3]">{{statusText[index%3]}}</div>
                                    <div class="create-time">创建时间2020-10-28</div>
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

    export default {
        name: "ManagePage",
        components: {
            HeaderUserInfo
        },
        data() {
            return {
                activeName: 'first',
                count: 10,
                status: ["status1", "status2", "status3"],
                statusText: ["收集中", "草稿", "已停止"]
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