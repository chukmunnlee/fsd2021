export interface Registration {
  name: string
  email: string
  gender: string
  hobbies: boolean[]
}

export interface LineItem {
  description: string
  quantity: number
}
export interface Order {
  name: string
  address: string
  phone: string
  delivery: string
  comments: string
  lineItems: LineItem[]
}
