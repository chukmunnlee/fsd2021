export interface LineItem {
	description: string
	quantity: number
	unitPrice: number
}

export interface PurchaseOrder {
	orderId?: number
	name: string
	email: string
	lineItems: LineItem[]
}

export interface PostPurchaseOrderResult {
	orderId: number
}

export interface PurchaseOrderTotal {
	orderId?: number
	name: string
	total: number
}
