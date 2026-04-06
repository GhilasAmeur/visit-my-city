import { client } from '../api/client'
import { API_ROUTES } from '../api/endpoints'
import { saveAccessToken } from '../../auth/tokenStorage'

export async function login(email, password) {
   const data = await client(API_ROUTES.AUTH.LOGIN, {
      method: 'POST',
      body: JSON.stringify({ email, password }),
   })

   await saveAccessToken(data.access_token)
   return data
}

export function register(username, email, password) {
   return client(API_ROUTES.AUTH.REGISTER, {
      method: 'POST',
      body: JSON.stringify({ username, email, password }),
   })
}
