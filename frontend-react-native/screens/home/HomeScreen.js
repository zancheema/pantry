import { StatusBar } from 'expo-status-bar';
import { Button, Pressable, Text, View } from 'react-native';
import styles, { colors } from '../../common/styles';

export default function HomeScreen({ route, navigation }) {
    const { onLogout } = route.params;

    function checkAndAdd() {
        navigation.navigate('Add Product');
    }

    function use() {
        navigation.navigate('Use Product', { name: 'Zain' });
    }

    return (
        <View style={{ flex: 1, backgroundColor: colors.bgColor }}>
            <View style={styles.container}>
                <View style={{ marginBottom: 10 }}>
                    <Button title='Check & Add' onPress={checkAndAdd} />
                </View>

                <View style={{ marginBottom: 10 }}>
                    <Button title='Use' onPress={use} />
                </View>


            </View>

            <Pressable style={{
                ...styles.secondaryButtonWide,
                marginBottom: 20,
                alignSelf: 'center'
            }} onPress={onLogout}>
                <Text style={styles.filledButtonText}>Logout</Text>
            </Pressable>

            <StatusBar style="auto" />
        </View>
    );
};