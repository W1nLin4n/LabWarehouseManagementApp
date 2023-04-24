import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDatabase {
    private static final String DB_URL = "jdbc:derby:products_database";
    private static ProductsDatabase instance;
    private Connection con;

    /**
     * If database already exists, establishes connection to it. Otherwise, creates new empty database
     */
    private ProductsDatabase(){
        try{
            con = DriverManager.getConnection(DB_URL);
            return;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(new JFrame(), "Database does not exist. An empty database will be created");
        }
        try{
            con = DriverManager.getConnection(DB_URL+";create=true");
            Statement statement = con.createStatement();
            statement.execute(
                "CREATE TABLE product_groups (\n" +
                        "name VARCHAR(50) NOT NULL PRIMARY KEY,\n" +
                        "description VARCHAR(500) NOT NULL\n" +
                    ")"
            );
            statement.execute(
                "CREATE TABLE products (\n" +
                        "name VARCHAR(50) NOT NULL PRIMARY KEY,\n" +
                        "product_group VARCHAR(50) NOT NULL,\n" +
                        "description VARCHAR(500) NOT NULL,\n" +
                        "manufacturer VARCHAR(50) NOT NULL,\n" +
                        "price DOUBLE NOT NULL,\n" +
                        "quantity INT NOT NULL,\n" +
                        "CONSTRAINT product_group_fk\n" +
                        "FOREIGN KEY (product_group)\n" +
                        "REFERENCES product_groups (name)\n" +
                        "ON DELETE CASCADE\n" +
                    ")"
            );
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds product group to the database
     * @param productGroup product group to add
     */
    public void addProductGroup(ProductGroup productGroup){
        try {
            if(!productGroup.isValid()){
                JOptionPane.showMessageDialog(new JFrame(), "There can be no empty fields");
                return;
            }
            if(getProductGroup(productGroup.getName()) != null){
                JOptionPane.showMessageDialog(new JFrame(), "There already exists product group with this name");
                return;
            }
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO product_groups VALUES (?, ?)");
            preparedStatement.setString(1, productGroup.getName());
            preparedStatement.setString(2, productGroup.getDescription());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds product to the database
     * @param product product to add
     */
    public void addProduct(Product product){
        try {
            if(!product.isValid()){
                JOptionPane.showMessageDialog(new JFrame(), "There can be no empty fields");
                return;
            }
            if(getProduct(product.getName()) != null){
                JOptionPane.showMessageDialog(new JFrame(), "There already exists product with this name");
                return;
            }
            if(getProductGroup(product.getGroup()) == null){
                JOptionPane.showMessageDialog(new JFrame(), "There is no product group with this name");
                return;
            }
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getGroup());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getManufacturer());
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.setInt(6, product.getQuantity());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes product group from the database
     * @param name name of product group to delete
     */
    public void deleteProductGroup(String name){
        try {
            if(getProductGroup(name) == null){
                JOptionPane.showMessageDialog(new JFrame(), "There is no product group with this name");
                return;
            }
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM product_groups WHERE name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes product from the database
     * @param name name of product to delete
     */
    public void deleteProduct(String name){
        try {
            if(getProduct(name) == null){
                JOptionPane.showMessageDialog(new JFrame(), "There is no product with this name");
                return;
            }
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM products WHERE name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates product group in the database
     * @param productGroup updated product group
     */
    public void updateProductGroup(ProductGroup productGroup){
        try {
            if(!productGroup.isValid()){
                JOptionPane.showMessageDialog(new JFrame(), "There can be no empty fields");
                return;
            }
            if(getProductGroup(productGroup.getName()) == null){
                JOptionPane.showMessageDialog(new JFrame(), "There is no product group with this name");
                return;
            }
            PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE product_groups\n" +
                    "SET\n" +
                        "description = ?\n" +
                    "WHERE\n" +
                        "name = ?"
            );
            preparedStatement.setString(1, productGroup.getDescription());
            preparedStatement.setString(2, productGroup.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates product in the database
     * @param product updated product
     */
    public void updateProduct(Product product){
        try {
            if(!product.isValid()){
                JOptionPane.showMessageDialog(new JFrame(), "There can be no empty fields");
                return;
            }
            if(getProduct(product.getName()) == null){
                JOptionPane.showMessageDialog(new JFrame(), "There is no product with this name");
                return;
            }
            if(getProductGroup(product.getGroup()) == null){
                JOptionPane.showMessageDialog(new JFrame(), "There is no product group with this name");
                return;
            }
            PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE products\n" +
                    "SET\n" +
                        "product_group = ?\n" +
                        "description = ?\n" +
                        "manufacturer = ?\n" +
                        "price = ?\n" +
                        "quantity = ?\n" +
                    "WHERE\n" +
                        "name = ?"
            );
            preparedStatement.setString(1, product.getGroup());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getManufacturer());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.setString(6, product.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all product groups
     * @return all product groups
     */
    public List<ProductGroup> getProductGroups(){
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product_groups");
            List<ProductGroup> list = new ArrayList<>();
            while(resultSet.next()){
                list.add(new ProductGroup(
                        resultSet.getString(1),
                        resultSet.getString(2)
                ));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Gets all products
     * @return all products
     */
    public List<Product> getProducts(){
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            List<Product> list = new ArrayList<>();
            while(resultSet.next()){
                list.add(new Product(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getInt(6)
                ));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Gets product group with specified name
     * @param name name of product group
     * @return product group with specified name
     */
    public ProductGroup getProductGroup(String name){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM product_groups WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            if(!resultSet.next()){
                return null;
            }
            return new ProductGroup(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets product with specified name
     * @param name name of product
     * @return product with specified name
     */
    public Product getProduct(String name){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM products WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            if(!resultSet.next()){
                return null;
            }
            return new Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Closes connection to database
     */
    public void close() {
        try {
            con.close();
            instance = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets instance of the database
     * @return instance of the database
     */
    public static ProductsDatabase getInstance(){
        if(instance == null){
            instance = new ProductsDatabase();
        }
        return instance;
    }

}
