public class ProductGroup {
    private String name;
    private String description;

    public ProductGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean isValid(){
        if(
            name == "" || name == null ||
            description == "" || description == null
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
