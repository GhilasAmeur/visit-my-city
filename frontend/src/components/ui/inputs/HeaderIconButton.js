import { Ionicons } from '@expo/vector-icons'
import { Pressable, StyleSheet } from 'react-native'

export const HeaderIconButton = ({
   icon,
   onPress,
   style,
   isBoxShadow = false,
}) => {
   return (
      <Pressable
         style={[
            styles.buttonContainer,
            style,
            isBoxShadow && styles.boxShadow,
         ]}
         onPress={onPress}
      >
         <Ionicons style={{ fontSize: 20 }} name={icon} />
      </Pressable>
   )
}

const styles = StyleSheet.create({
   buttonContainer: {
      backgroundColor: 'white',
      width: 45,
      height: 45,
      alignItems: 'center',
      justifyContent: 'center',
      borderRadius: 30,
   },

   boxShadow: {
      shadowColor: '#000',
      shadowOffset: {
         width: 0,
         height: 1,
      },
      shadowOpacity: 0.22,
      shadowRadius: 2.22,

      elevation: 3,
   },
})
