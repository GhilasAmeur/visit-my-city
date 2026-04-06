import { StyleSheet, Text, View } from 'react-native'

export const EmptyState = ({ headerTitle, text }) => {
   return (
      <View style={styles.container}>
         <Text style={styles.headerTitle}>{headerTitle}</Text>
         <Text style={styles.text}>{text}</Text>
      </View>
   )
}

const styles = StyleSheet.create({
   container: {
      maxWidth: 345,
      paddingVertical: 14,
   },
   headerTitle: {
      fontWeight: 600,
      textAlign: 'center',
      marginBottom: 6,
      fontSize: 16,
   },
   text: {
      textAlign: 'center',
      fontSize: 16,
      color: '#6B7280',
   },
})
