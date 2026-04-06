import { useEffect, useRef, useState } from 'react'

export default function useDelayLoader(isLoading) {
   const [showLoader, setShowLoader] = useState(false)

   const appearTimer = useRef(null)
   const hideTimer = useRef(null)
   const visibleSince = useRef(null)

   useEffect(() => {
      if (isLoading) {
         // Apparition du loader au bout d'une requête > 250ms
         appearTimer.current = setTimeout(() => {
            visibleSince.current = Date.now()
            setShowLoader(true)
         }, 250)
      } else {
         clearTimeout(appearTimer.current)

         // Affiche le loader minimum 600 ms
         if (visibleSince.current) {
            const elapsed = Date.now() - visibleSince.current
            const remaining = Math.max(600 - elapsed, 0)

            hideTimer.current = setTimeout(() => {
               setShowLoader(false)
               visibleSince.current = null
            }, remaining)
         }
      }

      return () => {
         if (appearTimer.current) clearTimeout(appearTimer.current)
         if (hideTimer.current) clearTimeout(hideTimer.current)
      }
   }, [isLoading])

   return showLoader
}
