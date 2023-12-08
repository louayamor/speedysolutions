package view.common;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class UserProfile extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserProfile() throws MalformedURLException {
        setLayout(new BorderLayout());
        setBackground(new Color(18, 32, 47));

        Inspector inspector = new Inspector();
        add(inspector, BorderLayout.CENTER);
    }

    private class Inspector extends JPanel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Inspector() throws MalformedURLException {
            setLayout(new BorderLayout());
            setBackground(new Color(44, 47, 72));

            UserInfo userInfo = new UserInfo();
            add(userInfo, BorderLayout.CENTER);
        }
    }

    private class UserInfo extends JPanel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UserInfo() throws MalformedURLException {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(44, 47, 72));

            add(createHeader());
            add(createProfileImage());
            add(createStack());
        }

        private JPanel createHeader() {
            JPanel headerPanel = new JPanel();
            headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            headerPanel.setBackground(new Color(0xFF2C2F48));

            // Create header components (similar to Flutter icons)
            // ...

            return headerPanel;
        }

        private JPanel createProfileImage() throws MalformedURLException {
            JPanel profilePanel = new JPanel();
            profilePanel.setBackground(new Color(44, 47, 72));

            // Create profile image (similar to Flutter container with image)
            URL imageUrl = new URL("https://via.placeholder.com/124x124");
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            JLabel profileImageLabel = new JLabel(imageIcon);

            profilePanel.add(profileImageLabel);

            return profilePanel;
        }

        private JPanel createStack() {
            JPanel stackPanel = new JPanel();
            stackPanel.setLayout(new BoxLayout(stackPanel, BoxLayout.Y_AXIS));
            stackPanel.setBackground(new Color(44, 47, 72));

            // Create stacked components (similar to Flutter stack)
            // ...

            return stackPanel;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Profile");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                UserProfile userProfile = new UserProfile();
                frame.getContentPane().add(userProfile);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
