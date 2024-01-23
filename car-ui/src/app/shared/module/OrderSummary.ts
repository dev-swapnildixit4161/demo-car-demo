export class OrderSummary {
    orderId: string;
    message: string;
    orderStatus: string;

    constructor(orderId: string, message: string, orderStatus: string) {
        this.orderId = orderId;
        this.message = message;
        this.orderStatus = orderStatus;
    }
}