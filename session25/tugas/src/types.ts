export type ISBN = `${number}-${number}-${number}-${number}-${number}` | string;

export interface Book {
  id: string;
  isbn: ISBN;
  title: string;
  author: string;
  year?: number;
  totalCopies: number;
  availableCopies: number;
}

export interface Member {
  id: string;
  name: string;
  email: string;
  joinedAt: Date;
  isActive: boolean;
  maxLoans?: number; // default 3
}

export interface Loan {
  id: string;
  bookId: Book["id"];
  memberId: Member["id"];
  loanedAt: Date;
  dueAt: Date;
  returnedAt?: Date;
  status: "ONGOING" | "LATE" | "RETURNED";
}

export interface CreateBookInput {
  isbn: ISBN;
  title: string;
  author: string;
  year?: number;
  totalCopies?: number; // default 1
}

export interface CreateMemberInput {
  name: string;
  email: string;
  maxLoans?: number;
}
