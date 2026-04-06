// prettier-ignore
import { ContentContainer, HeroBanner, ScreenWrapper, SectionDivider } from '../../components/ui'
import { ScrollView } from 'react-native'
import KeyInfosSection from '../../components/sections/KeyInfosSection/KeyInfosSection'
import DescriptionSection from '../../components/sections/DescriptionSection'
import VisitInfoSection from '../../components/sections/VisitInfoSection/VisitInfoSection'
import SchedulesSection from '../../components/sections/SchedulesSection'
import MapSection from '../../components/sections/MapSection'
import useBuilding from '../../services/hooks/useBuilding'
import useDelayLoader from '../../services/hooks/useDelayedLoader'
import { Loader } from '../../components/ui/Loader'

export const BuildingDetailScreen = ({ route }) => {
   const { buildingId } = route.params
   const { building, isLoading } = useBuilding(buildingId)
   const showLoader = useDelayLoader(isLoading)

   if (showLoader) {
      return <Loader />
   }

   return (
      <ScreenWrapper useEdges={false}>
         <ScrollView>
            {!showLoader && building && (
               <>
                  <HeroBanner
                     title={building.name}
                     image={{ uri: building.image }}
                  />

                  <ContentContainer style={{ paddingBottom: 20 }}>
                     <KeyInfosSection building={building} />
                     <SectionDivider />

                     <DescriptionSection
                        text={building.description}
                        linesNumber={4}
                     />
                     <SectionDivider />

                     <VisitInfoSection building={building} />
                     <SectionDivider />

                     <SchedulesSection buildingSchedules={building.schedules} />
                     <SectionDivider />

                     <MapSection
                        name={building.name}
                        address={[building.address, building.postalCode]}
                        region={building.coords}
                     />
                     <SectionDivider />
                  </ContentContainer>
               </>
            )}
         </ScrollView>
      </ScreenWrapper>
   )
}
