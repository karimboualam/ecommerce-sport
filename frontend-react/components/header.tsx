"use client"

import { useState } from "react"
import Link from "next/link"
import { useRouter } from "next/navigation"
import { Search, ShoppingCart, User, Menu, X } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Badge } from "@/components/ui/badge"
import { useCart } from "@/contexts/cart-context"
import { useAuth } from "@/contexts/auth-context"
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"

export default function Header() {
  const [isMenuOpen, setIsMenuOpen] = useState(false)
  const [searchInput, setSearchInput] = useState("")
  const router = useRouter()
  const { items } = useCart()
  const { user, logout } = useAuth()

  const itemCount = items.reduce((sum, item) => sum + item.quantity, 0)

  const handleSearchInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value
    setSearchInput(value)
    router.push(`/products?search=${encodeURIComponent(value)}`)
  }

  return (
    <header className="sticky top-0 z-50 w-full border-b bg-white">
      <div className="container mx-auto px-4">
        <div className="flex h-16 items-center justify-between">
          {/* Logo */}
          <Link href="/" className="flex items-center space-x-2">
            <div className="h-8 w-8 bg-blue-600 rounded-full flex items-center justify-center">
              <span className="text-white font-bold text-sm">SZ</span>
            </div>
            <span className="font-bold text-xl">SportZ</span>
          </Link>

          {/* Search Bar */}
          <div className="hidden md:flex flex-1 max-w-sm mx-6">
            <div className="relative w-full">
              <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-4 w-4" />
              <Input
                placeholder="Rechercher un produit..."
                className="pl-10"
                value={searchInput}
                onChange={handleSearchInputChange}
              />
            </div>
          </div>

          {/* Actions */}
          <div className="flex items-center space-x-4">
            {/* User Menu */}
            {user ? (
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <Button variant="ghost" size="sm">
                    <User className="h-4 w-4" />
                    <span className="hidden sm:ml-2 sm:inline">Bonjour, {user.prenom}</span>
                  </Button>
                </DropdownMenuTrigger>
                <DropdownMenuContent align="end">
                  <DropdownMenuItem asChild>
                    <Link href="/profile">Mon Profil</Link>
                  </DropdownMenuItem>
                  <DropdownMenuItem asChild>
                    <Link href="/orders">Mes Commandes</Link>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={logout}>Déconnexion</DropdownMenuItem>
                </DropdownMenuContent>
              </DropdownMenu>
            ) : (
              <Button variant="ghost" size="sm" asChild>
                <Link href="/auth">
                  <User className="h-4 w-4" />
                  <span className="hidden sm:ml-2 sm:inline">Connexion</span>
                </Link>
              </Button>
            )}

            {/* Cart */}
            <Button variant="ghost" size="sm" asChild className="relative">
              <Link href="/cart">
                <ShoppingCart className="h-4 w-4" />
                {itemCount > 0 && (
                  <Badge className="absolute -top-2 -right-2 h-5 w-5 rounded-full p-0 flex items-center justify-center text-xs">
                    {itemCount}
                  </Badge>
                )}
                <span className="hidden sm:ml-2 sm:inline">Panier</span>
              </Link>
            </Button>

            {/* Mobile Menu */}
            <Button
              variant="ghost"
              size="sm"
              className="md:hidden"
              onClick={() => setIsMenuOpen(!isMenuOpen)}
            >
              {isMenuOpen ? <X className="h-4 w-4" /> : <Menu className="h-4 w-4" />}
            </Button>
          </div>
        </div>

        {/* Mobile Navigation */}
        {isMenuOpen && (
          <div className="md:hidden border-t py-4">
            <nav className="flex flex-col space-y-4 px-4">
              <Input
                placeholder="Rechercher un produit..."
                value={searchInput}
                onChange={handleSearchInputChange}
              />
            </nav>
          </div>
        )}
      </div>
    </header>
  )
}
