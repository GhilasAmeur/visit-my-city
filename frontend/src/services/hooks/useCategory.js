import { useEffect, useState } from 'react'
import { getCategoryById } from '../../services/categories/categories.service'
import { Alert } from 'react-native'

export default function useCategory(id) {
   const [category, setCategory] = useState(null)
   const [isLoading, setIsLoading] = useState(false)

   useEffect(() => {
      loadCategory()
   }, [id])

   const loadCategory = async () => {
      try {
         const data = await getCategoryById(id)
         setCategory(data)
      } catch (error) {
         Alert.alert('Une erreur est survenue')
         console.log(error)
      } finally {
         setIsLoading(false)
      }
   }

   return { category, isLoading }
}
