import axios from './axios';

export async function getProducts() {
    return await axios.get('/products');
}

export async function getProduct(barcode) {
    return await axios.get(`/products/${barcode}`);
}

export async function productExists(barcode) {
    return await axios.get(`/products/${barcode}/exists`);
}

export async function addProduct(product) {
    return await axios.post('/products/add', product);
}

export async function useProduct(barcode, { quantity }) {
    return await axios.patch(`/products/${barcode}/use`, { quantity });
}

export async function useProductCompletely(barcode) {
    await axios.delete(`/products/${barcode}/use/all`);
}