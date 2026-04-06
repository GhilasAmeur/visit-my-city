import { StyleSheet, TouchableOpacity, Text, Image } from 'react-native'

export const PlaceCard = ({ image, name, style, imgStyle, onPress }) => {
   return (
      <TouchableOpacity style={[styles.card, style]} onPress={onPress}>
         <Image
            source={{ uri: image }}
            style={[styles.image, imgStyle]}
            resizeMode="cover"
         />
         <Text style={styles.name}>{name}</Text>
      </TouchableOpacity>
   )
}

const styles = StyleSheet.create({
   card: {
      width: '100%',
      borderRadius: 8,
   },

   image: {
      width: '100%',
      height: 120,
      borderRadius: 8,
   },

   name: {
      paddingTop: 4,
      textAlign: 'center',
      fontSize: 16,
   },
})
