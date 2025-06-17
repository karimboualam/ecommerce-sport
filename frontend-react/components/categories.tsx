import Link from "next/link"
import Image from "next/image"
import { Card, CardContent } from "@/components/ui/card"

const categories = [
  {
    name: "Running",
    image: "/images/category-running.jpg",
    link: "/products?category=running",
    description: "Chaussures et équipements de course",
  },
  {
    name: "Fitness",
    image: "/images/category-fitness.jpg",
    link: "/products?category=fitness",
    description: "Matériel de musculation et fitness",
  },
  {
    name: "Football",
    image: "/images/category-football.jpg",
    link: "/products?category=football",
    description: "Maillots, chaussures et accessoires",
  },
  {
    name: "Basketball",
    image: "/images/category-basketball.jpg",
    link: "/products?category=basketball",
    description: "Équipements de basketball",
  },
]

export default function Categories() {
  return (
    <section className="py-16 bg-gray-50">
      <div className="container mx-auto px-4">
        <div className="text-center mb-12">
          <h2 className="text-3xl font-bold mb-4">Nos Catégories</h2>
          <p className="text-gray-600 max-w-2xl mx-auto">
            Découvrez notre large gamme d'équipements sportifs pour tous vos besoins
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {categories.map((category) => (
            <Link key={category.name} href={category.link}>
              <Card className="group hover:shadow-lg transition-shadow cursor-pointer h-full">
                <CardContent className="p-0 flex flex-col h-full">
                  <div className="relative h-48 overflow-hidden rounded-t-lg">
                    <Image
                      src={category.image || "/placeholder.svg"}
                      alt={category.name}
                      fill
                      className="object-cover group-hover:scale-105 transition-transform duration-300"
                    />
                  </div>
                  <div className="p-4 flex flex-col flex-1">
                    <h3 className="font-semibold text-lg mb-2 min-h-[2rem] flex items-center">{category.name}</h3>
                    <p className="text-gray-600 text-sm flex-1">{category.description}</p>
                  </div>
                </CardContent>
              </Card>
            </Link>
          ))}
        </div>
      </div>
    </section>
  )
}
