<template>
  <div id='appContainer'>
    <RouterView />
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<style scoped>
#appContainer {
  width: 100%;
  height: 100%;
  background: #555555;
  cursor: url("@/assets/cursor/default.cur"), default;
}
</style>
