package com.nashtech.inventory.aggregate;


import com.nashtech.common.command.CancelProductReserveCommand;
import com.nashtech.common.command.ReserveProductCommand;
import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReserveFailedEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.command.CreateProductCommand;
import com.nashtech.inventory.events.ProductCreatedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.StringUtils;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Data
@Aggregate
@Slf4j
public class InventoryAggregate{

	@AggregateIdentifier
	private String productId;
	private String brand;
	private String model;
	Integer year;
	String color;
	Double mileage;
	private Double basePrice;
	private Float tax;
	private Integer quantity;

	private String userId;
	private String orderId;
	Double subTotal;
	Double total;
	Float totalTax;

	public InventoryAggregate() {

	}

	@CommandHandler
	public InventoryAggregate(CreateProductCommand createProductCommand) {
	log.info("CreateProductCommand started with productId {}",createProductCommand.getProductId());
		if(StringUtils.emptyOrNull(createProductCommand.getBrand())) {
			throw new IllegalArgumentException("Brand cannot be empty");
		}

		ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
				.productId(createProductCommand.getProductId())
				.brand(createProductCommand.getBrand())
				.model(createProductCommand.getModel())
				.year(createProductCommand.getYear())
				.color(createProductCommand.getColor())
				.mileage(createProductCommand.getMileage())
				.basePrice(createProductCommand.getBasePrice())
				.quantity(createProductCommand.getQuantity())
				.tax(createProductCommand.getTax())
				.build();

		AggregateLifecycle.apply(productCreatedEvent);
	}

	@CommandHandler
	public void handle(ReserveProductCommand reserveProductCommand) {
		log.info("ReserveProductCommand started with productId {}",reserveProductCommand.getProductId());
		if(quantity<=0 ||  quantity < reserveProductCommand.getQuantity()) {
			log.warn("Current stock is {} of the product {}", quantity, reserveProductCommand.getProductId());
			ProductReserveFailedEvent productFailedEvent = ProductReserveFailedEvent.builder()
					.productId(reserveProductCommand.getProductId())
					.userId(reserveProductCommand.getUserId())
					.orderId(reserveProductCommand.getOrderId())
					.reasonToFailed("Insufficient number of items in stock for product "+reserveProductCommand.getProductId())
					.build();
			AggregateLifecycle.apply(productFailedEvent);
			return;
		}

		double productSubTotal = reserveProductCommand.getQuantity() * basePrice;
		float productTotalTax= reserveProductCommand.getQuantity() * tax;
		double productTotal = productSubTotal + productTotalTax;

		ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
				.orderId(reserveProductCommand.getOrderId())
				.productId(reserveProductCommand.getProductId())
				.userId(reserveProductCommand.getUserId())
				.quantity(reserveProductCommand.getQuantity())
				.brand(brand)
				.basePrice(basePrice)
				.tax(tax)
				.totalTax(productTotalTax)
				.subTotal(productSubTotal)
				.total(productTotal)
				.model(model)
				.mileage(mileage)
				.color(color)
				.year(year)
				.build();

		AggregateLifecycle.apply(productReservedEvent);
	}

	@CommandHandler
	public void handle(CancelProductReserveCommand cancelProductReservationCommand) {
		log.info("ProductReserveCancelledEvent started with productId {}",cancelProductReservationCommand.getProductId());
		ProductReserveCancelledEvent productReservationCancelledEvent = ProductReserveCancelledEvent.builder()
				.productId(cancelProductReservationCommand.getProductId())
				.userId(cancelProductReservationCommand.getUserId())
				.orderId(cancelProductReservationCommand.getOrderId())
				.quantity(cancelProductReservationCommand.getQuantity())
				.build();

		AggregateLifecycle.apply(productReservationCancelledEvent);
	}


	@EventSourcingHandler
	public void on(ProductReserveCancelledEvent productReservationCancelledEvent) {
		this.quantity += productReservationCancelledEvent.getQuantity();
	}


	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		this.productId = productCreatedEvent.getProductId();
		this.brand = productCreatedEvent.getBrand();
		this.model = productCreatedEvent.getModel();
		this.year = productCreatedEvent.getYear();
		this.color = productCreatedEvent.getColor();
		this.mileage = productCreatedEvent.getMileage();
		this.basePrice = productCreatedEvent.getBasePrice();
		this.tax = productCreatedEvent.getTax();
		this.quantity = productCreatedEvent.getQuantity();
	}


	@EventSourcingHandler
	public void on(ProductReservedEvent productReservedEvent) {
		this.userId = productReservedEvent.getUserId();
		this.orderId = productReservedEvent.getOrderId();
		this.quantity -= productReservedEvent.getQuantity();
		this.totalTax = productReservedEvent.getTotalTax();
		this.subTotal = productReservedEvent.getSubTotal();
		this.total = productReservedEvent.getTotal();
	}

}
