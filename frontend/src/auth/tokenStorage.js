import * as SecureStore from 'expo-secure-store'

const ACCESS_TOKEN_KEY = 'access_token'

export async function getAccessToken() {
   return await SecureStore.getItemAsync(ACCESS_TOKEN_KEY)
}

export async function saveAccessToken(token) {
   await SecureStore.setItemAsync(ACCESS_TOKEN_KEY, token)
}

export async function removeAccessToken() {
   await SecureStore.deleteItemAsync(ACCESS_TOKEN_KEY)
}
