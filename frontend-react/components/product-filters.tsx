"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Slider } from "@/components/ui/slider"
import { Star } from "lucide-react"

interface Filters {
  category: string
  priceRange: number[]
  brand: string
  rating: number
}

interface ProductFiltersProps {
  filters: Filters
  onFiltersChange: (filters: Filters) => void
}

const categories = [
  { value: "", label: "Toutes les catégories" },
  { value: "running", label: "Running" },
  { value: "fitness", label: "Fitness" },
  { value: "football", label: "Football" },
  { value: "basketball", label: "Basketball" },
]

const brands = [
  { value: "", label: "Toutes les marques" },
  { value: "Nike", label: "Nike" },
  { value: "Adidas", label: "Adidas" },
  { value: "Puma", label: "Puma" },
  { value: "Under Armour", label: "Under Armour" },
]

export default function ProductFilters({ filters, onFiltersChange }: ProductFiltersProps) {
  const updateFilter = (key: keyof Filters, value: any) => {
    onFiltersChange({ ...filters, [key]: value })
  }

  const resetFilters = () => {
    onFiltersChange({
      category: "",
      priceRange: [0, 500],
      brand: "",
      rating: 0,
    })
  }

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h2 className="text-lg font-semibold">Filtres</h2>
        <Button variant="outline" size="sm" onClick={resetFilters}>
          Réinitialiser
        </Button>
      </div>

      {/* Category Filter */}
      <Card>
        <CardHeader>
          <CardTitle className="text-base">Catégorie</CardTitle>
        </CardHeader>
        <CardContent className="space-y-2">
          {categories.map((category) => (
            <label key={category.value} className="flex items-center space-x-2 cursor-pointer">
              <input
                type="radio"
                name="category"
                value={category.value}
                checked={filters.category === category.value}
                onChange={(e) => updateFilter("category", e.target.value)}
                className="text-blue-600"
              />
              <span className="text-sm">{category.label}</span>
            </label>
          ))}
        </CardContent>
      </Card>

      {/* Price Range Filter */}
      <Card>
        <CardHeader>
          <CardTitle className="text-base">Prix</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="space-y-4">
            <Slider
              value={filters.priceRange}
              onValueChange={(value) => updateFilter("priceRange", value)}
              max={500}
              min={0}
              step={10}
              className="w-full"
            />
            <div className="flex justify-between text-sm text-gray-600">
              <span>{filters.priceRange[0]}€</span>
              <span>{filters.priceRange[1]}€</span>
            </div>
          </div>
        </CardContent>
      </Card>

      {/* Brand Filter */}
      <Card>
        <CardHeader>
          <CardTitle className="text-base">Marque</CardTitle>
        </CardHeader>
        <CardContent className="space-y-2">
          {brands.map((brand) => (
            <label key={brand.value} className="flex items-center space-x-2 cursor-pointer">
              <input
                type="radio"
                name="brand"
                value={brand.value}
                checked={filters.brand === brand.value}
                onChange={(e) => updateFilter("brand", e.target.value)}
                className="text-blue-600"
              />
              <span className="text-sm">{brand.label}</span>
            </label>
          ))}
        </CardContent>
      </Card>

      {/* Rating Filter */}
      <Card>
        <CardHeader>
          <CardTitle className="text-base">Note minimum</CardTitle>
        </CardHeader>
        <CardContent className="space-y-2">
          {[4, 3, 2, 1, 0].map((rating) => (
            <label key={rating} className="flex items-center space-x-2 cursor-pointer">
              <input
                type="radio"
                name="rating"
                value={rating}
                checked={filters.rating === rating}
                onChange={(e) => updateFilter("rating", Number(e.target.value))}
                className="text-blue-600"
              />
              <div className="flex items-center">
                {rating > 0 ? (
                  <>
                    {[...Array(5)].map((_, i) => (
                      <Star
                        key={i}
                        className={`h-4 w-4 ${i < rating ? "text-yellow-400 fill-current" : "text-gray-300"}`}
                      />
                    ))}
                    <span className="text-sm ml-1">et plus</span>
                  </>
                ) : (
                  <span className="text-sm">Toutes les notes</span>
                )}
              </div>
            </label>
          ))}
        </CardContent>
      </Card>
    </div>
  )
}
