package main.java;

import java.awt.*;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Main {
    private static final HashMap<String, JFrame> openedFrames = new HashMap<>();
    private static final HashMap<String, ImageIcon> cachedIcons = new HashMap<>();
    private static final ImageIcon originalIcon = new ImageIcon("./assets/logo.png");

    private static final Category[] categories = new Category[] {
            new Category(
                    "Pain\nRelievers",
                    null,
                    new Product[] {}),
            new Category("Antibiotics",
                    null,
                    new Product[] {}),
            new Category("Anti\nAllergy",
                    null,
                    new Product[] {}),
            new Category("Respiratory\nMedicine",
                    null,
                    new Product[] {}),
            new Category("Fever\nMedicine",
                    null,
                    new Product[] {}),
            new Category("Vitamins",
                    null,
                    new Product[] {}),
            new Category(
                    "Dietary\nSupp.",
                    null,
                    new Product[] {}),
            new Category("Mineral\nSupp.",
                    null,
                    new Product[] {}),
            new Category("Bandages",
                    null,
                    new Product[] {}),
            new Category("Cotton\nItems",
                    null,
                    new Product[] {}),
            new Category("Antiseptics",
                    null,
                    new Product[] {}),
            new Category("Personal\nHygiene",
                    null,
                    new Product[] {}),
            new Category("Surgical\nEquipment",
                    null,
                    new Product[] {}),
            new Category("Assistive\n Devices",
                    null,
                    new Product[] {}),
    };

    public static void main(String[] args) {
        setLookAndFeel();
        // openGreeting();
        openMainPanel();
        openNavigationPanel();
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
    public static void openNavigationPanel() {
        final String panelCode = "PRODUCT_NAVIGATION_PANEL";
        final String panelName = "Product Navigation Panel";

        spawnPanel(panelCode, panelName, (frame) -> new NavigationPanel(frame));
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
     * A function that returns a cached version of the scaled icon.
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
        if (cachedIcons.containsKey(key)) {
            return cachedIcons.get(key);
        }

        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);

        /// After creating the new instance, put it in the cache.
        cachedIcons.put(key, icon);

        return icon;
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

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;

            /// This is hard coded.
            String label = """
                    Welcome to
                    Uncle Andy's Pharmacy
                      """;
            String[] lines = label.split("\n");

            for (String line : lines) {
                JLabel jLabel = new JLabel(line);

                /// Create a copy of the font with the set font style and font size.
                Font boldFont = new Font(jLabel.getName(), Font.BOLD, 20);

                jLabel.setFont(boldFont);
                panel.add(jLabel, constraints);

                constraints.gridy += 1;
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
            button.setMargin(new Insets(12, 0, 12, 0));
            button.setPreferredSize(new Dimension(192, 48));
            button.addActionListener(e -> {
                frame.dispose();
            });

            return button;
        }

        private GreetingPanel(JFrame frame) {
            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;

            constraints.insets = new Insets(0, 0, -24, 0);

            this.add(createLogoLabel(), constraints);

            constraints.gridy += 1;

            constraints.insets = new Insets(0, 0, 12, 0);

            this.add(createWelcomeLabel(), constraints);

            constraints.gridy += 1;

            this.add(createEntryButton(), constraints);

            constraints.gridy += 1;

            this.add(createExitButton(frame), constraints);

            constraints.insets = new Insets(0, 0, 0, 0);
        }
    }

    private static class MainPanel extends JPanel {
        private final EntrySubmenuPanel submenuPanel;
        private static String[][] data = {
                { "H.Index", "H.Item", "H.Price", "H.Count", "H.Total" },
                { "Index", "Item", "Price", "Count", "Total" },
                { "Index", "Item", "Price", "Count", "Total" },
        };

        @Override
        public Insets getInsets() {
            return new Insets(12, 12, 12, 12);
        }

        private MainPanel(JFrame frame) {
            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.anchor = GridBagConstraints.NORTHWEST;
            constraints.weightx = 1.0;

            this.add(createTopBar(), constraints);

            constraints.gridy += 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;

            this.add(createCategoryArea(), constraints);

            constraints.weighty = 1.0;
            this.add(new JLabel(""), constraints);
            constraints.gridy += 1;
            constraints.weighty = 0.0;

            constraints.gridy += 1;
            constraints.gridheight = GridBagConstraints.REMAINDER;

            this.add(submenuPanel = new EntrySubmenuPanel(), constraints);
        }

        private static Component createTopBar() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;

            panel.add(createSmallLogo(), constraints);
            constraints.gridx += 1;
            constraints.weightx = 1;

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

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.insets = new Insets(0, 0, 0, 0);

            ImageIcon icon = getScaledIcon(42, 42);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(52, 52));

            panel.add(button, constraints);

            ++constraints.gridy;

            for (String line : category.title.split("\n")) {
                JLabel jLabel = new JLabel(line);
                panel.add(jLabel, constraints);

                constraints.gridy += 1;
            }

            return panel;
        }

        private static Component createCategoryArea() {
            final int rowCount = 2;
            final int columnCount = categories.length / rowCount;

            JPanel panel = new JPanel();
            panel.setBorder(new CompoundBorder(new TitledBorder("System Database"), new EmptyBorder(8, 0, 0, 0)));
            panel.setLayout(new GridBagLayout());
            panel.setBackground(new Color(230, 230, 230));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.ipadx = 4;
            constraints.ipady = 4;
            constraints.weightx = 1.0;
            constraints.weighty = 1.0;
            constraints.insets = new Insets(8, 8, 8, 8);
            constraints.anchor = GridBagConstraints.NORTH;

            for (int y = 0; y < rowCount; ++y) {
                for (int x = 0; x < columnCount; ++x) {
                    int i = y * columnCount + x;
                    Category category = categories[i];

                    panel.add(categoryButton(i + 1, category), constraints);
                    ++constraints.gridx;
                }
                constraints.gridx = 0;
                ++constraints.gridy;
            }

            return panel;
        }

        private static class EntrySubmenuPanel extends JPanel {
            private DefaultTableModel model;
            private JTable table;

            private EntrySubmenuPanel() {
                this.setLayout(new GridBagLayout());
                this.setBackground(new Color(255, 0, 0));

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.weightx = 1;
                constraints.weighty = 1;
                constraints.fill = GridBagConstraints.BOTH;

                this.add(createSummaryArea(), constraints);

                constraints.insets = new Insets(0, 8, 0, 0);
                constraints.gridx += 1;
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

            private Component createRightSubmenu() {
                JPanel panel = new JPanel();
                panel.setBorder(new CompoundBorder(new TitledBorder("System Database"), new EmptyBorder(8, 0, 0, 0)));
                panel.setLayout(new GridBagLayout());
                panel.setBackground(new Color(230, 230, 230));
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.gridwidth = GridBagConstraints.REMAINDER;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.weightx = 1;

                panel.add(new JButton("First"), constraints);
                constraints.gridy += 1;

                panel.add(new JButton("Second"), constraints);
                constraints.gridy += 1;

                panel.add(new JButton("Third"), constraints);
                constraints.gridy += 1;

                JButton last = new JButton("Add dummy row");
                last.addActionListener(e -> {
                    /// Add item to table
                    addRowToTable(model.getRowCount(), "Name", 0, 1, 0);
                });
                panel.add(last, constraints);
                constraints.gridy += 1;

                return panel;
            }

            private Component createSummaryArea() {
                JPanel panel = new JPanel();
                panel.setBorder(new CompoundBorder(
                        new TitledBorder("Summary of Purchases"),
                        new EmptyBorder(8, 0, 0, 0)));
                panel.setLayout(new GridBagLayout());
                panel.setBackground(new Color(230, 230, 230));

                String[] columnNames = { "Index", "Item", "Price", "Quantity", "Total" };

                model = new DefaultTableModel(columnNames, 0);
                for (String[] row : data) {
                    model.addRow(row);
                }
                model.addTableModelListener(e -> table.revalidate());

                table = new JTable(model);
                table.setEnabled(false);
                table.getTableHeader().setReorderingAllowed(false);
                table.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                for (int i = 0; i < columnNames.length; i++) {
                    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.gridy = 0;
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

    private static class NavigationPanel extends JPanel {
        @Override
        public Insets getInsets() {
            return new Insets(12, 12, 12, 12);
        }

        private static String instructions = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut aliquet massa sed odio rutrum vestibulum. Nulla facilisi. Praesent vel suscipit est. Fusce ullamcorper at mi ac pellentesque. Suspendisse quis imperdiet velit. Donec accumsan augue et ornare vulputate. Vestibulum id tellus egestas, aliquet odio eu, condimentum sem. Aenean facilisis cursus augue a placerat.";

        private NavigationPanel(JFrame frame) {
            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 0.0;
            constraints.weighty = 1.0;
            constraints.anchor = GridBagConstraints.NORTH;
            constraints.fill = GridBagConstraints.VERTICAL;

            this.add(createCategoryTitle(), constraints);

            constraints.gridy += 1;

            this.add(createNavigationInstructions(), constraints);

            constraints.gridy += 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            this.add(createProductList(), constraints);

            constraints.gridy += 2;
            constraints.anchor = GridBagConstraints.SOUTH;
            constraints.fill = GridBagConstraints.REMAINDER;
            this.add(createNavigationButtons(), constraints);

        }

        private static Component createCategoryTitle() {
            JPanel panel = new JPanel();
            panel.setBackground(Color.LIGHT_GRAY);
            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1;
            constraints.gridwidth = GridBagConstraints.REMAINDER;

            JLabel label = new JLabel("Insert Category Name Here");
            label.setFont(new Font(label.getName(), Font.BOLD, 20));

            panel.add(label);

            return panel;
        }

        private static Component createNavigationInstructions() {
            JPanel panel = new JPanel();
            panel.setBackground(Color.orange);
            panel.setLayout(new GridBagLayout());
            panel.setBorder(new CompoundBorder(new TitledBorder("Instructions"), new EmptyBorder(8, 0, 0, 0)));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1;

            JLabel label = new JLabel(instructions);
            panel.add(label, constraints);

            return panel;
        }

        private static Component createProductList() {
            JPanel panel = new JPanel();
            panel.setBackground(Color.BLUE);
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.anchor = GridBagConstraints.CENTER;

            for (int i = 0; i <= 2; i++) {
                JButton button = new JButton("Product " + i);
                constraints.gridy = 0;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.ipadx = 200;
                constraints.ipady = 200;
                panel.add(button, constraints);

                constraints.gridy++;

                constraints.ipadx = 0;
                constraints.ipady = 0;
                JLabel name = new JLabel("Product " + i + " Name");
                panel.add(name, constraints);

                constraints.gridy++;
                JLabel price = new JLabel("Price");
                panel.add(price, constraints);

                constraints.gridx++;
            }

            return panel;
        }

        private static Component createNavigationButtons() {
            JPanel panel = new JPanel();
            panel.setBackground(Color.RED);
            GridBagConstraints constraints = new GridBagConstraints();
            panel.setLayout(new GridBagLayout());

            JButton continueButton = new JButton("Continue");
            JButton exitButton = new JButton("Exit");

            constraints.insets = new Insets(0, 50, 0, 50);
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1.0;
            constraints.ipadx = 100;
            constraints.ipady = 50;
            panel.add(continueButton, constraints);

            constraints.gridx += 2;

            panel.add(exitButton, constraints);

            return panel;
        }

    }

    public static class Category {
        private String title;

        public String getTitle() {
            return title;
        }

        private String iconPath;

        public String getIconPath() {
            return iconPath;
        }

        private Product[] products;

        public Product[] getProducts() {
            return products;
        }

        public Category(String title, String iconPath, Product[] products) {
            this.title = title;
            this.iconPath = iconPath;
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

        public Product(String title, String iconPath, String description, int price) {
            this.title = title;
            this.iconPath = iconPath;
            this.description = description;
        }
    }
}
