import axios from 'axios'


const request = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 5000
})

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=UTF-8';
    //把token放到请求头里面
    const user = localStorage.getItem("user");
    if (user) {
        config.headers['token'] = JSON.parse(user).token;
        config.headers['role'] = JSON.parse(user).role;
    }

    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request