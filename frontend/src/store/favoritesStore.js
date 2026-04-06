import { create } from 'zustand'
import { createJSONStorage, persist } from 'zustand/middleware'
import AsyncStorage from '@react-native-async-storage/async-storage'

export const useFavorite = create(
   persist(
      (set, get) => ({
         favoriteCities: [],
         favoriteBuildings: [],

         toggleFavoriteCity: (id) =>
            set((state) => {
               if (!state.favoriteCities.includes(id)) {
                  return {
                     favoriteCities: [...state.favoriteCities, id],
                  }
               }

               const newFavoriteCities = state.favoriteCities.filter(
                  (el) => el !== id
               )
               return {
                  favoriteCities: newFavoriteCities,
               }
            }),
         toggleFavoriteBuilding: (id) =>
            set((state) => {
               if (!state.favoriteBuildings.includes(id)) {
                  return {
                     favoriteBuildings: [...state.favoriteBuildings, id],
                  }
               }

               const newFavoriteBuildings = state.favoriteBuildings.filter(
                  (el) => el !== id
               )
               return {
                  favoriteBuildings: newFavoriteBuildings,
               }
            }),
         isFavoriteCity: (id) => get().favoriteCities.includes(id),
         isFavoriteBuilding: (id) => get().favoriteBuildings.includes(id),
      }),

      {
         name: 'favorites-storage',
         storage: createJSONStorage(() => AsyncStorage),
      }
   )
)
