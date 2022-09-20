<template>
    <div class='home-page-container'>
        <div class='home-page-title'>欢迎!</div>
        <div class='home-page-description'>这里是飞屎计划</div>
        <div class='home-page-description'>This is Not-Shit-Project</div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

</script>

<style scoped lang='scss'>
.home-page-container {
    width: 100%;
    height: 100%;
    line-height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #dedede;

    .home-page-title {
        height: 30px;
        line-height: 30px;
        font-size: 30px;
        font-weight: 800;
        margin: 5px 0px;
    }

    .home-page-description {
        height: 20px;
        line-height: 20px;
        font-size: 20px;
        font-weight: 600;
        margin: 5px 0px;
    }
}
</style>