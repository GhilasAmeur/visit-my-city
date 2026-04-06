import { StyleSheet, View } from 'react-native'

export const CarouselItem = ({ children }) => {
   return <View style={styles.item}>{children}</View>
}

const styles = StyleSheet.create({
   item: {
      width: 130,
      marginRight: 16,
   },
})
