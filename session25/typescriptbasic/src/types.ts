type Person = {
  name: string;
  age: number;
  email: string;
};

interface Product {
  id: number;
  name: string;
  price: number;
  inStock: boolean;
}

function calculateTotal(products: Product[]): number {
  let total = 0;

  for (const p of products) {
    if (p.inStock) {
      total += p.price;
    }
  }

  return total;
}

function getAdults(people: Person[]): Person[] {
  const result: Person[] = [];

  for (const p of people) {
    if (p.age > 18) {
      result.push(p);
    }
  }

  return result;
}

const people: Person[] = [
  { name: 'Alice', age: 25, email: 'alice@example.com' },
  { name: 'Bob', age: 17, email: 'bob@example.com' },
  { name: 'Charlie', age: 30, email: 'charlie@example.com' },
  { name: 'Diana', age: 16, email: 'diana@example.com' },
  { name: 'Eve', age: 22, email: 'eve@example.com' }
];

const products: Product[] = [
  { id: 1, name: 'Laptop', price: 1200, inStock: true },
  { id: 2, name: 'Mouse', price: 25, inStock: true },
  { id: 3, name: 'Keyboard', price: 75, inStock: false },
  { id: 4, name: 'Monitor', price: 300, inStock: true },
  { id: 5, name: 'Headphones', price: 150, inStock: true }
];

console.log("Adults:", getAdults(people));
console.log("Total Price:", calculateTotal(products));
