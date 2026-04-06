import { addBuilding } from '../buildings/buildings.service'
import { useState } from 'react'

export function useAddBuilding() {
   const [isLoadingBuild, setLoadingBuild] = useState(false)

   const addBuildingHandler = async (data) => {
      try {
         setLoadingBuild(true)
         const result = await addBuilding(data)
         return result
      } catch (error) {
         console.log(error)
      } finally {
         setLoadingBuild(false)
      }
   }

   return { addBuildingHandler, isLoadingBuild }
}
