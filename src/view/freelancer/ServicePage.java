package view.freelancer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ServicePage extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServicePage() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

	private void initComponents() {
        Container container = getContentPane();
        container.setLayout(null);

        // Circles
        CircleComponent circle1 = new CircleComponent(50, 100, 200, new Color(30, 144, 255), Color.BLACK);
        CircleComponent circle2 = new CircleComponent(500, 50, 300, new Color(30, 144, 255), Color.BLACK);
        container.add(circle1);
        container.add(circle2);

        // Rectangles
        RectangleComponent rectangle1 = new RectangleComponent(250, 400, 170, 53, new Color(30, 144, 255), Color.BLACK);
        RectangleComponent rectangle2 = new RectangleComponent(250, 250, 193, 69, new Color(30, 144, 255), Color.BLACK);
        RectangleComponent rectangle3 = new RectangleComponent(250, 100, 170, 62, new Color(30, 144, 255), Color.BLACK);
        container.add(rectangle1);
        container.add(rectangle2);
        container.add(rectangle3);

        // Labels
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setBounds(300, 80, 100, 30);
        container.add(nameLabel);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(new Font("Arial", Font.BOLD, 28));
        descLabel.setBounds(300, 250, 170, 53);
        container.add(descLabel);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 30));
        priceLabel.setBounds(300, 420, 100, 30);
        container.add(priceLabel);

        // Other components
        JButton mainButton = new JButton("Main");
        mainButton.setBounds(50, 500, 135, 53);
        container.add(mainButton);

        JButton linkedinButton = new JButton("LinkedIn Profile");
        linkedinButton.setBounds(200, 500, 158, 53);
        container.add(linkedinButton);

        JButton sendMailButton = new JButton("Send Mail");
        sendMailButton.setBounds(370, 500, 142, 53);
        container.add(sendMailButton);

        JButton whatsappButton = new JButton("WhatsApp");
        whatsappButton.setBounds(520, 500, 123, 53);
        container.add(whatsappButton);
    }

    private static class CircleComponent extends JComponent {
        private final int x, y, radius;
        private final Color fillColor;
        private final Color strokeColor;

        public CircleComponent(int x, int y, int radius, Color fillColor, Color strokeColor) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.fillColor = fillColor;
            this.strokeColor = strokeColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(fillColor);
            g2d.fill(new Ellipse2D.Double(x, y, radius, radius));
            g2d.setColor(strokeColor);
            g2d.draw(new Ellipse2D.Double(x, y, radius, radius));
        }
    }

    private static class RectangleComponent extends JComponent {
        private final int x, y, width, height;
        private final Color fillColor;
        private final Color strokeColor;

        public RectangleComponent(int x, int y, int width, int height, Color fillColor, Color strokeColor) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.fillColor = fillColor;
            this.strokeColor = strokeColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(fillColor);
            g2d.fill(new Rectangle2D.Double(x, y, width, height));
            g2d.setColor(strokeColor);
            g2d.draw(new Rectangle2D.Double(x, y, width, height));
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServicePage());
    }
}
