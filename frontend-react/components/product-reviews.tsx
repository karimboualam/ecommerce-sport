"use client"

import type React from "react"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Textarea } from "@/components/ui/textarea"
import { Star, ThumbsUp } from "lucide-react"
import { useState } from "react"

interface Review {
  id: number
  author: string
  rating: number
  date: string
  title: string
  content: string
  helpful: number
}

const mockReviews: Review[] = [
  {
    id: 1,
    author: "Marie L.",
    rating: 5,
    date: "2024-01-15",
    title: "Excellent produit !",
    content:
      "Très satisfaite de cet achat. La qualité est au rendez-vous et le confort exceptionnel. Je recommande vivement !",
    helpful: 12,
  },
  {
    id: 2,
    author: "Pierre M.",
    rating: 4,
    date: "2024-01-10",
    title: "Bon rapport qualité-prix",
    content:
      "Produit conforme à mes attentes. Livraison rapide et emballage soigné. Petit bémol sur la taille qui taille un peu grand.",
    helpful: 8,
  },
  {
    id: 3,
    author: "Sophie D.",
    rating: 5,
    date: "2024-01-05",
    title: "Parfait pour le sport",
    content: "Utilisé pour mes séances de running, ce produit tient toutes ses promesses. Très confortable et durable.",
    helpful: 15,
  },
]

interface ProductReviewsProps {
  productId: number
}

export default function ProductReviews({ productId }: ProductReviewsProps) {
  const [newReview, setNewReview] = useState({
    rating: 5,
    title: "",
    content: "",
  })

  const handleSubmitReview = (e: React.FormEvent) => {
    e.preventDefault()
    // Here you would typically send the review to your API
    console.log("New review:", newReview)
    // Reset form
    setNewReview({ rating: 5, title: "", content: "" })
  }

  return (
    <div className="space-y-6">
      {/* Reviews Summary */}
      <Card>
        <CardHeader>
          <CardTitle>Avis clients</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <div className="flex items-center gap-2 mb-2">
                <span className="text-3xl font-bold">4.5</span>
                <div className="flex">
                  {[...Array(5)].map((_, i) => (
                    <Star key={i} className={`h-5 w-5 ${i < 4 ? "text-yellow-400 fill-current" : "text-gray-300"}`} />
                  ))}
                </div>
              </div>
              <p className="text-gray-600">Basé sur {mockReviews.length} avis</p>
            </div>

            <div className="space-y-2">
              {[5, 4, 3, 2, 1].map((stars) => (
                <div key={stars} className="flex items-center gap-2">
                  <span className="text-sm w-8">{stars}★</span>
                  <div className="flex-1 bg-gray-200 rounded-full h-2">
                    <div
                      className="bg-yellow-400 h-2 rounded-full"
                      style={{ width: `${stars === 5 ? 60 : stars === 4 ? 30 : 10}%` }}
                    />
                  </div>
                  <span className="text-sm text-gray-600 w-8">{stars === 5 ? "60%" : stars === 4 ? "30%" : "10%"}</span>
                </div>
              ))}
            </div>
          </div>
        </CardContent>
      </Card>

      {/* Add Review Form */}
      <Card>
        <CardHeader>
          <CardTitle>Laisser un avis</CardTitle>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmitReview} className="space-y-4">
            <div>
              <label className="block text-sm font-medium mb-2">Note</label>
              <div className="flex gap-1">
                {[1, 2, 3, 4, 5].map((star) => (
                  <button
                    key={star}
                    type="button"
                    onClick={() => setNewReview((prev) => ({ ...prev, rating: star }))}
                    className="p-1"
                  >
                    <Star
                      className={`h-6 w-6 ${
                        star <= newReview.rating ? "text-yellow-400 fill-current" : "text-gray-300"
                      }`}
                    />
                  </button>
                ))}
              </div>
            </div>

            <div>
              <label htmlFor="title" className="block text-sm font-medium mb-2">
                Titre de l'avis
              </label>
              <input
                id="title"
                type="text"
                value={newReview.title}
                onChange={(e) => setNewReview((prev) => ({ ...prev, title: e.target.value }))}
                className="w-full px-3 py-2 border border-gray-300 rounded-md"
                placeholder="Résumez votre expérience"
              />
            </div>

            <div>
              <label htmlFor="content" className="block text-sm font-medium mb-2">
                Votre avis
              </label>
              <Textarea
                id="content"
                value={newReview.content}
                onChange={(e) => setNewReview((prev) => ({ ...prev, content: e.target.value }))}
                placeholder="Partagez votre expérience avec ce produit"
                rows={4}
              />
            </div>

            <Button type="submit">Publier l'avis</Button>
          </form>
        </CardContent>
      </Card>

      {/* Reviews List */}
      <div className="space-y-4">
        {mockReviews.map((review) => (
          <Card key={review.id}>
            <CardContent className="p-6">
              <div className="flex justify-between items-start mb-4">
                <div>
                  <div className="flex items-center gap-2 mb-1">
                    <span className="font-semibold">{review.author}</span>
                    <div className="flex">
                      {[...Array(5)].map((_, i) => (
                        <Star
                          key={i}
                          className={`h-4 w-4 ${i < review.rating ? "text-yellow-400 fill-current" : "text-gray-300"}`}
                        />
                      ))}
                    </div>
                  </div>
                  <p className="text-sm text-gray-600">{new Date(review.date).toLocaleDateString("fr-FR")}</p>
                </div>
              </div>

              <h4 className="font-semibold mb-2">{review.title}</h4>
              <p className="text-gray-700 mb-4">{review.content}</p>

              <div className="flex items-center gap-4">
                <Button variant="ghost" size="sm">
                  <ThumbsUp className="h-4 w-4 mr-1" />
                  Utile ({review.helpful})
                </Button>
              </div>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  )
}
