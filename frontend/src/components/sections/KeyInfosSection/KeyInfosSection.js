import { SectionTitle } from '../../ui'
import KeyInfosGrid from './KeyInfosGrid'
import { View } from 'react-native'
import { getBuildingKeyInfos } from '../../../utils/buildings'

export default function KeyInfosSection({ building }) {
   const buildingKeyInfos = getBuildingKeyInfos(building)

   return (
      <View>
         <SectionTitle style={{ fontSize: 22 }}>Infos clés</SectionTitle>
         <KeyInfosGrid items={buildingKeyInfos} />
      </View>
   )
}
