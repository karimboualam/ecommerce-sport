"use client"

import { useEffect, useState } from "react"
import Link from "next/link"
import Image from "next/image"
import { Card, CardContent } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Star } from "lucide-react"

interface Product {
  id: number
  name: string
  price: number
  originalPrice?: number
  image: string
  rating: number
  reviews: number
  category: string
  isNew?: boolean
  isSale?: boolean
}

export default function FeaturedProducts() {
  const [products, setProducts] = useState<Product[]>([])

  useEffect(() => {
    const token = localStorage.getItem("token")
    fetch("http://localhost:8080/api/client/articles/featured", {
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    })
      .then((res) => {
        if (!res.ok) throw new Error("Erreur lors du chargement des produits vedettes")
        return res.json()
      })
      .then((data) => {
        const mapped = data.map((article: any) => ({
          id: article.reference,
          name: article.nom,
          price: article.prix,
          originalPrice: article.ancienPrix || null,
          image: article.image?.startsWith("http") ? article.image : `/images/${article.image}`,
          rating: article.note || 4.5,
          reviews: article.avis || 25,
          category: article.categorie,
          isNew: article.nouveau || false,
          isSale: article.promo || false,
        }))
        setProducts(mapped)
      })
      .catch((err) => {
        console.error(err)
      })
  }, [])

  return (
    <section className="py-16">
      <div className="container mx-auto px-4">
        <div className="text-center mb-12">
          <h2 className="text-3xl font-bold mb-4">Produits Vedettes</h2>
          <p className="text-gray-600 max-w-2xl mx-auto">Découvrez notre sélection des meilleurs produits sportifs</p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {products.map((product) => (
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
