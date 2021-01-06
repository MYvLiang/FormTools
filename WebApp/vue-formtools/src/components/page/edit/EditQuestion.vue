<template>
    <div>
        <div class="question-container">
            <div class="question-type"><a @click="addQue('danx')" href="javascript:void(0);">单选题</a></div>
            <div class="question-type"><a @click="addQue('duox')" href="javascript:void(0);">多选题</a></div>
            <div class="question-type"><a @click="addQue('danhtk')" href="javascript:void(0);">单行文本题</a></div>
            <div class="question-type"><a @click="addQue('duohtk')" href="javascript:void(0);">多行文本题</a></div>
            <div class="question-type"><a @click="addQue('img')" href="javascript:void(0);">图片题</a></div>
            <div class="question-type"><a @click="addQue('fujian')" href="javascript:void(0);">附件题</a></div>
        </div>

        <div class="form-design" v-on:click="selectQuestion(-1)">
            <div class="form-design-container">
                <el-card class="box-card">
                    <!--表单标题和说明-->
                    <div class="form-design-title">
                        <input placeholder="请输入标题..." v-model="formData.formTitle">
                        <el-input
                                type="textarea"
                                autosize
                                placeholder="请输入说明..."
                                v-model="formData.formInfo.explain">
                        </el-input>
                    </div>
                    <!--表单所有题目-->
                    <div class="form-question" v-for="(item, index) in formData.formInfo.subjects" v-bind:id="'q'+index"
                         :key="item.id" v-on:click.stop="selectQuestion(index)">
                        <!--选中的题目class="form-design-question"-->
                        <div v-if="index===selectId">
                            <el-card>
                                <el-form label-position="top" :model="item">
                                    <el-form-item :label="(index+1)+'.请输入题目'">
                                        <el-input v-model="item.title"></el-input>
                                    </el-form-item>
                                    <el-form-item label="请输入备注（可选）">
                                        <el-input v-model="item.tip"></el-input>
                                    </el-form-item>
                                </el-form>
                                <div v-if="item.type === 'duox'||item.type === 'danx'">
                                    <el-form label-width="40px" label-position="right">
                                        <el-form-item v-for="(selectitem, s) in item.options" :key="s"
                                                      :label="(s+1)+').'">
                                            <el-input v-model="item.options[s]"></el-input>
                                        </el-form-item>
                                    </el-form>

                                    <div class="question-select-add">
                                        <el-button type="primary" size="mini">增加选项<i
                                                class="el-icon-circle-plus el-icon--right"></i>
                                        </el-button>
                                    </div>
                                </div>
                                <el-row :gutter="30">
                                    <el-col :span="8">
                                        <el-select size="mini" v-model="item.type"
                                                   v-on:change="selectType($event,index)"
                                                   placeholder="请选择题型">
                                            <el-option
                                                    v-for="item in typeOptions"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </el-col>
                                    <el-col :span="5">
                                        <div class="switch-necessary">
                                            <el-switch v-model="item.necessary" active-color="#1593ff"
                                                       inactive-color="#eeeeee"
                                                       active-text="必填">
                                            </el-switch>
                                        </div>
                                    </el-col>
                                    <el-col :span="2">
                                        <el-button type="danger" size="mini" icon="el-icon-delete" circle
                                                   v-on:click.stop="delQue(index)"></el-button>
                                    </el-col>
                                    <el-col :span="3">
                                        <el-button size="mini" type="primary" v-on:click="copyQue(index)">复制</el-button>
                                    </el-col>
                                    <el-col :span="3">
                                        <el-button size="mini" type="primary" v-on:click="xiayi(index)">下移</el-button>
                                    </el-col>
                                    <el-col :span="3">
                                        <el-button size="mini" type="primary" v-on:click="shangyi(index)">上移</el-button>
                                    </el-col>
                                </el-row>
                            </el-card>
                        </div>
                        <!--无选中的题目-->
                        <div v-else class="question-item">
                            <div class="question-title-text">{{ index+1 }}.{{ item.title }}</div>
                            <div class="question-item-tip-text">{{ item.tip }}</div>
                            <div v-if="item.type === 'danhtk'" class="question-item-input">答题区</div>
                            <div v-if="item.type === 'duohtk'" class="question-item-areatext">答题区</div>
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
                                        v-bind:disabled="upload"
                                        class="upload-demo"
                                        drag
                                        action="#"
                                        list-type="picture"
                                        multiple>
                                    <i class="el-icon-upload"></i>
                                    <div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>
                                    <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过5000kb</div>
                                </el-upload>
                            </div>
                            <div v-if="item.type === 'fujian'">
                                <el-upload
                                        v-bind:disabled="upload"
                                        class="upload-demo"
                                        list-type="picture"
                                        drag
                                        action="#"
                                        multiple>
                                    <i class="el-icon-upload"></i>
                                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                    <div class="el-upload__tip" slot="tip">上传文件，且不超过50m</div>
                                </el-upload>
                            </div>
                        </div>
                    </div>
                </el-card>
            </div>
            <div class="set-container">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>设置</span>
                    </div>
                    <div class="block">
                        <div class="set-explain">最大填写人数</div>
                        <el-input-number v-model="formData.maxCount" :step="1000" :min="1"
                                         :max="10000"></el-input-number>
                    </div>
                    <div class="block">
                        <div class="set-explain">填写时间</div>
                        <el-date-picker
                                v-model="timeArr"
                                @change="changeTime"
                                type="datetimerange"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                        </el-date-picker>
                    </div>
                    <div class="block">
                        <div class="set-explain">表单类型</div>
                        <el-select v-model="formData.formType" placeholder="请选择">
                            <el-option
                                    v-for="item in options1"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </div>

                </el-card>
            </div>
        </div>

    </div>
</template>

<script>
    let beginTime = new Date();
    let endTime = new Date(2030, 1, 1, 12, 0);
    export default {
        name: "EditQuestion",
        data() {
            return {
                timeArr: [beginTime, endTime],
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
                    formTitle: "马拉松参与者需求调查问卷",
                    formInfo: {
                        explain: "您好，为了更好了解马拉松参与者的需求，我们特地开展本次调查，希望您在百忙之中抽出一分钟时间帮助我们完成这次调查，我们希望得到您真实的想法。谢谢您的合作！",
                        subjects: [
                            {
                                id: 1,
                                title: "你的性别",
                                tip: "",
                                type: "danx",
                                necessary: true,
                                options: ["男", "女"],
                                other: false
                            }, {
                                id: 11,
                                title: "你的年龄",
                                tip: "",
                                type: "duohtk",
                                necessary: true
                            }, {
                                id: 111,
                                title: "你的跑步频率",
                                tip: "",
                                type: "danx",
                                necessary: true,
                                options: ["每天", "一周1至3次", "一周4次以上","基本不跑"],
                                other: false
                            }, {
                                id: 113435,
                                title: "题目标题",
                                tip: "题目说明",
                                type: "danx",
                                necessary: true,
                                options: ["选项1", "选项2", "选项3"],
                                other: false
                            }, {
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
                    state: false
                },
                selectId: -1
            }
        },
        methods: {
            changeTime(data) {
                console.log("changeTime", data);
                this.formData.beginTime = data[0];
                this.formData.endTime = data[1];
            },
            selectQuestion: function (id) {
                console.log(id);
                this.selectId = id;
            },
            selectType: function (type, index) {
                if (this.formData.formInfo.subjects[index].type === "danx") {
                    this.formData.formInfo.subjects[index].options = ["", ""];
                } else if (this.formData.formInfo.subjects[index].type === "duox") {
                    this.formData.formInfo.subjects[index].options = ["", "", ""];
                }
            },
            delQue: function (index) {
                this.formData.formInfo.subjects.splice(index, 1);
                this.selectId--;
                console.log(this.selectId)
            },
            copyQue: function (index) {
                let temp = JSON.parse(JSON.stringify(this.formData.formInfo.subjects[index]));
                temp.id = (new Date()).getTime();
                this.formData.formInfo.subjects.push(temp);
                let that = this;
                setTimeout(function () {
                    that.updated(that.formData.formInfo.subjects.length - 1);
                }, 200);

                if (this.formData.formInfo.subjects.length > 3) {
                    let toId = '#q' + String(this.formData.formInfo.subjects.length - 2);
                    $("html,body").animate({
                        scrollTop: $(toId).offset().top
                    }, {duration: 500, easing: "swing"});
                }
            },
            xiayi: function (index) {
                if (index < this.formData.formInfo.subjects.length - 1) {
                    let temp = this.formData.formInfo.subjects[index];
                    this.formData.formInfo.subjects[index] = this.formData.formInfo.subjects[index + 1];
                    this.formData.formInfo.subjects[index + 1] = temp;
                    this.selectId = index + 1;
                }
            },
            shangyi: function (index) {
                if (index > 0) {
                    let temp = this.formData.formInfo.subjects[index];
                    this.formData.formInfo.subjects[index] = this.formData.formInfo.subjects[index - 1];
                    this.formData.formInfo.subjects[index - 1] = temp;
                    this.selectId = index - 1;
                }
            },
            addQue: function (type) {
                let que = {
                    id: (new Date()).getTime(),
                    title: "",
                    tip: "",
                    type: type,
                    necessary: true,
                    options: ["", "", ""]
                };
                if (this.selectId === -1 || this.selectId === this.formData.formInfo.subjects.length - 1) {

                    this.formData.formInfo.subjects.push(que);
                    console.log('add-1', this.formData.formInfo.subjects.length - 1);
                    this.updated(this.formData.formInfo.subjects.length - 1);
                    if (this.formData.formInfo.subjects.length > 3) {
                        let toId = '#q' + String(this.formData.formInfo.subjects.length - 2);
                        $('html,body').animate({
                            scrollTop: $(toId).offset().top
                        }, {duration: 500, easing: "swing"});
                    }
                } else {
                    this.formData.formInfo.subjects.splice(this.selectId + 1, 0, que);
                    this.updated(this.selectId + 1);
                }
            },
            updated: function (updateSelect) {
                this.$nextTick(function () {
                    if (updateSelect || updateSelect === 0) {
                        console.log('updateSelect');
                        this.selectId = updateSelect;
                    } else {
                        console.log('update', updateSelect)
                    }
                })
            }
        }
    }
</script>

<style scoped>
    a {
        text-decoration: none;
        color: #000000;
    }

    .question-container {
        padding: 10px;
        background-color: #ffffff;
        position: fixed;
        top: 50px;
        left: 0;
        bottom: 0;
        z-index: 3000;
    }

    .question-type {
        margin: 10px;
        border: 1px solid #dddddd;
        border-radius: 2px;
    }

    .question-type a {
        padding: 10px 20px;
        display: block;
        font-size: 16px;
        color: #666666;
    }

    .question-type a:hover {
        background-color: #eeeeee;
    }

    .form-design {
        min-height: 800px;
        width: 100%;
        /*margin-left: 10px;*/
        /*display: flex;*/
        /*padding: 0 500px;*/
    }

    .form-design-container {
        margin-top: 20px;
        width: 700px;
        /*padding: 0 50px;*/
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

    .set-container {
        position: fixed;
        top: 70px;
        right: 70px;
        width: 450px;
    }

    .form-design-question {
        border: 1px solid #eeeeee;
        border-radius: 10px;
        margin-bottom: 20px;
        box-shadow: 0 0 5px 5px #eeeeee;
    }

    .form-design-question-index {
        padding-left: 20px;
        padding-top: 10px;
        font-size: 20px;
    }

    .question-title {
        display: flex;
        margin: 10px 20px;
        align-items: center;
    }

    .question-title-text {
        font-size: 16px;
        min-width: 50px;
        height: 30px;
        line-height: 30px;
    }

    .question-tip {
        display: flex;
        margin: 10px 20px;
        align-items: center;
    }

    .question-tip-text {
        font-size: 14px;
        width: 40px;
        height: 30px;
        line-height: 30px;
        color: #777777;
        padding-left: 12px;
    }

    .question-tip-input {
        width: 100%;
    }

    .question-set {
        margin: 0px 20px 20px 60px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-bottom: 10px;
    }

    .question-select-container {
        margin: 0px 20px 10px 65px;
    }

    .question-select-item {
        display: flex;
        align-items: center;
        height: 55px;
    }

    .question-select-item-index {
        width: 20px;
    }

    .question-select-add {
        margin-left: 20px;
        margin-bottom: 20px;
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

    .question-item-input {
        width: 100%;
        height: 15px;
        border: 1px solid #999999;
        margin-bottom: 10px;
        border-radius: 4px;
        padding: 5px;
        font-size: 12px;
        color: #999999;
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

    .question-item-areatext {
        width: 100%;
        height: 50px;
        border: 1px solid #999999;
        margin-bottom: 10px;
        border-radius: 4px;
        padding: 5px;
        font-size: 12px;
        color: #999999;
    }

    .question-select-item-input {
        width: 100%;
        height: 20px;
        padding: 5px;
    }

    .switch-necessary {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 28px;
    }

    .set-explain {
        font-size: 16px;
        padding: 10px 0;
    }
</style>