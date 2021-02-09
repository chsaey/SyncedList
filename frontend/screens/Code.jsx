import React, {useState} from 'react';
import { StyleSheet, Text, View, TextInput, Button } from 'react-native';

function Code({ navigation, route }) {
    const [code, setCode] = React.useState('');
    return (
      <View style={styles.container}>
        <Text style={styles.logo}>Enter Code</Text>
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="Code" 
            placeholderTextColor="#003f5c"
            onChangeText={text => setCode(text)}/>
        </View>
        <Button
        title="Enter"
        onPress={() => {
          // submit
          navigation.navigate('Home', { codeText: code });
        }}
      />      
      </View>
    );
  }

  export default Code;

  const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: 'black',
      alignItems: 'center',
      justifyContent: 'center',
    },
    logo:{
      fontWeight:"bold",
      fontSize:50,
      color:"white",
      marginBottom:40
    },
    inputView:{
      width:"80%",
      backgroundColor:"white",
      borderRadius:25,
      height:50,
      marginBottom:20,
      justifyContent:"center",
      padding:20
    },
    inputText:{
      height:50,
      color:"black"
    },
    forgot:{
      color:"white",
      fontSize:11
    },
    loginBtn:{
      width:"80%",
      backgroundColor:"green",
      borderRadius:25,
      height:50,
      alignItems:"center",
      justifyContent:"center",
      marginTop:40,
      marginBottom:10
    },
    loginText:{
      color:"white"
    }
  });