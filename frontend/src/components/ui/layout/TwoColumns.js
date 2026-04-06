import { View } from 'react-native'
import { StyleSheet } from 'react-native'

export const TwoColumns = ({ children }) => {
   return (
      <View>
         <View style={styles.container}>{children}</View>
      </View>
   )
}

const styles = StyleSheet.create({
   container: {
      flexDirection: 'row',
      justifyContent: 'space-between',
   },
})
