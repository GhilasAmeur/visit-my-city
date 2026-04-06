export const API_BASE_URL =
   process.env.EXPO_PUBLIC_API_URL_TELEPHONE ??
   process.env.EXPO_PUBLIC_API_URL_ANDROID_EMULATOR ??
   process.env.EXPO_PUBLIC_API_URL_IOS_SIMULATOR

export const API_ROUTES = {
   AUTH: {
      REGISTER: '/auth/register',
      LOGIN: '/auth/login',
   },
   BUILDING: {
      FINDALL: '/building/buildingsdto',
      FIND_BY_ID: (id) => `/building/buildingdto/${id}`,
      FIND_BUILDINGS_BY_CITY: (id) => `/building/buildingsdto/city/${id}`,
      SEARCH_BUILDINGS_BY_CITY: (name) =>
         `/buildingdto/cityname/${encodeURIComponent(name)}`,
      ADD_BUILDING: `/building/add/add`,
   },
   CITY: {
      FINDALL: '/city/cities',
      FIND_BY_ID: (id) => `/city/${id}`,
      DELETE: (id) => `/city/delete/${id}`,
   },
   CATEGORY: {
      FINDALL: '/category/categories',
      FIND_BY_ID: (id) => `/category/${id}`,
      FIND_BUILDINGS_BY_CATEGORY: (id) =>
         `/building/buildingsdto/category/${id}`,
   },
}
