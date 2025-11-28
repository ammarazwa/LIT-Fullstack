"use strict";
//testing
const draftProduct = {
    name: "Draft Item",
};
const preview = {
    id: "p001",
    name: "Laptop",
    price: 15000000,
};
const noStock = {
    id: "p002",
    name: "Mechanical Keyboard",
    price: 800000,
    description: "RGB hot-swappable keyboard",
};
const orderStatus = {
    pending: "Order received, waiting to process",
    shipped: "Order is on the way",
    delivered: "Order delivered to customer",
};
console.log("Partial Product:", draftProduct);
console.log("Preview:", preview);
console.log("Without Stock:", noStock);
console.log("Status Map:", orderStatus);
//# sourceMappingURL=utility-types.js.map