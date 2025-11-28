interface Product {
  id: string;
  name: string;
  price: number;
  description: string;
  stock: number;
}

type PartialProduct = Partial<Product>;
type ProductPreview = Pick<Product, "id" | "name" | "price">;
type ProductWithoutStock = Omit<Product, "stock">;
type StatusMap = Record<"pending" | "shipped" | "delivered", string>;

//testing
const draftProduct: PartialProduct = {
  name: "Draft Item",
};

const preview: ProductPreview = {
  id: "p001",
  name: "Laptop",
  price: 15000000,
};

const noStock: ProductWithoutStock = {
  id: "p002",
  name: "Mechanical Keyboard",
  price: 800000,
  description: "RGB hot-swappable keyboard",
};

const orderStatus: StatusMap = {
  pending: "Order received, waiting to process",
  shipped: "Order is on the way",
  delivered: "Order delivered to customer",
};

console.log("Partial Product:", draftProduct);
console.log("Preview:", preview);
console.log("Without Stock:", noStock);
console.log("Status Map:", orderStatus);
