// src/library.ts
import { db } from "./data.js";
import {
  Book,
  CreateBookInput,
  CreateMemberInput,
  Loan,
  Member
} from "./types.js";
import {
  ConflictError,
  NotFoundError,
  PermissionError,
  ValidationError
} from "./errors.js";
import { daysFromNow, generateId, now } from "./utils.js";

export class LibraryService {
  // === CREATE ===
  addBook(input: CreateBookInput): Book {
    if (!input.title?.trim()) throw new ValidationError("Title is required");
    if (!input.author?.trim()) throw new ValidationError("Author is required");

    const id = generateId("book");
    const total = Math.max(1, input.totalCopies ?? 1);

    // year bersifat optional → hanya set jika ada (agar cocok dgn exactOptionalPropertyTypes)
    const book: Book = {
      id,
      isbn: input.isbn,
      title: input.title.trim(),
      author: input.author.trim(),
      ...(input.year !== undefined ? { year: input.year } : {}),
      totalCopies: total,
      availableCopies: total
    };

    db.books.set(id, book);
    return book;
  }

  registerMember(input: CreateMemberInput): Member {
    if (!input.name?.trim()) throw new ValidationError("Name is required");
    if (!/^\S+@\S+\.\S+$/.test(input.email))
      throw new ValidationError("Invalid email");

    const id = generateId("mem");
    const member: Member = {
      id,
      name: input.name.trim(),
      email: input.email.toLowerCase(),
      joinedAt: now(),
      isActive: true,
      ...(input.maxLoans !== undefined ? { maxLoans: input.maxLoans } : {})
    };

    db.members.set(id, member);
    return member;
  }

  // === READ / SEARCH ===
  getBook(id: string): Book {
    const b = db.books.get(id);
    if (!b) throw new NotFoundError("Book", id);
    return b;
  }

  getMember(id: string): Member {
    const m = db.members.get(id);
    if (!m) throw new NotFoundError("Member", id);
    return m;
  }

  searchBooks(query: string): Book[] {
    const q = query.toLowerCase();
    return [...db.books.values()].filter(
      (b) =>
        b.title.toLowerCase().includes(q) ||
        b.author.toLowerCase().includes(q) ||
        b.isbn.toLowerCase().includes(q)
    );
  }

  // === LOANS ===
  checkoutBook(params: {
    bookId: string;
    memberId: string;
    days?: number; // default 7
  }): Loan {
    const book = this.getBook(params.bookId);
    const member = this.getMember(params.memberId);

    if (!member.isActive) throw new PermissionError("Member not active");
    if (book.availableCopies <= 0)
      throw new ConflictError("No available copies");

    const activeLoansForMember = [...db.loans.values()].filter(
      (l) => l.memberId === member.id && l.status !== "RETURNED"
    );
    if (activeLoansForMember.length >= (member.maxLoans ?? 3))
      throw new PermissionError("Loan limit exceeded");

    const loan: Loan = {
      id: generateId("loan"),
      bookId: book.id,
      memberId: member.id,
      loanedAt: now(),
      dueAt: daysFromNow(params.days ?? 7),
      status: "ONGOING"
    };

    // update stok
    book.availableCopies -= 1;
    db.books.set(book.id, book);
    db.loans.set(loan.id, loan);
    return loan;
  }

  returnBook(loanId: string): Loan {
    const loan = db.loans.get(loanId);
    if (!loan) throw new NotFoundError("Loan", loanId);
    if (loan.status === "RETURNED")
      throw new ConflictError("Loan already returned");

    const book = this.getBook(loan.bookId);

    // set returnedAt optional hanya jika ada
    const returnedAt = now();
    loan.returnedAt = returnedAt;
    loan.status = loan.dueAt < returnedAt ? "LATE" : "RETURNED";

    book.availableCopies = Math.min(
      book.totalCopies,
      book.availableCopies + 1
    );

    db.loans.set(loan.id, loan);
    db.books.set(book.id, book);
    return loan;
  }

  listLoans(filter?: { memberId?: string; status?: Loan["status"] }): Loan[] {
    let items = [...db.loans.values()];
    if (filter?.memberId)
      items = items.filter((l) => l.memberId === filter.memberId);
    if (filter?.status) items = items.filter((l) => l.status === filter.status);

    // perbarui flag 'LATE' on-the-fly
    const nowDate = now();
    for (const l of items) {
      if (l.status === "ONGOING" && l.dueAt < nowDate) l.status = "LATE";
    }
    return items;
  }
}
