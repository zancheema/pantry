import { useState, useEffect } from 'react';
import { Text, View, StyleSheet, Button, Alert } from 'react-native';
import { BarCodeScanner } from 'expo-barcode-scanner';
import styles from '../../common/styles';
import CreateProductDialog from './CreateProductDialog';
import { productExists } from '../../service/productService';
import { AddProductDialog } from './AddProductDialog';

export default function CheckAndAddProductScreen({ navigation }) {
    const [hasPermission, setHasPermission] = useState(null);
    const [barcode, setBarcode] = useState(null);

    const [createDialogVisible, setCreateDialogVisible] = useState(false);
    const showCreateDialog = () => setCreateDialogVisible(true);
    const hideCreateDialog = () => setCreateDialogVisible(false);

    const [addDialogVisible, setAddDialogVisible] = useState(false);
    const showAddDialog = () => setAddDialogVisible(true);
    const hideAddDialog = () => setAddDialogVisible(false);

    const showProductAlreadyExists = (quantity) => Alert.alert(
        'Already Exists',
        `${quantity} instances of this product already exist. Do you still want to add more?`,
        [
            {
                text: 'Cancel',
                style: 'cancel'
            },
            {
                text: 'Yes',
                onPress: showAddDialog
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
            showProductAlreadyExists(quantity);
        } else {
            showCreateDialog();
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

            <CreateProductDialog
                barcode={barcode}
                dialogVisible={createDialogVisible}
                hideDialog={hideCreateDialog}
            />

            <AddProductDialog
                barcode={barcode}
                dialogVisible={addDialogVisible}
                hideDialog={hideAddDialog}
            />
        </View>
    );
}