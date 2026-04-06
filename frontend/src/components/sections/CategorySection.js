import { View } from 'react-native'
import { SectionTitle, CategoryButton, PlacesCarousel } from '../ui'
import { groupCategories } from '../../utils/utils'

export default function CategorySection({ navigation, title, data }) {
   const groupedCategories = groupCategories(data ?? [])

   return (
      <View>
         <SectionTitle>{title}</SectionTitle>

         <PlacesCarousel
            data={groupedCategories}
            keyExtractor={(_, index) => index}
            renderItem={({ item }) => (
               <View>
                  {item.map((cat) => (
                     <CategoryButton
                        key={cat.id}
                        id={cat.id}
                        name={cat.name}
                        icon={cat.icon}
                        onPress={() =>
                           navigation.navigate('CategoryDetail', {
                              categoryId: cat.id,
                           })
                        }
                     />
                  ))}
               </View>
            )}
         />
      </View>
   )
}
