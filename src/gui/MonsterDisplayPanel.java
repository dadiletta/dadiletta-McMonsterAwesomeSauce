package gui;

import javax.swing.*;
import game.Monster;
import java.awt.*;
import java.util.ArrayList;

/**
 * MonsterDisplayPanel - Displays monster tiles in a responsive grid
 * Cyberpunk HUD Theme Edition: Features glowing borders, neon accents, dark gradients,
 * and high-contrast telemetry panels while maintaining full compatibility with the host app.
 */
public class MonsterDisplayPanel extends JPanel {
    
    private ArrayList<Monster> monsters;
    private ArrayList<MonsterTile> tiles;
    private int highlightedIndex = -1;
    
    // Cyberpunk Visual Constants
    private static final Color BG_DARK = new Color(10, 11, 16);
    private static final Color GRID_LINE_COLOR = new Color(0, 240, 255, 12);
    private static final Color COLOR_NEON_CYAN = new Color(0, 240, 255);
    private static final Color COLOR_NEON_PINK = new Color(255, 0, 127);
    private static final Color COLOR_NEON_YELLOW = new Color(255, 230, 0);
    private static final Color COLOR_NEON_GREEN = new Color(0, 255, 136);
    private static final Color COLOR_TILE_BORDER = new Color(35, 45, 65);
    private static final Color COLOR_CYAN_GLOW = new Color(0, 240, 255, 80);
    private static final Color HEALTH_BAR_BG = new Color(15, 18, 28);
    
    private static final Font FONT_TITLE = new Font("Consolas", Font.BOLD, 16);
    private static final Font FONT_MONO = new Font("Consolas", Font.BOLD, 12);
    private static final Font FONT_MONO_SMALL = new Font("Consolas", Font.BOLD, 11);

    private static final int TILE_SPACING = 12;
    
    /**
     * Constructor
     */
    public MonsterDisplayPanel() {
        monsters = new ArrayList<>();
        tiles = new ArrayList<>();
        setBackground(BG_DARK);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_NEON_CYAN, 2),
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                "// SYSTEM HUD :: TARGET MONSTERS",
                0, 0, FONT_TITLE, COLOR_NEON_CYAN
            )
        ));
    }
    
    /**
     * Set the monsters to display
     * 
     * @param monsters The list of monsters
     */
    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
        this.highlightedIndex = -1;
        createTiles();
    }
    
    /**
     * Highlight a specific monster
     * 
     * @param index The index to highlight (-1 for none)
     */
    public void highlightMonster(int index) {
        this.highlightedIndex = index;
        repaint();
    }
    
    /**
     * Create tiles for all monsters
     */
    private void createTiles() {
        removeAll();
        tiles.clear();
        
        if (monsters == null || monsters.isEmpty()) {
            JLabel emptyLabel = new JLabel("// SYSTEM OFFLINE: NO TARGETS DETECTED //");
            emptyLabel.setFont(FONT_TITLE);
            emptyLabel.setForeground(COLOR_NEON_CYAN);
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(emptyLabel);
            revalidate();
            repaint();
            return;
        }
        
        // Determine grid layout
        int monsterCount = monsters.size();
        int cols = (int) Math.ceil(Math.sqrt(monsterCount));
        int rows = (int) Math.ceil((double) monsterCount / cols);
        
        setLayout(new GridLayout(rows, cols, TILE_SPACING, TILE_SPACING));
        
        // Create a tile for each monster
        for (int i = 0; i < monsters.size(); i++) {
            MonsterTile tile = new MonsterTile(monsters.get(i), i);
            tiles.add(tile);
            add(tile);
        }
        
        revalidate();
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Paint faint Cyberpunk grid lines in background
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(GRID_LINE_COLOR);
        int gridSpacing = 24;
        for (int x = 0; x < getWidth(); x += gridSpacing) {
            g2d.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += gridSpacing) {
            g2d.drawLine(0, y, getWidth(), y);
        }
        g2d.dispose();
        
        // Update highlighting
        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).setHighlighted(i == highlightedIndex);
        }
    }
    
    /**
     * MonsterTile - Individual monster display card
     */
    private class MonsterTile extends JPanel {
        private Monster monster;
        private int index;
        private boolean highlighted = false;
        
        // Cyber Tile Colors
        private final Color COLOR_CARD_TOP = new Color(22, 26, 40);
        private final Color COLOR_CARD_BOT = new Color(12, 14, 22);
        private final Color COLOR_DEAD_TOP = new Color(42, 14, 24);
        private final Color COLOR_DEAD_BOT = new Color(18, 6, 12);
        
        public MonsterTile(Monster monster, int index) {
            this.monster = monster;
            this.index = index;
            setLayout(new BorderLayout(6, 6));
            setOpaque(false);
            updateTileBorder();
            createLayout();
        }
        
        private void updateTileBorder() {
            if (highlighted) {
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COLOR_NEON_PINK, 3),
                    BorderFactory.createEmptyBorder(6, 6, 6, 6)
                ));
            } else {
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COLOR_TILE_BORDER, 1),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));
            }
        }
        
        private void createLayout() {
            // Monster info panel
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setOpaque(false);
            
            // Monster number/name
            JLabel numberLabel = new JLabel("[ TARGET #" + index + " ]");
            numberLabel.setFont(FONT_MONO);
            numberLabel.setForeground(COLOR_NEON_CYAN);
            numberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            // Stats
            JLabel healthLabel = new JLabel("HP  : " + monster.health());
            healthLabel.setFont(FONT_MONO);
            healthLabel.setForeground(COLOR_NEON_GREEN);
            healthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel damageLabel = new JLabel("DMG : " + monster.damage());
            damageLabel.setFont(FONT_MONO);
            damageLabel.setForeground(COLOR_NEON_PINK);
            damageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel speedLabel = new JLabel("SPD : " + monster.speed());
            speedLabel.setFont(FONT_MONO);
            speedLabel.setForeground(COLOR_NEON_YELLOW);
            speedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            infoPanel.add(numberLabel);
            infoPanel.add(Box.createVerticalStrut(6));
            infoPanel.add(healthLabel);
            infoPanel.add(damageLabel);
            infoPanel.add(speedLabel);
            
            // Special ability if exists
            String special = monster.special();
            if (special != null && !special.isEmpty()) {
                infoPanel.add(Box.createVerticalStrut(4));
                Font specialFont = new Font("Consolas", Font.ITALIC, 11);
                JLabel specialLabel = new JLabel(Emoji.fit("★ " + special, specialFont));
                specialLabel.setForeground(COLOR_NEON_YELLOW);
                specialLabel.setFont(specialFont);
                specialLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                infoPanel.add(specialLabel);
            }
            
            add(infoPanel, BorderLayout.CENTER);
            
            // Health bar at bottom
            add(new HealthBar(), BorderLayout.SOUTH);
        }
        
        public void setHighlighted(boolean highlighted) {
            if (this.highlighted != highlighted) {
                this.highlighted = highlighted;
                updateTileBorder();
                repaint();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int w = getWidth();
            int h = getHeight();
            
            boolean isDead = monster != null && monster.health() <= 0;
            Color topColor = isDead ? COLOR_DEAD_TOP : COLOR_CARD_TOP;
            Color botColor = isDead ? COLOR_DEAD_BOT : COLOR_CARD_BOT;
            
            // Draw gradient card background
            GradientPaint gp = new GradientPaint(0, 0, topColor, 0, h, botColor);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
            
            // Subtle card horizontal scanlines
            g2d.setColor(new Color(255, 255, 255, 4));
            for (int y = 0; y < h; y += 4) {
                g2d.drawLine(0, y, w, y);
            }
            
            g2d.dispose();
            super.paintComponent(g);
        }
        
        /**
         * Visual health bar (Segmented Cyberpunk Energy Trough)
         */
        private class HealthBar extends JPanel {
            private static final int BAR_HEIGHT = 20;
            
            public HealthBar() {
                setPreferredSize(new Dimension(0, BAR_HEIGHT));
                setOpaque(false);
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                
                // Draw trough background
                g2.setColor(HEALTH_BAR_BG);
                g2.fillRect(0, 0, width - 1, height - 1);
                
                // Calculate health percentage (assume max 100)
                double healthPercent = Math.max(0, Math.min(100, monster.health())) / 100.0;
                int barWidth = (int) ((width - 2) * healthPercent);
                
                if (barWidth > 0) {
                    Color startColor, endColor;
                    if (healthPercent > 0.5) {
                        startColor = COLOR_NEON_GREEN;
                        endColor = COLOR_NEON_CYAN;
                    } else if (healthPercent > 0.25) {
                        startColor = COLOR_NEON_YELLOW;
                        endColor = new Color(255, 140, 0);
                    } else {
                        startColor = COLOR_NEON_PINK;
                        endColor = new Color(220, 20, 60);
                    }
                    
                    GradientPaint fillGp = new GradientPaint(0, 0, startColor, barWidth, height, endColor);
                    g2.setPaint(fillGp);
                    g2.fillRect(1, 1, barWidth, height - 2);
                    
                    // Cyberpunk energy segment overlays
                    g2.setColor(new Color(0, 0, 0, 90));
                    int segmentWidth = 10;
                    for (int x = segmentWidth; x < barWidth; x += segmentWidth) {
                        g2.drawLine(x, 1, x, height - 2);
                    }
                }
                
                // Outer Trough Glow Border
                g2.setColor(COLOR_CYAN_GLOW);
                g2.drawRect(0, 0, width - 1, height - 1);
                
                // HP Text inside bar with drop shadow
                String hpText = monster.health() + " HP";
                g2.setFont(FONT_MONO_SMALL);
                FontMetrics fm = g2.getFontMetrics();
                int textX = (width - fm.stringWidth(hpText)) / 2;
                int textY = ((height - fm.getHeight()) / 2) + fm.getAscent();
                
                g2.setColor(Color.BLACK);
                g2.drawString(hpText, textX + 1, textY + 1);
                g2.setColor(Color.WHITE);
                g2.drawString(hpText, textX, textY);
                
                g2.dispose();
            }
        }
    }
}