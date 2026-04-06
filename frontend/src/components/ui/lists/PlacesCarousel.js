import { FlatList, Text } from 'react-native'

export const PlacesCarousel = ({
   data,
   renderItem,
   keyExtractor,
   emptyComponent,
   scrollEnabled,
}) => {
   return (
      <FlatList
         horizontal
         showsHorizontalScrollIndicator={false}
         data={data}
         renderItem={renderItem}
         keyExtractor={keyExtractor}
         ListEmptyComponent={emptyComponent}
         scrollEnabled={scrollEnabled}
      />
   )
}
