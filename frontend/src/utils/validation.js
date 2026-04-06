export const validators = {
   email: (value) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value),
   minLength: (value, min = 8) => value?.length >= min,
   required: (value) => value?.trim(),
}
