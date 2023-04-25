import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ProductManagerUI extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel productGroupPanel;
    private JPanel productPanel;
    private JPanel statisticsPanel;
    private DefaultListModel<String> productGroupListModel;
    private DefaultListModel<String> productListModel;
    private JList<String> productGroupList;
    private JList<String> productList;
    static int mode=1;

    public ProductManagerUI() {
        ProductsDatabase database = ProductsDatabase.getInstance();
        setTitle("Product Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // initialize components
        tabbedPane = new JTabbedPane();
        productGroupPanel = new JPanel(new BorderLayout());
        productPanel = new JPanel(new BorderLayout());
        statisticsPanel = new JPanel(new BorderLayout());
        productGroupListModel = new DefaultListModel<String>();
        productListModel = new DefaultListModel<>();
        productGroupList = new JList<>(productGroupListModel);
        productList = new JList<>(productListModel);



        // add components to product group panel
        JPanel productGroupListPanel = new JPanel(new BorderLayout());
        productGroupListPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        productGroupListPanel.add(new JLabel("Product Groups"), BorderLayout.NORTH);
        JScrollPane productGroupScrollPane = new JScrollPane(productGroupList);
        productGroupListPanel.add(productGroupScrollPane, BorderLayout.CENTER);

        JPanel productGroupButtonPanel = new JPanel();
        JButton viewProductGroupInfoButton = new JButton("View");
        JTextField searchGroupField = new JTextField();
        searchGroupField.setColumns(15);
        JButton searchGroupButton = new JButton("Search");
        JButton addProductGroupButton = new JButton("Add");
        JButton editProductGroupButton = new JButton("Edit");
        JButton deleteProductGroupButton = new JButton("Delete");
        productGroupButtonPanel.add(searchGroupField);
        productGroupButtonPanel.add(searchGroupButton);
        productGroupButtonPanel.add(addProductGroupButton);
        productGroupButtonPanel.add(editProductGroupButton);
        productGroupButtonPanel.add(deleteProductGroupButton);
        productGroupButtonPanel.add(viewProductGroupInfoButton);


        JPanel productGroupDetailsPanel = new JPanel(new GridLayout(8, 6, 5, 5));
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
        productGroupDescriptionArea.setColumns(30);
        productGroupDescriptionArea.setRows(30);
        productGroupDetailsPanel.add(new JScrollPane(productGroupDescriptionArea));
        productGroupDetailsPanel.add(new JLabel("Products:"));
        productGroupDetailsPanel.add(new JPanel()); // placeholder for product list

        JPanel productGroupDetailsButtonPanel = new JPanel();
        JButton completeAddingGroup = new JButton("Save");
        completeAddingGroup.setSize(30,5);
        productGroupDetailsButtonPanel.add(completeAddingGroup);

        JPanel productGroupDetailsWrapperPanel = new JPanel(new BorderLayout());
        productGroupDetailsWrapperPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        productGroupDetailsWrapperPanel.add(productGroupDetailsPanel, BorderLayout.CENTER);
        productGroupDetailsWrapperPanel.add(productGroupDetailsButtonPanel, BorderLayout.SOUTH);

        productGroupPanel.add(productGroupListPanel, BorderLayout.CENTER);
        productGroupPanel.add(productGroupButtonPanel, BorderLayout.NORTH);
        productGroupPanel.add(productGroupDetailsWrapperPanel, BorderLayout.EAST);


        JPanel productListPanel = new JPanel(new BorderLayout());
        productListPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        productListPanel.add(new JLabel("Products"), BorderLayout.NORTH);
        JScrollPane productScrollPane = new JScrollPane(productList);
        productListPanel.add(productScrollPane, BorderLayout.CENTER);

        JPanel productButtonPanel = new JPanel();
        JButton addProductButton = new JButton("Add");
        JButton editProductButton = new JButton("Edit");
        JButton deleteProductButton = new JButton("Delete");
        JButton viewProductInfoButton = new JButton("View");
        JTextField searchProductField = new JTextField();
        searchProductField.setColumns(15);
        JButton searchProductButton = new JButton("Search");
        productButtonPanel.add(searchProductField);
        productButtonPanel.add(searchProductButton);
        productButtonPanel.add(addProductButton);
        productButtonPanel.add(editProductButton);
        productButtonPanel.add(deleteProductButton);
        productButtonPanel.add(viewProductInfoButton);

        JPanel productDetailsPanel = new JPanel(new GridLayout(6, 2, 6, 6));
        productDetailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Product Details"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JTextField productNameField = new JTextField();
        JTextField productGroupField = new JTextField();
        JTextArea productDescriptionArea = new JTextArea();
        productDescriptionArea.setLineWrap(true);
        productDescriptionArea.setWrapStyleWord(true);
        JTextField productManufacturerField = new JTextField();
        JTextField productPriceField = new JTextField();
        JSpinner productQuantitySpinner = new JSpinner();
        productDetailsPanel.add(new JLabel("Name:"));
        productDetailsPanel.add(productNameField);
        productDetailsPanel.add(new JLabel("Group:"));
        productDetailsPanel.add(productGroupField);
        productDetailsPanel.add(new JLabel("Description:"));
        productDetailsPanel.add(new JScrollPane(productDescriptionArea));
        productDetailsPanel.add(new JLabel("Manufacturer:"));
        productDetailsPanel.add(productManufacturerField);
        productDetailsPanel.add(new JLabel("Price:"));
        productDetailsPanel.add(productPriceField);


        JPanel productDetailsButtonPanel = new JPanel();
        JLabel empty = new JLabel();
        JButton saveChangesButton = new JButton("Save");
        productDetailsPanel.add(empty);
        productDetailsPanel.add(saveChangesButton);

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
        addProductGroupButton.addActionListener(e -> {
            mode = 1;
            productGroupNameField.setEditable(true);
            productGroupDescriptionArea.setEditable(true);


        });
        editProductGroupButton.addActionListener(e -> {
            mode = 2;
            productGroupNameField.setEditable(false);
            productGroupDescriptionArea.setEditable(true);
            String selectedProductGroup = productGroupList.getSelectedValue();
            if (selectedProductGroup != null) {
                productGroupNameField.setText(database.getProductGroup(selectedProductGroup).getName());
                productGroupDescriptionArea.setText(database.getProductGroup(selectedProductGroup).getDescription());
            }
        });
        deleteProductGroupButton.addActionListener(e -> {
            mode = 3;
            productGroupNameField.setEditable(false);
            productGroupDescriptionArea.setEditable(false);
            String selectedProductGroup = productGroupList.getSelectedValue();
            if (selectedProductGroup != null) {
                productGroupNameField.setText(database.getProductGroup(selectedProductGroup).getName());
                productGroupDescriptionArea.setText(database.getProductGroup(selectedProductGroup).getDescription());
            }

        });

        searchGroupButton.addActionListener(e -> {
            productGroupListModel.clear();
            List<ProductGroup> productsGroups = database.searchProductGroup(searchGroupField.getText());
            List<String> productGroups_names = new ArrayList<>();
            for(ProductGroup productGroup : productsGroups){
                productGroups_names.add(productGroup.getName());
                System.out.println(getName());
            }
            System.out.println(productGroups_names.toString());
            productGroupListModel.addAll(productGroups_names);




        });
        viewProductGroupInfoButton.addActionListener(e -> {
            productGroupNameField.setEditable(false);
            productGroupDescriptionArea.setEditable(false);
            String selectedProductGroup = productGroupList.getSelectedValue();
            if (selectedProductGroup != null) {
                productGroupNameField.setText(database.getProductGroup(selectedProductGroup).getName());
                productGroupDescriptionArea.setText(database.getProductGroup(selectedProductGroup).getDescription());
            }
        });

        completeAddingGroup.addActionListener(e -> {
            System.out.println(mode);
            if(mode==1) {
                String name = productGroupNameField.getText();
                String description = productGroupDescriptionArea.getText();
                ProductGroup productGroup = new ProductGroup(name, description);
                database.addProductGroup(productGroup);
                productGroupListModel.addElement(name);
                JOptionPane.showMessageDialog(null, "Group successfully added.");

            }
            else if(mode==2){
                String name = productGroupNameField.getText();
                String description = productGroupDescriptionArea.getText();
                ProductGroup productGroup = new ProductGroup(name, description);
                database.updateProductGroup(productGroup);
                JOptionPane.showMessageDialog(null, "Group successfully edited.");
            }
            else if(mode==3){
                database.deleteProductGroup( productGroupNameField.getText());
                productGroupListModel.removeElement(productGroupNameField.getText());
                JOptionPane.showMessageDialog(null, "Group successfully deleted.");
            }
            productGroupNameField.setText("");
            productGroupDescriptionArea.setText("");
        });
        addProductButton.addActionListener(e -> {
            mode = 4;
            productNameField.setEditable(true);
            productGroupField.setEditable(true);
            productDescriptionArea.setEditable(true);
            productManufacturerField.setEditable(true);
            productPriceField.setEditable(true);

        });
        editProductButton.addActionListener(e -> {
            mode = 5;
            productNameField.setEditable(false);
            productGroupField.setEditable(true);
            productDescriptionArea.setEditable(true);
            productManufacturerField.setEditable(true);
            productPriceField.setEditable(true);
            String selectedProduct = productList.getSelectedValue();
            if (selectedProduct != null) {
                productNameField.setText(database.getProduct(selectedProduct).getName());
                productDescriptionArea.setText(database.getProduct(selectedProduct).getDescription());
                productGroupField.setText(database.getProduct(selectedProduct).getGroup());
                productManufacturerField.setText(database.getProduct(selectedProduct).getManufacturer());
                productPriceField.setText(String.valueOf(database.getProduct(selectedProduct).getPrice()));
                productQuantitySpinner.setValue((database.getProduct(selectedProduct).getQuantity()));
            }

        });
        deleteProductButton.addActionListener(e -> {
                    mode = 6;
                    productNameField.setEditable(false);
                    productGroupField.setEditable(false);
                    productDescriptionArea.setEditable(false);
                    productManufacturerField.setEditable(false);
                    productPriceField.setEditable(false);
                    String selectedProduct = productList.getSelectedValue();
                    if (selectedProduct != null) {
                        productNameField.setText(database.getProduct(selectedProduct).getName());
                        productDescriptionArea.setText(database.getProduct(selectedProduct).getDescription());
                        productGroupField.setText(database.getProduct(selectedProduct).getGroup());
                        productManufacturerField.setText(database.getProduct(selectedProduct).getManufacturer());
                        productPriceField.setText(String.valueOf(database.getProduct(selectedProduct).getPrice()));
                        productQuantitySpinner.setValue((database.getProduct(selectedProduct).getQuantity()));
                    }
                });
        saveChangesButton.addActionListener(e -> {
            System.out.println(mode);
            if(mode==4) {
                String name = productNameField.getText();
                String description = productDescriptionArea.getText();
                String manufacturer = productManufacturerField.getText();
                Double price = Double.valueOf(productPriceField.getText());
                String group = productGroupField.getText();
                Product product = new Product(name,group, description,manufacturer,price,0);
                database.addProduct(product);
                productListModel.addElement(name);
                JOptionPane.showMessageDialog(null, "Product successfully added.");

            }
            else if(mode==5){
                String name = productNameField.getText();
                String description = productDescriptionArea.getText();
                String manufacturer = productManufacturerField.getText();
                Double price = Double.valueOf(productPriceField.getText());
                String group = productGroupField.getText();
                int quantity = (int) productQuantitySpinner.getValue();
                Product product = new Product(name,group,description,manufacturer,price,quantity);
                database.updateProduct(product);
                JOptionPane.showMessageDialog(null, "Product successfully edited.");

            }
            else if(mode==6){
                database.deleteProduct( productNameField.getText());
                productListModel.removeElement(productNameField.getText());
                JOptionPane.showMessageDialog(null, "Product successfully deleted.");
            }
            productNameField.setText("");
            productDescriptionArea.setText("");
            productManufacturerField.setText("");
            productPriceField.setText("");
            productGroupField.setText("");
            productQuantitySpinner.setValue(0);
        });
        viewProductInfoButton.addActionListener(e -> {
                    productNameField.setEditable(false);
                    productGroupField.setEditable(false);
                    productDescriptionArea.setEditable(false);
                    productManufacturerField.setEditable(false);
                    productPriceField.setEditable(false);
                    String selectedProduct = productList.getSelectedValue();
                    if (selectedProduct != null) {
                        productNameField.setText(database.getProduct(selectedProduct).getName());
                        productDescriptionArea.setText(database.getProduct(selectedProduct).getDescription());
                        productGroupField.setText(database.getProduct(selectedProduct).getGroup());
                        productManufacturerField.setText(database.getProduct(selectedProduct).getManufacturer());
                        productPriceField.setText(String.valueOf(database.getProduct(selectedProduct).getPrice()));
                        productQuantitySpinner.setValue((database.getProduct(selectedProduct).getQuantity()));
                    }
        });
        searchProductButton.addActionListener(e -> {
            productListModel.clear();
            List<Product> products = database.searchProduct(searchProductField.getText());
            List<String> product_names = new ArrayList<>();
            for(Product product : products){
                product_names.add(product.getName());
                System.out.println(getName());
            }
            System.out.println(product_names.toString());
            productListModel.addAll(product_names);




        });

        productDetailsButtonPanel.add(new JLabel("Quantity:"));
        JButton changeQuantity = new JButton("Change");
        productDetailsButtonPanel.add(productQuantitySpinner);
        productDetailsButtonPanel.add(changeQuantity);



        // initialize main frame
        JFrame frame = new JFrame("Product Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(tabbedPane);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductManagerUI::new);
    }
}
