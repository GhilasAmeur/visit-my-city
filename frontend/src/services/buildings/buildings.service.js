import { client } from '../api/client'
import { API_ROUTES } from '../api/endpoints'

export function getBuildings() {
   return client(API_ROUTES.BUILDING.FINDALL)
}

export function getBuildingById(id) {
   return client(API_ROUTES.BUILDING.FIND_BY_ID(id))
}

export function getBuildingsByCity(id) {
   return client(API_ROUTES.BUILDING.FIND_BUILDINGS_BY_CITY(id))
}

export function searchBuildingsByCity(id) {
   return client(API_ROUTES.BUILDING.SEARCH_BUILDINGS_BY_CITY(id))
}

export function addBuilding(data) {
   return client(API_ROUTES.BUILDING.ADD_BUILDING, {
      method: 'POST',
      body: JSON.stringify(data),
   })
}
