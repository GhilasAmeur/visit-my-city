import { useEffect, useState } from 'react'
import { getCategories } from '../categories/categories.service'
import { Alert } from 'react-native'

export default function useCategories() {
   const [categories, setCategories] = useState(null)
   const [isLoadingCat, setLoadingCat] = useState(false)

   useEffect(() => {
      loadCategories()
   }, [])

   const loadCategories = async () => {
      try {
         setLoadingCat(true)
         const data = await getCategories()
         setCategories(data)
      } catch (error) {
         Alert.alert('Une erreur est survenue')
         console.log(error)
      } finally {
         setLoadingCat(false)
      }
   }

   return { categories, isLoadingCat }
}
