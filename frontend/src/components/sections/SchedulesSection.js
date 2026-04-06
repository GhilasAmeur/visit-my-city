import { StyleSheet, Text, View } from 'react-native'
import { Accordion } from '../ui'
import { firstChartToUpperCase } from '../../utils/utils'
import { Linking } from 'react-native'
import { orderDaysProperly } from '../../utils/buildings'

export default function SchedulesSection({ buildingSchedules }) {
   const schedulesType = buildingSchedules.type
   const schedulesNote = buildingSchedules.note
   const schedulesUrl = buildingSchedules.officialHoursUrl
   const schedulesDays = orderDaysProperly(buildingSchedules.days)

   return (
      <View>
         <Accordion title={'Horaires'}>
            {schedulesType != 'Variable' &&
               Object.entries(schedulesDays).map(([day, slots]) => (
                  <View style={styles.schedulesRow} key={day}>
                     <Text style={styles.day}>
                        {firstChartToUpperCase(day)}
                     </Text>
                     <Text>
                        {slots.length > 0
                           ? `${slots[0].start} - ${slots[0].end}`
                           : 'Fermé'}
                     </Text>
                  </View>
               ))}

            <View style={styles.noteContainer}>
               {schedulesType == 'Variable' && (
                  <Text style={styles.variableSchedules}>
                     Horaires Variables
                  </Text>
               )}
               <Text style={styles.note}>{schedulesNote}</Text>
               {schedulesUrl && (
                  <Text
                     style={styles.link}
                     onPress={() => Linking.openURL(schedulesUrl)}
                  >
                     Consultez le site pour vérifier les horaires avant votre
                     visite.
                  </Text>
               )}
            </View>
         </Accordion>
      </View>
   )
}

const styles = StyleSheet.create({
   schedulesRow: {
      flexDirection: 'row',
      justifyContent: 'space-between',
      paddingVertical: 10,
   },
   day: {
      fontWeight: 600,
   },
   noteContainer: {
      padding: 16,
      marginTop: 10,
      backgroundColor: 'rgb(230, 230, 230)',
      borderRadius: 16,
      gap: 10,
   },
   note: {
      color: '#393939',
   },
   link: {
      fontWeight: 500,
      color: '#393939',
      textDecorationLine: 'underline',
   },
   variableSchedules: {
      fontWeight: 600,
      fontSize: 16,
   },
})
