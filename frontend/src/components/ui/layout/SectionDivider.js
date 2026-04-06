import { StyleSheet, View } from 'react-native'

export const SectionDivider = ({ style }) => {
   return <View style={[styles.container, style]}></View>
}

const styles = StyleSheet.create({
   container: {
      borderWidth: 0.6,
      borderColor: '#dedede',
      flex: 1,
      marginTop: 28,
   },
})
