import { Text, View } from 'react-native'
import { SectionTitle } from '../typography/SectionTitle'
import { Controller } from 'react-hook-form'
import { FormInput } from '../inputs/FormInput'
import { CustomDropDown } from '../inputs/CustomDropDown'
import { SectionDivider } from '../layout/SectionDivider'
import { StyleSheet } from 'react-native'
import { ACCESS_STATUS_DATA } from '../../../constants/accessStatusData'
import { BOOKING_DATA } from '../../../constants/bookingData'
import { ACCESSIBILITY_DATA } from '../../../constants/accessibilityData'
import { SCHEDULES_TYPE_DATA } from '../../../constants/schedulesTypeData'
import { STYLE_ARCHITECTURE_DATA } from '../../../constants/styleArchitectureData'
import { VISIT_TIMES_DATA } from '../../../constants/visitTimesData'
import { generateTimeSlots } from '../../../utils/utils'
import { TwoColumns } from '../layout/TwoColumns'
import { Checkbox } from 'expo-checkbox'
import { ErrorMessage } from './ErrorMessage'

export const AddBuildingForm = ({
   control,
   errors,
   watch,
   cityDropDown,
   categoryDropDown,
}) => {
   const sameForAllDays = watch('schedules.sameForAllDays')

   return (
      <View style={styles.inputsContainer}>
         <SectionTitle
            containerStyle={{ paddingBottom: 0, paddingTop: 0 }}
            style={{ fontSize: 22 }}
         >
            Infos principales
         </SectionTitle>

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="name"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={'Nom'}
                  isRequired={true}
                  errorMessage={
                     errors.name && (
                        <ErrorMessage message={'Le nom est requis.'} />
                     )
                  }
                  returnKeyType={'next'}
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="address"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={'Adresse'}
                  isRequired={true}
                  errorMessage={
                     errors.address && (
                        <ErrorMessage message={"L'adresse est requise."} />
                     )
                  }
                  returnKeyType={'next'}
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="postalCode"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={'Code postal'}
                  isRequired={true}
                  errorMessage={
                     errors.postalCode && (
                        <ErrorMessage message={'Le code postal est requis.'} />
                     )
                  }
                  returnKeyType={'next'}
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="cityId"
            render={({ field: { value, onChange } }) => (
               <CustomDropDown
                  data={cityDropDown}
                  value={value}
                  onChange={onChange}
                  label={'Ville'}
                  isRequired={true}
                  errorMessage={
                     errors.cityId && (
                        <ErrorMessage message={'La ville est requise.'} />
                     )
                  }
                  placeholder={'Sélectionner une ville'}
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="categoriesId"
            render={({ field: { value, onChange } }) => (
               <CustomDropDown
                  data={categoryDropDown}
                  value={value}
                  onChange={onChange}
                  label={'Catégorie'}
                  isRequired={true}
                  errorMessage={
                     errors.categoriesId && (
                        <ErrorMessage message={'La catégorie est requise'} />
                     )
                  }
                  placeholder={'Sélectionner une catégorie'}
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="image"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={"URL de l'image"}
                  isRequired={true}
                  errorMessage={
                     errors.image && (
                        <ErrorMessage
                           message={"L'URL de l'image est requise."}
                        />
                     )
                  }
                  returnKeyType={'next'}
               />
            )}
         />

         <SectionDivider style={{ marginTop: 10 }} />
         <SectionTitle
            containerStyle={{ paddingBottom: 0, paddingTop: 0 }}
            style={{ fontSize: 22 }}
         >
            Infos clés
         </SectionTitle>

         <Controller
            control={control}
            name="constructionYear"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={'Année de construction'}
                  keyboardType="numeric"
                  returnKeyType={'next'}
               />
            )}
         />

         <Controller
            control={control}
            name="architect"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={'Architecte'}
                  returnKeyType={'next'}
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="style"
            render={({ field: { value, onChange } }) => (
               <CustomDropDown
                  data={STYLE_ARCHITECTURE_DATA}
                  value={value}
                  onChange={onChange}
                  label={"Style d'architecture"}
                  isRequired={true}
                  placeholder={'Sélectionner un style'}
                  errorMessage={
                     errors.style && (
                        <ErrorMessage
                           message={'Le style architectural est requis.'}
                        />
                     )
                  }
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="description"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={'Description'}
                  isRequired={true}
                  style={{ height: 120 }}
                  multiline={true}
                  numberOfLines={3}
                  errorMessage={
                     errors.description && (
                        <ErrorMessage message={'La description est requise.'} />
                     )
                  }
                  returnKeyType={'next'}
               />
            )}
         />

         <SectionDivider style={{ marginTop: 10 }} />
         <SectionTitle
            containerStyle={{ paddingBottom: 0, paddingTop: 0 }}
            style={{ fontSize: 22 }}
         >
            Infos de visite
         </SectionTitle>

         <TwoColumns>
            <Controller
               control={control}
               rules={{
                  required: true,
               }}
               name="ticketPrice"
               render={({ field: { value, onChange } }) => (
                  <FormInput
                     value={value}
                     onChangeText={onChange}
                     onPress={() => onChange('')}
                     label={'Tarif'}
                     isRequired={true}
                     keyboardType="numeric"
                     containerStyle={{ width: '48%' }}
                     errorMessage={
                        errors.ticketPrice && (
                           <ErrorMessage message={'Le tarif est requis.'} />
                        )
                     }
                     returnKeyType={'next'}
                  />
               )}
            />

            <Controller
               control={control}
               name="visitDuration"
               render={({ field: { value, onChange } }) => (
                  <CustomDropDown
                     data={VISIT_TIMES_DATA}
                     onChange={onChange}
                     value={value}
                     label={'Temps de visite'}
                     containerStyle={{ width: '48%' }}
                     search={false}
                     placeholder={'Sélectionner un temps de visite'}
                     errorMessage={
                        errors.visitDuration && (
                           <ErrorMessage
                              message={'Le temps de visite est requis.'}
                           />
                        )
                     }
                  />
               )}
            />
         </TwoColumns>

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="booking"
            render={({ field: { value, onChange } }) => (
               <CustomDropDown
                  data={BOOKING_DATA}
                  value={value}
                  onChange={onChange}
                  label={'Réservation'}
                  isRequired={true}
                  placeholder={'Sélectionner un type de réservation'}
                  errorMessage={
                     errors.booking && (
                        <ErrorMessage
                           message={'Le type de réservation est requis.'}
                        />
                     )
                  }
                  search={false}
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="accessStatus"
            render={({ field: { value, onChange } }) => (
               <CustomDropDown
                  data={ACCESS_STATUS_DATA}
                  value={value}
                  onChange={onChange}
                  label={"Statut d'accès"}
                  isRequired={true}
                  placeholder={'Sélectionner un statut'}
                  errorMessage={
                     errors.accessStatus && (
                        <ErrorMessage
                           message={"Le statut d'accès est requis."}
                        />
                     )
                  }
                  search={false}
               />
            )}
         />

         <Controller
            control={control}
            name="accessiblePRM"
            render={({ field: { value, onChange } }) => (
               <CustomDropDown
                  data={ACCESSIBILITY_DATA}
                  value={value}
                  onChange={onChange}
                  label={'Accessibilité PMR'}
                  placeholder={"Sélectionner un type d'accessibilité"}
                  search={false}
               />
            )}
         />

         <SectionDivider style={{ marginTop: 10 }} />
         <SectionTitle
            containerStyle={{ paddingBottom: 0, paddingTop: 0 }}
            style={{ fontSize: 22 }}
         >
            Horaires
         </SectionTitle>

         <View style={styles.checkboxContainer}>
            <Text style={styles.checkboxLabel}>
               Les horaires sont les mêmes tous les jours
            </Text>
            <Controller
               control={control}
               name="schedules.sameForAllDays"
               render={({ field: { value, onChange } }) => (
                  <Checkbox
                     style={styles.checkbox}
                     value={value}
                     onValueChange={onChange}
                  />
               )}
            />
         </View>

         {sameForAllDays && (
            <TwoColumns>
               <Controller
                  control={control}
                  name="schedules.globalStart"
                  render={({ field: { value, onChange } }) => (
                     <CustomDropDown
                        data={generateTimeSlots()}
                        value={value}
                        onChange={onChange}
                        placeholder={"Heure d'ouverture"}
                        containerStyle={{ width: '48%' }}
                        isLabel={false}
                        search={false}
                     />
                  )}
               />

               <Controller
                  control={control}
                  name="schedules.globalEnd"
                  render={({ field: { value, onChange } }) => (
                     <CustomDropDown
                        data={generateTimeSlots()}
                        value={value}
                        onChange={onChange}
                        placeholder={'Heure de fermeture'}
                        containerStyle={{ width: '48%' }}
                        isLabel={false}
                        search={false}
                     />
                  )}
               />
            </TwoColumns>
         )}

         {!sameForAllDays && (
            <>
               <TwoColumns>
                  <Controller
                     control={control}
                     name="schedules.days.lundi.0.start"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           label={'Lundi'}
                           placeholder={"Heure d'ouverture"}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />

                  <Controller
                     control={control}
                     name="schedules.days.lundi.0.end"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           placeholder={'Heure de fermeture'}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />
               </TwoColumns>

               <TwoColumns>
                  <Controller
                     control={control}
                     name="schedules.days.mardi.0.start"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           label={'Mardi'}
                           placeholder={"Heure d'ouverture"}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />

                  <Controller
                     control={control}
                     name="schedules.days.mardi.0.end"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           placeholder={'Heure de fermeture'}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />
               </TwoColumns>

               <TwoColumns>
                  <Controller
                     control={control}
                     name="schedules.days.mercredi.0.start"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           label={'Mercredi'}
                           placeholder={"Heure d'ouverture"}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />

                  <Controller
                     control={control}
                     name="schedules.days.mercredi.0.end"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           placeholder={'Heure de fermeture'}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />
               </TwoColumns>

               <TwoColumns>
                  <Controller
                     control={control}
                     name="schedules.days.jeudi.0.start"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           label={'Jeudi'}
                           placeholder={"Heure d'ouverture"}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />

                  <Controller
                     control={control}
                     name="schedules.days.jeudi.0.end"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           placeholder={'Heure de fermeture'}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />
               </TwoColumns>

               <TwoColumns>
                  <Controller
                     control={control}
                     name="schedules.days.vendredi.0.start"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           label={'Vendredi'}
                           placeholder={"Heure d'ouverture"}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />

                  <Controller
                     control={control}
                     name="schedules.days.vendredi.0.end"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           placeholder={'Heure de fermeture'}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />
               </TwoColumns>

               <TwoColumns>
                  <Controller
                     control={control}
                     name="schedules.days.samedi.0.start"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           label={'Samedi'}
                           placeholder={"Heure d'ouverture"}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />

                  <Controller
                     control={control}
                     name="schedules.days.samedi.0.end"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           placeholder={'Heure de fermeture'}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />
               </TwoColumns>

               <TwoColumns>
                  <Controller
                     control={control}
                     name="schedules.days.dimanche.0.start"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           label={'Dimanche'}
                           placeholder={"Heure d'ouverture"}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />

                  <Controller
                     control={control}
                     name="schedules.days.dimanche.0.end"
                     render={({ field: { value, onChange } }) => (
                        <CustomDropDown
                           data={generateTimeSlots()}
                           value={value}
                           onChange={onChange}
                           placeholder={'Heure de fermeture'}
                           containerStyle={{ width: '48%' }}
                           search={false}
                        />
                     )}
                  />
               </TwoColumns>
            </>
         )}

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="schedules.type"
            render={({ field: { value, onChange } }) => (
               <CustomDropDown
                  data={SCHEDULES_TYPE_DATA}
                  value={value}
                  onChange={onChange}
                  label={"Type d'horaire"}
                  isRequired={true}
                  placeholder={"Sélectionner un type d'horaire"}
                  errorMessage={
                     errors.schedules?.type && (
                        <ErrorMessage
                           message={"Le type d'horaire est requis."}
                        />
                     )
                  }
                  search={false}
               />
            )}
         />

         <Controller
            control={control}
            name="schedules.note"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={"Note d'information"}
                  multiline={true}
                  numberOfLines={3}
                  style={{ height: 100 }}
                  returnKeyType={'next'}
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="schedules.officialHoursUrl"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={'URL des horaires officiels'}
                  isRequired={true}
                  errorMessage={
                     errors.schedules?.officialHoursUrl && (
                        <ErrorMessage
                           message={"L'URL des horaires officiels est requise."}
                        />
                     )
                  }
                  returnKeyType={'next'}
               />
            )}
         />

         <SectionDivider style={{ marginTop: 10 }} />
         <SectionTitle
            containerStyle={{ paddingBottom: 0, paddingTop: 0 }}
            style={{ fontSize: 22 }}
         >
            Coordonnées
         </SectionTitle>

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="latitude"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={'Latitude'}
                  isRequired={true}
                  keyboardType="numbers-and-punctuation"
                  errorMessage={
                     errors.latitude && (
                        <ErrorMessage message={'La latitude est requise.'} />
                     )
                  }
                  returnKeyType={'next'}
               />
            )}
         />

         <Controller
            control={control}
            rules={{
               required: true,
            }}
            name="longitude"
            render={({ field: { value, onChange } }) => (
               <FormInput
                  value={value}
                  onChangeText={onChange}
                  onPress={() => onChange('')}
                  label={'Longitude'}
                  isRequired={true}
                  keyboardType="numbers-and-punctuation"
                  errorMessage={
                     errors.longitude && (
                        <ErrorMessage message={'La longitude est requise'} />
                     )
                  }
                  returnKeyType={'next'}
               />
            )}
         />

         {Object.entries(errors).length > 0 && (
            <ErrorMessage
               message={
                  "Un ou plusieurs champs requis n'ont pas été complétés, veuillez réviser le formulaire."
               }
            />
         )}
      </View>
   )
}

const styles = StyleSheet.create({
   inputsContainer: {
      gap: 24,
      paddingBottom: 20,
   },
   checkboxContainer: {
      flexDirection: 'row',
      alignItems: 'flex-end',
      gap: 6,
   },
   checkboxLabel: {
      fontSize: 16,
   },
})
