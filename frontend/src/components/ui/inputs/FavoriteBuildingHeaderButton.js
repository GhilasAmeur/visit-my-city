import { useFavorite } from '../../../store/favoritesStore'
import { HeaderIconButton } from './HeaderIconButton'

export const FavoriteBuildingHeaderButton = ({ route }) => {
   const { buildingId } = route.params
   // prettier-ignore
   const toggleFavBuilding = useFavorite((state) => state.toggleFavoriteBuilding)
   const favoritesBuildings = useFavorite((state) => state.favoriteBuildings)
   const isFav = favoritesBuildings.includes(buildingId)

   return (
      <HeaderIconButton
         icon={isFav ? 'heart' : 'heart-outline'}
         style={{ marginRight: 16 }}
         onPress={() => toggleFavBuilding(buildingId)}
      />
   )
}
