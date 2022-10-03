<template>
    <div>
        <textarea :id="id" name="content"></textarea>
    </div>
</template>
<script>
export default {
    props: ["content"],//从父组件转递的内容
    data() {
        return {
            id: parseInt(Math.random() * 10000).toString(),
            ckeditor: null
        };
    },
    // 初始化加载内容
    mounted() {
        // 渲染编辑器
        this.ckeditor = window.CKEDITOR.replace('content', { height: '500px' })//定义编辑器的高度
        // 设置初始内容
        this.ckeditor.setData(this.content)
        // 监听内容变更事件
        const that = this
        this.ckeditor.on("change", () => {
            that.$emit("sendContent", that.ckeditor.getData())
        });
    }
};
</script>
<style scoped>

</style>
  