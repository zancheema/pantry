import { useState } from 'react';
import { Alert } from 'react-native';
import Dialog from 'react-native-dialog';
import { getProduct, useProduct } from '../../service/productService';

export default function UseProductDialog({ dialogVisible, hideDialog, barcode }) {
    const [quantity, setQuantity] = useState(1);

    async function useCurrentProduct() {
        const product = (await getProduct(barcode)).data;
        if (quantity > product.quantity) {
            Alert.alert(`Qty. can be max of ${product.quantity}`);
            return;
        }
        const usedProduct = (await useProduct(barcode, { quantity })).data;
        hideDialog();
        Alert.alert(`${usedProduct.quantity} remaining`);
    }

    return (
        <Dialog.Container visible={dialogVisible}>
            <Dialog.Title>Use Product</Dialog.Title>
            <Dialog.Description>
                Set quantity for the product:
            </Dialog.Description>
            <Dialog.Input
                onChangeText={(text) => setQuantity(text)}
                keyboardType='numeric'
                placeholder='Qty.'
                value={quantity}
            />
            <Dialog.Button label='Cancel' onPress={hideDialog} />
            <Dialog.Button label='OK' onPress={useCurrentProduct} />
        </Dialog.Container>
    );
}