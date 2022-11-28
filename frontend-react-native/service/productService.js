import { api } from './axios';

export async function getProducts() {
    // return await axios.get('/products');
    return await api({ method: 'get', url: 'products' });
}

export async function getProduct(barcode) {
    // return await axios.get(`/products/${barcode}`);
    return await api({ method: 'get', url: `products/${barcode}` });
}

export async function productExists(barcode) {
    // return await axios.get(`/products/${barcode}/exists`);
    return await api({ method: 'get', url: `products/${barcode}/exists` });
}

export async function addProduct(product) {
    // return await axios.post('/products/add', product);
    console.log('add product: ' + JSON.stringify(product));
    return await api({ method: 'post', url: 'products/add', data: product });
}

export async function useProduct(barcode, { quantity }) {
    // return await axios.patch(`/products/${barcode}/use`, { quantity });
    return await api({
        method: 'patch',
        url: `products/${barcode}/use`,
        data: { quantity }
    });
}

export async function useProductCompletely(barcode) {
    // await axios.delete(`/products/${barcode}/use/all`);
    return await api({ method: 'delete', url: `products/${barcode}/use/all` });
}