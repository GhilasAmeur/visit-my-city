import { Ionicons } from '@expo/vector-icons'
import { StyleSheet, Text, View } from 'react-native'

export const ErrorMessage = ({ message }) => {
   return (
      <View style={styles.errorContainer}>
         <Ionicons name="alert-circle-outline" style={styles.errorIcon} />
         <Text style={styles.error}>{message}</Text>
      </View>
   )
}

const styles = StyleSheet.create({
   errorContainer: {
      flexDirection: 'row',
      gap: 4,
   },
   error: {
      color: 'red',
   },
   errorIcon: {
      fontSize: 16,
      color: 'red',
   },
})
