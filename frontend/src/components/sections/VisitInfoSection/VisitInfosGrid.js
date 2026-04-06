import { StyleSheet, View } from 'react-native'
import { VisitInfoCard } from '../../ui/cards/VisitInfoCard'
import { divideOddsAndEvens } from '../../../utils/utils'

export default function VisitInfosGrid({ items }) {
   const { leftColumn, rightColumn } = divideOddsAndEvens(items)

   return (
      <View style={styles.container}>
         <View style={styles.cardContainer}>
            {leftColumn.map((el, index) => (
               <VisitInfoCard
                  key={index}
                  icon={el.icon}
                  label={el.label}
                  value={el.value}
               />
            ))}
         </View>
         <View style={styles.cardContainer}>
            {rightColumn.map((el, index) => (
               <VisitInfoCard
                  key={index}
                  icon={el.icon}
                  label={el.label}
                  value={el.value}
               />
            ))}
         </View>
      </View>
   )
}

const styles = StyleSheet.create({
   container: {
      gap: 12,
      flexDirection: 'row',
      flexWrap: 'wrap',
   },
   cardContainer: {
      flex: 1,
      gap: 12,
   },
})
