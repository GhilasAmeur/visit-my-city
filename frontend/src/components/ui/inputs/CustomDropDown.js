import { StyleSheet, Text, View } from 'react-native'
import { Dropdown } from 'react-native-element-dropdown'

export const CustomDropDown = ({
   data,
   value,
   onChange,
   label,
   placeholder,
   containerStyle,
   isLabel = true,
   search = true,
   errorMessage,
   isRequired,
}) => {
   return (
      <View style={[styles.container, containerStyle]}>
         {isLabel && (
            <Text style={styles.label}>
               {label} {isRequired && <Text style={styles.required}>*</Text>}
            </Text>
         )}
         <Dropdown
            style={styles.dropDown}
            search={search}
            searchPlaceholder="Rechercher"
            placeholderStyle={styles.placeholderStyle}
            labelField="label"
            valueField="value"
            data={data}
            value={value}
            onChange={(item) => onChange(item.value)}
            placeholder={placeholder}
         />
         {errorMessage}
      </View>
   )
}

const styles = StyleSheet.create({
   container: {
      gap: 9,
   },
   label: {
      fontWeight: 600,
      fontSize: 16,
   },
   dropDown: {
      height: 50,
      paddingHorizontal: 10,
      borderRadius: 8,
      backgroundColor: 'white',
      borderWidth: 0.3,
      borderColor: '#6B7280',
   },

   placeholderStyle: {
      color: '#888',
   },
   required: {
      color: 'red',
   },
})
