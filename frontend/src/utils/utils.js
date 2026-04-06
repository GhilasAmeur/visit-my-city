export const groupCategories = (categories) => {
   const result = []

   for (let i = 0; i < categories.length; i += 2) {
      result.push(categories.slice(i, i + 2))
   }

   return result
}

export const divideOddsAndEvens = (items) => {
   const leftColumn = []
   const rightColumn = []

   for (let i = 0; i < items.length; i++) {
      if (i % 2 == 0) {
         leftColumn.push(items[i])
      } else {
         rightColumn.push(items[i])
      }
   }

   return { leftColumn, rightColumn }
}

export const firstChartToUpperCase = (value) => {
   return value.charAt(0).toUpperCase() + value.slice(1)
}

export const getPlacesByIds = (idsArray, placesList) => {
   const result = []

   placesList.forEach((place) => {
      if (idsArray.includes(place.id)) {
         result.push(place)
      }
   })

   return result
}

// Version optimisé de getPlacesByIds si un jour l'appli avait énormément de données :
// export const getPlacesByIds = (idsArray = [], placesList = []) => {
//   const idsSet = new Set(idsArray)
//   return placesList.filter((place) => idsSet.has(place.id))
// }

export const generateTimeSlots = () => {
   const slots = []

   for (let hour = 0; hour < 24; hour++) {
      for (let minute of [0, 30]) {
         const h = hour.toString().padStart(2, '0')
         const m = minute.toString().padStart(2, '0')
         const time = `${h}:${m}`

         slots.push({
            label: time,
            value: time,
         })
      }
   }

   return slots
}
