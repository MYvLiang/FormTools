<template>
    <div class="header">
        <a class="header-item" v-bind:class="{ 'header-item-select': topSelect === 0 }" @click="selectItem(0)"
           href="javascript:void(0);">编辑</a>
        <a class="header-item" v-bind:class="{ 'header-item-select': topSelect === 1 }" @click="selectItem(1)"
           href="javascript:void(0);">预览</a>
        <a class="header-item" @click="$emit('save')" href="javascript:void(0);">保存草稿</a>
        <a class="header-item" @click="$emit('release-form')" href="javascript:void(0);">发布</a>
    </div>
</template>

<script>
    export default {
        name: "EditHeader",
        data() {
            return {
                topSelect: 0
            }
        },
        methods: {
            selectItem(id) {
                this.topSelect = id;
                this.$emit('select-item',id)
            }
        }
    }
</script>

<style scoped>
    .header {
        padding-left: 140px;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: #ffffff;
        box-shadow: 0px 1px 5px 1px #f5f5f5;
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        height: 50px;
        z-index: 1000;
    }

    .header-item {
        padding: 0px 10px;
        margin: 10px 0px;
        font-size: 16px;
    }

    .header-item:not(:last-child) {
        border-right: 2px solid #eeeeee;
    }

    .header-item-select {
        color: #1593ff;
    }

    a {
        text-decoration: none;
        color: #000000;
    }
</style>