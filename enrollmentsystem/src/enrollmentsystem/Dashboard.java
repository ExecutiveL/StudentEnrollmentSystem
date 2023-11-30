package enrollmentsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Dashboard {

	private JFrame frame;
	private JLabel total;
	private JLabel totalstemlbl;
	private JLabel totalIctlbl;
    private JLabel totalabmbl;
    private JLabel totalhumbl_1_1;
    private JLabel totalgaslbl_1_2;
    private JLabel totalcooklbl_1_3;
    private JTable table;
    private JTextField searchtf;
    private DefaultTableModel model;
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
		populateTable();
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 415);
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
		menupnl.setBounds(0, 0, 129, 365);
		frame.getContentPane().add(menupnl);
		menupnl.setLayout(null);
		
		JPanel Dashboard = new JPanel();
		Dashboard.setBackground(new Color(4, 169, 192));
		Dashboard.setBorder(new LineBorder(new Color(0, 0, 0)));
		Dashboard.setBounds(139, 11, 635, 354);
		frame.getContentPane().add(Dashboard);
		Dashboard.setLayout(null);
		
		JPanel infopnl = new JPanel();
		infopnl.setBounds(135, 11, 680, 354);
		infopnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(infopnl);
		infopnl.setVisible(false);
		infopnl.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 547, 354);
        infopnl.add(scrollPane);

        table = new JTable();
        table.getTableHeader().setReorderingAllowed(false);
        table.setFocusable(false);
        table.setRowSelectionAllowed(true);

        scrollPane.setViewportView(table);
       
        
        JLabel searchforlbl = new JLabel("Search For:");
        searchforlbl.setBounds(571, 11, 95, 14);
        infopnl.add(searchforlbl);
        
        searchtf = new JTextField();
        searchtf.setBounds(557, 36, 105, 20);
        infopnl.add(searchtf);
        searchtf.setColumns(10);
        
        JButton searchbtn = new JButton("Search");
        searchbtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
        			String keyword = searchtf.getText();
        			PreparedStatement pr = con.prepareStatement("SELECT * FROM studentinfo WHERE ID = ? OR fname = ? OR mname = ? OR lname = ? OR age = ? OR bdate = ? OR gender = ? OR pnumber = ? OR email = ? OR strand = ? OR Subjects = ?");
        			pr.setString(1, keyword);
        			pr.setString(2, keyword);
        			pr.setString(3, keyword);
        			pr.setString(4, keyword);
        			pr.setString(5, keyword);
        			pr.setString(6, keyword);
        			pr.setString(7, keyword);
        			pr.setString(8, keyword);
        			pr.setString(9, keyword);
        			pr.setString(10, keyword);
        			
        			
        			ResultSet rs = pr.executeQuery();
        			 model.setRowCount(0);
        			while(rs.next()) {
        				  Object[] rowData = {
                                  rs.getInt("ID"),
                                  rs.getString("fname"),
                                  rs.getString("mname"),
                                  rs.getString("lname"),
                                  rs.getString("age"),
                                  rs.getString("bdate"),
                                  rs.getString("gender"),
                                  rs.getString("pnumber"),
                                  rs.getString("email"),
                                  rs.getString("strand"),
                                  rs.getString("Subjects")
                          };
        				  model.addRow(rowData);
        			}
        			
        			
        		} catch(Exception r) {
        			
        		}
        	}
        });
        searchbtn.setBounds(557, 67, 105, 23);
        infopnl.add(searchbtn);
        
        JButton deletebtn = new JButton("DELETE");
        deletebtn.setBounds(557, 117, 105, 23);
        infopnl.add(deletebtn);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		UpdateData();
	        	}
	        });
        btnUpdate.setBounds(557, 152, 105, 23);
        infopnl.add(btnUpdate);
       
		JLabel dashboardbtn = new JLabel("DashBoard");
		dashboardbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				infopnl.setVisible(false);
				Dashboard.setVisible(true);
				
			}
		});
		dashboardbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dashboardbtn.setBounds(10, 136, 105, 36);
		menupnl.add(dashboardbtn);
		
		JLabel lblStudentinfo = new JLabel("StudentInfo");
		lblStudentinfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				populateTable();
			    Dashboard.setVisible(false);
			    infopnl.setVisible(true);
			    frame.setBounds(100, 100, 850, 415);
			}
		});
		lblStudentinfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStudentinfo.setBounds(10, 187, 105, 36);
		menupnl.add(lblStudentinfo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(99, 238, 133));
		panel_1.setBounds(20, 22, 593, 66);
		Dashboard.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel totalstudlbl = new JLabel("Total Students:");
		totalstudlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		totalstudlbl.setBounds(10, 24, 189, 14);
		panel_1.add(totalstudlbl);
	
	
		total = new JLabel("0");
		total.setFont(new Font("Tahoma", Font.PLAIN, 23));
		total.setBounds(326, 11, 46, 44);
		panel_1.add(total);
		
		JPanel ictpnl = new JPanel();
		ictpnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		ictpnl.setBackground(new Color(4, 126, 250));
		ictpnl.setBounds(39, 99, 174, 80);
		Dashboard.add(ictpnl);
		ictpnl.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Total Tvl-ICT Students");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 163, 14);
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
		stempnl.setBounds(242, 99, 174, 80);
		Dashboard.add(stempnl);
		stempnl.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Total STEM Students");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 11, 167, 14);
		stempnl.add(lblNewLabel_1_1);
		
		totalstemlbl = new JLabel("0");
		totalstemlbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalstemlbl.setForeground(Color.WHITE);
		totalstemlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalstemlbl.setBounds(0, 36, 177, 14);
		stempnl.add(totalstemlbl);
		
		JPanel abmpnl = new JPanel();
		abmpnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		abmpnl.setBackground(new Color(4, 126, 250));
		abmpnl.setBounds(439, 99, 174, 80);
		Dashboard.add(abmpnl);
		abmpnl.setLayout(null);
		
		totalabmbl = new JLabel("0");
		totalabmbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalabmbl.setForeground(Color.WHITE);
		totalabmbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalabmbl.setBounds(0, 37, 165, 14);
		abmpnl.add(totalabmbl);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Total ABM Students");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 11, 145, 14);
		abmpnl.add(lblNewLabel_1_1_1);
		
		JPanel humpnl = new JPanel();
		humpnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		humpnl.setBackground(new Color(4, 126, 250));
		humpnl.setBounds(39, 209, 174, 80);
		Dashboard.add(humpnl);
		humpnl.setLayout(null);
		
		totalhumbl_1_1 = new JLabel("0");
		totalhumbl_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		totalhumbl_1_1.setForeground(Color.WHITE);
		totalhumbl_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		totalhumbl_1_1.setBounds(20, 39, 127, 14);
		humpnl.add(totalhumbl_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Total HUMSS Students");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(10, 8, 164, 14);
		humpnl.add(lblNewLabel_1_1_2);
		
		JPanel gaspnl = new JPanel();
		gaspnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		gaspnl.setBackground(new Color(4, 126, 250));
		gaspnl.setBounds(242, 209, 179, 80);
		Dashboard.add(gaspnl);
		gaspnl.setLayout(null);
		
		totalgaslbl_1_2 = new JLabel("0");
		totalgaslbl_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		totalgaslbl_1_2.setForeground(Color.WHITE);
		totalgaslbl_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalgaslbl_1_2.setBounds(20, 36, 127, 14);
		gaspnl.add(totalgaslbl_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Total GAS Students");
		lblNewLabel_1_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_3.setBounds(10, 11, 159, 14);
		gaspnl.add(lblNewLabel_1_1_3);
		
		JPanel cookpnl = new JPanel();
		cookpnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		cookpnl.setBackground(new Color(4, 126, 250));
		cookpnl.setBounds(439, 209, 174, 80);
		Dashboard.add(cookpnl);
		cookpnl.setLayout(null);
		
		totalcooklbl_1_3 = new JLabel("0");
		totalcooklbl_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		totalcooklbl_1_3.setForeground(Color.WHITE);
		totalcooklbl_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalcooklbl_1_3.setBounds(20, 36, 127, 14);
		cookpnl.add(totalcooklbl_1_3);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("Total Cookery Students");
		lblNewLabel_1_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_4.setBounds(0, 11, 164, 14);
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
    			totalgaslbl_1_2.setText(String.valueOf(strandcounts.get("GAS")));
    			totalcooklbl_1_3.setText(String.valueOf(strandcounts.get("TVL-HE")));
    			
    			
    		}
    	}
    }
    	}
    	catch (Exception r) {
    		r.printStackTrace();
    	}
    }
    	private void populateTable() {
            try {
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM studentinfo");

                model = new DefaultTableModel();
                table.setModel(model);

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                
                for (int column = 1; column <= columnCount; column++) {
                    model.addColumn(metaData.getColumnName(column));
                }

               
                while (resultSet.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(rowData);
                }

            } catch (Exception e) {
                e.printStackTrace();
            
                }
           
        }
    	private void UpdateData() {
    	    	try {
    	                
    	    	        String query = "UPDATE studentinfo SET fname = ?, mname = ?, lname = ?, age = ?, bdate = ?, gender = ?, pnumber = ?, email = ?, strand = ?, Subjects = ? WHERE ID = ?";
    	    	        PreparedStatement updateStatement = con.prepareStatement(query);
    	    	        updateStatement.setString(1, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        updateStatement.setString(2, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        updateStatement.setString(3, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        updateStatement.setString(4, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        updateStatement.setString(5, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        updateStatement.setString(6, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        updateStatement.setString(7, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        updateStatement.setString(8, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        updateStatement.setString(9, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        updateStatement.setString(10, model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
    	    	        
    	    	        updateStatement.setInt(11, Integer.parseInt(model.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString()));

    	    	        updateStatement.executeUpdate();
    	    	   
    	    	    
    	    	} catch (Exception ex) {
    	    	    ex.printStackTrace();
    	    	}
    	    
    	    }
}

