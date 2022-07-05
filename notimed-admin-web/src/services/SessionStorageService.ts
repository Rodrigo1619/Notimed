export const sessionSave = (key: string, data: string) => {
    sessionStorage.setItem(key, data)
}