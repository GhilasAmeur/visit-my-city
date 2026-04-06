import { View } from 'react-native'
import { MapViewCustom, SectionTitle } from '../ui'

export default function MapSection({ name, address, region }) {
   return (
      <View>
         <SectionTitle style={{ fontSize: 22 }}>S'y rendre</SectionTitle>
         <MapViewCustom name={name} address={address} region={region} />
      </View>
   )
}
