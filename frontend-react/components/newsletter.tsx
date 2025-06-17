import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"

export default function Newsletter() {
  return (
    <section className="py-16 bg-blue-600 text-white">
      <div className="container mx-auto px-4 text-center">
        <h2 className="text-3xl font-bold mb-4">Restez informé de nos nouveautés</h2>
        <p className="text-blue-100 mb-8 max-w-2xl mx-auto">
          Inscrivez-vous à notre newsletter pour recevoir nos offres exclusives et être le premier informé de nos
          nouveaux produits
        </p>

        <div className="max-w-md mx-auto flex gap-2">
          <Input type="email" placeholder="Votre adresse email" className="bg-white text-black" />
          <Button variant="secondary">S'inscrire</Button>
        </div>
      </div>
    </section>
  )
}
