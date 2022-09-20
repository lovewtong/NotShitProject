import Axios from 'axios'

let axios = Axios.create({
    timeout: 1800000
})

// 设置axios参数
// 请求拦截器
axios.interceptors.request.use((config) => {
    return config
}, (error) => {
    return error
})

// 响应拦截器
axios.interceptors.response.use((response) => {
    if (response.resultCode === 200) {
        return response.data
    } else {
        return { resultCode: -1 }
    }
})

export default axios