import { useEffect, useState } from 'react'
import { getBuildingsByCategory } from '../categories/categories.service'
import { Alert } from 'react-native'

export default function useBuildingsByCategory(id) {
   const [buildingsByCategory, setBuildingsByCategory] = useState([])
   const [isLoading, setIsLoading] = useState(false)

   useEffect(() => {
      loadBuildingsByCategory()
   }, [id])

   const loadBuildingsByCategory = async () => {
      try {
         setIsLoading(true)
         const data = await getBuildingsByCategory(id)
         setBuildingsByCategory(data)
      } catch (error) {
         Alert.alert('Une erreur est survenue')
         console.log(error)
      } finally {
         setIsLoading(false)
      }
   }

   return { buildingsByCategory, isLoading }
}
