<template>
    <div>
        <div class="form-design">
            <div class="form-design-container">
                <el-card class="box-card">
                    <!--表单标题和说明-->
                    <div class="form-title">
                        <h2>{{formData.formTitle}}</h2>
                    </div>
                    <div class="form-explain">{{formData.formInfo.explain}}</div>
                    <!--表单所有题目-->
                    <div class="form-question" v-for="(item, index) in formData.formInfo.subjects" v-bind:id="'q'+index"
                         :key="item.id">
                        <div class="question-item">
                            <div class="question-title-text">
                                <span v-if="item.necessary">*</span>
                                {{ index+1 }}.{{ item.title }}
                            </div>
                            <div class="question-item-tip-text">{{ item.tip }}</div>
                            <el-input v-if="item.type === 'danhtk'" placeholder="请输入内容" v-model="input" clearable> </el-input>

<!--                            <div v-if="item.type === 'danhtk'" class="question-item-input">答题区</div>-->
                            <el-input v-if="item.type === 'duohtk'" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                    placeholder="请输入内容" v-model="input">
                            </el-input>
                            <div v-if="item.type === 'danx'" class="question-item-radio">
                                <el-radio v-for="(rad, r) in item.options" v-model="item.radioValue" :key="r"
                                          v-bind:label="r+1">{{rad}}
                                </el-radio>
                            </div>
                            <div v-if="item.type === 'duox'" class="question-item-checkbox">
                                <el-checkbox-group v-model="selectOptions">
                                    <el-checkbox v-for="(checkbox,cc) in item.options" :label="checkbox" :key="cc">
                                        {{checkbox}}
                                    </el-checkbox>
                                </el-checkbox-group>
                            </div>
                            <div v-if="item.type === 'img'">
                                <el-upload
                                        drag="drag"
                                        action="#"
                                        list-type="picture"
                                        multiple>
                                    <i class="el-icon-upload"></i>
                                    <div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>
                                    <div class="el-upload__tip" slot="tip">上传图片</div>
                                </el-upload>
                            </div>
                            <div v-if="item.type === 'fujian'">
                                <el-upload
                                        drag="drag"
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        list-type="picture"
                                        multiple>
                                    <i class="el-icon-upload"></i>
                                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                    <div class="el-upload__tip" slot="tip">上传文件</div>
                                </el-upload>
                            </div>
                        </div>
                    </div>
                    <div class="commit">
                        <el-button style="width: 200px;" type="primary">提交</el-button>
                    </div>
                </el-card>
            </div>
        </div>
        <div class="foot">
           <a href="/"> 通用表单工具提供技术支持</a>
        </div>
    </div>
</template>

<script>
    let beginTime = new Date();
    let endTime = new Date(2030, 1, 1, 12, 0);
    export default {
        name: "FillForm",
        data() {
            return {
                input:'',
                drag:true,
                picture:'picture',
                selectId: -1,
                options1: [{
                    value: 'W',
                    label: '调查问卷'
                }, {
                    value: 'D',
                    label: '信息登记'
                }, {
                    value: 'B',
                    label: '活动报名'
                }],
                upload: true,
                typeOptions: [{
                    value: 'danx',
                    label: '单选题'
                }, {
                    value: 'duox',
                    label: '多选题'
                }, {
                    value: 'danhtk',
                    label: '单行文本题'
                }, {
                    value: 'duohtk',
                    label: '多行文本题'
                }, {
                    value: 'img',
                    label: '图片题'
                }, {
                    value: 'fujian',
                    label: '附件题'
                }],
                selectOptions: [],
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
                    beginTime: beginTime,
                    endTime: endTime,
                    maxCount: 1000,
                    formType: "B",
                    state:false
                }
            }
        },
        methods: {}
    }
</script>

<style scoped>
    .commit{
        display: flex;
        justify-content: center;
        padding: 20px;
    }
    .form-title{
        text-align: center;
    }
    .form-explain{
        text-align: center;
        font-size: 14px;
        color: #666666;
        padding: 15px 20px;
    }
    .question-type a {
        padding: 10px;
        display: block;
    }

    .form-design {
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .form-design-container {
        width: 700px;
        max-width: 100%;
        padding: 0 5px;
    }

    .form-design-title input {
        border: 0;
        font-size: 22px;
        text-align: center;
        width: 100%;
        padding: 10px 0;
        margin-bottom: 2px;
        margin-top: 20px;
    }

    .question-title-text {
        font-size: 16px;
        min-width: 50px;
        height: 30px;
        line-height: 30px;
    }
    .question-title-text span{
        color: #e95642;
    }

    .form-question {
        margin-bottom: 10px;
    }

    .question-item {
        padding: 5px 20px;
    }

    .question-item-tip-text {
        font-size: 14px;
        color: #777777;
        padding-top: 5px;
        padding-bottom: 10px;
    }

    .question-item-radio {
        display: flex;
        flex-direction: column;
        padding-bottom: 10px;
    }

    .question-item-radio label {
        margin: 5px 0;
    }

    .question-item-checkbox {
        width: 10px;
        margin-bottom: 10px;
    }

    .question-item-checkbox label {
        margin: 5px 0;
    }

    .foot{
        text-align: center;
        color: #666666;
        font-size: 12px;
        margin: 30px 0;
    }
</style>