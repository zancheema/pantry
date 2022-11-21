import { useState } from 'react';
import { Alert, Button, Pressable, Text, TextInput, View } from 'react-native';
import styles from '../../common/styles';
import { signin } from '../../service/authService';

export default function SignInScreen({ route, navigation }) {
    const [username, onChangeUsername] = useState('');
    const [password, onChangePassword] = useState('');

    const { onLogin } = route.params;

    async function handleLogin() {
        if (username === '' || password === '') {
            Alert.alert('No field should be left empty');
            return;
        }
        try {
            const { accessToken } = (await signin(username, password)).data;
            onLogin(accessToken);
        } catch (e) {
            console.log(e);
            Alert.alert('Failed to sign in');
        }
    }

    function openSignup() {
        navigation.navigate('SignUp');
    }

    return (
        <View style={styles.container}>
            <TextInput
                style={styles.input}
                placeholder='Username'
                onChangeText={onChangeUsername}
                value={username}
            />
            <TextInput
                style={styles.input}
                placeholder='Password'
                secureTextEntry={true}
                onChangeText={onChangePassword}
                value={password}
            />
            <Pressable style={styles.primaryButtonWide} onPress={handleLogin}>
                <Text style={styles.filledButtonText}>Login</Text>
            </Pressable>
            <View style={{ marginTop: 30 }}>
                <Button onPress={openSignup} title='Sign Up' />
            </View>
        </View>
    );
}