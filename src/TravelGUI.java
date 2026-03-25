import java.awt.*;
import java.io.File;
import java.util.List;
import javax.swing.*;

public class TravelGUI extends JFrame {

    private JTextField nameField, destField, budgetField, daysField;
    private JComboBox<String> sortBox, typeBox;

    private final Color NAVY = new Color(12, 35, 64);
    private final Color BACKGROUND = new Color(236, 240, 245);

    public TravelGUI() {
        setTitle("Vacation Itinerary Generator - Input");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setBackground(NAVY);
        header.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        JLabel title = new JLabel("TRAVEL ITINERARY INPUT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);

        nameField = addField(formPanel, "Name");
        destField = addField(formPanel, "Destination");
        budgetField = addField(formPanel, "Total Budget (₹)");
        daysField = addField(formPanel, "Number of Days");

        formPanel.add(new JLabel("Sort Hotels"));
        String[] sortOptions = {"None", "Price Ascending", "Price Descending"};
        sortBox = new JComboBox<>(sortOptions);
        formPanel.add(sortBox);

        formPanel.add(new JLabel("Filter Activities"));
        String[] typeOptions = {"All", "Adventure", "Culture", "Relaxation"};
        typeBox = new JComboBox<>(typeOptions);
        formPanel.add(typeBox);

        JButton generateBtn = new JButton("Generate Itinerary");
        generateBtn.setBackground(NAVY);
        generateBtn.setForeground(Color.WHITE);
        generateBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
        generateBtn.setFocusPainted(false);
        formPanel.add(new JLabel());
        formPanel.add(generateBtn);

        add(formPanel, BorderLayout.CENTER);

        // ===== BUTTON ACTION =====
        generateBtn.addActionListener(e -> generateItineraryWindow());
    }

    private JTextField addField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label);
        panel.add(field);
        return field;
    }

    private void generateItineraryWindow() {
        try {
            String name = nameField.getText();
            String destination = destField.getText();
            int budget = Integer.parseInt(budgetField.getText());
            int days = Integer.parseInt(daysField.getText());

            int perDayBudget = budget / days;
            int hotelBudget = perDayBudget / 2;
            int activityBudget = perDayBudget / 2;

            // Sort & Filter
            String sortOrder = null;
            if (sortBox.getSelectedIndex() == 1) sortOrder = "asc";
            else if (sortBox.getSelectedIndex() == 2) sortOrder = "desc";

            String typeFilter = null;
            if (typeBox.getSelectedIndex() != 0) typeFilter = (String) typeBox.getSelectedItem();

            List<Hotel> hotels = ItineraryGenerator.getHotels(destination, hotelBudget, sortOrder);
            List<Activity> activities = ItineraryGenerator.getActivities(destination, activityBudget, typeFilter);

            // ===== OUTPUT WINDOW =====
            JFrame outputFrame = new JFrame("Generated Itinerary - " + destination);
            outputFrame.setSize(800, 700);
            outputFrame.setLocationRelativeTo(this);

            // Horizontal split: left = itinerary, right = images
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            splitPane.setDividerLocation(400);
            splitPane.setResizeWeight(0.5);
            outputFrame.add(splitPane);

            // ===== LEFT PANEL: ITINERARY TEXT =====
            JTextArea itineraryText = new JTextArea();
            itineraryText.setEditable(false);
            itineraryText.setFont(new Font("Consolas", Font.PLAIN, 14));
            itineraryText.setLineWrap(true);
            itineraryText.setWrapStyleWord(true);

            StringBuilder sb = new StringBuilder();
            sb.append("Client Name: ").append(name).append("\n");
            sb.append("Destination: ").append(destination).append("\n");
            sb.append("Total Budget: ₹").append(budget).append("\n");
            sb.append("Hotel Budget: ₹").append(hotelBudget).append("\n");
            sb.append("Activity Budget: ₹").append(activityBudget).append("\n\n");

            sb.append("--- Recommended Hotels ---\n");
            if (hotels.isEmpty()) sb.append("No hotels within budget.\n");
            else for (Hotel h : hotels)
                sb.append(h.name).append(" - ₹").append(h.pricePerNight).append("\n");

            sb.append("\n--- Suggested Activities ---\n");
            if (activities.isEmpty()) sb.append("No activities within budget.\n");
            else for (Activity a : activities)
                sb.append(a.name).append(" - ₹").append(a.cost).append(" Type: ").append(a.type).append("\n");

            sb.append("\n--- Day-wise Plan ---\n");
            for (int i = 0; i < days; i++) {
                if (activities.isEmpty()) break;
                Activity act = activities.get(i % activities.size());
                sb.append("Day ").append(i + 1).append(": ").append(act.name)
                        .append(" (₹").append(act.cost).append(") Type: ").append(act.type).append("\n");
            }

            itineraryText.setText(sb.toString());
            JScrollPane textScroll = new JScrollPane(itineraryText);
            splitPane.setLeftComponent(textScroll);

            // ===== RIGHT PANEL: IMAGES VERTICALLY =====
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
            imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            for (Hotel h : hotels) addImage(imagePanel, h.image, h.name);
            for (Activity a : activities) addImage(imagePanel, a.image, a.name);

            JScrollPane imageScroll = new JScrollPane(imagePanel);
            splitPane.setRightComponent(imageScroll);

            outputFrame.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values for Budget and Days.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addImage(JPanel panel, String path, String labelText) {
        File f = new File(path);
        if (!f.exists()) path = "src/images/placeholder.jpg"; // fallback image

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel imgLabel = new JLabel();
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(scaled));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(labelText, SwingConstants.CENTER);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(imgLabel);
        p.add(Box.createRigidArea(new Dimension(0, 5)));
        p.add(nameLabel);
        p.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(p);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TravelGUI().setVisible(true));
    }
}