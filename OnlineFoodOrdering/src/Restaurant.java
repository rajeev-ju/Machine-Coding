import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private List<String> items;
    private List<Integer> ItemsPrices;
    private int maxNumberOfOrdersServedAt1Time;
    private double rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<Integer> getItemsPrices() {
        return ItemsPrices;
    }

    public void setItemsPrices(List<Integer> itemsPrices) {
        ItemsPrices = itemsPrices;
    }

    public int getMaxNumberOfOrdersServedAt1Time() {
        return maxNumberOfOrdersServedAt1Time;
    }

    public void setMaxNumberOfOrdersServedAt1Time(int maxNumberOfOrdersServedAt1Time) {
        this.maxNumberOfOrdersServedAt1Time = maxNumberOfOrdersServedAt1Time;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", ItemsPrices=" + ItemsPrices +
                ", maxNumberOfOrdersServedAt1Time=" + maxNumberOfOrdersServedAt1Time +
                ", rating=" + rating +
                '}';
    }
}
