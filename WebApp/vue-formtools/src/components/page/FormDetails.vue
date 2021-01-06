<template>
    <div>
        <div class="header">
            <el-page-header @back="goBack" :content="formData.formTitle"></el-page-header>
            <header-user-info></header-user-info>
        </div>
    </div>
</template>

<script>
    import HeaderUserInfo from '../common/HeaderUserInfo.vue';
    export default {
        name: "FormDetails",
        components: {
            HeaderUserInfo
        },
        data() {
            return {
                formData: {
                    formId: 1600345245035002,
                    userId: 1586002337793,
                    formTitle: "活动报名表",
                    formInfo: {
                        explain: "表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明表单说明",
                        subjects: [
                            {
                                id: 1,
                                title: "题目标题",
                                tip: "",
                                type: "danhtk",
                                necessary: true
                            }
                            , {
                                id: 11,
                                title: "题目标题",
                                tip: "",
                                type: "duohtk",
                                necessary: false
                            }, {
                                id: 111,
                                title: "题目标题",
                                tip: "",
                                type: "danx",
                                necessary: true,
                                options: ["选项1fgfd", "选项2", "选项3", "选项2", "选项3"],
                                other: false
                            }, {
                                id: 113435,
                                title: "题目标题",
                                tip: "题目说明",
                                type: "danx",
                                necessary: true,
                                options: ["选项1", "选项2", "选项3"],
                                other: false
                            }
                            , {
                                id: 1111,
                                title: "题目标题",
                                tip: "题目说明",
                                type: "duox",
                                necessary: true,
                                options: ["选项1", "选项2", "选项3"],
                                other: false
                            }, {
                                id: 1112,
                                title: "题目标题",
                                tip: "题目说明",
                                type: "duox",
                                necessary: true,
                                options: ["选项111", "选项222", "选项33"],
                                other: false
                            }, {
                                id: 16666,
                                title: "题目标题",
                                tip: "题目说明",
                                type: "img",
                                necessary: true
                            }, {
                                id: 1777,
                                title: "题目标题",
                                tip: "题目说明",
                                type: "fujian",
                                necessary: true
                            }
                        ]
                    },
                    builtTime: new Date(),
                    beginTime: new Date(),
                    endTime: new Date(),
                    maxCount: 1000,
                    formType: "B",
                    state:false
                },
            }
        },
        methods: {
            goBack(){
                console.log('back')
            }
        }
    }
</script>

<style scoped>
    .header{
        display: flex;
        padding: 20px;
        align-items: center;
        background-color: #ffffff;
        box-shadow: 1px 1px 5px 1px #eee;
        margin-bottom: 2px;
    }
</style>