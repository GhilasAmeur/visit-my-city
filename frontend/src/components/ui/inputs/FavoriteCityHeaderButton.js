import { useFavorite } from '../../../store/favoritesStore'
import { HeaderIconButton } from './HeaderIconButton'

export const FavoriteCityHeaderButton = ({ route }) => {
   const { cityId } = route.params
   const toggleFavCity = useFavorite((state) => state.toggleFavoriteCity)
   const favoritesCities = useFavorite((state) => state.favoriteCities)
   const isFav = favoritesCities.includes(cityId)

   return (
      <HeaderIconButton
         icon={isFav ? 'heart' : 'heart-outline'}
         style={{ marginRight: 16 }}
         onPress={() => toggleFavCity(cityId)}
      />
   )
}
