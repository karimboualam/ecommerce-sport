"use client"

import type React from "react"

import Link from "next/link"
import Image from "next/image"
import { Card, CardContent } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Star, ShoppingCart } from "lucide-react"
import { useCart } from "@/contexts/cart-context"
import type { Product } from "@/types/product"

interface ProductCardProps {
  product: Product
  viewMode?: "grid" | "list"
}

export default function ProductCard({ product, viewMode = "grid" }: ProductCardProps) {
  const { addItem } = useCart()

  const handleAddToCart = (e: React.MouseEvent) => {
    e.preventDefault()
    addItem({
      id: product.id,
      name: product.name,
      price: product.price,
      image: product.image,
      quantity: 1,
    })
  }

  if (viewMode === "list") {
    return (
      <Card className="hover:shadow-lg transition-shadow">
        <CardContent className="p-0">
          <Link href={`/products/${product.id}`}>
            <div className="flex">
              <div className="relative w-48 h-48 flex-shrink-0">
                <Image
                  src={product.image || "/placeholder.svg"}
                  alt={product.name}
                  fill
                  className="object-cover rounded-l-lg"
                />
                <div className="absolute top-2 left-2 flex flex-col gap-1">
                  {product.isNew && <Badge className="bg-green-500">Nouveau</Badge>}
                  {product.isSale && <Badge className="bg-red-500">Promo</Badge>}
                </div>
              </div>

              <div className="flex-1 p-6">
                <div className="flex justify-between items-start">
                  <div className="flex-1">
                    <h3 className="font-semibold text-xl mb-2">{product.name}</h3>
                    <p className="text-gray-600 mb-4 line-clamp-2">{product.description}</p>

                    <div className="flex items-center mb-4">
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
                      <span className="text-sm text-gray-600 ml-2">({product.reviews} avis)</span>
                    </div>
                  </div>

                  <div className="text-right ml-6">
                    <div className="flex items-center gap-2 mb-4">
                      <span className="text-2xl font-bold text-blue-600">{product.price}€</span>
                      {product.originalPrice && (
                        <span className="text-lg text-gray-500 line-through">{product.originalPrice}€</span>
                      )}
                    </div>

                    <Button onClick={handleAddToCart} className="w-full">
                      <ShoppingCart className="h-4 w-4 mr-2" />
                      Ajouter au panier
                    </Button>
                  </div>
                </div>
              </div>
            </div>
          </Link>
        </CardContent>
      </Card>
    )
  }

  return (
    <Card className="group hover:shadow-lg transition-shadow">
      <CardContent className="p-0">
        <Link href={`/products/${product.id}`}>
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

          <div className="p-4">
            <h3 className="font-semibold text-lg mb-2 line-clamp-2">{product.name}</h3>

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
          </div>
        </Link>

        <div className="px-4 pb-4">
          <Button onClick={handleAddToCart} className="w-full">
            <ShoppingCart className="h-4 w-4 mr-2" />
            Ajouter au panier
          </Button>
        </div>
      </CardContent>
    </Card>
  )
}
