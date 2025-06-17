import Link from "next/link"
import { Facebook, Twitter, Instagram, Youtube } from "lucide-react"

export default function Footer() {
  return (
    <footer className="bg-gray-900 text-white">
      <div className="container mx-auto px-4 py-12">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          {/* Company Info */}
          <div>
            <div className="flex items-center space-x-2 mb-4">
              <div className="h-8 w-8 bg-blue-600 rounded-full flex items-center justify-center">
                <span className="text-white font-bold text-sm">SZ</span>
              </div>
              <span className="font-bold text-xl">SportZone</span>
            </div>
            <p className="text-gray-400 mb-4">
              Votre boutique en ligne spécialisée dans les équipements sportifs de qualité.
            </p>
            <div className="flex space-x-4">
              <Link href="#" className="text-gray-400 hover:text-white">
                <Facebook className="h-5 w-5" />
              </Link>
              <Link href="#" className="text-gray-400 hover:text-white">
                <Twitter className="h-5 w-5" />
              </Link>
              <Link href="#" className="text-gray-400 hover:text-white">
                <Instagram className="h-5 w-5" />
              </Link>
              <Link href="#" className="text-gray-400 hover:text-white">
                <Youtube className="h-5 w-5" />
              </Link>
            </div>
          </div>

          {/* Products */}
          <div>
            <h3 className="font-semibold text-lg mb-4">Produits</h3>
            <ul className="space-y-2">
              <li>
                <Link href="/products?category=running" className="text-gray-400 hover:text-white">
                  Running
                </Link>
              </li>
              <li>
                <Link href="/products?category=fitness" className="text-gray-400 hover:text-white">
                  Fitness
                </Link>
              </li>
              <li>
                <Link href="/products?category=football" className="text-gray-400 hover:text-white">
                  Football
                </Link>
              </li>
              <li>
                <Link href="/products?category=basketball" className="text-gray-400 hover:text-white">
                  Basketball
                </Link>
              </li>
            </ul>
          </div>

          {/* Customer Service */}
          <div>
            <h3 className="font-semibold text-lg mb-4">Service Client</h3>
            <ul className="space-y-2">
              <li>
                <Link href="/contact" className="text-gray-400 hover:text-white">
                  Nous contacter
                </Link>
              </li>
              <li>
                <Link href="/shipping" className="text-gray-400 hover:text-white">
                  Livraison
                </Link>
              </li>
              <li>
                <Link href="/returns" className="text-gray-400 hover:text-white">
                  Retours
                </Link>
              </li>
              <li>
                <Link href="/faq" className="text-gray-400 hover:text-white">
                  FAQ
                </Link>
              </li>
            </ul>
          </div>

          {/* Legal */}
          <div>
            <h3 className="font-semibold text-lg mb-4">Informations</h3>
            <ul className="space-y-2">
              <li>
                <Link href="/about" className="text-gray-400 hover:text-white">
                  À propos
                </Link>
              </li>
              <li>
                <Link href="/privacy" className="text-gray-400 hover:text-white">
                  Confidentialité
                </Link>
              </li>
              <li>
                <Link href="/terms" className="text-gray-400 hover:text-white">
                  Conditions d'utilisation
                </Link>
              </li>
              <li>
                <Link href="/careers" className="text-gray-400 hover:text-white">
                  Carrières
                </Link>
              </li>
            </ul>
          </div>
        </div>

        <hr className="border-gray-800 my-8" />

        <div className="flex flex-col md:flex-row justify-between items-center">
          <p className="text-gray-400 text-sm">© {new Date().getFullYear()} SportZone. Tous droits réservés.</p>
          <div className="flex space-x-4 mt-4 md:mt-0">
            <span className="text-gray-400 text-sm">Paiement sécurisé</span>
            <span className="text-gray-400 text-sm">Livraison gratuite dès 50€</span>
          </div>
        </div>
      </div>
    </footer>
  )
}
