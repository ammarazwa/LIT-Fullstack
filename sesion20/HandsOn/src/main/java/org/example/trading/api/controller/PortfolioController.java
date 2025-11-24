package org.example.trading.api.controller;

@RestController
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    // GET - List all portfolios (with pagination)
    @GetMapping
    public ResponseEntity<PagedResponse<Portfolio>> getAllPortfolios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        PagedResponse<Portfolio> portfolios = portfolioService.getPortfolios(page, size, sortBy);
        return ResponseEntity.ok(portfolios);
    }

    // GET - Retrieve specific portfolio by ID
    @GetMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable Long portfolioId) {
        Portfolio portfolio = portfolioService.findById(portfolioId);
        return ResponseEntity.ok(portfolio);
    }

    // POST - Create new portfolio
    @PostMapping
    public ResponseEntity<Portfolio> createPortfolio(@Valid @RequestBody CreatePortfolioRequest request) {
        Portfolio portfolio = portfolioService.createPortfolio(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/portfolios/" + portfolio.getId())
                .body(portfolio);
    }

    // PUT - Complete replacement of portfolio
    @PutMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> updatePortfolio(
            @PathVariable Long portfolioId,
            @Valid @RequestBody Portfolio portfolio) {
        Portfolio updated = portfolioService.replacePortfolio(portfolioId, portfolio);
        return ResponseEntity.ok(updated);
    }

    // PATCH - Partial update of portfolio
    @PatchMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> patchPortfolio(
            @PathVariable Long portfolioId,
            @RequestBody Map<String, Object> updates) {
        Portfolio patched = portfolioService.patchPortfolio(portfolioId, updates);
        return ResponseEntity.ok(patched);
    }

    // DELETE - Remove portfolio
    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long portfolioId) {
        portfolioService.deletePortfolio(portfolioId);
        return ResponseEntity.noContent().build();
    }

    // GET - Nested resource: portfolio positions
    @GetMapping("/{portfolioId}/positions")
    public ResponseEntity<List<Position>> getPortfolioPositions(@PathVariable Long portfolioId) {
        List<Position> positions = portfolioService.getPositions(portfolioId);
        return ResponseEntity.ok(positions);
    }

    // POST - Add position to portfolio
    @PostMapping("/{portfolioId}/positions")
    public ResponseEntity<Position> addPosition(
            @PathVariable Long portfolioId,
            @Valid @RequestBody AddPositionRequest request) {
        Position position = portfolioService.addPosition(portfolioId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(position);
    }
}
