import { client } from '../api/client'
import { API_ROUTES } from '../api/endpoints'

export function getCities() {
   return client(API_ROUTES.CITY.FINDALL)
}

export function getCityById(id) {
   return client(API_ROUTES.CITY.FIND_BY_ID(id))
}

export function deleteCity(id) {
   return client(API_ROUTES.CITY.DELETE(id), {
      method: 'DELETE',
   })
}
