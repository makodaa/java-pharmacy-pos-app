package main.java;

import java.awt.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Main {
    private static final HashMap<String, JFrame> openedFrames = new HashMap<>();
    private static final HashMap<String, ImageIcon> cachedIcons = new HashMap<>();
    private static final ImageIcon originalIcon = new ImageIcon("./assets/logo.png");

    private static final Category[] categories = new Category[] {
            new Category(
                    "Pain\nRelievers",
                    null,
                    null,
                    null,
                    new Product[] {
                            new Product("Product A", null, null, 100),
                            new Product("Product B", null, null, 150),
                            new Product("Product C", null, null, 250),
                    }),
            new Category(
                    "Antibiotics",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Anti\nAllergy",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Respiratory\nMedicine",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Fever\nMedicine",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Vitamins",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Dietary\nSupp.",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Mineral\nSupp.",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Bandages",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Cotton\nItems",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Antiseptics",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Personal\nHygiene",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Surgical\nEquipment",
                    null,
                    null,
                    null,
                    new Product[] {}),
            new Category(
                    "Assistive\n Devices",
                    null,
                    null,
                    null,
                    new Product[] {}),
    };

    public static void main(String[] args) {
        setLookAndFeel();
        openGreeting();
        // openMainPanel();
    }

    /**
     * Interface for lambdas. Basically, anything that accepts `PanelBuilder`
     * should have an anonymous function with types f :: JFrame -> JPanel
     */
    private static interface PanelBuilder {
        JPanel run(JFrame frame);
    }

    /**
     * This creates a panel if it is new, or reveals a previously created one.
     *
     * @param panelCode Unique identifier that allows the system to identify the
     *                  created panel.
     * @param panelName The title shown on the panel.
     * @param builder   An anonymous function that creates a JPanel instance when
     *                  called.
     */
    public static void spawnPanel(String panelCode, String panelName, PanelBuilder builder) {
        /// If we have created a `panelCode` before,
        /// then we should just reveal that.
        if (openedFrames.containsKey(panelCode)) {
            /// If the panel is found, then just hide the others.
            /// and reveal this one.

            for (Entry<String, JFrame> openedFrameEntry : openedFrames.entrySet()) {
                String key = openedFrameEntry.getKey();
                JFrame frame = openedFrameEntry.getValue();

                if (!key.equals(panelCode)) {
                    frame.setVisible(false);
                } else {
                    frame.setVisible(true);
                }
            }

        } else {
            /// If the panel isn't found, then hide all others.
            for (JFrame openedFrame : openedFrames.values()) {
                openedFrame.setVisible(false);
            }

            /// Create a new frame and panel.
            JFrame frame = new JFrame(panelName);
            JPanel panel = builder.run(frame);

            frame.setMinimumSize(new Dimension(750, 600));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.setVisible(true);
            frame.setResizable(false);

            frame.pack();
            openedFrames.put(panelCode, frame);
        }
    }

    /**
     * Opens the greeting panel.
     */
    public static void openGreeting() {
        final String panelCode = "GREETING_PANEL";
        final String panelName = "Greeting Panel";

        spawnPanel(panelCode, panelName, (frame) -> new GreetingPanel(frame));
    }

    /**
     * Opens the main panel (should be the main window.)
     */
    public static void openMainPanel() {
        final String panelCode = "MAIN_PANEL";
        final String panelName = "Main Panel";

        spawnPanel(panelCode, panelName, (frame) -> new MainPanel(frame));
    }

    /**
     * Opens the product navigation panel
     */
    public static void openNavigationPanel(Category category) {
        final String panelCode = "PRODUCT_NAVIGATION_PANEL;" + category.title;
        final String panelName = "Product Navigation Panel - " + category.title;

        spawnPanel(panelCode, panelName, (frame) -> new NavigationPanel(category, frame));
    }

    /**
     * Sets the theme of the program to match the current operating system.
     */
    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * A function that returns a cached version of the scaled icon (logo).
     * If the icon is called with a new `width` and `height`, then a new
     * `ImageIcon` is created, else it is returned from the cache.
     *
     * @param width  The width of the scaled icon
     * @param height The height of the scaled icon
     * @return The cached/new `ImageIcon`
     */
    private static ImageIcon getScaledIcon(int width, int height) {
        /// Key to be used in the cache.
        String key = Integer.toString(width) + ";" + Integer.toString(height);

        /// If the key is in the cache (aka it has been called before)
        /// Then just return the saved one.
        if (cachedIcons.containsKey(key)) {
            return cachedIcons.get(key);
        } else {
            /// Otherwise, scale the icon.
            Image image = originalIcon.getImage();
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);

            /// After creating the scaled icon instance, put it in the cache.
            cachedIcons.put(key, icon);

            return icon;
        }
    }

    private static class GreetingPanel extends JPanel {
        /**
         * Creates a cached `JLabel` with the logo.
         *
         * @return Logo JLabel
         */
        private static JLabel createLogoLabel() {
            ImageIcon scaledIcon = getScaledIcon(256, 256);
            JLabel picLabel = new JLabel(scaledIcon);

            return picLabel;
        }

        /**
         * Creates the multilined welcome label below the logo.
         *
         * @return Welcome Label
         */
        private static JPanel createWelcomeLabel() {
            /// PANEL SETUP
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();

            /// This is hard coded.
            String label = "Welcome to\nUncle Andy's Pharmacy";
            for (String line : label.split("\n")) {
                JLabel jLabel = new JLabel(line);

                /// Create a copy of the font with the set font style and font size.
                Font boldFont = new Font("Segoe UI", Font.BOLD, 20);

                jLabel.setFont(boldFont);
                panel.add(jLabel, constraints);

                ++constraints.gridy;
            }

            return panel;
        }

        /**
         * Creates the button that opens the next window.
         *
         * @return Button
         */
        private static Component createEntryButton() {
            JButton button = new JButton("Make a Purchase");
            button.setFocusPainted(false);
            button.setMargin(new Insets(12, 0, 12, 0));
            button.setPreferredSize(new Dimension(192, 48));
            button.addActionListener(e -> {
                openMainPanel();
            });

            return button;
        }

        /**
         * Creates the button that opens the exits the entire program.
         *
         * @param frame Reference to the frame that holds the entire program.
         * @return Button
         */
        private static Component createExitButton(JFrame frame) {
            JButton button = new JButton("Exit");
            button.setFocusPainted(false);
            button.setMargin(new Insets(12, 0, 12, 0));
            button.setPreferredSize(new Dimension(192, 48));
            button.addActionListener(e -> {
                frame.dispose();
            });

            return button;
        }

        private GreetingPanel(JFrame frame) {
            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.insets = new Insets(0, 0, -24, 0);

            this.add(createLogoLabel(), constraints);

            ++constraints.gridy;

            constraints.insets = new Insets(0, 0, 12, 0);

            this.add(createWelcomeLabel(), constraints);

            ++constraints.gridy;

            this.add(createEntryButton(), constraints);

            ++constraints.gridy;

            this.add(createExitButton(frame), constraints);

            constraints.insets = new Insets(0, 0, 0, 0);
        }
    }

    private static class MainPanel extends JPanel {
        private final EntrySubmenuPanel submenuPanel;
        private static String[][] data = {
                { "H.Code", "H.Item", "H.Price", "H.Count", "H.Total" },
                { "Code", "Item", "Price", "Count", "Total" },
        };

        @Override
        public Insets getInsets() {
            return new Insets(12, 12, 12, 12);
        }

        private MainPanel(JFrame frame) {
            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.anchor = GridBagConstraints.NORTHWEST;
            constraints.weightx = 1.0;

            this.add(createTopBar(), constraints);

            ++constraints.gridy;
            constraints.fill = GridBagConstraints.HORIZONTAL;

            this.add(createCategoryArea(), constraints);

            constraints.weighty = 1.0;
            this.add(new JLabel(""), constraints);

            ++constraints.gridy;
            constraints.weighty = 0.0;

            ++constraints.gridy;
            constraints.gridheight = GridBagConstraints.REMAINDER;

            this.add(submenuPanel = new EntrySubmenuPanel(frame), constraints);
        }

        private static Component createTopBar() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = generateConstraints();

            panel.add(createSmallLogo(), constraints);
            constraints.weightx = 1;
            ++constraints.gridx;

            panel.add(createTitle(), constraints);

            return panel;
        }

        private static Component createSmallLogo() {
            Image unscaledImage = originalIcon.getImage();
            Image newImage = unscaledImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(newImage);
            JLabel picLabel = new JLabel(scaledIcon);

            return picLabel;
        }

        private static Component createTitle() {
            JLabel jlabel = new JLabel("Uncle Andy's Pharmacy");
            jlabel.setFont(new Font(jlabel.getName(), Font.BOLD, 20));

            return jlabel;
        }

        private static Component categoryButton(int idx, Category category) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.insets = new Insets(0, 0, 0, 0);

            ImageIcon icon = getScaledIcon(42, 42);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(52, 52));
            button.addActionListener(e -> {
                openNavigationPanel(category);
            });

            panel.add(button, constraints);

            ++constraints.gridy;

            for (String line : category.title.split("\n")) {
                JLabel jLabel = new JLabel(line);
                panel.add(jLabel, constraints);

                ++constraints.gridy;
            }

            return panel;
        }

        private static Component createCategoryArea() {
            final int rowCount = 2;
            final int columnCount = Math.ceilDiv(categories.length, rowCount);

            JPanel gridPanel = new JPanel();
            gridPanel.setBorder(new CompoundBorder(new TitledBorder("System Database"), new EmptyBorder(8, 0, 0, 0)));
            gridPanel.setLayout(new GridBagLayout());

            GridBagConstraints rowConstraints = generateConstraints();
            rowConstraints.ipadx = 4;
            rowConstraints.ipady = 4;
            rowConstraints.weightx = 1.0;
            rowConstraints.weighty = 1.0;
            rowConstraints.insets = new Insets(8, 8, 8, 8);
            rowConstraints.anchor = GridBagConstraints.NORTH;
            rowConstraints.fill = GridBagConstraints.HORIZONTAL;

            for (int y = 0; y < rowCount; ++y) {
                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new GridBagLayout());

                GridBagConstraints columnConstraints = generateConstraints();
                columnConstraints.weightx = 1.0;
                columnConstraints.weighty = 1.0;
                columnConstraints.anchor = GridBagConstraints.NORTH;

                for (int x = 0; x < columnCount; ++x) {
                    int i = y * columnCount + x;

                    if (i >= categories.length) {
                        break;
                    }

                    Category category = categories[i];
                    Component button = categoryButton(i + 1, category);

                    rowPanel.add(button, columnConstraints);
                    ++columnConstraints.gridx;
                }

                gridPanel.add(rowPanel, rowConstraints);
                ++rowConstraints.gridy;
            }

            return gridPanel;
        }

        private static class EntrySubmenuPanel extends JPanel {
            private DefaultTableModel model;
            private JFrame frame;
            private JTable table;

            private EntrySubmenuPanel(JFrame frame) {
                this.frame = frame;
                this.setLayout(new GridBagLayout());
                this.setBackground(new Color(255, 0, 0));

                GridBagConstraints constraints = generateConstraints();
                constraints.weightx = 1;
                constraints.weighty = 1;
                constraints.fill = GridBagConstraints.BOTH;

                this.add(createSummaryArea(), constraints);

                constraints.insets = new Insets(0, 8, 0, 8);
                ++constraints.gridx;
                this.add(createRightSubmenu(), constraints);

                constraints.insets = new Insets(0, 0, 0, 0);
            }

            private void addRowToTable(int index,
                    String name,
                    int price,
                    int quantity,
                    int total) {
                String[] newRow = {
                        "Index (" + index + ")",
                        name,
                        Integer.toString(price),
                        Integer.toString(quantity),
                        Integer.toString(total),
                };
                model.addRow(newRow);
            }

            private JButton createExitButton() {
                JButton button = new JButton("Exit");
                button.addActionListener(e -> {
                    frame.dispose();
                });

                return button;
            }

            private Component createRightSubmenu() {
                JPanel panel = new JPanel();
                panel.setBorder(new CompoundBorder(new TitledBorder(""), new EmptyBorder(8, 0, 0, 0)));
                panel.setLayout(new GridBagLayout());
                panel.setBackground(new Color(230, 230, 230));

                GridBagConstraints constraints = generateConstraints();
                constraints.gridwidth = GridBagConstraints.REMAINDER;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.weightx = 1;

                JButton search = new JButton("Search Product");
                JButton confirm = new JButton("Confirm Purchases");
                JButton voidItem = new JButton("Void Item");
                JButton clear = new JButton("Clear Cart");
                JButton exit = createExitButton();

                panel.add(search, constraints);
                ++constraints.gridy;

                panel.add(confirm, constraints);
                ++constraints.gridy;

                panel.add(voidItem, constraints);
                ++constraints.gridy;

                panel.add(clear, constraints);
                ++constraints.gridy;

                panel.add(exit, constraints);
                ++constraints.gridy;

                JButton last = new JButton("Add dummy row");
                last.addActionListener(e -> {
                    /// Add item to table
                    addRowToTable(model.getRowCount(), "Name", 0, 1, 0);
                });
                panel.add(last, constraints);
                ++constraints.gridy;

                return panel;
            }

            private void setupTableModels() {
                final String[] columnNames = { "Code", "Item", "Price", "Quantity", "Total" };
                model = new DefaultTableModel(columnNames, 0);
                for (String[] row : data) {
                    model.addRow(row);
                }
                model.addTableModelListener(e -> table.revalidate());
            }

            private Component createSummaryArea() {
                JPanel panel = new JPanel();
                panel.setBorder(
                        new CompoundBorder(
                                new TitledBorder("Summary of Purchases"),
                                new EmptyBorder(8, 0, 0, 0)));
                panel.setLayout(new GridBagLayout());
                panel.setBackground(new Color(230, 230, 230));

                setupTableModels();

                table = new JTable(model);
                table.setEnabled(false);
                table.getTableHeader().setReorderingAllowed(false);
                table.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
                table.setFont(new Font("Segoe UI", Font.BOLD, 12));
                table.setRowHeight((int) Math.floor(table.getFont().getSize() * 2.5));

                TableColumnModel tcm = table.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                Enumeration<TableColumn> enumeration = tcm.getColumns();
                while (enumeration.hasMoreElements()) {
                    enumeration.nextElement().setCellRenderer(centerRenderer);
                }

                GridBagConstraints constraints = generateConstraints();
                constraints.gridwidth = GridBagConstraints.REMAINDER;
                constraints.fill = GridBagConstraints.BOTH;
                constraints.weightx = 1;
                constraints.weighty = 1;

                JScrollPane scrollPane = new JScrollPane(table);
                panel.add(scrollPane, constraints);

                return panel;
            }
        }
    }

    /// This is shown when a category button has been pressed.
    private static class NavigationPanel extends JPanel {
        @Override
        public Insets getInsets() {
            return new Insets(12, 12, 12, 12);
        }

        private Category category;

        public String getInstructions() {
            String instructions = category.getInstructions();

            if (instructions == null) {
                return "Lorem ipsum";
            } else {
                return instructions;
            }
        }

        private NavigationPanel(Category category, JFrame frame) {
            this.category = category;

            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.weightx = 0.0;
            constraints.weighty = 1.0;
            constraints.anchor = GridBagConstraints.NORTH;
            constraints.fill = GridBagConstraints.VERTICAL;

            this.add(createCategoryTitle(), constraints);

            ++constraints.gridy;

            this.add(createNavigationInstructions(), constraints);

            ++constraints.gridy;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            this.add(createProductList(), constraints);

            constraints.gridy += 2;
            constraints.anchor = GridBagConstraints.SOUTH;
            constraints.fill = GridBagConstraints.REMAINDER;
            this.add(createNavigationButtons(), constraints);

        }

        private Component createCategoryTitle() {
            JPanel panel = new JPanel();
            panel.setBackground(Color.LIGHT_GRAY);
            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = generateConstraints();
            constraints.weightx = 1;
            constraints.gridwidth = GridBagConstraints.REMAINDER;

            JLabel label = new JLabel(category.getTitle());
            label.setFont(new Font(label.getName(), Font.BOLD, 20));

            panel.add(label);

            return panel;
        }

        private Component createNavigationInstructions() {
            JPanel panel = new JPanel();
            panel.setBackground(Color.orange);
            panel.setLayout(new GridBagLayout());
            panel.setBorder(new CompoundBorder(new TitledBorder("Instructions"), new EmptyBorder(8, 0, 0, 0)));

            GridBagConstraints constraints = generateConstraints();
            constraints.weightx = 1;

            JLabel label = new JLabel(getInstructions());
            panel.add(label, constraints);

            return panel;
        }

        private Component createProductList() {
            JPanel panel = new JPanel();
            panel.setBackground(Color.BLUE);
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.anchor = GridBagConstraints.CENTER;

            for (int i = 0; i < category.getProducts().length; i++) {
                Product product = category.products[i];
                JButton button = new JButton("Product " + (i + 1));
                constraints.gridy = 0;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                // constraints.ipadx = 200;
                // constraints.ipady = 200;
                panel.add(button, constraints);

                constraints.gridy++;

                constraints.ipadx = 0;
                constraints.ipady = 0;
                JLabel name = new JLabel(product.getTitle());
                panel.add(name, constraints);

                constraints.gridy++;
                JLabel price = new JLabel(Double.toString(product.getPrice()));
                panel.add(price, constraints);

                constraints.gridx++;
            }

            return panel;
        }

        private Component createExitButton() {
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> {
            });

            return exitButton;
        }

        private Component createNavigationButtons() {
            JPanel panel = new JPanel();
            GridBagConstraints constraints = new GridBagConstraints();
            panel.setLayout(new GridBagLayout());

            JButton continueButton = new JButton("Continue");

            constraints.insets = new Insets(0, 50, 0, 50);
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1.0;
            constraints.ipadx = 100;
            constraints.ipady = 50;
            panel.add(continueButton, constraints);

            constraints.gridx += 2;

            panel.add(createExitButton(), constraints);

            return panel;
        }

    }

    /// Data classes

    public static class Category {
        private final String title;

        public String getTitle() {
            return title;
        }

        private final String name;

        public String getName() {
            return name;
        }

        private final String iconPath;

        public String getIconPath() {
            return iconPath;
        }

        private final String instructions;

        public String getInstructions() {
            return instructions;
        }

        private final Product[] products;

        public Product[] getProducts() {
            return products;
        }

        public Category(String title, String name, String iconPath, String instructions, Product[] products) {
            this.title = title;
            this.name = name;
            this.iconPath = iconPath;
            this.instructions = instructions;
            this.products = products;
        }
    }

    public static class Product {
        private final String title;

        public String getTitle() {
            return title;
        }

        private final String iconPath;

        public String getIconPath() {
            return iconPath;
        }

        private final String description;

        public String getDescription() {
            return description;
        }

        private final int price;

        public int getPrice() {
            return price;
        }

        public Product(String title, String iconPath, String description, int price) {
            this.title = title;
            this.iconPath = iconPath;
            this.description = description;
            this.price = price;
        }
    }

    /// Helper methods

    /**
     * Helper method that returns a [GridBagConstraints] with `gridx` and `gridy`
     * already set to 0.
     *
     * @return GridBagConstraints
     */
    private static GridBagConstraints generateConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;

        return constraints;
    }

}
