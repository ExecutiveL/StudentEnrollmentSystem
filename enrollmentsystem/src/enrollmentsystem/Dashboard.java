package enrollmentsystem;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard {

	private JFrame frame;
	private JLabel total;
	private JLabel totalstemlbl;
	private JLabel totalIctlbl;
    private JLabel totalabmbl;
    private JLabel totalhumbl_1_1;
    private JLabel totalgaslbl_1_2;
    private JLabel totalcooklbl_1_3; 
	private static Connection con;
	
	
	
private void connect() {
	try {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Ralp\\Documents\\studinfo.db");
		con.setAutoCommit(false);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public Dashboard() {
		initialize();
		connect();
		updateTotalStudents();
		TotalStrandStudents();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 670, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(99, 238, 133));
		panel.setBounds(0, 0, 129, 127);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Ralp\\Pictures\\download.png"));
		lblNewLabel.setBounds(0, 0, 129, 127);
		panel.add(lblNewLabel);
		
		JPanel menupnl = new JPanel();
		menupnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		menupnl.setBackground(new Color(4, 182, 169));
		menupnl.setBounds(0, 0, 129, 341);
		frame.getContentPane().add(menupnl);
		menupnl.setLayout(null);
		
		JPanel Dashboard = new JPanel();
		Dashboard.setBackground(new Color(4, 169, 192));
		Dashboard.setBorder(new LineBorder(new Color(0, 0, 0)));
		Dashboard.setBounds(139, 11, 505, 319);
		frame.getContentPane().add(Dashboard);
		Dashboard.setLayout(null);
		
		JLabel dashboardbtn = new JLabel("DashBoard");
		dashboardbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard.setVisible(false);
			}
		});
		dashboardbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dashboardbtn.setBounds(10, 136, 105, 36);
		menupnl.add(dashboardbtn);
		
		JPanel infopnl = new JPanel();
		infopnl.setBounds(139, 11, 505, 319);
		frame.getContentPane().add(infopnl);

		JLabel lblStudentinfo = new JLabel("StudentInfo");
		lblStudentinfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		lblStudentinfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStudentinfo.setBounds(10, 187, 105, 36);
		menupnl.add(lblStudentinfo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(99, 238, 133));
		panel_1.setBounds(10, 22, 485, 66);
		Dashboard.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel totalstudlbl = new JLabel("Total Students:");
		totalstudlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalstudlbl.setBounds(10, 24, 116, 14);
		panel_1.add(totalstudlbl);
	
	
		total = new JLabel("0");
		total.setFont(new Font("Tahoma", Font.PLAIN, 20));
		total.setBounds(269, 26, 46, 14);
		panel_1.add(total);
		
		JPanel ictpnl = new JPanel();
		ictpnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		ictpnl.setBackground(new Color(4, 126, 250));
		ictpnl.setBounds(10, 104, 147, 80);
		Dashboard.add(ictpnl);
		ictpnl.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Total Tvl-ICT Students");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 127, 14);
		ictpnl.add(lblNewLabel_1);
		
		totalIctlbl = new JLabel("0");
		totalIctlbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalIctlbl.setForeground(Color.WHITE);
		totalIctlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalIctlbl.setBounds(10, 36, 127, 14);
		ictpnl.add(totalIctlbl);
		
		JPanel stempnl = new JPanel();
		stempnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		stempnl.setBackground(new Color(4, 126, 250));
		stempnl.setBounds(182, 104, 147, 80);
		Dashboard.add(stempnl);
		stempnl.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Total STEM Students");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(10, 11, 127, 14);
		stempnl.add(lblNewLabel_1_1);
		
		totalstemlbl = new JLabel("0");
		totalstemlbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalstemlbl.setForeground(Color.WHITE);
		totalstemlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalstemlbl.setBounds(10, 36, 127, 14);
		stempnl.add(totalstemlbl);
		
		JPanel abmpnl = new JPanel();
		abmpnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		abmpnl.setBackground(new Color(4, 126, 250));
		abmpnl.setBounds(348, 104, 147, 80);
		Dashboard.add(abmpnl);
		abmpnl.setLayout(null);
		
		totalabmbl = new JLabel("0");
		totalabmbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalabmbl.setForeground(Color.WHITE);
		totalabmbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalabmbl.setBounds(10, 36, 127, 14);
		abmpnl.add(totalabmbl);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Total ABM Students");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(10, 11, 127, 14);
		abmpnl.add(lblNewLabel_1_1_1);
		
		JPanel humpnl = new JPanel();
		humpnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		humpnl.setBackground(new Color(4, 126, 250));
		humpnl.setBounds(10, 209, 147, 80);
		Dashboard.add(humpnl);
		humpnl.setLayout(null);
		
		totalhumbl_1_1 = new JLabel("0");
		totalhumbl_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		totalhumbl_1_1.setForeground(Color.WHITE);
		totalhumbl_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		totalhumbl_1_1.setBounds(10, 33, 127, 14);
		humpnl.add(totalhumbl_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Total HUMSS Students");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_2.setBounds(10, 8, 127, 14);
		humpnl.add(lblNewLabel_1_1_2);
		
		JPanel gaspnl = new JPanel();
		gaspnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		gaspnl.setBackground(new Color(4, 126, 250));
		gaspnl.setBounds(182, 209, 147, 80);
		Dashboard.add(gaspnl);
		gaspnl.setLayout(null);
		
		totalgaslbl_1_2 = new JLabel("0");
		totalgaslbl_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		totalgaslbl_1_2.setForeground(Color.WHITE);
		totalgaslbl_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalgaslbl_1_2.setBounds(10, 32, 127, 14);
		gaspnl.add(totalgaslbl_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Total GAS Students");
		lblNewLabel_1_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_3.setBounds(10, 11, 127, 14);
		gaspnl.add(lblNewLabel_1_1_3);
		
		JPanel cookpnl = new JPanel();
		cookpnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		cookpnl.setBackground(new Color(4, 126, 250));
		cookpnl.setBounds(348, 209, 147, 80);
		Dashboard.add(cookpnl);
		cookpnl.setLayout(null);
		
		totalcooklbl_1_3 = new JLabel("0");
		totalcooklbl_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		totalcooklbl_1_3.setForeground(Color.WHITE);
		totalcooklbl_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalcooklbl_1_3.setBounds(10, 33, 127, 14);
		cookpnl.add(totalcooklbl_1_3);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("Total Cookery Students");
		lblNewLabel_1_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_4.setBounds(10, 11, 137, 14);
		cookpnl.add(lblNewLabel_1_1_4);
		
				
	}
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		frame.setVisible(visible);
	}
    private void updateTotalStudents() {
        try {
            String query = "SELECT COUNT(*) as total_students FROM studentinfo";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();

               
                int totalStudents = 0;
                if (resultSet.next()) {
                    totalStudents = resultSet.getInt("total_students");
                }

               
                total.setText(String.valueOf(totalStudents));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void TotalStrandStudents() {
    	try {
    		HashMap<String, Integer> strandcounts = new HashMap<>();
    	    List<String> strands = Arrays.asList("TVL-ICT","STEM","ABM","HUMSS","GAS","TVL-HE");
    		String query = "SELECT COUNT(*) from studentinfo WHERE strand =?";
    		
    		try (PreparedStatement pr = con.prepareStatement(query)) {
    			for (String Strand : strands) {
    				pr.setString(1, Strand);
    			
    		try (ResultSet resultset = pr.executeQuery()) {
    			if (resultset.next()) {
        			int count = resultset.getInt(1);
        			strandcounts.put(Strand, count);
    			}
    			totalIctlbl.setText(String.valueOf(strandcounts.get("TVL-ICT")));
    			totalstemlbl.setText(String.valueOf(strandcounts.get("STEM")));
    			totalabmbl.setText(String.valueOf(strandcounts.get("ABM")));
    			totalhumbl_1_1.setText(String.valueOf(strandcounts.get("HUMSS")));
    			totalgaslbl_1_2.setText(String.valueOf(strandcounts.get("G")));
    			totalcooklbl_1_3.setText(String.valueOf(strandcounts.get("TVL-HE")));
    			
    			
    		}
    	}
    }
    	}
    	catch (Exception r) {
    		r.printStackTrace();
    	}
    }
}