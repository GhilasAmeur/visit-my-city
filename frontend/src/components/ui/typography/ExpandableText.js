import { useState } from 'react'
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'

export const ExpandableText = ({ linesNumber, text }) => {
   const [isExpanded, setIsExpanded] = useState(false)

   return (
      <View>
         <Text
            numberOfLines={!isExpanded ? linesNumber : 0}
            ellipsizeMode="tail"
         >
            {text}
         </Text>
         <TouchableOpacity
            style={styles.button}
            onPress={() =>
               !isExpanded ? setIsExpanded(true) : setIsExpanded(false)
            }
         >
            <Text style={styles.textButton}>
               {!isExpanded ? 'Voir plus' : 'Voir moins'}
            </Text>
         </TouchableOpacity>
      </View>
   )
}

const styles = StyleSheet.create({
   button: {
      marginTop: 10,
   },
   textButton: {
      fontSize: 16,
      fontWeight: 600,
   },
})
