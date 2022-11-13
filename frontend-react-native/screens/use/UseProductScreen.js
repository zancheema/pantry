import { useState, useEffect } from 'react';
import { Text, View, StyleSheet, Button, Alert } from 'react-native';
import { BarCodeScanner } from 'expo-barcode-scanner';
import styles from '../../common/styles';
import UseProductDialog from './UseProductDialog';
import AddProductDialog from '../add/AddProductDialog';
import { productExists } from '../../service/productService';

export default function UseProductScreen({ navigation, route }) {
    const [hasPermission, setHasPermission] = useState(null);
    const [barcode, setBarcode] = useState(null);

    const [addProductDialogVisible, setAddProductDialogVisible] = useState(false);
    const showAddProductDialog = () => setAddProductDialogVisible(true);
    const hideAddProductDialog = () => setAddProductDialogVisible(false);

    const [useProductDialogVisible, setUseProductDialogVisible] = useState(false);
    const showUseProductDialog = () => setUseProductDialogVisible(true);
    const hideUseProductDialog = () => setUseProductDialogVisible(false);

    const showProductDoesNotExist = (quantity) => Alert.alert(
        'Not registered',
        'The product is not scanned in pantry before. Do you wnat to add it?',
        [
            {
                text: 'Cancel',
                style: 'cancel'
            },
            {
                text: 'Yes',
                onPress: showAddProductDialog
            }
        ],
        {
            cancelable: true
        }
    );

    useEffect(() => {
        const getBarCodeScannerPermissions = async () => {
            const { status } = await BarCodeScanner.requestPermissionsAsync();
            setHasPermission(status === 'granted');
        };

        getBarCodeScannerPermissions();
    }, []);

    async function handleBarCodeScanned({ type, data }) {
        setBarcode(data);
        // alert(`Bar code with type ${type} and data ${data} has been scanned!`);

        // will use a conditon here if the barcode exists or not.
        const { exists, quantity } = await (await productExists(data)).data;
        console.log('exists: ' + exists);
        if (exists) {
            showUseProductDialog();
        } else {
            showProductDoesNotExist(quantity);
        }
    }

    if (hasPermission === null) {
        return <Text>Requesting for camera permission</Text>;
    }
    if (hasPermission === false) {
        return <Text>No access to camera</Text>;
    }

    return (
        <View style={styles.container}>
            <BarCodeScanner
                onBarCodeScanned={barcode ? undefined : handleBarCodeScanned}
                style={StyleSheet.absoluteFillObject}
            />
            {barcode && <Button title={'Tap to Scan Again'} onPress={() => setBarcode(false)} />}

            <AddProductDialog
                barcode={barcode}
                dialogVisible={addProductDialogVisible}
                hideDialog={hideAddProductDialog}
            />

            <UseProductDialog
                barcode={barcode}
                dialogVisible={useProductDialogVisible}
                hideDialog={hideUseProductDialog}
            />
        </View>
    );
}