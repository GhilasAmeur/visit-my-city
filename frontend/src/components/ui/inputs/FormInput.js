import { Ionicons } from '@expo/vector-icons'
import { useEffect, useState } from 'react'
import {
   StyleSheet,
   Text,
   TextInput,
   TouchableOpacity,
   View,
} from 'react-native'

export const FormInput = ({
   label,
   placeholder,
   keyboardType = 'default',
   secureTextEntry = false,
   returnKeyType,
   value,
   onChangeText,
   onPress,
   numberOfLines,
   multiline,
   style,
   containerStyle,
   errorMessage,
   isRequired,
}) => {
   const [isIcon, setIsIcon] = useState(true)

   const handleIconPress = () => {
      setIsIcon(false)
      onChangeText('')
   }

   useEffect(() => {
      if (value == '' && !isIcon) {
         setIsIcon(true)
      }
   }, [isIcon])

   return (
      <View style={[styles.container, containerStyle]}>
         <Text style={styles.label}>
            {label} {isRequired && <Text style={styles.required}>*</Text>}
         </Text>
         <View style={[styles.inputContainer, style]}>
            <TextInput
               style={styles.input}
               placeholder={placeholder}
               keyboardType={keyboardType}
               secureTextEntry={secureTextEntry}
               onChangeText={onChangeText}
               value={value}
               returnKeyType={returnKeyType}
               placeholderTextColor={'#a4a8b1c2'}
               multiline={multiline}
               numberOfLines={numberOfLines}
            />
            {value != '' && isIcon && (
               <TouchableOpacity
                  style={styles.iconContainer}
                  onPress={onPress ? onPress : handleIconPress}
               >
                  <Ionicons style={styles.icon} name={'close-outline'} />
               </TouchableOpacity>
            )}
         </View>
         {errorMessage}
      </View>
   )
}

const styles = StyleSheet.create({
   container: {
      gap: 8,
   },
   label: {
      fontWeight: 600,
      fontSize: 16,
   },
   inputContainer: {
      flexDirection: 'row',
      height: 50,
      paddingLeft: 10,
      borderRadius: 8,
      backgroundColor: 'white',
      borderWidth: 0.3,
      borderColor: '#6B7280',
   },
   input: {
      flex: 1,
   },
   iconContainer: {
      alignItems: 'center',
      justifyContent: 'center',
      width: 44,
   },
   icon: {
      fontSize: 24,
   },
   required: {
      color: 'red',
   },
})
