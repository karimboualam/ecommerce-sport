"use client"

import { useState } from "react"
import { useParams } from "next/navigation"
import Image from "next/image"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Card, CardContent } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Star, ShoppingCart, Heart, Share2, Truck, Shield, RotateCcw } from "lucide-react"
import { products } from "@/data/products"
import { useCart } from "@/contexts/cart-context"
import { useToast } from "@/hooks/use-toast"
import ProductReviews from "@/components/product-reviews"

export default function ProductPage() {
  const params = useParams()
  const productId = Number.parseInt(params.id as string)
  const product = products.find((p) => p.id === productId)
  const { addItem } = useCart()
  const { toast } = useToast()

  const [selectedSize, setSelectedSize] = useState("")
  const [selectedColor, setSelectedColor] = useState("")
  const [quantity, setQuantity] = useState(1)

  if (!product) {
    return (
      <div className="container mx-auto px-4 py-16 text-center">
        <h1 className="text-2xl font-bold">Produit non trouvé</h1>
      </div>
    )
  }

  const handleAddToCart = () => {
    // Validation des sélections obligatoires
    const missingSelections = []

    if (product.sizes && product.sizes.length > 0 && !selectedSize) {
      missingSelections.push("la taille")
    }

    if (product.colors && product.colors.length > 0 && !selectedColor) {
      missingSelections.push("la couleur")
    }

    if (missingSelections.length > 0) {
      const message = `Veuillez sélectionner ${missingSelections.join(" et ")} avant d'ajouter le produit au panier.`
      toast({
        title: "Sélection incomplète",
        description: message,
        variant: "destructive",
      })
      return
    }

    if (quantity < 1) {
      toast({
        title: "Quantité invalide",
        description: "Veuillez sélectionner une quantité valide (minimum 1).",
        variant: "destructive",
      })
      return
    }

    // Si toutes les validations passent, ajouter au panier
    addItem({
      id: product.id,
      name: product.name,
      price: product.price,
      image: product.image,
      quantity,
      size: selectedSize,
      color: selectedColor,
    })
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-12">
        {/* Product Images */}
        <div className="space-y-4">
          <div className="relative aspect-square overflow-hidden rounded-lg">
            <Image src={product.image || "/placeholder.svg"} alt={product.name} fill className="object-cover" />
            <div className="absolute top-4 left-4 flex flex-col gap-2">
              {product.isNew && <Badge className="bg-green-500">Nouveau</Badge>}
              {product.isSale && <Badge className="bg-red-500">Promo</Badge>}
            </div>
          </div>
        </div>

        {/* Product Info */}
        <div className="space-y-6">
          <div>
            <h1 className="text-3xl font-bold mb-2">{product.name}</h1>
            <div className="flex items-center gap-4 mb-4">
              <div className="flex items-center">
                {[...Array(5)].map((_, i) => (
                  <Star
                    key={i}
                    className={`h-5 w-5 ${
                      i < Math.floor(product.rating) ? "text-yellow-400 fill-current" : "text-gray-300"
                    }`}
                  />
                ))}
              </div>
              <span className="text-gray-600">
                {product.rating} ({product.reviews} avis)
              </span>
            </div>
          </div>

          {/* Price */}
          <div className="flex items-center gap-4">
            <span className="text-3xl font-bold text-blue-600">{product.price}€</span>
            {product.originalPrice && (
              <span className="text-xl text-gray-500 line-through">{product.originalPrice}€</span>
            )}
          </div>

          {/* Description */}
          <p className="text-gray-600 leading-relaxed">{product.description}</p>

          {/* Size Selection */}
          {product.sizes && (
            <div>
              <h3 className="font-semibold mb-3">
                Taille <span className="text-red-500">*</span>
              </h3>
              <div className="flex flex-wrap gap-2">
                {product.sizes.map((size) => (
                  <Button
                    key={size}
                    variant={selectedSize === size ? "default" : "outline"}
                    size="sm"
                    onClick={() => setSelectedSize(size)}
                    className={selectedSize === size ? "ring-2 ring-blue-500" : ""}
                  >
                    {size}
                  </Button>
                ))}
              </div>
              {product.sizes.length > 0 && !selectedSize && (
                <p className="text-sm text-gray-500 mt-1">Sélection obligatoire</p>
              )}
            </div>
          )}

          {/* Color Selection */}
          {product.colors && (
            <div>
              <h3 className="font-semibold mb-3">
                Couleur <span className="text-red-500">*</span>
              </h3>
              <div className="flex flex-wrap gap-2">
                {product.colors.map((color) => (
                  <Button
                    key={color}
                    variant={selectedColor === color ? "default" : "outline"}
                    size="sm"
                    onClick={() => setSelectedColor(color)}
                    className={selectedColor === color ? "ring-2 ring-blue-500" : ""}
                  >
                    {color}
                  </Button>
                ))}
              </div>
              {product.colors.length > 0 && !selectedColor && (
                <p className="text-sm text-gray-500 mt-1">Sélection obligatoire</p>
              )}
            </div>
          )}

          {/* Quantity */}
          <div>
            <h3 className="font-semibold mb-3">Quantité</h3>
            <div className="flex items-center gap-2">
              <Button variant="outline" size="sm" onClick={() => setQuantity(Math.max(1, quantity - 1))}>
                -
              </Button>
              <span className="px-4 py-2 border rounded-md min-w-[60px] text-center">{quantity}</span>
              <Button variant="outline" size="sm" onClick={() => setQuantity(quantity + 1)}>
                +
              </Button>
            </div>
          </div>

          {/* Stock Status */}
          <div className="flex items-center gap-2">
            <div className={`w-3 h-3 rounded-full ${product.stock > 0 ? "bg-green-500" : "bg-red-500"}`} />
            <span className={product.stock > 0 ? "text-green-600" : "text-red-600"}>
              {product.stock > 0 ? `En stock (${product.stock} disponibles)` : "Rupture de stock"}
            </span>
          </div>

          {/* Actions */}
          <div className="flex gap-4">
            <Button onClick={handleAddToCart} disabled={product.stock === 0} className="flex-1">
              <ShoppingCart className="h-4 w-4 mr-2" />
              Ajouter au panier
            </Button>
            <Button variant="outline" size="icon">
              <Heart className="h-4 w-4" />
            </Button>
            <Button variant="outline" size="icon">
              <Share2 className="h-4 w-4" />
            </Button>
          </div>

          {/* Features */}
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 pt-6 border-t">
            <div className="flex items-center gap-2">
              <Truck className="h-5 w-5 text-blue-600" />
              <span className="text-sm">Livraison gratuite</span>
            </div>
            <div className="flex items-center gap-2">
              <Shield className="h-5 w-5 text-blue-600" />
              <span className="text-sm">Garantie 2 ans</span>
            </div>
            <div className="flex items-center gap-2">
              <RotateCcw className="h-5 w-5 text-blue-600" />
              <span className="text-sm">Retour 30 jours</span>
            </div>
          </div>
        </div>
      </div>

      {/* Product Details Tabs */}
      <div className="mt-16">
        <Tabs defaultValue="description" className="w-full">
          <TabsList className="grid w-full grid-cols-3">
            <TabsTrigger value="description">Description</TabsTrigger>
            <TabsTrigger value="specifications">Caractéristiques</TabsTrigger>
            <TabsTrigger value="reviews">Avis ({product.reviews})</TabsTrigger>
          </TabsList>

          <TabsContent value="description" className="mt-6">
            <Card>
              <CardContent className="p-6">
                <h3 className="font-semibold text-lg mb-4">Description détaillée</h3>
                <div className="prose max-w-none">
                  <p>{product.description}</p>
                  <p className="mt-4">
                    Ce produit de la marque {product.brand} est conçu pour offrir les meilleures performances dans la
                    catégorie {product.category}. Fabriqué avec des matériaux de haute qualité, il garantit durabilité
                    et confort d'utilisation.
                  </p>
                </div>
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="specifications" className="mt-6">
            <Card>
              <CardContent className="p-6">
                <h3 className="font-semibold text-lg mb-4">Caractéristiques techniques</h3>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                  <div>
                    <strong>Marque:</strong> {product.brand}
                  </div>
                  <div>
                    <strong>Catégorie:</strong> {product.category}
                  </div>
                  {product.sizes && (
                    <div>
                      <strong>Tailles disponibles:</strong> {product.sizes.join(", ")}
                    </div>
                  )}
                  {product.colors && (
                    <div>
                      <strong>Couleurs disponibles:</strong> {product.colors.join(", ")}
                    </div>
                  )}
                  <div>
                    <strong>Stock:</strong> {product.stock} unités
                  </div>
                  <div>
                    <strong>Note moyenne:</strong> {product.rating}/5
                  </div>
                </div>
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="reviews" className="mt-6">
            <ProductReviews productId={product.id} />
          </TabsContent>
        </Tabs>
      </div>
    </div>
  )
}
