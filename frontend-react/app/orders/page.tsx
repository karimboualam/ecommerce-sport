"use client"

import { useEffect, useState } from "react"
import { useAuth } from "@/contexts/auth-context"
import { useToast } from "@/hooks/use-toast"

interface LigneCommande {
  id: number
  article: {
    reference: number
    nom: string
  }
  prix: number
  quantite: number
}

interface Commande {
  id: number
  adresseLivraison: string
  date: string
  montant: number
  status: string
  ligneCommandes: LigneCommande[]
}

export default function OrdersPage() {
  const { user } = useAuth()
  const { toast } = useToast()
  const [commandes, setCommandes] = useState<Commande[]>([])

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const token = localStorage.getItem("token")
        console.log("ORDERS/PAGE.TSX TOKEN =>", token)
        const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/client/commandes`, {
          headers: {
            Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
          },
        })

        if (!res.ok) throw new Error("Erreur lors du chargement des commandes")
        const data = await res.json()
        setCommandes(data)
      } catch (err) {
        console.error(err)
        toast({
          title: "Erreur",
          description: "Impossible de charger vos commandes",
          variant: "destructive",
        })
      }
    }

    fetchOrders()
  }, [])

  if (!user) {
    return <p className="text-center mt-10">Veuillez vous connecter pour voir vos commandes.</p>
  }

  return (
    <div className="container mx-auto px-4 py-16">
      <h1 className="text-2xl font-bold mb-6">Mes Commandes</h1>

      {commandes.length === 0 ? (
        <p>Vous n'avez pas encore passé de commande.</p>
      ) : (
        <div className="space-y-6">
          {commandes.map((cmd) => (
            <div key={cmd.id} className="border p-4 rounded-md">
              <p className="font-semibold">Commande n° {cmd.id}</p>
              <p>Adresse : {cmd.adresseLivraison}</p>
              <p>Date : {new Date(cmd.date).toLocaleString()}</p>
              <p>Montant : {cmd.montant} €</p>
              <p>Statut : {cmd.status}</p>

              <ul className="mt-2 list-disc list-inside">
                {cmd.ligneCommandes.map((ligne) => (
                  <li key={ligne.id}>
                    {ligne.article.nom} x {ligne.quantite} – {ligne.prix} €
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </div>
      )}
    </div>
  )
}
