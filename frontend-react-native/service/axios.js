import axios from 'axios';
import * as SecureStore from 'expo-secure-store';

const IP_ADDRESS = '192.168.43.149'

export const api = async (config) => {
    const token = await SecureStore.getItemAsync('access-token');
    console.log('api request token: ' + token);
    console.log('api request url: ' + config.url);
    return await axios.request({
        ...config,
        url: `http://${IP_ADDRESS}:8080/api/${config.url}`,
        headers: {
            // ...config.headers,
            Authorization: `Bearer ${token}`
        }
    });
};

export const auth = async (config) => await axios.request({
    ...config,
    url: `http://${IP_ADDRESS}:8080/auth/${config.url}`
});