import Link from "next/link"
import Image from "next/image"
import { Card, CardContent } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Star } from "lucide-react"

const featuredProducts = [
  {
    id: 1,
    name: "Nike Air Zoom Pegasus 40",
    price: 129.99,
    originalPrice: 149.99,
    image: "/images/nike-pegasus.jpg",
    rating: 4.5,
    reviews: 234,
    category: "running",
    isNew: true,
  },
  {
    id: 2,
    name: "Haltères Réglables 20kg",
    price: 89.99,
    image: "/images/dumbbells.jpg",
    rating: 4.8,
    reviews: 156,
    category: "fitness",
    isSale: true,
  },
  {
    id: 3,
    name: "Maillot PSG Domicile 2024",
    price: 79.99,
    image: "/images/psg-jersey.jpg",
    rating: 4.3,
    reviews: 89,
    category: "football",
    isNew: true,
  },
  {
    id: 4,
    name: "Chaussures Basketball Jordan",
    price: 199.99,
    image: "/images/jordan-basketball.jpg",
    rating: 4.7,
    reviews: 312,
    category: "basketball",
  },
]

export default function FeaturedProducts() {
  return (
    <section className="py-16">
      <div className="container mx-auto px-4">
        <div className="text-center mb-12">
          <h2 className="text-3xl font-bold mb-4">Produits Vedettes</h2>
          <p className="text-gray-600 max-w-2xl mx-auto">Découvrez notre sélection des meilleurs produits sportifs</p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {featuredProducts.map((product) => (
            <Card key={product.id} className="group hover:shadow-lg transition-shadow h-full">
              <CardContent className="p-0 flex flex-col h-full">
                <div className="relative">
                  <div className="relative h-64 overflow-hidden rounded-t-lg">
                    <Image
                      src={product.image || "/placeholder.svg"}
                      alt={product.name}
                      fill
                      className="object-cover group-hover:scale-105 transition-transform duration-300"
                    />
                  </div>
                  <div className="absolute top-2 left-2 flex flex-col gap-1">
                    {product.isNew && <Badge className="bg-green-500">Nouveau</Badge>}
                    {product.isSale && <Badge className="bg-red-500">Promo</Badge>}
                  </div>
                </div>

                <div className="p-4 flex flex-col flex-1">
                  <h3 className="font-semibold text-lg mb-2 line-clamp-2 min-h-[3.5rem]">{product.name}</h3>

                  <div className="flex items-center mb-2">
                    <div className="flex items-center">
                      {[...Array(5)].map((_, i) => (
                        <Star
                          key={i}
                          className={`h-4 w-4 ${
                            i < Math.floor(product.rating) ? "text-yellow-400 fill-current" : "text-gray-300"
                          }`}
                        />
                      ))}
                    </div>
                    <span className="text-sm text-gray-600 ml-2">({product.reviews})</span>
                  </div>

                  <div className="flex items-center justify-between mb-4">
                    <div className="flex items-center gap-2">
                      <span className="text-xl font-bold text-blue-600">{product.price}€</span>
                      {product.originalPrice && (
                        <span className="text-sm text-gray-500 line-through">{product.originalPrice}€</span>
                      )}
                    </div>
                  </div>

                  <div className="mt-auto">
                    <Button asChild className="w-full">
                      <Link href={`/products/${product.id}`}>Voir le produit</Link>
                    </Button>
                  </div>
                </div>
              </CardContent>
            </Card>
          ))}
        </div>

        <div className="text-center mt-12">
          <Button variant="outline" size="lg" asChild>
            <Link href="/products">Voir tous les produits</Link>
          </Button>
        </div>
      </div>
    </section>
  )
}
