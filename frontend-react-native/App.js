import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { StyleSheet, View } from 'react-native';
import AddProductScreen from './screens/add/CheckAndAddProductScreen';
import HomeScreen from './screens/home/HomeScreen';
import UseProductScreen from './screens/use/UseProductScreen';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
          <Stack.Screen name='Home' component={HomeScreen} />
          <Stack.Screen name='Add Product' component={AddProductScreen} />
          <Stack.Screen name='Use Product' component={UseProductScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}