"use client"

import { createContext, useContext, useEffect, useState, type ReactNode } from "react"

interface User {
  id: number
  prenom: string
  nom: string
  email: string
  username: string
  role: string
  adresse: string
}

interface AuthContextType {
  user: User | null
  token: string | null
  isLoading: boolean
  login: (email: string, password: string) => Promise<boolean>
  register: (
    prenom: string,
    nom: string,
    username: string,
    email: string,
    password: string,
    adresse: string
  ) => Promise<boolean>
  logout: () => void
}

const AuthContext = createContext<AuthContextType | null>(null)

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null)
  const [token, setToken] = useState<string | null>(null)
  const [isLoading, setIsLoading] = useState(true)

  useEffect(() => {
    const savedUser = localStorage.getItem("user")
    const savedToken = localStorage.getItem("token")
    if (savedUser && savedToken) {
      setUser(JSON.parse(savedUser))
      setToken(savedToken)
    }
    setIsLoading(false)
  }, [])

  const login = async (email: string, password: string): Promise<boolean> => {
    setIsLoading(true)
    try {
      const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      })

      if (!res.ok) throw new Error("Login failed")

      const jwt = await res.text()
      localStorage.setItem("token", jwt)
      setToken(jwt)

      const userRes = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/client/profil`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      })

      const userData = await userRes.json()
      setUser(userData)
      localStorage.setItem("user", JSON.stringify(userData))

      return true
    } catch (err) {
      console.error(err)
      return false
    } finally {
      setIsLoading(false)
    }
  }

  const register = async (
    prenom: string,
    nom: string,
    username: string,
    email: string,
    password: string,
    adresse: string
  ): Promise<boolean> => {
    setIsLoading(true)
    try {
      const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/auth/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ prenom, nom, username, email, password, adresse }),
      })

      if (!res.ok) throw new Error("Register failed")
      return true
    } catch (err) {
      console.error(err)
      return false
    } finally {
      setIsLoading(false)
    }
  }

  const logout = () => {
    localStorage.removeItem("token")
    localStorage.removeItem("user")
    setUser(null)
    setToken(null)
  }

  return (
    <AuthContext.Provider value={{ user, token, isLoading, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  const context = useContext(AuthContext)
  if (!context) throw new Error("useAuth must be used within AuthProvider")
  return context
}
