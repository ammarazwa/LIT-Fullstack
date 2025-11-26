export const db = {
    books: new Map(),
    members: new Map(),
    loans: new Map()
};
export function seed() {
    const b1 = {
        id: "b1",
        isbn: "978-0-13-110362-7",
        title: "The C Programming Language",
        author: "Kernighan & Ritchie",
        year: 1988,
        totalCopies: 3,
        availableCopies: 3
    };
    const m1 = {
        id: "m1",
        name: "Nikita",
        email: "nikita@example.com",
        joinedAt: new Date("2024-01-10"),
        isActive: true,
        maxLoans: 3
    };
    db.books.set(b1.id, b1);
    db.members.set(m1.id, m1);
}
