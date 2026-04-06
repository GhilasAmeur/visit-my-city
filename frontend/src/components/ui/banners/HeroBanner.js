import { StyleSheet, ImageBackground, Text } from 'react-native'

export const HeroBanner = ({ title, image, style }) => {
   return (
      <ImageBackground source={image} style={styles.banner}>
         <Text style={[styles.bannerText, style]}>{title}</Text>
      </ImageBackground>
   )
}

const styles = StyleSheet.create({
   banner: {
      height: 280,
      justifyContent: 'flex-end',
      paddingHorizontal: 16,
   },

   bannerContainer: {
      gap: 12,
      width: '100%',
   },

   bannerText: {
      color: '#fff',
      fontSize: 30,
      fontWeight: 'bold',
      marginBottom: 10,
      textShadowColor: 'rgba(0,0,0,0.9)',
      textShadowOffset: { width: 0, height: 1 },
      textShadowRadius: 6,
   },
})
