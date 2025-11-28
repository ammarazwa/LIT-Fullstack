"use strict";
class ApiError extends Error {
    constructor(message) {
        super(message);
        this.name = "ApiError";
    }
}
class ValidationError extends ApiError {
    constructor(message) {
        super(message);
        this.name = "ValidationError";
    }
}
class NotFoundError extends ApiError {
    constructor(message) {
        super(message);
        this.name = "NotFoundError";
    }
}
const mockProducts = [
    { id: 1, name: "Laptop Asus Zenbook", price: 15000000 },
    { id: 2, name: "Logitech Wireless Mouse", price: 350000 },
];
async function fetchProduct(id) {
    await new Promise((res) => setTimeout(res, 700));
    if (id < 0)
        throw new ValidationError("Product ID must be positive");
    const product = mockProducts.find((p) => p.id === id);
    if (!product)
        throw new NotFoundError(`Product with id ${id} not found`);
    if (Math.random() < 0.1)
        throw new ApiError("Random API failure occurred");
    return product;
}
function isValidationError(err) {
    return err instanceof ValidationError;
}
function isNotFoundError(err) {
    return err instanceof NotFoundError;
}
function isApiError(err) {
    return err instanceof ApiError;
}
async function safeApiCall(fn) {
    try {
        const data = await fn();
        return { success: true, data };
    }
    catch (err) {
        if (isApiError(err))
            return { success: false, error: err };
        return { success: false, error: new ApiError("Unknown error") };
    }
}
async function test() {
    console.log("\n=== TEST 1: Fetch valid product ===");
    console.log(await safeApiCall(() => fetchProduct(1)));
    console.log("\n=== TEST 2: Fetch invalid ID (validation error) ===");
    console.log(await safeApiCall(() => fetchProduct(-5)));
    console.log("\n=== TEST 3: Fetch non-existent product ===");
    console.log(await safeApiCall(() => fetchProduct(99)));
    console.log("\n=== TEST 4: Random API Error Chance ===");
    console.log(await safeApiCall(() => fetchProduct(2)));
}
test();
//# sourceMappingURL=api.js.map