import { StyleSheet, View } from 'react-native'

export const ContentContainer = ({ children, style }) => (
   <View style={[styles.container, style]}>{children}</View>
)

const styles = StyleSheet.create({
   container: {
      paddingHorizontal: 16,
   },
})
