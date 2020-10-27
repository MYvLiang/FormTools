<template>
    <div class="preview-page-body">
        预览{{formData.formTitle}}
    </div>
</template>

<script>
    export default {
        name: "EditPreview",
        props: ['formData'],
    }
</script>

<style scoped>
    .preview-page-body{
        min-height: 20px;
        background-color: #00a854;
    }
</style>