import { StyleSheet, Text, View } from 'react-native'
import { SectionTitle } from '../ui'

export function HeaderSection({ title, subTitle }) {
   return (
      <View style={styles.header}>
         <SectionTitle style={{ fontSize: 30 }}>{title}</SectionTitle>
         <Text style={styles.headerSubtitle}>{subTitle}</Text>
      </View>
   )
}

const styles = StyleSheet.create({
   header: {
      paddingVertical: 45,
   },
   headerSubtitle: {
      color: '#6B7280',
      fontSize: 16,
   },
})
