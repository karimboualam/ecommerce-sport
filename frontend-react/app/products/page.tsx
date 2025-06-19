"use client"

import { useEffect, useState } from "react"
import { useSearchParams } from "next/navigation"
import ProductCard from "@/components/product-card"
import { Button } from "@/components/ui/button"
import { Grid, List } from "lucide-react"

export default function ProductsPage() {
  const searchParams = useSearchParams()
  const [searchKeyword, setSearchKeyword] = useState("")
  const [categoryFilter, setCategoryFilter] = useState("")
  const [products, setProducts] = useState<any[]>([])
  const [filteredProducts, setFilteredProducts] = useState<any[]>([])
  const [viewMode, setViewMode] = useState<"grid" | "list">("grid")
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  // 🔍 Met à jour les filtres à partir de l'URL
  useEffect(() => {
    const keyword = searchParams.get("search") || ""
    const category = searchParams.get("category") || ""
    setSearchKeyword(keyword.toLowerCase())
    setCategoryFilter(category.toLowerCase())
  }, [searchParams])

  // 🔁 Récupération des articles
  useEffect(() => {
    const token = localStorage.getItem("token")

    fetch("http://localhost:8080/api/client/articles", {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        if (!res.ok) throw new Error("Erreur HTTP : " + res.status)
        return res.json()
      })
      .then((data) => {
        if (!Array.isArray(data)) throw new Error("La réponse n’est pas un tableau")

        const transformed = data.map((article: any) => ({
          id: article.reference,
          name: article.nom,
          description: article.description,
          price: article.prix,
          image: article.image?.startsWith("http") ? article.image : `/images/${article.image}`,
          category: article.categorie,
          brand: article.marque,
          stock: article.stock,
          sizes: article.taille ? article.taille.split(",") : [],
          colors: article.couleur ? article.couleur.split(",") : [],
          rating: 4.5,
          reviews: 20,
        }))

        setProducts(transformed)
        setLoading(false)
      })
      .catch((err) => {
        console.error("Erreur chargement des articles :", err)
        setError(err.message)
        setLoading(false)
      })
  }, [])

  // 🧠 Filtrage combiné : par mot-clé et par catégorie
  useEffect(() => {
    const filtered = products.filter((p) => {
      const matchKeyword =
        p.name.toLowerCase().includes(searchKeyword) ||
        p.description.toLowerCase().includes(searchKeyword) ||
        p.brand.toLowerCase().includes(searchKeyword)

      const matchCategory = categoryFilter ? p.category.toLowerCase() === categoryFilter : true

      return matchKeyword && matchCategory
    })

    setFilteredProducts(filtered)
  }, [products, searchKeyword, categoryFilter])

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Produits</h1>
        <div className="flex gap-2">
          <Button variant={viewMode === "grid" ? "default" : "ghost"} onClick={() => setViewMode("grid")}>
            <Grid className="h-4 w-4" />
          </Button>
          <Button variant={viewMode === "list" ? "default" : "ghost"} onClick={() => setViewMode("list")}>
            <List className="h-4 w-4" />
          </Button>
        </div>
      </div>

      {loading && (
        <div className="text-center py-16">
          <p>Chargement des produits...</p>
        </div>
      )}

      {error && !loading && (
        <div className="text-center py-12 text-red-500">
          <p>Erreur : {error}</p>
        </div>
      )}

      {!loading && filteredProducts.length > 0 && (
        <div className={viewMode === "grid" ? "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6" : "space-y-4"}>
          {filteredProducts.map((product) => (
            <ProductCard key={product.id} product={product} viewMode={viewMode} />
          ))}
        </div>
      )}

      {!loading && filteredProducts.length === 0 && !error && (
        <div className="text-center py-12">
          <p className="text-gray-500 text-lg">Aucun produit trouvé.</p>
        </div>
      )}
    </div>
  )
}
