"use client"

import { useAuth } from "@/contexts/auth-context"
import Hero from "@/components/hero"
import FeaturedProducts from "@/components/featured-products"
import Categories from "@/components/categories"
import Newsletter from "@/components/newsletter"

export default function HomePage() {
  const { user } = useAuth()

  return (
    <div className="min-h-screen">
      {/*  Message de bienvenue personnalisé */}
      {user && (
        <div className="bg-blue-50 text-blue-800 text-center py-2 font-medium">
          Bonjour, {user.prenom} 👋
        </div>
      )}

      <Hero />
      <Categories />
      <FeaturedProducts />
      <Newsletter />
    </div>
  )
}
