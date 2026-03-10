package module07;

public record RichTradeEvent(String symbol, double price, double quantity, double volume) {
	public RichTradeEvent(TradeEvent event) {
		this(event.symbol(), event.price(), event.quantity(), event.price() * event.quantity());
	}
}
