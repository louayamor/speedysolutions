package view.admin;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class StatisticsPage extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblAdminCount;
    private JLabel lblFreelancerCount;
    private JLabel lblBusinessOwnerCount;
    private ChartPanel chartPanel;

    public StatisticsPage() throws SQLException {
        initComponents();
        countUserRoles();
        createPieChart();
    }

    private void initComponents() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Color backgroundColor = new Color(52, 73, 94);  
        Color panelColor = new Color(44, 47, 72);       
        Color labelTextColor = new Color(236, 240, 241); 
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        JPanel labelsPanel = new JPanel(new GridLayout(3, 1));
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelsPanel.setBackground(panelColor);

        lblAdminCount = new JLabel("Admins: ");
        lblFreelancerCount = new JLabel("Freelancers: ");
        lblBusinessOwnerCount = new JLabel("Business Owners: ");

        lblAdminCount.setForeground(labelTextColor);
        lblFreelancerCount.setForeground(labelTextColor);
        lblBusinessOwnerCount.setForeground(labelTextColor);

        lblAdminCount.setFont(labelFont);
        lblFreelancerCount.setFont(labelFont);
        lblBusinessOwnerCount.setFont(labelFont);

        labelsPanel.add(lblAdminCount);
        labelsPanel.add(lblFreelancerCount);
        labelsPanel.add(lblBusinessOwnerCount);

        mainPanel.add(labelsPanel, BorderLayout.WEST);

        chartPanel = new ChartPanel(null);
        mainPanel.add(chartPanel, BorderLayout.CENTER);

        add(mainPanel);

        setTitle("Statistics Page");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void countUserRoles() throws SQLException {
        UserController uc = new UserController();

        int adminCount = uc.countUsersByRole("Admin");
        int freelancerCount = uc.countUsersByRole("Freelancer");
        int businessOwnerCount = uc.countUsersByRole("Business Owner");

        lblAdminCount.setText("Admins: " + adminCount);
        lblFreelancerCount.setText("Freelancers: " + freelancerCount);
        lblBusinessOwnerCount.setText("Business Owners: " + businessOwnerCount);
    }

    private void createPieChart() throws SQLException {
        UserController uc = new UserController();

        int adminCount = uc.countUsersByRole("Admin");
        int freelancerCount = uc.countUsersByRole("Freelancer");
        int businessOwnerCount = uc.countUsersByRole("Business Owner");

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Admins", adminCount);
        dataset.setValue("Freelancers", freelancerCount);
        dataset.setValue("Business Owners", businessOwnerCount);

        JFreeChart pieChart = ChartFactory.createPieChart("User Roles", dataset, true, true, false);
        PiePlot plot = (PiePlot) pieChart.getPlot();

        plot.setSectionPaint("Admins", new Color(46, 204, 113)); 
        plot.setSectionPaint("Freelancers", new Color(52, 152, 219)); 
        plot.setSectionPaint("Business Owners", new Color(230, 126, 34)); 

        chartPanel.setChart(pieChart);
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                try {
                    new StatisticsPage().setVisible(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
