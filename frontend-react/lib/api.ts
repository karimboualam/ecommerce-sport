// lib/api.ts
export async function fetchWithAuth(url: string, options = {}) {
  const token = localStorage.getItem("token")
  const headers = {
    ...(options as any).headers,
    Authorization: `Bearer ${token}`,
  }

  return fetch(url, { ...options, headers })
}
