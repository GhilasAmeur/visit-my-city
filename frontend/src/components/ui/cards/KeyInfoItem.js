import { Ionicons } from '@expo/vector-icons'
import { StyleSheet, Text, View } from 'react-native'

export const KeyInfoItem = ({ icon, label, value }) => {
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
      alignItems: 'center',
      padding: 8,
      gap: 6,
   },
   icon: {
      fontSize: 22,
   },
   textContainer: {
      flex: 1,
   },
   label: {
      color: '#6B7280',
   },
   value: {
      fontWeight: 600,
      lineHeight: 20,
   },
})
