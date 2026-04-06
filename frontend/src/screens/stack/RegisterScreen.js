import { Alert, ScrollView, StyleSheet, View } from 'react-native'
import { ContentContainer, FormInput, ScreenWrapper } from '../../components/ui'
import { HeaderSection } from '../../components/sections/HeaderSection'
import ActionsSections from '../../components/sections/ActionsSections'
import { StackActions } from '@react-navigation/native'
import { useState } from 'react'
import { validators } from '../../utils/validation'
import { register } from '../../services/auth/auth.service'

export const RegisterScreen = ({ navigation }) => {
   const [email, setEmail] = useState('')
   const [name, setName] = useState('')
   const [password, setPassword] = useState('')

   const handleRegister = async () => {
      if (
         !validators.required(email) ||
         !validators.required(name) ||
         !validators.required(password)
      ) {
         Alert.alert("Le nom, l'adresse email et le mot de passe sont requis.")
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
         await register(name, email, password)
         navigation.navigate('LoginScreen')
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
            automaticallyAdjustKeyboardInsets={true}
         >
            <ContentContainer>
               <HeaderSection
                  title={'Inscription'}
                  subTitle={
                     'Incrivez-vous à Visit My Cities pour avoir accès à davantage de fonctionnalités.'
                  }
               />

               <View style={styles.inputsContainer}>
                  <FormInput
                     value={name}
                     onChangeText={setName}
                     label={'Nom et Prénom'}
                     returnKeyType={'next'}
                  />
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
                     secureTextEntry={true}
                     returnKeyType={'send'}
                  />
               </View>

               <ActionsSections
                  primaryTitle={'Valider'}
                  primaryOnPress={handleRegister}
                  secondaryTitle={'Se connecter'}
                  secondaryOnPress={() =>
                     navigation.dispatch(StackActions.replace('LoginScreen'))
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
