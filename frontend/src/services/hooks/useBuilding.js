import { useEffect, useState } from 'react'
import { Alert } from 'react-native'
import { getBuildingById } from '../buildings/buildings.service'

export default function useBuilding(id) {
   const [building, setBuilding] = useState(null)
   const [isLoading, setIsLoading] = useState(false)

   useEffect(() => {
      loadBuildings()
   }, [id])

   const loadBuildings = async () => {
      try {
         setIsLoading(true)
         const data = await getBuildingById(id)
         setBuilding(data)
      } catch (error) {
         Alert.alert('Une erreur est survenue')
         console.log(error)
      } finally {
         setIsLoading(false)
      }
   }

   return { building, isLoading }
}
