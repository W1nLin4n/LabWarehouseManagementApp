import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;  import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class ProductManagerUI extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel productGroupPanel;
    private JPanel productPanel;
    private JPanel statisticsPanel;
    private DefaultListModel<ProductGroup> productGroupListModel;
    private DefaultListModel<Product> productListModel;
    private JList<ProductGroup> productGroupList;
    private JList<Product> productList;

    public ProductManagerUI() {
        setTitle("Product Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // initialize components
        tabbedPane = new JTabbedPane();
        productGroupPanel = new JPanel(new BorderLayout());
        productPanel = new JPanel(new BorderLayout());
        statisticsPanel = new JPanel(new BorderLayout());
        productGroupListModel = new DefaultListModel<>();
        productListModel = new DefaultListModel<>();
        productGroupList = new JList<>(productGroupListModel);
        productList = new JList<>(productListModel);

        // initialize product groups
        ArrayList<ProductGroup> productGroups = new ArrayList<>();
        productGroups.add(new ProductGroup("Group 1", "Description for Group 1"));
        productGroups.add(new ProductGroup("Group 2", "Description for Group 2"));
        productGroups.add(new ProductGroup("Group 3", "Description for Group 3"));

        // add components to product group panel
        JPanel productGroupListPanel = new JPanel(new BorderLayout());
        productGroupListPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        productGroupListPanel.add(new JLabel("Product Groups"), BorderLayout.NORTH);
        JScrollPane productGroupScrollPane = new JScrollPane(productGroupList);
        productGroupListPanel.add(productGroupScrollPane, BorderLayout.CENTER);

        JPanel productGroupButtonPanel = new JPanel();
        JButton addProductGroupButton = new JButton("Add");
        JButton editProductGroupButton = new JButton("Edit");
        JButton deleteProductGroupButton = new JButton("Delete");
        productGroupButtonPanel.add(addProductGroupButton);
        productGroupButtonPanel.add(editProductGroupButton);
        productGroupButtonPanel.add(deleteProductGroupButton);

        JPanel productGroupDetailsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        productGroupDetailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Product Group Details"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JTextField productGroupNameField = new JTextField();
        JTextArea productGroupDescriptionArea = new JTextArea();
        productGroupDescriptionArea.setLineWrap(true);
        productGroupDescriptionArea.setWrapStyleWord(true);
        productGroupDetailsPanel.add(new JLabel("Name:"));
        productGroupDetailsPanel.add(productGroupNameField);
        productGroupDetailsPanel.add(new JLabel("Description:"));
        productGroupDetailsPanel.add(new JScrollPane(productGroupDescriptionArea));
        productGroupDetailsPanel.add(new JLabel("Products:"));
        productGroupDetailsPanel.add(new JPanel()); // placeholder for product list

        JPanel productGroupDetailsButtonPanel = new JPanel();
        JButton addProductToGroupButton = new JButton("Add Product");
        JButton editProductInGroupButton = new JButton("Edit Product");
        JButton deleteProductFromGroupButton = new JButton("Delete Product");
        productGroupDetailsButtonPanel.add(addProductToGroupButton);
        productGroupDetailsButtonPanel.add(editProductInGroupButton);
        productGroupDetailsButtonPanel.add(deleteProductFromGroupButton);

        JPanel productGroupDetailsWrapperPanel = new JPanel(new BorderLayout());
        productGroupDetailsWrapperPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        productGroupDetailsWrapperPanel.add(productGroupDetailsPanel, BorderLayout.CENTER);
        productGroupDetailsWrapperPanel.add(productGroupDetailsButtonPanel, BorderLayout.SOUTH);

        productGroupPanel.add(productGroupListPanel, BorderLayout.CENTER);
        productGroupPanel.add(productGroupButtonPanel, BorderLayout.NORTH);
        productGroupPanel.add(productGroupDetailsWrapperPanel, BorderLayout.EAST);

        // initialize products
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "Description for Product 1", "Manufacturer 1", 10.0, 5));
        products.add(new Product("Product 2", "Description for Product 2", "Manufacturer 2", 20.0, 10));
        products.add(new Product("Product 3", "Description for Product 3", "Manufacturer 1", 15.0, 3));
        products.add(new Product("Product 4", "Description for Product 4", "Manufacturer 3", 5.0, 15));
        products.add(new Product("Product 5", "Description for Product 5", "Manufacturer 2", 25.0, 8));

        // add components to product panel
        JPanel productListPanel = new JPanel(new BorderLayout());
        productListPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        productListPanel.add(new JLabel("Products"), BorderLayout.NORTH);
        JScrollPane productScrollPane = new JScrollPane(productList);
        productListPanel.add(productScrollPane, BorderLayout.CENTER);

        JPanel productButtonPanel = new JPanel();
        JButton addProductButton = new JButton("Add");
        JButton editProductButton = new JButton("Edit");
        JButton deleteProductButton = new JButton("Delete");
        productButtonPanel.add(addProductButton);
        productButtonPanel.add(editProductButton);
        productButtonPanel.add(deleteProductButton);

        JPanel productDetailsPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        productDetailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Product Details"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JTextField productNameField = new JTextField();
        JTextArea productDescriptionArea = new JTextArea();
        productDescriptionArea.setLineWrap(true);
        productDescriptionArea.setWrapStyleWord(true);
        JTextField productManufacturerField = new JTextField();
        JTextField productPriceField = new JTextField();
        JSpinner productQuantitySpinner = new JSpinner();
        productDetailsPanel.add(new JLabel("Name:"));
        productDetailsPanel.add(productNameField);
        productDetailsPanel.add(new JLabel("Description:"));
        productDetailsPanel.add(new JScrollPane(productDescriptionArea));
        productDetailsPanel.add(new JLabel("Manufacturer:"));
        productDetailsPanel.add(productManufacturerField);
        productDetailsPanel.add(new JLabel("Price:"));
        productDetailsPanel.add(productPriceField);
        productDetailsPanel.add(new JLabel("Quantity:"));
        productDetailsPanel.add(productQuantitySpinner);

        JPanel productDetailsButtonPanel = new JPanel();
        JButton addQuantityButton = new JButton("Add Quantity");
        JButton subtractQuantityButton = new JButton("Subtract Quantity");
        productDetailsButtonPanel.add(addQuantityButton);
        productDetailsButtonPanel.add(subtractQuantityButton);

        JPanel productDetailsWrapperPanel = new JPanel(new BorderLayout());
        productDetailsWrapperPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        productDetailsWrapperPanel.add(productDetailsPanel, BorderLayout.CENTER);
        productDetailsWrapperPanel.add(productDetailsButtonPanel, BorderLayout.SOUTH);

        productPanel.add(productListPanel, BorderLayout.CENTER);
        productPanel.add(productButtonPanel, BorderLayout.NORTH);
        productPanel.add(productDetailsWrapperPanel, BorderLayout.EAST);
        // initialize statistics panel
        JPanel statisticsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        statisticsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Statistics"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JLabel totalCostLabel = new JLabel("Total Cost of Goods in Stock:");
        JLabel totalCostValueLabel = new JLabel("$0.00");
        JLabel groupCostLabel = new JLabel("Total Cost of Goods in Each Group:");
        JComboBox<String> groupComboBox = new JComboBox<>();
        JLabel groupCostValueLabel = new JLabel("$0.00");
        JButton calculateButton = new JButton("Calculate");
        statisticsPanel.add(totalCostLabel);
        statisticsPanel.add(totalCostValueLabel);
        statisticsPanel.add(groupCostLabel);
        statisticsPanel.add(groupComboBox);
        statisticsPanel.add(groupCostValueLabel);
        statisticsPanel.add(calculateButton);

        // initialize tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Product Groups", productGroupPanel);
        tabbedPane.addTab("Products", productPanel);
        tabbedPane.addTab("Statistics", statisticsPanel);

        // initialize main frame
        JFrame frame = new JFrame("Product Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ProductsDatabase database = ProductsDatabase.getInstance();
        SwingUtilities.invokeLater(ProductManagerUI::new);
        database.close();
    }
}
