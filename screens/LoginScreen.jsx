import React ,{useState,useContext}from 'react';
import {
  StyleSheet,
  Text,
  View,
  TextInput,
  TouchableOpacity,
  ImageBackground,
  Alert,
} from 'react-native';
import {
  createStaticNavigation,
  useNavigation,
} from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import axios from 'axios';
import getIpAddress  from "../config/Config";

import AuthContext from "./AuthProvider"
import AsyncStorage from '@react-native-async-storage/async-storage';


const ipAddress = getIpAddress();
console.log(ipAddress)
const LoginScreen = ({navigation}) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  // const navigation = useNavigation();
  const { login } = useContext(AuthContext);


  const handleLogin = async  () => {

    const otp =1000;
    const params = {
      username:email,
      password:password,
      companyId:"C00001",
      branchid:"B00001"    
    }

    try {
      const res = await axios.post(`${ipAddress}auth/login/${otp}`,params ,{
        headers: {
            'React-Page-Name': 'Login',
            'Content-Type': 'application/json'
           
        }
        });

      if(res.status === 200){
        console.log("sucesss");
        const { jwtToken, userId, username, branchId, companyid, role, companyname, branchname, logintype, logintypeid, userType} = res.data;
        login(jwtToken, userId, username, branchId, companyid, role, companyname, branchname, logintype, logintypeid, userType);


        navigation.navigate("homescreen")

      }

      
    }
      catch (error) {
        console.error('Error:', error.message);
        
      }

    // console.log('Email:', email);
    // console.log('Password:', password)
    
  };


  

  return (
    <ImageBackground
      source={{ uri:"https://rapportsoft.co.in/img/products/cfs.jpg"}} 
      style={styles.backgroundImage}
      blurRadius={2}
    >
      
      <View style={styles.container}>
        <Text style={styles.title}>Welcome to RapportSoft </Text>

        <TextInput
          style={styles.input}
          placeholder="User Name"
          placeholderTextColor="#fff"
          keyboardType="email-address"
          value={email}
          onChangeText={(text) => setEmail(text)}
        />

        <TextInput
          style={styles.input}
          placeholder="Password"
          placeholderTextColor="#fff"
          secureTextEntry
          value={password}
          onChangeText={(text) => setPassword(text)}
        />

        <TouchableOpacity style={styles.button} onPress={handleLogin}>
          <Text style={styles.buttonText}>Login</Text>
        </TouchableOpacity>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
    backgroundImage: {
      flex: 1,
      resizeMode: 'cover',
      justifyContent: 'center',
      
    },
    container: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
      paddingHorizontal: 20,
      backgroundColor: 'rgba(0, 0, 0, 0.5)', // Overlay to darken the background
    },
    title: {
      fontSize: 28,
      color: '#fff',
      fontWeight: 'bold',
      marginBottom: 40,
    },
    input: {
      width: '100%',
      height: 50,
      backgroundColor: 'rgba(255, 255, 255, 0.2)',
      borderRadius: 25,
      paddingHorizontal: 20,
      color: '#fff',
      fontSize: 16,
      marginBottom: 20,
    },
    button: {
      width: '100%',
      height: 50,
      backgroundColor: '#4CAF50',
      borderRadius: 25,
      justifyContent: 'center',
      alignItems: 'center',
      marginTop: 20,
    },
    buttonText: {
      color: '#fff',
      fontSize: 18,
      fontWeight: 'bold',
    },
   
  });
  

export default LoginScreen
