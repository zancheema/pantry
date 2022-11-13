import axios from 'axios';

const IP_ADDRESS = '192.168.43.149'
export default axios.create({
    baseURL: `http://${IP_ADDRESS}:8080/api/`,
    timeout: 5 * 60 * 1000, // 5 minutes
});