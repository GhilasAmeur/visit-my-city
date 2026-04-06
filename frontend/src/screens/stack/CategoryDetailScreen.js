import PlacesGridSection from '../../components/sections/PlacesGridSection'
import { ScreenWrapper } from '../../components/ui'
import useBuildingsByCategory from '../../services/hooks/useBuildingsByCategory'
import useCategory from '../../services/hooks/useCategory'
import useDelayLoader from '../../services/hooks/useDelayedLoader'
import { Loader } from '../../components/ui/Loader'

export const CategoryDetailScreen = ({ navigation, route }) => {
   const { categoryId } = route.params
   const { category } = useCategory(categoryId)
   const { buildingsByCategory, isLoading } = useBuildingsByCategory(categoryId)
   const showLoader = useDelayLoader(isLoading)

   if (showLoader) {
      return <Loader />
   }

   return (
      <ScreenWrapper useEdges={false}>
         <PlacesGridSection
            data={buildingsByCategory}
            heroTitle={`Les ${category?.name}`}
            heroImg={{ uri: category?.image }}
            searchInputPlaceHolder={'Rechercher un bâtiment'}
            sectionTitle={''}
            titleContainerStyle={{ paddingBottom: 0, paddingTop: 12 }}
            onPressItem={(item) => {
               navigation.navigate('BuildingDetail', {
                  buildingId: item.id,
               })
            }}
         />
      </ScreenWrapper>
   )
}
