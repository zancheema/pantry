import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { useEffect, useState } from 'react';
import AddProductScreen from './screens/add/CheckAndAddProductScreen';
import HomeScreen from './screens/home/HomeScreen';
import UseProductScreen from './screens/use/UseProductScreen';
import * as SecureStore from 'expo-secure-store';
import SignInScreen from './screens/signin/SignInScreen';
import SignUpScreen from './screens/signup/SignUpScreen';
import { getUserProfile } from './service/userService';
import ProductsScreen from './screens/products/ProductsScreen';

const Stack = createNativeStackNavigator();

export default function App() {
  const [userToken, setUserToken] = useState(null);

  useEffect(() => {
    refreshToken();
  });

  async function refreshToken() {
    try {
      await getUserProfile(); // validate token

      const token = await SecureStore.getItemAsync('access-token');
      setUserToken(token);
    } catch (e) {
      if (e?.response?.status === 401) {
        await onLogout();
      }
    }
  }

  async function onLogin(token) {
    console.log('onLogin: ' + token);
    await SecureStore.setItemAsync('access-token', token);
    refreshToken();
  }

  async function onLogout() {
    await SecureStore.deleteItemAsync('access-token');
    setUserToken(null);
  }

  return (
    <NavigationContainer>
      <Stack.Navigator>
        {
          userToken === null ? (
            <>
              <Stack.Screen name='SignIn' component={SignInScreen} initialParams={{ onLogin }} />
              <Stack.Screen name='SignUp' component={SignUpScreen} />
            </>
          ) : (
            <>
              <Stack.Screen name='Home' component={HomeScreen} initialParams={{ onLogout }} />
              <Stack.Screen name='Add Product' component={AddProductScreen} />
              <Stack.Screen name='Use Product' component={UseProductScreen} />
              <Stack.Screen name='Products' component={ProductsScreen} />
            </>
          )
        }
      </Stack.Navigator>
    </NavigationContainer>
  );
}