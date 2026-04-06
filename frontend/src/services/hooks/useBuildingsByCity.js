import { useEffect, useState } from 'react'
import { getBuildingsByCity } from '../buildings/buildings.service'
import { Alert } from 'react-native'

export default function useBuildingsByCity(id) {
   const [buildingsByCity, setBuildingsByCity] = useState([])
   const [isLoading, setIsLoading] = useState(false)

   useEffect(() => {
      loadBuildingsByCity()
   }, [id])

   const loadBuildingsByCity = async () => {
      try {
         setIsLoading(true)
         const data = await getBuildingsByCity(id)
         setBuildingsByCity(data)
      } catch (error) {
         Alert.alert('Une erreur est survenue')
         console.log(error)
      } finally {
         setIsLoading(false)
      }
   }

   return { buildingsByCity, isLoading }
}
