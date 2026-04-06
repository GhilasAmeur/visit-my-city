import { Ionicons } from '@expo/vector-icons'
import { StyleSheet, Text, View } from 'react-native'

export const VisitInfoCard = ({ icon, label, value }) => {
   return (
      <View style={styles.container}>
         <Ionicons style={styles.icon} name={icon} />
         <View style={styles.textContainer}>
            <Text style={styles.label}>{label}</Text>
            <Text style={styles.value} numberOfLines={2} ellipsizeMode="tail">
               {value}
            </Text>
         </View>
      </View>
   )
}

const styles = StyleSheet.create({
   container: {
      flexDirection: 'row',
      gap: 6,
      backgroundColor: 'white',
      alignItems: 'center',
      borderRadius: 16,
      padding: 8,
      shadowColor: '#000',
      shadowOffset: {
         width: 0,
         height: 1,
      },
      shadowOpacity: 0.2,
      shadowRadius: 1.41,
      elevation: 2,
   },
   icon: {
      fontSize: 22,
   },
   label: {
      fontWeight: 600,
   },
   textContainer: {
      flex: 1,
      gap: 4,
   },
})
