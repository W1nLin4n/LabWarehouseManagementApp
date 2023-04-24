public class Product {
    private String name;
    private String group;
    private String description;
    private String manufacturer;
    private double price;
    private int quantity;

    public Product(String name, String group, String description, String manufacturer, double price, int quantity){
        this.name = name;
        this.group = group;
        this.description = description;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public boolean isValid(){
        if(
            name == "" || name == null ||
            group == "" || group == null ||
            description == "" || description == null ||
            manufacturer == "" || manufacturer == null
        )
            return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
