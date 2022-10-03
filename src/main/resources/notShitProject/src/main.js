import { createApp } from 'vue'
import App from './App.vue'
import {axios, router} from './plugin'
// 引入ant-design-vue
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import { message } from 'ant-design-vue';


const app = createApp(App)

// 配置单节点vue3 app
app.use(router)
app.use(Antd)
app.config.globalProperties.$axios = axios
app.config.globalProperties.$message = message

app.mount('#app')
