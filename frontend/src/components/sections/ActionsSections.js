import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import { ActionButton } from '../ui'

export default function ActionsSections({
   primaryTitle,
   primaryOnPress,
   secondaryTitle,
   secondaryOnPress,
   containerStyle,
}) {
   return (
      <View style={[styles.inputsContainer, containerStyle]}>
         <ActionButton
            title={primaryTitle}
            containerStyle={styles.primaryContainer}
            textStyle={styles.primaryText}
            onPress={primaryOnPress}
         />
         {secondaryTitle && secondaryOnPress && (
            <TouchableOpacity
               style={styles.secondaryBtn}
               onPress={secondaryOnPress}
            >
               <Text style={styles.secondaryText}>{secondaryTitle}</Text>
            </TouchableOpacity>
         )}
      </View>
   )
}

const styles = StyleSheet.create({
   inputsContainer: {
      width: '100%',
      alignItems: 'center',
      gap: 14,
   },
   primaryContainer: {
      backgroundColor: '#3853d9',
      width: '100%',
      padding: 14,
   },
   primaryText: {
      fontSize: 16,
      fontWeight: 500,
   },
   secondaryText: {
      fontSize: 16,
      fontWeight: 500,
      padding: 20,
   },
})
