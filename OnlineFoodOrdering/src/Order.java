import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int userid;
    private List<String> items = new ArrayList<>();
    private List<Integer> quantity = new ArrayList<>();
    private OrderStatus orderStatus;
    private String orderAcceptedBy;

    public int getId() {
        return id;
    }

    public String getOrderAcceptedBy() {
        return orderAcceptedBy;
    }

    public void setOrderAcceptedBy(String orderAcceptedBy) {
        this.orderAcceptedBy = orderAcceptedBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userid=" + userid +
                ", items=" + items +
                ", quantity=" + quantity +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
