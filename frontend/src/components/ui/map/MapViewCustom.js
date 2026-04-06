import { Linking, StyleSheet, Text, View } from 'react-native'
import MapView, { Marker } from 'react-native-maps'
import { ActionButton } from '../inputs/ActionButton'

export const MapViewCustom = ({ name, address, region }) => {
   return (
      <View style={styles.container}>
         <MapView style={styles.map} region={region}>
            <Marker
               coordinate={{
                  latitude: region.latitude,
                  longitude: region.longitude,
               }}
            />
         </MapView>
         <View style={styles.addressContainer}>
            <Text style={styles.name}>{name}</Text>
            <Text style={styles.address}>{address.join(' • ')}</Text>
            <ActionButton
               title={"Afficher l'itinéraire"}
               onPress={() =>
                  Linking.openURL(
                     `https://www.google.com/maps/dir/?api=1&destination=${region.latitude},${region.longitude}`
                  )
               }
            />
         </View>
      </View>
   )
}

const styles = StyleSheet.create({
   container: {
      borderColor: '#9a9a9a',
      borderWidth: 1,
      marginTop: 4,
   },
   map: {
      height: 200,
   },
   addressContainer: {
      backgroundColor: 'white',
      padding: 10,
      alignItems: 'center',
   },
   name: {
      fontWeight: 600,
      alignSelf: 'flex-start',
   },
   address: {
      color: '#6B7280',
      fontWeight: 500,
      alignSelf: 'flex-start',
   },
})
