import { useEffect, useState } from 'react'
import { getBuildings } from '../buildings/buildings.service'
import { Alert } from 'react-native'

export default function useBuildings() {
   const [buildings, setBuildings] = useState(null)
   const [isLoadingBuild, setLoadingBuild] = useState(false)

   useEffect(() => {
      loadBuildings()
   }, [])

   const loadBuildings = async () => {
      try {
         setLoadingBuild(true)
         const data = await getBuildings()
         setBuildings(data)
      } catch (error) {
         Alert.alert('Une erreur est survenue')
         console.log(error)
      } finally {
         setLoadingBuild(false)
      }
   }

   return { buildings, isLoadingBuild }
}
