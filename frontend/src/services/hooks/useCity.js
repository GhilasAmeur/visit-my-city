import { useEffect, useState } from 'react'
import { Alert } from 'react-native'
import { getCityById } from '../cities/cities.service'

export default function useCity(id) {
   const [city, setCity] = useState(null)
   const [isLoading, setIsLoading] = useState(false)

   useEffect(() => {
      loadBuildings()
   }, [id])

   const loadBuildings = async () => {
      try {
         setIsLoading(true)
         const data = await getCityById(id)
         setCity(data)
      } catch (error) {
         Alert.alert('Une erreur est survenue')
         console.log(error)
      } finally {
         setIsLoading(false)
      }
   }

   return { city, isLoading }
}
