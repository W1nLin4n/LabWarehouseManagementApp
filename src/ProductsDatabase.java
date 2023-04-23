import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductsDatabase {
    private static final String DB_URL = "jdbc:derby:products_database";
    private static ProductsDatabase instance;
    private Connection con;
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
                        "producer VARCHAR(50) NOT NULL,\n" +
                        "amount INT NOT NULL,\n" +
                        "price INT NOT NULL,\n" +
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

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProductsDatabase getInstance(){
        if(instance == null){
            instance = new ProductsDatabase();
        }
        return instance;
    }

}
