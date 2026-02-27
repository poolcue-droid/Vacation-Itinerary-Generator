import java.awt.*;
import java.util.List;
import javax.swing.*;

public class TravelGUI extends JFrame {

    private JTextField nameField, destField, budgetField, daysField, travelersField;
    private JTextArea outputArea;

    private final Color NAVY = new Color(12, 35, 64);
    private final Color BACKGROUND = new Color(236, 240, 245);

    public TravelGUI() {
        setTitle("Vacation Itinerary Generator");
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setBackground(NAVY);
        header.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel title = new JLabel("PERSONALIZED TRAVEL PLANNER");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI Semibold", Font.BOLD, 26));
        header.add(title);

        add(header, BorderLayout.NORTH);

        // ===== MAIN PANEL (LEFT FORM + RIGHT OUTPUT) =====
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 30, 30));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // ===== LEFT SIDE — INPUT FORM =====
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 15, 18));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 220)),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        nameField = addField(formPanel, "Name");
        destField = addField(formPanel, "Destination");
        budgetField = addField(formPanel, "Total Budget (₹)");
        daysField = addField(formPanel, "Number of Days");
        travelersField = addField(formPanel, "Number of Travelers");

        JButton generateBtn = new JButton("Generate Itinerary");
        generateBtn.setBackground(NAVY);
        generateBtn.setForeground(Color.WHITE);
        generateBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
        generateBtn.setFocusPainted(false);
        generateBtn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        formPanel.add(new JLabel());
        formPanel.add(generateBtn);

        mainPanel.add(formPanel);

        // ===== RIGHT SIDE — LARGE OUTPUT PANEL =====
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBackground(Color.WHITE);
        outputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel outputTitle = new JLabel("GENERATED ITINERARY");
        outputTitle.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        outputTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 230)));

        outputPanel.add(outputTitle, BorderLayout.NORTH);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(outputPanel);

        add(mainPanel, BorderLayout.CENTER);

        // ===== BUTTON ACTION =====
        generateBtn.addActionListener(e -> generatePlan());
    }

    private JTextField addField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));

        panel.add(label);
        panel.add(field);

        return field;
    }

    private void generatePlan() {
        try {
            String name = nameField.getText();
            String destination = destField.getText();
            int budget = Integer.parseInt(budgetField.getText());
            int days = Integer.parseInt(daysField.getText());
            int travelers = Integer.parseInt(travelersField.getText());

            int perDayBudget = budget / days;
            int hotelBudget = perDayBudget / 2;
            int activityBudget = perDayBudget / 2;

            List<Hotel> hotels = ItineraryGenerator.getHotels(destination, hotelBudget);
            List<Activity> activities = ItineraryGenerator.getActivities(destination, activityBudget);

            StringBuilder sb = new StringBuilder();

            sb.append("Client Name: ").append(name).append("\n");
            sb.append("Destination: ").append(destination).append("\n");
            sb.append("Travelers: ").append(travelers).append("\n");
            sb.append("Total Budget: ₹").append(budget).append("\n\n");

            sb.append("Recommended Hotels:\n");
            if (hotels.isEmpty()) {
                sb.append("  No hotels available within budget.\n");
            } else {
                for (Hotel h : hotels) {
                    sb.append("  - ").append(h.name)
                      .append(" (₹").append(h.pricePerNight).append(" per night)\n");
                }
            }

            sb.append("\nSuggested Activities:\n");
            if (activities.isEmpty()) {
                sb.append("  No activities available within budget.\n");
            } else {
                for (Activity a : activities) {
                    sb.append("  - ").append(a.name)
                      .append(" (₹").append(a.cost).append(")\n");
                }
            }

            sb.append("\nDay-wise Plan:\n");
            for (int i = 1; i <= days; i++) {
                sb.append("  Day ").append(i)
                  .append(": Explore ").append(destination)
                  .append(" and participate in selected activities.\n");
            }

            outputArea.setText(sb.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values for Budget, Days, and Travelers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TravelGUI().setVisible(true));
    }
}