import { StyleSheet, Text, TouchableOpacity } from 'react-native'
import { CATEGORY_COLORS } from '../../../constants/categoryColors'

export const CategoryButton = ({ id, name, icon, onPress }) => {
   const backgroundColor = CATEGORY_COLORS[id] ?? '#D0EBFF'

   return (
      <TouchableOpacity
         style={[styles.chip, { backgroundColor }]}
         onPress={onPress}
      >
         <Text style={styles.name}>
            {icon} {name}
         </Text>
      </TouchableOpacity>
   )
}

const styles = StyleSheet.create({
   chip: {
      alignItems: 'center',
      padding: 20,
      margin: 5,
      borderRadius: 30,
   },

   name: {
      fontSize: 16,
      fontWeight: '500',
   },
})
