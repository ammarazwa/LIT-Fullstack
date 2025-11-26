export const now = () => new Date();

export const daysFromNow = (d: number) => {
  const n = new Date();
  n.setDate(n.getDate() + d);
  return n;
};

export function assert(condition: unknown, msg: string): asserts condition {
  if (!condition) throw new Error(msg);
}

export function generateId(prefix: string = "id"): string {
  return `${prefix}_${Math.random().toString(36).slice(2, 10)}`;
}
