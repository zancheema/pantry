import { useEffect, useState } from 'react'
import { Text, TextInput, View } from 'react-native';
import styles from '../../common/styles';
import { getProducts } from '../../service/productService'
import SearchBar from '../common/SearchBar';

const MAX_TEXT_LENGTH = 25;

export default function ProductsScreen({ navigation }) {
    const [products, setProducts] = useState([]);
    const [filter, setFilter] = useState('');

    async function loadProducts() {
        const { products } = (await getProducts()).data;
        setProducts(products);
    }

    useEffect(() => {
        loadProducts();
    }, []);

    return (
        <View>
            <SearchBar keyword={filter} setKeyword={setFilter} />
            <View style={{
                backgroundColor: 'black',
                padding: 10,
            }}>
                <View style={styles.row}>
                    <Text style={{ color: 'white' }}>Name</Text>
                    <Text style={{ color: 'white' }}>Qty.</Text>
                </View>
            </View>
            {
                products.map(product =>
                    product.name.toUpperCase().includes(filter.toUpperCase()) &&
                    (<>
                        <View style={{ ...styles.row, margin: 10 }}>
                            <Text style={{ flexShrink: 1 }}>
                                {
                                    product.name.length > MAX_TEXT_LENGTH
                                        ? product.name.substring(0, MAX_TEXT_LENGTH - 3) + '...'
                                        : product.name
                                }
                            </Text>
                            <Text style={{ flexShrink: 1 }}>{product.quantity}</Text>
                        </View>
                        <View style={styles.divider} />
                    </>)
                )
            }
        </View>
    );
}