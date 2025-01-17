import React, { useEffect, useState, useContext } from "react";
import { StyleSheet, Text, View, FlatList, Image, TouchableOpacity, ImageBackground,Alert  } from 'react-native'
import { DataTable, TextInput, Button, Appbar, IconButton ,Dialog, Portal,PaperProvider,Snackbar } from 'react-native-paper'; // Using React Native Paper's DataTable
import { launchImageLibrary } from 'react-native-image-picker';
import AuthContext from "../screens/AuthProvider"
import * as ImagePicker from 'expo-image-picker';
import getIpAddress  from "../config/Config";
import axios from 'axios'
const ipAddress = getIpAddress();

const Homescreen = () => {
  const {companyId,branchId,jwtToken ,username} = useContext(AuthContext);
  console.log("data from cid ",companyId);
  console.log("data from bid ",username);
  console.log("data from bid ",jwtToken);

  const[resultAsset,setResultAsset] =useState([]);
  const [selectedImages, setSelectedImages] = useState([]);

  const [container, setContainer] = useState(''); // State for input value
  const [data, setData] = useState([]); // State for table data
  const [isInputDisabled, setInputDisabled] = useState(false);
  const [visible, setVisible] =useState(false);
  const [isSearchCompleted, setSearchCompleted] = useState(false); // Track search status
  const [snackbarVisible, setSnackbarVisible] = useState(false); // Snackbar visibility state

  
  const hideDialog = () => setVisible(false);
  const showDialog = () => setVisible(true);
  const _goBack = () => console.log('Went back');

  const _handleMore = () => console.log('Shown more');

  console.log("image uri is "+selectedImages[0]);

  const openImagePicker = async () => {


    try {
      const permissionResult = await ImagePicker.requestMediaLibraryPermissionsAsync();
      if (permissionResult.status !== 'granted') {
        Alert.alert('Permission Denied', 'We need access to your gallery to proceed.');
        return;
      }



      const result = await ImagePicker.launchImageLibraryAsync({
        mediaTypes: ImagePicker.MediaTypeOptions.Images,
        allowsEditing: true,
        allowsMultipleSelection: true,
        quality: 1,
        maxHeight: 200,
        maxWidth: 200
      });

      setSelectedImages([]);

      if (!result.canceled) {

        console.log(result.assets)
        setResultAsset(result.assets)
        const imageUris = result.assets.map(asset => asset.uri); // Extract URIs from selected images
        setSelectedImages(prevImages => [...prevImages, ...imageUris]); // Add selected images to state

      }
    }
    catch (error) {
      console.error('Error opening gallery:', error);
      Alert.alert('Error', 'Something went wrong while opening the gallery.');
    }
  };

  const handleResponse = (response) => {
    console.log("inside handle response ")
    if (response.didCancel) {
      console.log('User cancelled image picker');
    } else if (response.error) {
      console.log('Image picker error: ', response.error);
    } else {
      let imageUri = response.uri || response.assets?.[0]?.uri;
      setSelectedImages(imageUri);
    }
  };

  const removeImage = (uri) => {
    setSelectedImages(prevImages => prevImages.filter(image => image !== uri));
  };

  const handleClear = () => {
    // Clear input and table data
    setContainer("");
    setData([]);
    setSelectedImages([]);
    setInputDisabled(false); // Re-enable the input and search button
    setSearchCompleted(false);


  };

  const handleSearch = async () => {
   
    console.log("data in serach "+companyId)
    const params = {
      
      companyId: companyId,   
      branchId: branchId,
      containerNo: container,
      finYear:2024    
    }

    try {
      const response = await axios.post(`${ipAddress}containerData/getconatinersearchdata`,params,
       {
        headers: {
          "Authorization": `Bearer ${jwtToken}`,
          "Content-Type": "application/json",
        },
      });

      if (response.data && response.data.length > 0) {
        setData(response.data);
        setInputDisabled(true);
        setSearchCompleted(true);
        setSnackbarVisible(true);
        // setTimeout(() => {
        //   setSnackbarVisible(false); // Hide snackbar after 3 seconds
        // }, 3000);
      } else {
        // Alert.alert("No Data Found", "No results match your search.");
        showDialog();
        setData([]); // Clear table data if no results
        setSearchCompleted(false);
      }
    }
    catch (error) {
      console.error("Error fetching data:", error);
      Alert.alert("Error", "Could not fetch data from the server.");
      setSearchCompleted(false);
    }
  }

  const uploadImages = async () => {
    console.log("img pressed")

    const formData = new FormData();

    selectedImages.forEach((image, index) => {
      const image1 = {
        uri: image,
        type: 'image/jpeg',
        filename:resultAsset[0].fileName
      }
      formData.append('files', image1);
    });

    console.log(formData)

    try {
      const response = await axios.post(
        'http://192.168.1.142:9080/containerData/igmUpload',formData,
        {
          headers: {

            "Authorization": `Bearer ${jwtToken}`,
            "Content-Type": "multipart/form-data"
           
          },
        }
      );

      Alert.alert("Success", "Images uploaded successfully!");
      console.log(response.data);
    } catch (error) {
      console.error(error);
      Alert.alert("Error", "Failed to upload images. Please try again.");
    }

  }

  return (

    <PaperProvider>


    <View style={styles.container}>

      <Appbar.Header mode='center-aligned'
        style={{ backgroundColor: "#CCCCCC" }}
      >
        <Appbar.BackAction onPress={_goBack} />
        <Appbar.Content title="Rapportsoft" />
        <Appbar.Action icon="dots-vertical" onPress={_handleMore} />
      </Appbar.Header>
      <Text style={styles.label}>Container</Text>
      <View style={styles.inputContainer}>

        <TextInput
          style={styles.input}
          placeholder="Enter container"
          value={container}
          onChangeText={text => setContainer(text)} // Update container state
          mode='flat'
          activeUnderlineColor="cornflowerblue"
          editable={!isInputDisabled}
        />


        <Button mode="contained-tonal"
          onPress={handleSearch}
          icon="text-search"
          disabled={isInputDisabled || !container.trim()}
        >Search</Button>

          <Button mode="contained-tonal"
          onPress={handleClear}
          icon="text-search"
        >Clear</Button>
      </View>


      <DataTable style={styles.table}>
        <DataTable.Header>
          <DataTable.Title style={styles.sizeTypeHeader} >Size/Type</DataTable.Title>
          <DataTable.Title style={styles.sizeTypeHeader}>IGM</DataTable.Title>
        </DataTable.Header>


        <FlatList
          data={data}
          keyExtractor={(_, index) => index.toString()}
          renderItem={({ item }) => (
            <DataTable.Row>
              <DataTable.Cell>{item.containerSize} / {item.containerType}</DataTable.Cell>
              <DataTable.Cell>{item.igmNo}</DataTable.Cell>
            </DataTable.Row>
          )}
        />
      </DataTable>

      

      <View style={{ marginTop: 14 }}>
        {selectedImages.length > 0 && (
          <FlatList
            data={selectedImages}
            keyExtractor={(uri, index) => index.toString()}
            horizontal
            renderItem={({ item }) => (
              <View style={styles.imageContainer}>
                <Image
                  source={{ uri: item }}
                  style={{
                    height: 200,
                    width: 200,
                    marginRight: 10,
                    borderWidth: 2,
                    borderColor: 'gray',
                    borderRadius: 10,
                  }}
                />

                <IconButton

                  contentStyle={{ flexDirection: 'row-reverse' }}

                  onPress={() => removeImage(item)}
                  icon="close-circle"
                  style={styles.removeButton}
                />
              </View>
            )}
          />

        )}

      </View>
      <View style={styles.buttonRow}>
        <Button
          style={styles.button}
          mode="contained-tonal"
          icon="image-plus"
          disabled={!isSearchCompleted}
          onPress={openImagePicker}>Upload Images</Button>

        <Button mode="contained-tonal"
          icon="upload"
          style={styles.button}
          onPress={uploadImages}
          disabled={!isSearchCompleted} 
        >Upload</Button>
      </View>

      <View>
        {/* <Button onPress={showDialog}>Show Dialog</Button> */}
        <Portal>
          <Dialog visible={visible} onDismiss={hideDialog}>
            <Dialog.Title>No Data Found</Dialog.Title>
            <Dialog.Content>
              <Text >No results match your search.</Text>
            </Dialog.Content>
            <Dialog.Actions>
              <Button onPress={hideDialog}>Done</Button>
            </Dialog.Actions>
          </Dialog>
        </Portal>
      </View>


      <Snackbar
        visible={snackbarVisible}
        onDismiss={() => setSnackbarVisible(false)}
        duration={2000} // Snackbar duration
      >Data fetched successfully!</Snackbar>

      

      
    </View>

    </PaperProvider>





  );





}

export default Homescreen

const styles = StyleSheet.create({
  container: {
    flex: 1,

    backgroundColor: 'white',
    padding: 5,
  },
  backgroundImage: {
    flex: 1,  // Ensures the background image covers the entire screen
    justifyContent: 'center', // Center content vertically
    alignItems: 'center',
    // Center content horizontally
  },
  inputContainer: {
    flexDirection: 'row',
    marginBottom: 20,
    alignItems: 'center',
  },
  input: {
    flex: 1,
    height: 40,


    marginRight: 10,
    paddingLeft: 8,
  },
  sizeTypeHeader: {
    fontSize: 30, // Adjust the font size
    fontWeight: 'bold',

  },
  table: {
    borderWidth: 1, // Border for entire table
    borderColor: 'grey', // Border color for table
  },

  boldText: {
    fontSize: 24, // Font size for the text
    fontWeight: 'bold', // Make the text bold
    color: 'white', // Text color white for contrast on sky blue background
    textAlign: 'center'
  },
  label: {
    fontSize: 14,
    fontWeight: 'bold',
    marginBottom: 5, // Space between label and input
    marginTop: 15
  },


  buttonText: {
    color: '#fff',             // Text color
    fontSize: 16,              // Text size
    fontWeight: 'bold',
    padding: 8       // Optional: Bold text
  },

  buttonRow: {
    flexDirection: 'row',       // Align buttons in a row
    justifyContent: 'center', // Space out the buttons
    marginTop: 10,              // Space from the top (adjust as needed)
    width: '100%',               // Optional: Width of the row
  },

  button: {
    marginLeft: 25
  },

  removeButton: {
    position: 'absolute',
    top: -14,
    right: -4,
    zIndex: 1,
  },

  imageContainer: {
    position: 'relative',
    marginRight: 10,
  },

});

