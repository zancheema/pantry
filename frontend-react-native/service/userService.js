import { api } from './axios';

export async function getUserProfile() {
    return await api({ method: 'get', url: 'users/profile' });
}