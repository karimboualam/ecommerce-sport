"use client"

import { useEffect, useState } from "react"
import { useAuth } from "@/contexts/auth-context"
import { useToast } from "@/hooks/use-toast"
import { Card, CardContent } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Calendar, Euro, Clock } from "lucide-react"

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
        const res = await fetch("http://localhost:8080/api/client/commandes", {
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
    <div className="container mx-auto px-4 py-12">
      <h1 className="text-3xl font-bold mb-8 text-center">🧾 Mes Commandes</h1>

      {commandes.length === 0 ? (
        <div className="text-center text-gray-500 mt-12">
          Vous n'avez pas encore passé de commande.
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {commandes.map((cmd) => (
            <Card key={cmd.id} className="shadow-md">
              <CardContent className="p-6">
                <div className="mb-4">
                  <div className="flex justify-between items-center">
                    <h2 className="text-xl font-semibold">Commande #{cmd.id}</h2>
                    <Badge className="uppercase">{cmd.status}</Badge>
                  </div>
                  <p className="text-sm text-gray-500">📍 {cmd.adresseLivraison}</p>
                  <div className="flex items-center text-sm text-gray-500 mt-1">
                    <Calendar className="w-4 h-4 mr-1" />
                    {new Date(cmd.date).toLocaleString()}
                  </div>
                </div>

                <div className="border-t pt-3 space-y-2">
                  {cmd.ligneCommandes.map((ligne) => (
                    <div key={`${cmd.id}-${ligne.article.reference}`} className="flex justify-between text-sm">
                      <span>
                        {ligne.article.nom} x {ligne.quantite}
                      </span>
                      <span className="text-right text-blue-600 font-medium">
                        {(ligne.prix * ligne.quantite).toFixed(2)} €
                      </span>
                    </div>
                  ))}
                </div>

                <div className="border-t mt-4 pt-3 flex justify-between items-center text-base font-semibold">
                  <span>Total</span>
                  <div className="flex items-center text-blue-600">
                    <Euro className="w-4 h-4 mr-1" />
                    {cmd.montant.toFixed(2)} €
                  </div>
                </div>
              </CardContent>
            </Card>
          ))}
        </div>
      )}
    </div>
  )
}
