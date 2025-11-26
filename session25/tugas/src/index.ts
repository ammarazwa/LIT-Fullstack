import { db, seed } from "./data.js";
import { LibraryService } from "./library.js";
import { AppError } from "./errors.js";

// --- Bootstrapping / Demo ---
seed(); // isi contoh awal
const lib = new LibraryService();

async function main() {
  try {
    // Tambah buku & member
    const newBook = lib.addBook({
      isbn: "978-1-59327-950-9",
      title: "Eloquent JavaScript",
      author: "Marijn Haverbeke",
      year: 2018,
      totalCopies: 4
    });

    const member = lib.registerMember({
      name: "Clarisya",
      email: "clarisya@unpad.ac.id",
      maxLoans: 5
    });

    console.log("Added book:", newBook);
    console.log("Registered member:", member);

    // Cari buku
    const results = lib.searchBooks("JavaScript");
    console.log("Search results:", results.map((b) => b.title));

    // Pinjam & kembalikan
    const loan = lib.checkoutBook({
      bookId: newBook.id,
      memberId: member.id,
      days: 10
    });
    console.log("Loan created:", loan);

    const allLoans = lib.listLoans({ memberId: member.id });
    console.log("Member loans:", allLoans);

    const returned = lib.returnBook(loan.id);
    console.log("Returned:", returned);

    console.log(
      "Available copies after return:",
      db.books.get(newBook.id)?.availableCopies
    );
  } catch (e) {
    if (e instanceof AppError) {
      console.error(`[${e.code}] ${e.message}`);
    } else {
      console.error(e);
    }
  }
}

main();
