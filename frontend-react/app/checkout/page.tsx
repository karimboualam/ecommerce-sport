"use client"

import { useCart } from "@/contexts/cart-context"
import { useAuth } from "@/contexts/auth-context"
import { Button } from "@/components/ui/button"
import { useToast } from "@/hooks/use-toast"
import { useRouter } from "next/navigation"

export default function CheckoutPage() {
  const { items, total, clearCart } = useCart()
  const { user } = useAuth()
  const { toast } = useToast()
  const router = useRouter()

  if (items.length === 0) {
    return <p className="text-center mt-10">Votre panier est vide.</p>
  }

  const handleConfirm = async () => {
    try {
      const token = localStorage.getItem("token")
      if (!token) {
        toast({ title: "Erreur", description: "Utilisateur non connecté", variant: "destructive" })
        return
      }

      const body = {
        adresseLivraison: user?.adresse,
        status: "EN_ATTENTE",
        montant: total,
        ligneCommandes: items.map((item) => ({
          article: {
            reference: item.id, //  pas item.articleId ni item.reference
          },
          quantite: item.quantity,
          prix: item.price,
        })),
      }

      const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/client/commandes`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(body),
      })

      if (!res.ok) throw new Error("Commande échouée")

      toast({ title: "Commande confirmée", description: "Merci pour votre achat !" })
      clearCart()
      router.push("/")
    } catch (err) {
      console.error(err)
      toast({ title: "Erreur", description: "Impossible de confirmer la commande", variant: "destructive" })
    }
  }

  return (
    <div className="container mx-auto px-4 py-16">
      <h1 className="text-2xl font-bold mb-6">Validation de la commande</h1>

      <div className="space-y-4">
        {items.map((item) => (
          <div key={item.id} className="flex justify-between border-b pb-2">
            <div>
              <p className="font-semibold">{item.name}</p>
              <p>Quantité : {item.quantity}</p>
            </div>
            <p>{(item.price * item.quantity).toFixed(2)} €</p>
          </div>
        ))}
      </div>

      <div className="mt-6 text-right">
        <p className="text-lg font-bold">Total : {total.toFixed(2)} €</p>
        <Button className="mt-4" onClick={handleConfirm}>Confirmer la commande</Button>
      </div>
    </div>
  )
}
