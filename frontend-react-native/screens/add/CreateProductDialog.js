import { useState } from 'react';
import { Alert } from 'react-native';
import Dialog from 'react-native-dialog';
import { createProduct } from '../../service/productService';

export default function CreateProductDialog({ dialogVisible, hideDialog, barcode }) {
    const [quantity, setQuantity] = useState(1);
    const [name, setName] = useState('');

    async function addCurrentProduct() {
        if (quantity == 0 || quantity === '' || name === '') {
            Alert.alert('No field should be empty');
            return;
        }
        try {
            await createProduct({ barcode, name, quantity })
            hideDialog();
            Alert.alert('Product added successfully.');
        } catch (e) {
            console.log('add product error: ' + e);
            Alert.alert('Failed to add product');
        }
    }

    return (
        <Dialog.Container visible={dialogVisible}>
            <Dialog.Title>Add Product</Dialog.Title>
            <Dialog.Description>
                Set quantity for the product:
            </Dialog.Description>
            <Dialog.Input
                onChangeText={(text) => setName(text)}
                placeholder='Name'
                value={name}
            />
            <Dialog.Input
                onChangeText={(text) => setQuantity(text)}
                keyboardType='numeric'
                placeholder='Qty.'
                value={quantity}
            />
            <Dialog.Button label='Cancel' onPress={hideDialog} />
            <Dialog.Button label='OK' onPress={addCurrentProduct} />
        </Dialog.Container>
    );
}