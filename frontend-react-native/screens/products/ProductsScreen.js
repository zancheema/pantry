import { useEffect, useState } from 'react'
import { View } from 'react-native';
import styles from '../../common/styles';
import { getProducts } from '../../service/productService'

export default function ProductsScreen() {
    const [products, setProducts] = useState([]);

    async function loadProducts() {
        const {products} = (await getProducts()).data;
        setProducts(products);
    }

    useEffect(() => {
        loadProducts();
    });

    return (
        <View style={styles.container}>
            <View style={styles.row}>
                <Text>Barcode</Text>
                <Text>name</Text>
                <Text>Qty.</Text>
            </View>
            {
                products.map(product => (
                    <View style={styles.row}>
                        <Text>{product.barcode}</Text>
                        <Text>{product.name}</Text>
                        <Text>{product.quantity}</Text>
                    </View>
                ))
            }
        </View>
    );
}