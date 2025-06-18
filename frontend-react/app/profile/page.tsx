"use client"

import { useAuth } from "@/contexts/auth-context"
import { useEffect, useState } from "react"
import { useRouter } from "next/navigation"


export default function ProfilePage() {
  //const { token } = useAuth()
  const { token, logout } = useAuth()
    const router = useRouter()    

  const [userData, setUserData] = useState<any>(null)
  const [isEditing, setIsEditing] = useState(false)
  const [formData, setFormData] = useState<any>({})
  useEffect(() => {
      console.log("📦 Token utilisé :", token) // 🔍 debug

    // Charger le profil avec GET
    fetch("http://localhost:8080/api/client/profil", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setUserData(data)
        setFormData(data)
      })
      .catch((err) => {
        console.error("Erreur chargement profil :", err)
      })
  }, [token])

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setFormData((prev: any) => ({ ...prev, [name]: value }))
  }

  const handleSave = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/client/profil", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(formData),
      })

      if (res.ok) {
        alert("Profil mis à jour !")
        setIsEditing(false)
        const updated = await res.json()
        setUserData(updated)
      } else {
        alert("Erreur lors de la mise à jour.")
      }
    } catch (err) {
      console.error("Update error:", err)
    }
  }

const handleDelete = async () => {
  const confirmDelete = confirm("Êtes-vous sûr de vouloir supprimer votre compte ?")
  if (!confirmDelete) return

  try {
    const res = await fetch("http://localhost:8080/api/client/profil", {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })

    if (res.ok) {
      alert("Compte supprimé.")
      logout()         // Supprime les données
      router.push("/") // ✅ Redirige proprement
      console.log("🔄 Token supprimé, redirection en cours...")
     router.refresh() // Force le rechargement du DOM côté client après navigation
    } else {
      alert("Erreur lors de la suppression.")
    }
  } catch (err) {
    console.error("Delete error:", err)
  }
}

  if (!userData) {
    return <div className="text-center mt-10">Chargement du profil...</div>
  }

  return (
    <div className="container mx-auto px-4 py-16">
      <h1 className="text-2xl font-bold mb-6">Mon Profil</h1>

      <div className="space-y-4">
        <p><strong>Prénom :</strong> {isEditing ? (
          <input name="prenom" value={formData.prenom} onChange={handleChange} className="border p-1 w-full" />
        ) : userData.prenom}</p>

        <p><strong>Nom :</strong> {isEditing ? (
          <input name="nom" value={formData.nom} onChange={handleChange} className="border p-1 w-full" />
        ) : userData.nom}</p>

        <p><strong>Nom d'utilisateur :</strong> {isEditing ? (
          <input name="username" value={formData.username} onChange={handleChange} className="border p-1 w-full" />
        ) : userData.username}</p>

        <p><strong>Email :</strong> {userData.email}</p>

        <p><strong>Adresse :</strong> {isEditing ? (
          <input name="adresse" value={formData.adresse} onChange={handleChange} className="border p-1 w-full" />
        ) : userData.adresse}</p>

        <p><strong>Rôle :</strong> {userData.role}</p>

        <div className="flex gap-4 mt-6">
          {!isEditing ? (
            <button onClick={() => setIsEditing(true)} className="bg-yellow-500 text-white px-4 py-2 rounded">
              Modifier
            </button>
          ) : (
            <button onClick={handleSave} className="bg-blue-600 text-white px-4 py-2 rounded">
              Enregistrer
            </button>
          )}

          <button onClick={handleDelete} className="bg-red-600 text-white px-4 py-2 rounded">
            Supprimer mon compte
          </button>
        </div>
      </div>
    </div>
  )
}
