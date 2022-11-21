import { auth as axios } from './axios';
import base64 from 'base-64';

export async function signin(username, password) {
    return await axios({
        method:
            'post', url:
            'token',
        headers: {
            Authorization: 'Basic ' + base64.encode(`${username}:${password}`)
        }
    });
};

export async function signup(username, password) {
    return await axios({ method: 'post', url: 'signup', data: { username, password } });
}