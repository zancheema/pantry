import { StatusBar } from 'expo-status-bar';
import { Button, View } from 'react-native';
import styles from '../../common/styles';

export default function HomeScreen({ navigation }) {
    function checkAndAdd() {
        navigation.navigate('Add Product');
    }

    function use() {
        navigation.navigate('Use Product', { name: 'Zain' });
    }

    return (
        <View style={styles.container}>
            <Button title='Check & Add' onPress={checkAndAdd} />
            <View style={styles.verticalSpace} />
            <Button  title='Use' onPress={use} />


            <StatusBar style="auto" />
        </View>
    );
};