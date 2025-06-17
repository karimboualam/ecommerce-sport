"use client"

import { useState, useEffect } from "react"
import { useSearchParams } from "next/navigation"
import ProductCard from "@/components/product-card"
import ProductFilters from "@/components/product-filters"
import { products } from "@/data/products"
import { Button } from "@/components/ui/button"
import { Grid, List } from "lucide-react"

export default function ProductsPage() {
  const searchParams = useSearchParams()
  const [filteredProducts, setFilteredProducts] = useState(products)
  const [viewMode, setViewMode] = useState<"grid" | "list">("grid")
  const [sortBy, setSortBy] = useState("name")
  const [filters, setFilters] = useState({
    category: searchParams.get("category") || "",
    priceRange: [0, 500],
    brand: "",
    rating: 0,
  })

  useEffect(() => {
    let filtered = products

    // Filter by category
    if (filters.category) {
      filtered = filtered.filter((product) => product.category === filters.category)
    }

    // Filter by price range
    filtered = filtered.filter(
      (product) => product.price >= filters.priceRange[0] && product.price <= filters.priceRange[1],
    )

    // Filter by brand
    if (filters.brand) {
      filtered = filtered.filter((product) => product.brand === filters.brand)
    }

    // Filter by rating
    if (filters.rating > 0) {
      filtered = filtered.filter((product) => product.rating >= filters.rating)
    }

    // Sort products
    filtered.sort((a, b) => {
      switch (sortBy) {
        case "price-asc":
          return a.price - b.price
        case "price-desc":
          return b.price - a.price
        case "rating":
          return b.rating - a.rating
        case "name":
        default:
          return a.name.localeCompare(b.name)
      }
    })

    setFilteredProducts(filtered)
  }, [filters, sortBy])

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="flex flex-col lg:flex-row gap-8">
        {/* Filters Sidebar */}
        <div className="lg:w-1/4">
          <ProductFilters filters={filters} onFiltersChange={setFilters} />
        </div>

        {/* Products Grid */}
        <div className="lg:w-3/4">
          {/* Header */}
          <div className="flex items-center justify-between mb-6">
            <div>
              <h1 className="text-2xl font-bold">Produits {filters.category && `- ${filters.category}`}</h1>
              <p className="text-gray-600">{filteredProducts.length} produit(s) trouvé(s)</p>
            </div>

            <div className="flex items-center gap-4">
              {/* Sort */}
              <select
                value={sortBy}
                onChange={(e) => setSortBy(e.target.value)}
                className="border rounded-md px-3 py-2"
              >
                <option value="name">Nom A-Z</option>
                <option value="price-asc">Prix croissant</option>
                <option value="price-desc">Prix décroissant</option>
                <option value="rating">Mieux notés</option>
              </select>

              {/* View Mode */}
              <div className="flex border rounded-md">
                <Button
                  variant={viewMode === "grid" ? "default" : "ghost"}
                  size="sm"
                  onClick={() => setViewMode("grid")}
                >
                  <Grid className="h-4 w-4" />
                </Button>
                <Button
                  variant={viewMode === "list" ? "default" : "ghost"}
                  size="sm"
                  onClick={() => setViewMode("list")}
                >
                  <List className="h-4 w-4" />
                </Button>
              </div>
            </div>
          </div>

          {/* Products */}
          <div className={viewMode === "grid" ? "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6" : "space-y-4"}>
            {filteredProducts.map((product) => (
              <ProductCard key={product.id} product={product} viewMode={viewMode} />
            ))}
          </div>

          {filteredProducts.length === 0 && (
            <div className="text-center py-12">
              <p className="text-gray-500 text-lg">Aucun produit trouvé avec ces critères</p>
            </div>
          )}
        </div>
      </div>
    </div>
  )
}
