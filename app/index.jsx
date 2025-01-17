import React from 'react'
import { View, Text } from 'react-native'
import LoginScreen from "../screens/LoginScreen"
import Homescreen from "../screens/Homescreen"
import {AuthProvider} from "../screens/AuthProvider";

import { createNativeStackNavigator } from '@react-navigation/native-stack';
const Stack = createNativeStackNavigator();



const index = () => {
    return (
      <AuthProvider>
        <Stack.Navigator initialRouteName="login">
          <Stack.Screen name="login" component={LoginScreen} options={{ headerShown: false }} />
          <Stack.Screen name="homescreen" component={Homescreen} options={{ headerShown: false }} />
        </Stack.Navigator>
      </AuthProvider>
      
    
    )
}

export default index
