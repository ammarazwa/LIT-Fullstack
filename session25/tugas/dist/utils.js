export const now = () => new Date();
export const daysFromNow = (d) => {
    const n = new Date();
    n.setDate(n.getDate() + d);
    return n;
};
export function assert(condition, msg) {
    if (!condition)
        throw new Error(msg);
}
export function generateId(prefix = "id") {
    return `${prefix}_${Math.random().toString(36).slice(2, 10)}`;
}
