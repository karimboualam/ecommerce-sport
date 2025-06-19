"use client"

import { useEffect, useState } from "react"
import { useParams } from "next/navigation"
import Image from "next/image"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Card, CardContent } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Star, ShoppingCart, Heart, Share2, Truck, Shield, RotateCcw } from "lucide-react"
import { useCart } from "@/contexts/cart-context"
import { useToast } from "@/hooks/use-toast"
import ProductReviews from "@/components/product-reviews"

export default function ProductPage() {
  const params = useParams()
  const productId = Number.parseInt(params.id as string)
  const { addItem } = useCart()
  const { toast } = useToast()

  const [product, setProduct] = useState<any | null>(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  const [selectedSize, setSelectedSize] = useState("")
  const [selectedColor, setSelectedColor] = useState("")
  const [quantity, setQuantity] = useState(1)

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const token = localStorage.getItem("token")
        const res = await fetch(`http://localhost:8080/api/client/articles/${productId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        })
        if (!res.ok) throw new Error("Produit non trouvé")

        const data = await res.json()
        setProduct({
          id: data.reference,
          name: data.nom,
          description: data.description,
          price: data.prix,
          originalPrice: data.prix * 1.15, // ou autre calcul
          image: data.image?.startsWith("http") ? data.image : `/images/${data.image}`,
          category: data.categorie,
          brand: data.marque,
          stock: data.stock,
          sizes: data.taille ? data.taille.split(",") : [],
          colors: data.couleur ? data.couleur.split(",") : [],
          rating: 4.5,
          reviews: 18,
        })
      } catch (err: any) {
        console.error(err)
        setError(err.message)
      } finally {
        setLoading(false)
      }
    }

    fetchProduct()
  }, [productId])

  const handleAddToCart = () => {
    const missingSelections = []
    if (product.sizes?.length > 0 && !selectedSize) missingSelections.push("la taille")
    if (product.colors?.length > 0 && !selectedColor) missingSelections.push("la couleur")

    if (missingSelections.length > 0) {
      toast({
        title: "Sélection incomplète",
        description: `Veuillez sélectionner ${missingSelections.join(" et ")}.`,
        variant: "destructive",
      })
      return
    }

    if (quantity < 1) {
      toast({
        title: "Quantité invalide",
        description: "Minimum 1 produit requis.",
        variant: "destructive",
      })
      return
    }

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

  if (loading) return <div className="text-center py-16">Chargement...</div>
  if (error || !product)
    return <div className="text-center py-16 text-red-600">Erreur : {error || "Produit non trouvé"}</div>

  return (
    <div className="container mx-auto px-4 py-8">
      {/* 🖼️ Image + infos */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-12">
        <div className="space-y-4">
          <div className="relative aspect-square overflow-hidden rounded-lg">
            <Image src={product.image || "/placeholder.svg"} alt={product.name} fill className="object-cover" />
            <div className="absolute top-4 left-4 flex flex-col gap-2">
              <Badge className="bg-green-500">Nouveau</Badge>
            </div>
          </div>
        </div>

        <div className="space-y-6">
          <h1 className="text-3xl font-bold mb-2">{product.name}</h1>
          <div className="flex items-center gap-4">
            {[...Array(5)].map((_, i) => (
              <Star
                key={i}
                className={`h-5 w-5 ${i < Math.floor(product.rating) ? "text-yellow-400 fill-current" : "text-gray-300"}`}
              />
            ))}
            <span className="text-gray-600">{product.rating} ({product.reviews} avis)</span>
          </div>

          <div className="flex gap-4">
            <span className="text-3xl font-bold text-blue-600">{product.price}€</span>
            {product.originalPrice && (
              <span className="text-xl text-gray-500 line-through">{product.originalPrice}€</span>
            )}
          </div>

          <p className="text-gray-600">{product.description}</p>

          {/* Tailles */}
          {product.sizes.length > 0 && (
            <div>
              <h3 className="font-semibold mb-2">Taille <span className="text-red-500">*</span></h3>
              <div className="flex gap-2 flex-wrap">
                {product.sizes.map((size: string) => (
                  <Button
                    key={size}
                    variant={selectedSize === size ? "default" : "outline"}
                    onClick={() => setSelectedSize(size)}
                  >
                    {size}
                  </Button>
                ))}
              </div>
            </div>
          )}

          {/* Couleurs */}
          {product.colors.length > 0 && (
            <div>
              <h3 className="font-semibold mb-2">Couleur <span className="text-red-500">*</span></h3>
              <div className="flex gap-2 flex-wrap">
                {product.colors.map((color: string) => (
                  <Button
                    key={color}
                    variant={selectedColor === color ? "default" : "outline"}
                    onClick={() => setSelectedColor(color)}
                  >
                    {color}
                  </Button>
                ))}
              </div>
            </div>
          )}

          {/* Quantité */}
          <div>
            <h3 className="font-semibold mb-2">Quantité</h3>
            <div className="flex items-center gap-2">
              <Button variant="outline" onClick={() => setQuantity(Math.max(1, quantity - 1))}>-</Button>
              <span className="border px-4 py-2 rounded">{quantity}</span>
              <Button variant="outline" onClick={() => setQuantity(quantity + 1)}>+</Button>
            </div>
          </div>

          {/* Stock */}
          <div className="flex items-center gap-2">
            <div className={`w-3 h-3 rounded-full ${product.stock > 0 ? "bg-green-500" : "bg-red-500"}`} />
            <span>{product.stock > 0 ? `En stock (${product.stock})` : "Rupture de stock"}</span>
          </div>

          {/* Boutons */}
          <div className="flex gap-4">
            <Button className="flex-1" disabled={product.stock === 0} onClick={handleAddToCart}>
              <ShoppingCart className="h-4 w-4 mr-2" /> Ajouter au panier
            </Button>
            <Button variant="outline"><Heart className="h-4 w-4" /></Button>
            <Button variant="outline"><Share2 className="h-4 w-4" /></Button>
          </div>
        </div>
      </div>

      {/* Détails bas */}
      <div className="mt-16">
        <Tabs defaultValue="description">
          <TabsList className="grid w-full grid-cols-3">
            <TabsTrigger value="description">Description</TabsTrigger>
            <TabsTrigger value="specifications">Caractéristiques</TabsTrigger>
            <TabsTrigger value="reviews">Avis ({product.reviews})</TabsTrigger>
          </TabsList>

          <TabsContent value="description" className="mt-6">
            <Card><CardContent className="p-6">{product.description}</CardContent></Card>
          </TabsContent>

          <TabsContent value="specifications" className="mt-6">
            <Card>
              <CardContent className="p-6">
                <ul className="space-y-2">
                  <li><strong>Marque :</strong> {product.brand}</li>
                  <li><strong>Catégorie :</strong> {product.category}</li>
                  <li><strong>Stock :</strong> {product.stock} unités</li>
                  <li><strong>Tailles :</strong> {product.sizes.join(", ")}</li>
                  <li><strong>Couleurs :</strong> {product.colors.join(", ")}</li>
                </ul>
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
