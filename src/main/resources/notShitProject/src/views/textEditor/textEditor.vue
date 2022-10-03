<template>
    <div class="editor-container">
        <div class="container-panel">
            <div class="main-panel">
                <div class="title-bar">
                    飞屎计划--富文本编辑器
                </div>
                <div class="tool-bar">
                    <div class="title-input tool-bar-input tool-bar-item">
                        <div class="name">题目：</div>
                        <a-input class="input" v-model:value="title" placeholder="请输入题目"></a-input>
                    </div>
                    <div class="author-input tool-bar-input tool-bar-item">
                        <div class="name">作者：</div>
                        <a-input class="input" v-model:value="author" placeholder="请输入作者"></a-input>
                    </div>
                    <div class="id-input tool-bar-input tool-bar-item">
                        <div class="name">ID：</div>
                        <a-input class="input" v-model:value="id" placeholder="指定文章ID"></a-input>
                    </div>
                    <div class="id-reader tool-bar-item">
                        <a-button title="根据输入id读取文章内容" @click="getContentById">
                            <template #icon>
                                <download-outlined />
                            </template>
                            读取
                        </a-button>
                    </div>
                    <div class="save-button tool-bar-item">
                        <a-button title="保存文章内容，若ID为空则新建文章" @click="saveContent">
                            <template #icon>
                                <upload-outlined />
                            </template>
                            保存
                        </a-button>
                    </div>
                    <div class="delete-button tool-bar-item">
                        <a-button danger title="删除指定ID文章">
                            <template #icon>
                                <delete-outlined />
                            </template>
                            删除
                        </a-button>
                    </div>
                </div>
                <ck-editor class="ckeditor" @sendContent="getContent" :content="htmlContent"></ck-editor>
            </div>
        </div>
    </div>
</template>

<script>
import CkEditor from '../../components/ckeditor/ckeditor.vue'
import { DownloadOutlined, UploadOutlined, DeleteOutlined } from '@ant-design/icons-vue'

export default {
    components: { CkEditor, DownloadOutlined, UploadOutlined, DeleteOutlined },
    data() {
        return {
            htmlContent: '',
            infoText: '',
            author: '',
            id: '',
            title: ''
        }
    },
    methods: {
        // 当富文本内容变化时，获取富文本内容
        getContent(val) {
            this.infoText = val
        },
        // TODO 根据ID获取文章
        getContentById() {

        },
        // 保存文章
        saveContent() {
            // 简单表单验证
            if (this.author === '') {
                this.$message.error('没有填写作者！')
                return false
            } else if (this.infoText === '') {
                this.$message.error('没有填写文章内容！')
                return false
            } else if (this.title === '') {
                this.$message.error('没有填写题目！')
                return false
            }

            let params = {
                author: this.author,
                title: this.title,
                essay: this.infoText,
                id: null
            }
            // 如果存在id则设置id
            if (this.id) {
                params.id = this.id
            }
            // 发送保存请求
            this.$axios.put('api/zlibrary/controller/saveOrUpdate', JSON.stringify(params), {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((res) => {
                if (res === true) {
                    this.$message.success('保存成功！')
                } else {
                    this.$message.error('保存失败！')
                }
            }).catch((err) => {
                console.log(err)
                this.$message.error(err)
            })
        },
        // TODO 根据ID删除文章
        deleteContentById() {

        }
    }
}
</script>

<style lang="scss" scoped>
.editor-container {
    width: 100%;
    height: 100%;
    background: #555;

    .container-panel {
        height: 100%;
        width: 100%;
        background-image: url("@/assets/background.png");
        background-repeat: no-repeat;
        background-size: 100%;
        opacity: 0.6;

        .main-panel {
            height: 100%;
            width: 70%;
            margin-left: 15%;
            background: #555;
            opacity: 0.8;
            overflow-y: auto;

            .title-bar {
                height: 40px;
                line-height: 40px;
                width: calc(100% - 40px);
                margin: 20px 20px 0px 20px;
                font-size: 30px;
                font-weight: bold;
                color: #fff;
            }

            .tool-bar {
                display: flex;
                align-items: center;
                height: 40px;
                width: calc(100% - 40px);
                margin: 20px 20px 0px 20px;
                font-size: 16px;
                font-weight: bold;
                color: #fff;

                .tool-bar-input {
                    display: flex;

                    .name {
                        height: 32px;
                        line-height: 32px;
                        width: 50px;
                    }

                    .input {
                        height: 32px;
                        width: calc(100% - 50px);
                    }
                }

                .tool-bar-item {
                    padding-right: 10px;
                }
            }

            .ckeditor {
                margin: 20px;
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
}
</style>