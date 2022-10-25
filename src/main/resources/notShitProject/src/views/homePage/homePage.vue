<template>
    <div class="home-page-container">
        <div class="main-background"></div>
        <div class="main-head">
            <div class="main-title">
                <div class="main-title-name">飞屎计划</div>
                <div class="main-title-description">Not Shit Project</div>
            </div>
            <div class="main-tabs">
                <div class="main-tabs-item" :class="currentType === 'dayRead' ? 'selected-main-tab' : ''"
                    @click="changeTab('dayRead')">
                    <span>每日阅读</span>
                </div>
                <div class="main-tabs-item" :class="currentType === 'other' ? 'selected-main-tab' : ''"
                    @click="changeTab('other')">
                    <span>其他东西</span>
                </div>
            </div>
        </div>
        <div class="main-container">
            <div v-if="currentType === 'dayRead'" class="day-read-container">
                <day-read></day-read>
            </div>
            <div class="other-info-container">
                <div class="other-info">飞屎计划 @ 2022</div>
                <div class="other-info">备案号</div>
                <a target="_blank" rel="nofollow" href="https://beian.miit.gov.cn" class="other-info beian">
                    苏ICP备2022041752号-1</a>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, getCurrentInstance } from "vue"
import dayRead from "../dayRead/dayRead.vue";

let currentType = ref('dayRead')
const that = getCurrentInstance().proxy

function changeTab(tabName) {
    switch (tabName) {
        case 'dayRead':
            currentType.value = 'dayRead'
            break
        case 'other':
            that.$message.info('正在建设中！')
            break
        default:
            break
    }
}

</script>

<style scoped lang="scss">
.home-page-container {
    width: 100%;
    height: 100%;
    line-height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    background: #555555;

    .main-background {
        height: 100%;
        width: 100%;
        position: fixed;
        z-index: 0;
        background-image: url("@/assets/background.png");
        background-repeat: no-repeat;
        background-size: 100%;
        opacity: 0.5;
    }

    .main-head {
        width: 100%;
        height: 10%;
        background-image: url("@/assets/background.png");
        background-repeat: no-repeat;
        background-size: 100%;
        user-select: none;
        z-index: 999;
        position: fixed;

        .main-title {
            float: left;
            margin-left: 3%;

            .main-title-name {
                height: 2em;
                line-height: 2em;
                font-size: 2em;
                font-weight: 600;
            }

            .main-title-description {
                height: 1.5em;
                line-height: 1.5em;
                font-size: 1.5em;
            }
        }

        .main-tabs {
            float: right;
            display: flex;
            height: 100%;
            width: 30%;
            align-items: flex-end;

            .main-tabs-item {
                display: flex;
                height: 70%;
                text-align: center;
                cursor: url('@/assets/cursor/pointer.cur'), pointer;
                width: auto;
                padding: 0px 10px;
                justify-content: center;
                align-items: flex-end;
                transition: all 0.4s;

                span {
                    position: relative;
                    font-size: 1.8em;
                    height: 1.8em;
                    line-height: 1.8em;
                    margin-bottom: 10px;
                    font-weight: lighter;
                }
            }

            .main-tabs-item:hover {
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 50px;
                transition: all 0.4s;
            }

            .selected-main-tab {
                background-color: rgba(255, 255, 255, 0.5);
                border-radius: 5px;
                transition: all 0.4s;
            }
        }
    }

    .main-container {
        position: relative;
        top: 10%;
        height: 90%;
        width: 100%;
        overflow-y: auto;
        z-index: 99;

        .day-read-container {
            position: relative;
            left: 15%;
            height: auto;
            min-height: 97%;
            width: 70%;
            background-color: rgba(85, 85, 85, 0.6);
        }

        .other-info-container {
            display: flex;
            height: 3%;
            justify-content: center;
            align-items: center;
            background: rgba(255, 255, 255, 0.3);

            .other-info {
                margin: 0px 5px;
            }

            .beian {
                color: #000000
            }
        }
    }

    ::-webkit-scrollbar {
        width: 8px;
    }

    ::-webkit-scrollbar-track {
        background-color: #ffffff80;
        border-radius: 10px;
    }

    ::-webkit-scrollbar-thumb {
        background-color: rgba(0, 0, 0, 0.6);
        border-radius: 10px;
    }
}
</style>