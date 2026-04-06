import { create } from 'zustand'
import { createJSONStorage, persist } from 'zustand/middleware'
import AsyncStorage from '@react-native-async-storage/async-storage'

export const useUserStore = create(
   persist(
      (set, get) => ({
         user: {
            username: null,
            email: null,
            role: 'ROLE_VISITEUR',
         },
         token: null,

         setUser: ({ user, token }) => set({ user, token }),
         logout: () => set(() => ({ user: null, token: null })),
         isLoggedIn: () => !!get().user,
      }),

      {
         name: 'user-storage',
         storage: createJSONStorage(() => AsyncStorage),
      }
   )
)
