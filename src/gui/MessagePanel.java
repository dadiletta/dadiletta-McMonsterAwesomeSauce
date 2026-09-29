package gui;
import javax.swing.*;
import java.awt.*;

/**
 * MessagePanel - Displays battle messages and game events
 * 
 * Shows the past three messages in a scrolling display area
 */
public class MessagePanel extends JPanel {
    
    private JLabel oldestMessageLabel;
    private JLabel previousMessageLabel;
    private JLabel currentMessageLabel;
    
    // Visual constants
    private static final Color PANEL_BG = new Color(30, 30, 40);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color PREVIOUS_TEXT_COLOR = new Color(180, 180, 180);
    private static final Color OLDEST_TEXT_COLOR = new Color(120, 120, 120);
    private static final Font MESSAGE_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font PREVIOUS_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Font OLDEST_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final int PANEL_HEIGHT = 100;
    
    /**
     * Constructor
     */
    public MessagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(0, PANEL_HEIGHT));
        setBackground(PANEL_BG);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.CYAN, 2),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        
        // Oldest message label (most dimmed)
        oldestMessageLabel = new JLabel("", SwingConstants.CENTER);
        oldestMessageLabel.setFont(OLDEST_FONT);
        oldestMessageLabel.setForeground(OLDEST_TEXT_COLOR);
        oldestMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Previous message label (middle, dimmed)
        previousMessageLabel = new JLabel("", SwingConstants.CENTER);
        previousMessageLabel.setFont(PREVIOUS_FONT);
        previousMessageLabel.setForeground(PREVIOUS_TEXT_COLOR);
        previousMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Current message label (newest, brightest)
        currentMessageLabel = new JLabel("Welcome to Monster Battle!", SwingConstants.CENTER);
        currentMessageLabel.setFont(MESSAGE_FONT);
        currentMessageLabel.setForeground(TEXT_COLOR);
        currentMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(oldestMessageLabel);
        add(Box.createVerticalStrut(3));
        add(previousMessageLabel);
        add(Box.createVerticalStrut(5));
        add(currentMessageLabel);
    }
    
    /**
     * Set the message to display (shifts all messages down)
     * @param message The message text
     */
    public void setMessage(String message) {
        // Shift messages: current -> previous -> oldest
        oldestMessageLabel.setText(previousMessageLabel.getText());
        oldestMessageLabel.setForeground(OLDEST_TEXT_COLOR);
        
        previousMessageLabel.setText(currentMessageLabel.getText());
        previousMessageLabel.setForeground(PREVIOUS_TEXT_COLOR);
        
        // Set new current message
        currentMessageLabel.setText(Emoji.fit(message, MESSAGE_FONT));
        currentMessageLabel.setForeground(TEXT_COLOR);
    }
    
    /**
     * Set message with custom color
     * @param message The message text
     * @param color The text color
     */
    public void setMessage(String message, Color color) {
        // Shift messages: current -> previous -> oldest
        oldestMessageLabel.setText(previousMessageLabel.getText());
        oldestMessageLabel.setForeground(OLDEST_TEXT_COLOR);
        
        previousMessageLabel.setText(currentMessageLabel.getText());
        previousMessageLabel.setForeground(PREVIOUS_TEXT_COLOR);
        
        // Set new current message with color
        currentMessageLabel.setText(Emoji.fit(message, MESSAGE_FONT));
        currentMessageLabel.setForeground(color);
    }
    
    /**
     * Clear all messages
     */
    public void clearMessage() {
        oldestMessageLabel.setText("");
        previousMessageLabel.setText("");
        currentMessageLabel.setText("");
    }
}