export interface Product {
  id: number
  name: string
  description: string
  price: number
  originalPrice?: number
  image: string
  category: string
  brand: string
  rating: number
  reviews: number
  isNew?: boolean
  isSale?: boolean
  stock: number
  sizes?: string[]
  colors?: string[]
}

export interface CartItem {
  id: number
  name: string
  price: number
  image: string
  quantity: number
  size?: string
  color?: string
}
