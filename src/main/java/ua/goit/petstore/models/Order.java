package ua.goit.petstore.models;

public class Order {
    private Integer id;
    private Long petId;
    private Integer quantity;
    private String shipDate;
    private OrderStatus status;
    private boolean complete;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", petId=").append(petId);
        sb.append(", quantity=").append(quantity);
        sb.append(", shipDate='").append(shipDate).append('\'');
        sb.append(", status=").append(status);
        sb.append(", complete=").append(complete);
        sb.append('}');
        return sb.toString();
    }
}
