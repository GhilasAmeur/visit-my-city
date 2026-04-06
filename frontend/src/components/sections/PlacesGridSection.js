//prettier-ignore
import { ContentContainer, HeroBanner, PlaceCard, SearchInput, SectionTitle } from '../ui'
import { FlatList } from 'react-native'

export default function PlacesGridSection({
   data,
   heroTitle,
   heroImg,
   searchInputPlaceHolder,
   sectionTitle,
   titleContainerStyle,
   onPressItem,
}) {
   return (
      <FlatList
         data={data}
         keyExtractor={(item) => item.id}
         numColumns={2}
         renderItem={({ item }) => (
            <ContentContainer
               style={{
                  width: '50%',
                  paddingBottom: 16,
               }}
            >
               <PlaceCard
                  image={item.image}
                  name={item.name}
                  imgStyle={{ height: 150 }}
                  onPress={() => onPressItem(item)}
               />
            </ContentContainer>
         )}
         ListHeaderComponent={
            <>
               <HeroBanner title={heroTitle} image={heroImg} />
               <ContentContainer>
                  <SearchInput placeholder={searchInputPlaceHolder} />
                  <SectionTitle containerStyle={titleContainerStyle}>
                     {sectionTitle}
                  </SectionTitle>
               </ContentContainer>
            </>
         }
         contentContainerStyle={{ paddingBottom: 20 }}
         showsVerticalScrollIndicator={false}
      />
   )
}
