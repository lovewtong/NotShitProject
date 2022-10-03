<template>
    <div class="reader-container">
        <div class="read-panel">
            <div class="text-area">
                <div class="essay-title">{{ title }}</div>
                <div class="essay-author">{{ author }}</div>
                <div class="essay-html" v-html="htmlContent"></div>
            </div>
            <div class="read-background"></div>
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
            if (res.title && res.author && res.essay) {
                this.title = res.title
                this.author = res.author
                this.htmlContent = res.essay
            }
        })
    }
}
</script> 

<style scoped lang="scss">
.reader-container {
    width: 100%;
    height: 100%;

    .read-panel {
        height: 100%;
        width: 70%;
        margin-left: 15%;
        overflow-y: auto;
        position: relative;

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
                height: 40px;
                width: 100%;
                display: flex;
                font-size: 30px;
                justify-content: center;
                align-items: center;
                margin-bottom: 10px;
            }

            .essay-author {
                height: 30px;
                width: 100%;
                display: flex;
                font-size: 20px;
                justify-content: center;
                align-items: center;
                margin-bottom: 10px;
            }

            .essay-html {
                line-height: 2em;
                font-size: 16px;

                ::v-deep p {
                    margin-bottom: 0px !important;
                }
            }
        }

        .read-background {
            height: 100%;
            width: 70%;
            position: fixed;
            top: 0px;
            background-color: #555;
            opacity: 0.8;
            z-index: 9;
        }
    }

    ::-webkit-scrollbar {
        width: 8px;
    }

    ::-webkit-scrollbar-track {
        background-color: #F5F5F5;
    }

    ::-webkit-scrollbar-thumb {
        background-color: #000000;
    }
}
</style>