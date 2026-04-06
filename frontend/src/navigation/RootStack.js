import TabNavigator from './TabNavigator'
import { createStackNavigator } from '@react-navigation/stack'
import { CityDetailScreen } from '../screens/stack/CityDetailScreen'
import { BuildingDetailScreen } from '../screens/stack/BuildingDetailScreen'
import { CategoryDetailScreen } from '../screens/stack/CategoryDetailScreen'
import { HeaderIconButton } from '../components/ui/inputs/HeaderIconButton'
import { FavoriteCityHeaderButton } from '../components/ui/inputs/FavoriteCityHeaderButton'
import { FavoriteBuildingHeaderButton } from '../components/ui/inputs/FavoriteBuildingHeaderButton'
import { LoginScreen } from '../screens/stack/LoginScreen'
import { RegisterScreen } from '../screens/stack/RegisterScreen'

const Stack = createStackNavigator()

export default function RootStack() {
   return (
      <Stack.Navigator>
         <Stack.Screen
            name="Tabs"
            component={TabNavigator}
            options={{ headerShown: false }}
         />
         <Stack.Screen
            name="CityDetail"
            component={CityDetailScreen}
            options={({ navigation, route }) => ({
               headerTransparent: true,
               headerTitle: '',
               headerLeft: () => (
                  <HeaderIconButton
                     icon={'chevron-back-outline'}
                     style={{ marginLeft: 16 }}
                     onPress={() => navigation.goBack()}
                  />
               ),
               headerRight: () => <FavoriteCityHeaderButton route={route} />,
            })}
         />
         <Stack.Screen
            name="BuildingDetail"
            component={BuildingDetailScreen}
            options={({ navigation, route }) => ({
               headerTransparent: true,
               headerTitle: '',
               headerLeft: () => (
                  <HeaderIconButton
                     icon={'chevron-back-outline'}
                     style={{ marginLeft: 16 }}
                     onPress={() => navigation.goBack()}
                  />
               ),
               headerRight: () => (
                  <FavoriteBuildingHeaderButton route={route} />
               ),
            })}
         />
         <Stack.Screen
            name="CategoryDetail"
            component={CategoryDetailScreen}
            options={({ navigation }) => ({
               headerTransparent: true,
               headerTitle: '',
               headerLeft: () => (
                  <HeaderIconButton
                     icon={'chevron-back-outline'}
                     style={{ marginLeft: 16 }}
                     onPress={() => navigation.goBack()}
                  />
               ),
            })}
         />
         <Stack.Screen
            name="LoginScreen"
            component={LoginScreen}
            options={({ navigation }) => ({
               headerTransparent: true,
               headerTitle: '',
               headerLeft: () => (
                  <HeaderIconButton
                     icon={'chevron-back-outline'}
                     style={{ marginLeft: 16 }}
                     onPress={() => navigation.goBack()}
                     isBoxShadow={true}
                  />
               ),
            })}
         />
         <Stack.Screen
            name="RegisterScreen"
            component={RegisterScreen}
            options={({ navigation }) => ({
               headerTransparent: true,
               headerTitle: '',
               headerLeft: () => (
                  <HeaderIconButton
                     icon={'chevron-back-outline'}
                     style={{ marginLeft: 16 }}
                     onPress={() => navigation.goBack()}
                     isBoxShadow={true}
                  />
               ),
            })}
         />
      </Stack.Navigator>
   )
}
