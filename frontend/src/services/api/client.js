import { API_BASE_URL } from './endpoints'
import { getAccessToken } from '../../auth/tokenStorage'

export async function client(path, options = {}) {
   const token = await getAccessToken()

   const response = await fetch(`${API_BASE_URL}${path}`, {
      ...options,
      headers: {
         'Content-Type': 'application/json',
         ...(token ? { Authorization: `Bearer ${token}` } : {}),
      },
   })

   console.log('ENDPOINT:', path, 'METHOD:', options.method)

   if (!response.ok) {
      const error = await response.json()
      throw new Error(error.message || 'Une erreur est survenue.')
   }

   if (response.status === 204) {
      return null
   }

   const text = await response.text()

   return text ? JSON.parse(text) : null
}
