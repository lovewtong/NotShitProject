<template>
    <div class="reader-container">
        <div class="text-area">
            <div class="essay-title">{{ title }}</div>
            <div class="essay-author">{{ author }}</div>
            <div class="essay-html" v-html="htmlContent"></div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            title: '',
            author: '',
            htmlContent: ''
        }
    },
    mounted() {
        this.$axios.get('api/zlibrary/controller/randomRead').then((res) => {
            if (res.data.title && res.data.author && res.data.essay) {
                this.title = res.data.title
                this.author = res.data.author
                this.htmlContent = res.data.essay
            } else {
                that.$message.error('没有获取到文章信息！')
            }
        })
    }
}
</script> 

<style scoped lang="scss">
.reader-container {
    width: 100%;
    height: 100%;

    .text-area {
        position: relative;
        height: auto;
        width: 100%;
        padding: 20px 50px;
        z-index: 999;
        user-select: none;
        color: #dedede;
        background: rgba($color: #000000, $alpha: 0);

        .essay-title {
            height: 45px;
            width: 100%;
            display: flex;
            font-size: 40px;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;
        }

        .essay-author {
            height: 35px;
            width: 100%;
            display: flex;
            font-size: 25px;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;
        }

        .essay-html {
            line-height: 2em;
            font-size: 20px;

            :deep p {
                margin-bottom: 0px !important;
            }
        }
    }
}
</style>