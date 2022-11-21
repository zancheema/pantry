import { useState } from 'react';
import { Alert, Button, Pressable, Text, TextInput, View } from 'react-native';
import styles from '../../common/styles';
import { signup } from '../../service/authService';

export default function SignUpScreen({ navigation }) {
    const [username, onChangeUsername] = useState('');
    const [password, onChangePassword] = useState('');
    const [cpassword, onChangeCpassword] = useState('');

    async function handleSignup() {
        if (username === '' || password === '' || cpassword === '') {
            Alert.alert('No field should be left empty');
        } else if (password !== cpassword) {
            Alert.alert('Passwords do not match');
        } else {
            await signup(username, password);
            Alert.alert('Signup successful');
            navigation.navigate('SignIn');
        }
    }

    function openSignin() {
        navigation.navigate('SignIn');
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
            <TextInput
                style={styles.input}
                placeholder='Confirm Password'
                secureTextEntry={true}
                onChangeText={onChangeCpassword}
                value={cpassword}
            />
            <Pressable style={styles.primaryButtonWide} onPress={handleSignup}>
                <Text style={styles.filledButtonText}>Login</Text>
            </Pressable>
            <View style={{ marginTop: 30 }}>
                <Button onPress={openSignin} title='Sign In' />
            </View>
        </View>
    );
}