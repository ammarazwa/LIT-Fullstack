"use strict";
// ---------- Type Guards ----------
function isEquity(s) {
    return s.kind === "equity";
}
function isCrypto(s) {
    return s.kind === "crypto";
}
function isETF(s) {
    return s.kind === "etf";
}
function isPending(t) {
    return t.state.status === "pending";
}
function isCompleted(t) {
    return t.state.status === "completed";
}
function isFailed(t) {
    return t.state.status === "failed";
}
function Ok(value) {
    return { ok: true, value };
}
function Err(error) {
    return { ok: false, error };
}
// ---------- Errors ----------
class InvalidStockError extends Error {
    constructor(message = "Invalid stock symbol") {
        super(message);
        this.name = "InvalidStockError";
    }
}
class InsufficientFundsError extends Error {
    constructor(message = "Insufficient funds") {
        super(message);
        this.name = "InsufficientFundsError";
    }
}
class InsufficientHoldingsError extends Error {
    constructor(message = "Insufficient holdings") {
        super(message);
        this.name = "InsufficientHoldingsError";
    }
}
// ---------- Async Operations ----------
// Mock: Replace with real API call if needed
const MOCK_PRICES = {
    AAPL: 212.34,
    MSFT: 428.91,
    NVDA: 131.12,
    BTC: 97345.0,
    ETH: 4150.2,
    VOO: 487.5,
};
async function fetchStockPrice(symbol) {
    await new Promise((r) => setTimeout(r, 250));
    const price = MOCK_PRICES[symbol.toUpperCase()];
    if (typeof price !== "number")
        throw new InvalidStockError(`Unknown ${symbol}`);
    return price;
}
function createPortfolio(owner, seedCash = 0) {
    return { owner, cash: seedCash, positions: [], history: [] };
}
function getPosition(p, symbol) {
    return p.positions.find((x) => x.symbol === symbol.toUpperCase());
}
function upsertPosition(p, stock) {
    const i = p.positions.findIndex((x) => x.symbol === stock.symbol);
    if (i >= 0)
        p.positions[i] = stock;
    else
        p.positions.push(stock);
}
function updatePosition(p, patch) {
    const existing = getPosition(p, patch.symbol);
    if (!existing)
        return Err(new InvalidStockError("Position not found"));
    const updated = { ...existing, ...patch };
    upsertPosition(p, updated);
    return Ok(updated);
}
async function buy(p, symbol, quantity) {
    const tx = {
        id: cryptoRandomId(),
        symbol: symbol.toUpperCase(),
        quantity,
        createdAt: new Date(),
        state: { status: "pending" },
    };
    p.history.push(tx);
    try {
        const price = await fetchStockPrice(tx.symbol);
        const cost = price * quantity;
        if (cost > p.cash) {
            const failed = {
                ...tx,
                state: { status: "failed", reason: "Insufficient funds" },
            };
            replaceTx(p, failed);
            return Err(new InsufficientFundsError());
        }
        p.cash -= cost;
        const existing = getPosition(p, tx.symbol);
        if (existing) {
            const newQty = existing.quantity + quantity;
            const newAvg = (existing.purchasePrice * existing.quantity + cost) / newQty;
            upsertPosition(p, { ...existing, quantity: newQty, purchasePrice: newAvg });
        }
        else {
            // default to equity if unknown kind; real apps should resolve metadata
            const newPos = {
                kind: "equity",
                symbol: tx.symbol,
                name: tx.symbol,
                quantity,
                purchasePrice: price,
                exchange: "UNKNOWN",
            };
            upsertPosition(p, newPos);
        }
        const completed = {
            ...tx,
            state: { status: "completed", fillPrice: price },
        };
        replaceTx(p, completed);
        return Ok(completed);
    }
    catch (e) {
        const failed = {
            ...tx,
            state: { status: "failed", reason: e.message },
        };
        replaceTx(p, failed);
        return Err(e);
    }
}
async function sell(p, symbol, quantity) {
    const tx = {
        id: cryptoRandomId(),
        symbol: symbol.toUpperCase(),
        quantity,
        createdAt: new Date(),
        state: { status: "pending" },
    };
    p.history.push(tx);
    const pos = getPosition(p, tx.symbol);
    if (!pos || pos.quantity < quantity) {
        const failed = {
            ...tx,
            state: { status: "failed", reason: "Insufficient holdings" },
        };
        replaceTx(p, failed);
        return Err(new InsufficientHoldingsError());
    }
    try {
        const price = await fetchStockPrice(tx.symbol);
        const proceeds = price * quantity;
        p.cash += proceeds;
        const remaining = pos.quantity - quantity;
        if (remaining === 0) {
            p.positions = p.positions.filter((x) => x.symbol !== pos.symbol);
        }
        else {
            upsertPosition(p, { ...pos, quantity: remaining });
        }
        const completed = {
            ...tx,
            state: { status: "completed", fillPrice: price },
        };
        replaceTx(p, completed);
        return Ok(completed);
    }
    catch (e) {
        const failed = {
            ...tx,
            state: { status: "failed", reason: e.message },
        };
        replaceTx(p, failed);
        return Err(e);
    }
}
function replaceTx(p, next) {
    const idx = p.history.findIndex((t) => t.id === next.id);
    if (idx >= 0)
        p.history[idx] = next;
}
async function positionSummary(p, symbol) {
    const pos = getPosition(p, symbol);
    if (!pos)
        return Err(new InvalidStockError("Position not found"));
    const price = await fetchStockPrice(pos.symbol);
    return Ok({ symbol: pos.symbol, name: pos.name, quantity: pos.quantity, marketValue: price * pos.quantity });
}
async function totalMarketValue(p) {
    let total = 0;
    for (const pos of p.positions) {
        total += (await fetchStockPrice(pos.symbol)) * pos.quantity;
    }
    return total;
}
// ---------- Demo (can be removed in production) ----------
async function demo() {
    const pf = createPortfolio("Team", 100000);
    await buy(pf, "AAPL", 100);
    await buy(pf, "VOO", 50);
    await sell(pf, "AAPL", 20);
    updatePosition(pf, { symbol: "VOO", name: "Vanguard S&P 500 ETF" });
    const sum = await positionSummary(pf, "AAPL");
    if (sum.ok)
        console.log("AAPL:", sum.value);
    const mv = await totalMarketValue(pf);
    console.log({ cash: pf.cash.toFixed(2), marketValue: mv.toFixed(2) });
    for (const t of pf.history) {
        if (isCompleted(t))
            console.log(`TX ${t.id} completed @ ${t.state.fillPrice}`);
        if (isFailed(t))
            console.log(`TX ${t.id} failed: ${t.state.reason}`);
    }
}
// Only run demo when executed directly in Node (not when imported)
if (typeof require !== "undefined" && typeof module !== "undefined" && require.main === module) {
    demo();
}
// ---------- Utilities ----------
function cryptoRandomId(len = 12) {
    if (typeof crypto !== "undefined" && "getRandomValues" in crypto) {
        const arr = new Uint8Array(len);
        crypto.getRandomValues(arr);
        return Array.from(arr, (b) => b.toString(16).padStart(2, "0")).join("").slice(0, len);
    }
    return Math.random().toString(36).slice(2, 2 + len);
}
//# sourceMappingURL=activity-3.js.map