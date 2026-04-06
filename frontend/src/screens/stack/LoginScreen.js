import { Alert, ScrollView, StyleSheet } from 'react-native'
import { ContentContainer, FormInput, ScreenWrapper } from '../../components/ui'
import { View } from 'react-native'
import ActionsSections from '../../components/sections/ActionsSections'
import { HeaderSection } from '../../components/sections/HeaderSection'
import { StackActions } from '@react-navigation/native'
import { useUserStore } from '../../store/useUserStore'
import { useState } from 'react'
import { login } from '../../services/auth/auth.service'
import { validators } from '../../utils/validation'

export const LoginScreen = ({ navigation }) => {
   const [email, setEmail] = useState('')
   const [password, setPassword] = useState('')

   const handleLogin = async () => {
      if (!validators.required(email) || !validators.required(password)) {
         Alert.alert("L'adresse email et le mot de passe sont requis.")
         return
      }

      if (!validators.email(email)) {
         Alert.alert("L'adresse email est invalide.")
         return
      }

      if (!validators.minLength(password, 8)) {
         Alert.alert('Le mot de passe doit contenir au minimum 8 caractères.')
         return
      }

      try {
         const { user, access_token: token } = await login(email, password)
         useUserStore.getState().setUser({ user, token })

         navigation.navigate('Tabs', { screen: 'Profil' })
      } catch (error) {
         Alert.alert('Une erreur est survenue')
         console.log(error)
      }
   }

   return (
      <ScreenWrapper>
         <ScrollView
            showsVerticalScrollIndicator={false}
            keyboardShouldPersistTaps="handled"
         >
            <ContentContainer>
               <HeaderSection
                  title={'Connexion'}
                  subTitle={'Connectez-vous à Visit My Cities'}
               />

               <View style={styles.inputsContainer}>
                  <FormInput
                     value={email}
                     onChangeText={setEmail}
                     label={'Adresse email'}
                     keyboardType={'email-address'}
                     returnKeyType={'next'}
                  />
                  <FormInput
                     value={password}
                     onChangeText={setPassword}
                     label={'Mot de passe'}
                     keyboardType={'default'}
                     secureTextEntry={true}
                     returnKeyType={'send'}
                  />
               </View>

               <ActionsSections
                  primaryTitle={'Se Connecter'}
                  primaryOnPress={handleLogin}
                  secondaryTitle={'Créer un compte'}
                  secondaryOnPress={() =>
                     navigation.dispatch(StackActions.replace('RegisterScreen'))
                  }
               />
            </ContentContainer>
         </ScrollView>
      </ScreenWrapper>
   )
}

const styles = StyleSheet.create({
   inputsContainer: {
      gap: 24,
      paddingBottom: 20,
   },
})
