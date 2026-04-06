import { StyleSheet, Text, View } from 'react-native'

export const HeaderTitle = ({ children }) => {
   return (
      <View style={styles.container}>
         <Text style={styles.headerTitle}>{children}</Text>
      </View>
   )
}

const styles = StyleSheet.create({
   container: {
      alignItems: 'center',
      padding: 10,
   },
   headerTitle: {
      color: '#222222',
      fontSize: 26,
      fontWeight: 'bold',
   },
})
