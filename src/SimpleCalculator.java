import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField num1Field;
    private JTextField num2Field;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JLabel resultLabel;

    // Constants for colors and sizes
    private static final Color FRAME_BACKGROUND = new Color(230, 230, 250); // Light Lavender
    private static final Color FIELD_BACKGROUND = Color.WHITE;
    private static final Color BUTTON_BACKGROUND = new Color(173, 216, 230); // Light Blue
    private static final Color LABEL_FOREGROUND = new Color(0, 0, 139); // Dark Blue
    private static final int ICON_SIZE = 80;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(400, 500); // Increased height for image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for main frame

        // 1. Set Frame Background Color
        getContentPane().setBackground(FRAME_BACKGROUND);

        // 2. Create Custom Calculator Icon
        BufferedImage calculatorImage = createCalculatorIcon(ICON_SIZE);
        ImageIcon calculatorIcon = new ImageIcon(calculatorImage);

        // Panel for the icon
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        iconPanel.setBackground(FRAME_BACKGROUND);
        iconPanel.add(new JLabel(calculatorIcon));

        // Add icon panel to the top of the main frame
        add(iconPanel, BorderLayout.NORTH);

        // --- Panel for input fields, buttons, and result ---
        // Use a central panel with GridLayout for the rest of the components
        JPanel centerPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // Adjusted GridLayout rows
        centerPanel.setBackground(FRAME_BACKGROUND);

        // Components Initialization
        JLabel num1Label = new JLabel("Number 1:");
        num1Field = new JTextField();
        JLabel num2Label = new JLabel("Number 2:");
        num2Field = new JTextField();

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");

        resultLabel = new JLabel("Result: ");

        // Customize Component Colors
        num1Field.setBackground(FIELD_BACKGROUND);
        num1Field.setForeground(Color.BLACK);
        num2Field.setBackground(FIELD_BACKGROUND);
        num2Field.setForeground(Color.BLACK);

        resultLabel.setForeground(LABEL_FOREGROUND);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font for result

        // Style buttons
        addButton.setBackground(BUTTON_BACKGROUND);
        addButton.setForeground(Color.BLACK);
        subtractButton.setBackground(BUTTON_BACKGROUND);
        subtractButton.setForeground(Color.BLACK);
        multiplyButton.setBackground(BUTTON_BACKGROUND);
        multiplyButton.setForeground(Color.BLACK);
        divideButton.setBackground(BUTTON_BACKGROUND);
        divideButton.setForeground(Color.BLACK);

        // Make buttons opaque so their background color is visible
        addButton.setOpaque(true);
        subtractButton.setOpaque(true);
        multiplyButton.setOpaque(true);
        divideButton.setOpaque(true);

        // Add components to the center panel
        centerPanel.add(num1Label);
        centerPanel.add(num1Field);
        centerPanel.add(num2Label);
        centerPanel.add(num2Field);

        // Panel for operation buttons (to center them nicely)
        JPanel opButtonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        opButtonContainer.setBackground(FRAME_BACKGROUND);
        opButtonContainer.add(addButton);
        opButtonContainer.add(subtractButton);
        opButtonContainer.add(multiplyButton);
        opButtonContainer.add(divideButton);

        centerPanel.add(opButtonContainer); // This will take up one cell in the GridLayout
        centerPanel.add(new JLabel("")); // Empty cell to fill the grid

        centerPanel.add(resultLabel);
        centerPanel.add(new JLabel("")); // Empty cell for alignment

        // Add the central panel to the frame's center
        add(centerPanel, BorderLayout.CENTER);

        // Add action listeners
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);

        // Make the frame visible
        setVisible(true);
    }

    // Helper method to create the calculator icon
    private BufferedImage createCalculatorIcon(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Set rendering hints for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Draw background for the icon
        g2d.setColor(new Color(240, 240, 240)); // Light gray background
        g2d.fillRect(0, 0, size, size);

        // Draw calculator body
        g2d.setColor(new Color(100, 100, 100)); // Darker gray for body
        int bodyMargin = size / 8;
        g2d.fillRoundRect(bodyMargin, bodyMargin, size - 2 * bodyMargin, size - 2 * bodyMargin, size / 4, size / 4);

        // Draw screen area
        g2d.setColor(new Color(180, 210, 230)); // Light blue screen
        int screenMargin = size / 4;
        int screenWidth = size - 2 * screenMargin;
        int screenHeight = size / 4;
        g2d.fillRect(screenMargin, screenMargin, screenWidth, screenHeight);

        // Draw some buttons on the calculator icon
        g2d.setColor(new Color(150, 150, 150)); // Medium gray for buttons
        int buttonWidth = screenWidth / 3;
        int buttonHeight = size / 8;
        int buttonY = screenMargin + screenHeight + size / 16;
        g2d.fillRoundRect(screenMargin, buttonY, buttonWidth, buttonHeight, 5, 5);
        g2d.fillRoundRect(screenMargin + buttonWidth + size / 16, buttonY, buttonWidth, buttonHeight, 5, 5);
        g2d.fillRoundRect(screenMargin + 2 * buttonWidth + 2 * size / 16, buttonY, buttonWidth, buttonHeight, 5, 5);

        g2d.dispose();
        return image;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = 0;
            String operation = "";
            String equation = "";

            if (e.getSource() == addButton) {
                result = num1 + num2;
                operation = "+";
                equation = num1 + " + " + num2;
            } else if (e.getSource() == subtractButton) {
                result = num1 - num2;
                operation = "-";
                equation = num1 + " - " + num2;
            } else if (e.getSource() == multiplyButton) {
                result = num1 * num2;
                operation = "*";
                equation = num1 + " * " + num2;
            } else if (e.getSource() == divideButton) {
                if (num2 == 0) {
                    resultLabel.setText("Error: Div by zero");
                    // Show error in dialog as well for consistency
                    showErrorDialog("Division by zero is not allowed.");
                    return;
                }
                result = num1 / num2;
                operation = "/";
                equation = num1 + " / " + num2;
            }

            // Update main window label
            resultLabel.setText("Result: " + result);

            // --- Show result dialog with image ---
            showResultDialog(equation, result);

        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid input");
            showErrorDialog("Please enter valid numbers for both inputs.");
        } catch (Exception ex) {
            resultLabel.setText("Error: Unexpected");
            ex.printStackTrace();
            showErrorDialog("An unexpected error occurred: " + ex.getMessage());
        }
    }

    // Method to show the result dialog
    private void showResultDialog(String equation, double result) {
        int imageSize = 50;
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imageSize, imageSize);

        g2d.setColor(Color.GREEN.darker());
        g2d.fillOval(5, 5, imageSize - 10, imageSize - 10);
        g2d.dispose();
        ImageIcon successIcon = new ImageIcon(image);

        JDialog resultDialog = new JDialog(this, "Calculation Result", true);
        resultDialog.setLayout(new BorderLayout());
        resultDialog.setSize(350, 180);
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel dialogMessageLabel = new JLabel("<html><body><p align='center'>Calculation:<br>" + equation + " = " + result + "</p></body></html>", SwingConstants.CENTER);
        JLabel imageLabel = new JLabel(successIcon, SwingConstants.CENTER);

        resultDialog.add(dialogMessageLabel, BorderLayout.NORTH);
        resultDialog.add(imageLabel, BorderLayout.CENTER);

        resultDialog.setLocationRelativeTo(this);
        resultDialog.setVisible(true);
    }

    // Method to show an error dialog
    private void showErrorDialog(String message) {
        // Using a standard error icon
        Icon generalErrorIcon = UIManager.getIcon("OptionPane.errorIcon"); // Get as Icon
        ImageIcon errorIcon = null; // Initialize as null

        // Attempt to create an ImageIcon from the Icon
        if (generalErrorIcon instanceof ImageIcon) {
            errorIcon = (ImageIcon) generalErrorIcon;
        } else {
            // If it's not an ImageIcon, create one by painting the Icon onto a BufferedImage
            int iconWidth = generalErrorIcon.getIconWidth();
            int iconHeight = generalErrorIcon.getIconHeight();
            // Use TYPE_INT_ARGB for transparency support if needed
            BufferedImage img = new BufferedImage(iconWidth, iconHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            generalErrorIcon.paintIcon(null, g2d, 0, 0); // Paint the icon onto the image
            g2d.dispose();
            errorIcon = new ImageIcon(img);
        }

        JDialog errorDialog = new JDialog(this, "Error", true);
        errorDialog.setLayout(new BorderLayout());
        errorDialog.setSize(350, 180);
        errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel dialogMessageLabel = new JLabel("<html><body><p align='center'>" + message + "</p></body></html>", SwingConstants.CENTER);
        JLabel imageLabel = new JLabel(errorIcon, SwingConstants.CENTER); // Use the ImageIcon

        errorDialog.add(dialogMessageLabel, BorderLayout.NORTH);
        errorDialog.add(imageLabel, BorderLayout.CENTER);

        errorDialog.setLocationRelativeTo(this);
        errorDialog.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleCalculator();
            }
        });
    }
}
