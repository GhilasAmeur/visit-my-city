import { StyleSheet, Text, View } from 'react-native'

export const SectionTitle = ({ children, containerStyle, style, right }) => {
   return (
      <View style={[styles.container, containerStyle]}>
         <Text style={[styles.sectionTitle, style]}>{children}</Text>
         {right}
      </View>
   )
}

const styles = StyleSheet.create({
   container: {
      flex: 1,
      flexDirection: 'row',
      alignItems: 'center',
      justifyContent: 'space-between',
      paddingTop: 20,
      paddingBottom: 12,
   },
   sectionTitle: {
      fontSize: 24,
      fontWeight: '600',
      color: '#222222',
   },
})
