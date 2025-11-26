export class AppError extends Error {
    code;
    constructor(message, code) {
        super(message);
        this.code = code;
        this.name = new.target.name;
    }
}
export class NotFoundError extends AppError {
    constructor(entity, id) {
        super(`${entity} with id "${id}" not found`, "NOT_FOUND");
    }
}
export class ValidationError extends AppError {
    constructor(message) {
        super(message, "VALIDATION");
    }
}
export class ConflictError extends AppError {
    constructor(message) {
        super(message, "CONFLICT");
    }
}
export class PermissionError extends AppError {
    constructor(message) {
        super(message, "PERMISSION");
    }
}
