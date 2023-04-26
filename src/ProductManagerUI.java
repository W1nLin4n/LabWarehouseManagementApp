import javax.swing.*;
import java.awt.*;
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
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;


public class ProductManagerUI extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel productGroupPanel;
    private JPanel productPanel;
    private JPanel statisticsPanel;
    private DefaultListModel<String> productGroupListModel;
    private DefaultListModel<String> productListModel;
    private DefaultListModel<String> productListModel1;
    private JList<String> productGroupList;
    private JList<String> productList;
    private JList<String> productList1;
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
        productListModel1 = new DefaultListModel<>();
        productGroupList = new JList<>(productGroupListModel);
        productList = new JList<>(productListModel);
        productList1 = new JList<>(productListModel1);



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
        productGroupListModel.clear();
        List<ProductGroup> productsGroups1 = database.searchProductGroup(searchGroupField.getText());
        List<String> productGroups_names1 = new ArrayList<>();
        for(ProductGroup productGroup : productsGroups1){
            productGroups_names1.add(productGroup.getName());
        }
        productGroupListModel.addAll(productGroups_names1);

        JPanel productGroupDetailsPanel = new JPanel(new GridLayout(6, 6, 5, 5));
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
        JScrollPane productScrollPanel = new JScrollPane(productList1);
        productScrollPanel.setPreferredSize(new Dimension(100,200));
        productGroupDetailsPanel.add(productScrollPanel);

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
        productListModel.clear();
        List<Product> products1 = database.searchProduct(searchProductField.getText());
        List<String> product_names1 = new ArrayList<>();
        for(Product product : products1){
            product_names1.add(product.getName());
        }
        productListModel.addAll(product_names1);


        JPanel productDetailsPanel = new JPanel(new GridLayout(7, 2, 6, 6));
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
        JTextField productQuantityField = new JTextField();
        JSpinner productQuantitySpinner = new JSpinner();
        Dimension size = new Dimension(60, 20);
        productQuantitySpinner.setPreferredSize(size);
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
        productDetailsPanel.add(new JLabel("Quantity"));
        productDetailsPanel.add(productQuantityField);

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
        JPanel statisticsPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        statisticsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Statistics"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JLabel totalCostLabel = new JLabel("Total Cost of Goods in Stock:");
        JLabel totalCostValueLabel = new JLabel("$"+Double.toString(database.getTotalValueOfStock()));
        JLabel groupCostLabel = new JLabel("Select group:");
        JComboBox<String> groupComboBox = new JComboBox<>();
        JLabel productCostLabel = new JLabel("Select product:");
        JComboBox<String> productComboBox = new JComboBox<>();
        JLabel groupCostValueLabel = new JLabel("$0.00");
        JButton calculateButton = new JButton("Calculate");
        List<ProductGroup> productsGroups2 = database.searchProductGroup(searchGroupField.getText());
        for(ProductGroup productGroup : productsGroups2){
            groupComboBox.addItem(productGroup.getName());
        }
        groupComboBox.addActionListener(e -> {
            if(groupComboBox.getSelectedItem()!=null) {
                productComboBox.removeAllItems();
                List<Product> products2 = database.getProductsFromGroup((groupComboBox.getSelectedItem()).toString());
                productComboBox.addItem("All products");
                for (Product product : products2) {
                    productComboBox.addItem(product.getName());
                }

            }

        });
        calculateButton.addActionListener(e -> {
            if(productComboBox.getSelectedItem()!=null) {
                Double value;
                if(productComboBox.getSelectedItem().equals("All products")){
                    value = database.getTotalValueOfProductGroup((groupComboBox.getSelectedItem()).toString());
                }
                else{
                    value=database.getTotalValueOfProduct((productComboBox.getSelectedItem()).toString());
                }
                groupCostValueLabel.setText("$"+value);
            }
        });

        statisticsPanel.add(totalCostLabel);
        statisticsPanel.add(totalCostValueLabel);
        statisticsPanel.add(groupCostLabel);
        statisticsPanel.add(groupComboBox);
        statisticsPanel.add(productCostLabel);
        statisticsPanel.add(productComboBox);
        statisticsPanel.add(groupCostValueLabel);
        statisticsPanel.add(calculateButton);
        // initialize tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Product Groups", productGroupPanel);
        tabbedPane.addTab("Products", productPanel);
        tabbedPane.addTab("Statistics", statisticsPanel);
        addProductGroupButton.addActionListener(e -> {
            mode = 1;
            productListModel1.clear();
            productGroupNameField.setText("");
            productGroupDescriptionArea.setText("");

            completeAddingGroup.setText("Add");
            completeAddingGroup.setEnabled(true);
            productGroupNameField.setEditable(true);
            productGroupDescriptionArea.setEditable(true);


        });
        editProductGroupButton.addActionListener(e -> {
            mode = 2;
            completeAddingGroup.setText("Save");
            completeAddingGroup.setEnabled(true);
            productGroupNameField.setEditable(false);
            productGroupDescriptionArea.setEditable(true);
            String selectedProductGroup = productGroupList.getSelectedValue();
            if (selectedProductGroup != null) {
                productGroupNameField.setText(database.getProductGroup(selectedProductGroup).getName());
                productGroupDescriptionArea.setText(database.getProductGroup(selectedProductGroup).getDescription());
                productListModel1.clear();
                String productGroup = database.getProductGroup(selectedProductGroup).getName();
                List<Product> products = database.getProductsFromGroup(productGroup);
                List<String> product_names = new ArrayList<>();
                for(Product product : products){
                    product_names.add(product.getName());
                }
                productListModel1.addAll(product_names);
            }
        });

        deleteProductGroupButton.addActionListener(e -> {
            mode = 3;
            completeAddingGroup.setText("Delete");
            completeAddingGroup.setEnabled(true);
            productGroupNameField.setEditable(false);
            productGroupDescriptionArea.setEditable(false);
            String selectedProductGroup = productGroupList.getSelectedValue();
            if (selectedProductGroup != null) {
                productGroupNameField.setText(database.getProductGroup(selectedProductGroup).getName());
                productGroupDescriptionArea.setText(database.getProductGroup(selectedProductGroup).getDescription());
                productListModel1.clear();
                String productGroup = database.getProductGroup(selectedProductGroup).getName();
                List<Product> products = database.getProductsFromGroup(productGroup);
                List<String> product_names = new ArrayList<>();
                for(Product product : products){
                    product_names.add(product.getName());
                }
                productListModel1.addAll(product_names);
            }

        });

        searchGroupButton.addActionListener(e -> {
            completeAddingGroup.setEnabled(false);
            productGroupListModel.clear();
            List<ProductGroup> productsGroups = database.searchProductGroup(searchGroupField.getText());
            List<String> productGroups_names = new ArrayList<>();
            for(ProductGroup productGroup : productsGroups){
                productGroups_names.add(productGroup.getName());
            }
            productGroupListModel.addAll(productGroups_names);




        });
        viewProductGroupInfoButton.addActionListener(e -> {
            completeAddingGroup.setEnabled(false);
            productGroupNameField.setEditable(false);
            productGroupDescriptionArea.setEditable(false);
            String selectedProductGroup = productGroupList.getSelectedValue();
            if (selectedProductGroup != null) {
                productGroupNameField.setText(database.getProductGroup(selectedProductGroup).getName());
                productGroupDescriptionArea.setText(database.getProductGroup(selectedProductGroup).getDescription());
                productListModel1.clear();
                String productGroup = database.getProductGroup(selectedProductGroup).getName();
                List<Product> products = database.getProductsFromGroup(productGroup);
                List<String> product_names = new ArrayList<>();
                for(Product product : products){
                    product_names.add(product.getName());
                }
                productListModel1.addAll(product_names);

            }
        });

        completeAddingGroup.addActionListener(e -> {
            if(mode==1) {
                if(database.getProductGroup(productGroupNameField.getText())==null) {
                    String name = productGroupNameField.getText();
                    String description = productGroupDescriptionArea.getText();
                    ProductGroup productGroup = new ProductGroup(name, description);
                    database.addProductGroup(productGroup);
                    productGroupListModel.addElement(name);
                    JOptionPane.showMessageDialog(null, "Group successfully added.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Group already exists.");
                }

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
            productNameField.setText("");
            productDescriptionArea.setText("");
            productManufacturerField.setText("");
            productPriceField.setText("");
            productGroupField.setText("");
            productQuantityField.setText("");
            productQuantitySpinner.setValue(0);
            saveChangesButton.setText("Add");
            saveChangesButton.setEnabled(true);
            productNameField.setEditable(true);
            productGroupField.setEditable(true);
            productDescriptionArea.setEditable(true);
            productManufacturerField.setEditable(true);
            productPriceField.setEditable(true);
            productQuantityField.setEditable(true);

        });
        editProductButton.addActionListener(e -> {
            mode = 5;
            saveChangesButton.setText("Save");
            saveChangesButton.setEnabled(true);
            productNameField.setEditable(false);
            productGroupField.setEditable(true);
            productDescriptionArea.setEditable(true);
            productManufacturerField.setEditable(true);
            productPriceField.setEditable(true);
            productQuantityField.setEditable(true);
            String selectedProduct = productList.getSelectedValue();
            if (selectedProduct != null) {
                productNameField.setText(database.getProduct(selectedProduct).getName());
                productDescriptionArea.setText(database.getProduct(selectedProduct).getDescription());
                productGroupField.setText(database.getProduct(selectedProduct).getGroup());
                productManufacturerField.setText(database.getProduct(selectedProduct).getManufacturer());
                productPriceField.setText(String.valueOf(database.getProduct(selectedProduct).getPrice()));
                productQuantityField.setText(String.valueOf((database.getProduct(selectedProduct).getQuantity())));
            }

        });
        deleteProductButton.addActionListener(e -> {
                    mode = 6;
                    saveChangesButton.setText("Delete");
                    saveChangesButton.setEnabled(true);
                    productNameField.setEditable(false);
                    productGroupField.setEditable(false);
                    productDescriptionArea.setEditable(false);
                    productManufacturerField.setEditable(false);
                    productPriceField.setEditable(false);
                    productQuantityField.setEditable(false);
                    String selectedProduct = productList.getSelectedValue();
                    if (selectedProduct != null) {
                        productNameField.setText(database.getProduct(selectedProduct).getName());
                        productDescriptionArea.setText(database.getProduct(selectedProduct).getDescription());
                        productGroupField.setText(database.getProduct(selectedProduct).getGroup());
                        productManufacturerField.setText(database.getProduct(selectedProduct).getManufacturer());
                        productPriceField.setText(String.valueOf(database.getProduct(selectedProduct).getPrice()));
                        productQuantityField.setText(String.valueOf((database.getProduct(selectedProduct).getQuantity())));
                    }
                });
        saveChangesButton.addActionListener(e -> {
            if(mode==4) {
                if(database.getProduct(productNameField.getText())==null&&database.getProductGroup(productGroupField.getText())!=null) {

                    String name = productNameField.getText();
                    String description = productDescriptionArea.getText();
                    String manufacturer = productManufacturerField.getText();
                    String group = productGroupField.getText();
                    String quantityText = (productQuantityField.getText());
                    String priceText = (productPriceField.getText());
                    try {
                        int quantity = Integer.parseInt(quantityText);
                        double price = Double.parseDouble(priceText);
                        Product product = new Product(name, group, description, manufacturer, price, quantity);
                        database.addProduct(product);
                        productListModel.addElement(name);
                        JOptionPane.showMessageDialog(null, "Product successfully added.");
                    } catch (NumberFormatException s) {
                        JOptionPane.showMessageDialog(null, "Use only numbers.");
                        productPriceField.setText("");
                        productQuantityField.setText("");
                        return;
                    }
                }
                else if(database.getProduct(productNameField.getText())!=null&&database.getProductGroup(productGroupField.getText())==null){
                    JOptionPane.showMessageDialog(null, "Enter correct product and group.");
                    productNameField.setText("");
                    productGroupField.setText("");
                    return;
                }
                else if(database.getProduct(productNameField.getText())!=null){
                    JOptionPane.showMessageDialog(null, "Product already exists.");
                    productNameField.setText("");
                    return;
                }
                else if(database.getProductGroup(productGroupField.getText())==null){
                    JOptionPane.showMessageDialog(null, "Group does not exist.");
                    productGroupField.setText("");
                    return;
                }
            }
            else if(mode==5){
                if (database.getProductGroup(productGroupField.getText()) != null) {
                    String name = productNameField.getText();
                    String description = productDescriptionArea.getText();
                    String manufacturer = productManufacturerField.getText();
                    String quantityText = (productQuantityField.getText());
                    String priceText = (productPriceField.getText());
                    String group = productGroupField.getText();
                    try {
                        int quantity = Integer.parseInt(quantityText);
                        double price = Double.parseDouble(priceText);
                        Product product = new Product(name, group, description, manufacturer, price, quantity);
                        database.updateProduct(product);
                        JOptionPane.showMessageDialog(null, "Product successfully edited.");
                    } catch (NumberFormatException s) {
                        JOptionPane.showMessageDialog(null, "Use only numbers.");
                        productPriceField.setText("");
                        productQuantityField.setText("");
                        return;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Group does not exist.");
                    productGroupField.setText("");
                    return;
                }

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
            productQuantityField.setText("");
            productQuantitySpinner.setValue(0);
        });
        viewProductInfoButton.addActionListener(e -> {
                    saveChangesButton.setEnabled(false);
                    productNameField.setEditable(false);
                    productGroupField.setEditable(false);
                    productDescriptionArea.setEditable(false);
                    productManufacturerField.setEditable(false);
                    productPriceField.setEditable(false);
                    productQuantityField.setEditable(false);
                    String selectedProduct = productList.getSelectedValue();
                    if (selectedProduct != null) {
                        productNameField.setText(database.getProduct(selectedProduct).getName());
                        productDescriptionArea.setText(database.getProduct(selectedProduct).getDescription());
                        productGroupField.setText(database.getProduct(selectedProduct).getGroup());
                        productManufacturerField.setText(database.getProduct(selectedProduct).getManufacturer());
                        productPriceField.setText(String.valueOf(database.getProduct(selectedProduct).getPrice()));
                        productQuantityField.setText(String.valueOf((database.getProduct(selectedProduct).getQuantity())));
                    }
        });
        searchProductButton.addActionListener(e -> {
            saveChangesButton.setEnabled(false);
            productListModel.clear();
            List<Product> products = database.searchProduct(searchProductField.getText());
            List<String> product_names = new ArrayList<>();
            for(Product product : products){
                product_names.add(product.getName());
            }
            productListModel.addAll(product_names);




        });


        productDetailsButtonPanel.add(new JLabel("Quantity:"));
        JButton changeQuantity = new JButton("Change");
        productDetailsButtonPanel.add(productQuantitySpinner);
        productDetailsButtonPanel.add(changeQuantity);

        changeQuantity.addActionListener(e -> {
            String selectedProduct = productList.getSelectedValue();
            if (selectedProduct != null) {
                database.updateProductQuantity(selectedProduct,(Integer)productQuantitySpinner.getValue());
                productQuantitySpinner.setValue(0);
                String quantity = String.valueOf((database.getProduct(selectedProduct).getQuantity()));
                productQuantityField.setText(quantity);
                JOptionPane.showMessageDialog(null, "Product quantity successfully updated.");
            }
        });

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
