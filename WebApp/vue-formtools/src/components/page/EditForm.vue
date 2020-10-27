<template>
    <div class="body-background">
        <edit-header
                @save="save"
                @release-form="releaseForm"
                @select-item="selectItem"
        ></edit-header>
        <div class="page-body">
            <edit-question v-show="topSelect===0" ref="questionData"></edit-question>
            <edit-preview v-show="topSelect===1" :formData="formData"></edit-preview>
        </div>
    </div>
</template>

<script>
    import EditHeader from './edit/EditHeader';
    import EditQuestion from './edit/EditQuestion';
    import EditPreview from './edit/EditPreview';
    export default {
        name: "EditForm",
        components: {EditQuestion, EditHeader,EditPreview},
        data() {
            return {
                topSelect:0,
                formData:{}
            }
        },
        methods: {
            selectItem(id){
                this.formData=this.$refs.questionData.formData;
                this.topSelect=id;
            },

            save: function () {
                console.log('保存:', this.$refs.questionData.formData)
            },
            releaseForm: function () {
                console.log('发布:', this.$refs.questionData.formData)
            }
        }
    };
</script>
<style>
    .form-design-title textarea{
        text-align: center;
        border: none;
        resize:none;
        font-size: 18px;
        width: 100%;
        line-height:1.5;
        background-color: #ffffff;
        color: #676767;
        margin-bottom: 10px;
    }
</style>
<style scoped>
    .body-background{
        margin: 0;
        padding: 0;
        background-color: #f0f1f8;
    }

    .page-body{
        margin-top: 50px;
        display: flex;
        min-height: 800px;
        padding-bottom: 50px;
        justify-content: center;
    }

</style>