import { View, TextInput, StyleSheet } from 'react-native'
import Ionicons from 'react-native-vector-icons/Ionicons'

export const SearchInput = ({ placeholder }) => {
   return (
      <View style={styles.inputContainer}>
         <Ionicons name="search-outline" style={styles.icon} />
         <TextInput
            placeholder={placeholder}
            placeholderTextColor="#666"
            style={styles.input}
         />
      </View>
   )
}

const styles = StyleSheet.create({
   inputContainer: {
      flexDirection: 'row',
      alignItems: 'center',
      borderRadius: 8,
      height: 45,
      marginTop: 10,
      paddingHorizontal: 12,
      backgroundColor: '#fff',
      shadowColor: '#000',
      shadowOffset: {
         width: 0,
         height: 2,
      },
      shadowOpacity: 0.25,
      shadowRadius: 3.84,
      elevation: 5,
   },

   icon: {
      marginRight: 8,
      fontSize: 26,
      color: '#666',
   },

   input: {
      fontSize: 16,
      flex: 1,
      height: '100%',
   },
})
