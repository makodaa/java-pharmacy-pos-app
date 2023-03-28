package main.java;

import java.awt.*;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.*;

public class Main {
    private static final HashMap<String, JFrame> openedFrames = new HashMap<>();
    private static final ImageIcon originalIcon = new ImageIcon("./assets/logo.png");

    public static void main(String[] args) {
        setLookAndFeel();
        openGreeting();
    }

    private static interface PanelBuilder {
        JPanel run(JFrame frame);
    }

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

    public static void openGreeting() {
        final String panelCode = "GREETING_PANEL";
        final String panelName = "Greeting Panel";

        spawnPanel(panelCode, panelName, GreetingPanel::create);
    }

    public static void openMainPanel() {
        final String panelCode = "MAIN_PANEL";
        final String panelName = "Main Panel";

        spawnPanel(panelCode, panelName, MainPanel::create);
    }

    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class GreetingPanel extends JPanel {
        private JFrame frame;

        public static GreetingPanel create(JFrame frame) {
            return new GreetingPanel(frame);
        }

        private static JLabel createLogoLabel() {
            Image unscaledImage = originalIcon.getImage();
            Image newImage = unscaledImage.getScaledInstance(256, 256, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(newImage);
            JLabel picLabel = new JLabel(scaledIcon);

            return picLabel;
        }

        private static JPanel createWelcomeLabel() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;

            String label = """
                    Welcome to
                    Uncle Andy's Pharmacy
                      """;

            for (String line : label.split("\n")) {
                JLabel jlabel = new JLabel(line);
                jlabel.setFont(new Font(jlabel.getName(), Font.BOLD, 20));
                panel.add(jlabel, constraints);
                constraints.gridy += 1;
            }

            return panel;
        }

        private static Component createEntryButton(JFrame frame) {
            JPanel panel = new JPanel();

            JButton button = new JButton("Make a Purchase");
            button.setMargin(new Insets(12, 0, 12, 0));
            button.setPreferredSize(new Dimension(192, 48));
            button.addActionListener(e -> {
                openMainPanel();
            });

            panel.add(button);

            return panel;

            // return button;
        }

        private static Component createExitButton(JFrame frame) {
            JPanel panel = new JPanel();

            JButton button = new JButton("Exit");
            button.setMargin(new Insets(12, 0, 12, 0));
            button.setPreferredSize(new Dimension(192, 48));
            button.addActionListener(e -> {
                frame.dispose();
            });
            panel.add(button);

            return panel;
        }

        private GreetingPanel(JFrame frame) {
            this.frame = frame;

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

            this.add(createEntryButton(frame), constraints);

            constraints.gridy += 1;

            this.add(createExitButton(frame), constraints);

            constraints.insets = new Insets(0, 0, 0, 0);
        }
    }

    private static class MainPanel extends JPanel {
        private JFrame frame;

        public static MainPanel create(JFrame frame) {
            return new MainPanel(frame);
        }

        @Override
        public Insets getInsets() {
            return new Insets(12, 12, 12, 12);
        }

        private MainPanel(JFrame frame) {
            this.frame = frame;

            this.setLayout(new GridBagLayout());
            this.setBackground(new Color(255, 0, 0));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.anchor = GridBagConstraints.NORTHWEST;
            constraints.weighty = 1.0;
            constraints.weightx = 1.0;

            this.add(createTopBar(), constraints);

            constraints.gridy += 1;

            this.add(createCategoryArea(), constraints);
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

        private static Component createCategoryArea() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBorder(new CompoundBorder(new TitledBorder("System Database"), new EmptyBorder(8, 0, 0, 0)));
            panel.setBackground(new Color(225, 225, 225));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.fill = GridBagConstraints.BOTH;

            panel.add(new JLabel("Hello world!"), constraints);

            return panel;
        }
    }
}
