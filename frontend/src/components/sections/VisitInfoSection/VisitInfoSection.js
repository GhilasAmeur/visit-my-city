import { getBuildingVisitInfos } from '../../../utils/buildings'
import { SectionTitle } from '../../ui'
import VisitInfosGrid from './VisitInfosGrid'
import { View } from 'react-native'

export default function VisitInfoSection({ building }) {
   const buildingVisitInfos = getBuildingVisitInfos(building)

   return (
      <View>
         <SectionTitle style={{ fontSize: 22 }}>
            Informations de Visite
         </SectionTitle>
         <VisitInfosGrid items={buildingVisitInfos} />
      </View>
   )
}
