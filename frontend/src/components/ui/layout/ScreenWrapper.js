import { StyleSheet } from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context'

export const ScreenWrapper = ({ children, useEdges = true }) => {
   return (
      <SafeAreaView
         style={styles.container}
         edges={useEdges && ['top', 'bottom', 'left', 'right']}
      >
         {children}
      </SafeAreaView>
   )
}

const styles = StyleSheet.create({
   container: {
      flex: 1,
      backgroundColor: '#F6F6F6',
   },
})
