package enrollmentsystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class enrollmentform {

	private JFrame frmEnrollmentform;
	private JTextField fname;
	private JTextField mname;
	private JTextField lname;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblBirthdate;
	private JLabel lblAddress;
	private JTextField pnumber;
	private JLabel lblEmailAddress;
	private JTextField email;
	private JLabel lblChoosebStrand;
	private JComboBox strand;
	private JButton btnNewButton;
	private static Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Ralp\\Documents\\studinfo.db");
					con.setAutoCommit(false);
					enrollmentform window = new enrollmentform();
					window.frmEnrollmentform.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public enrollmentform() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEnrollmentform = new JFrame();
		frmEnrollmentform.getContentPane().setBackground(new Color(4, 169, 192));
		frmEnrollmentform.setTitle("Enrollment-Form");
		frmEnrollmentform.setBounds(100, 100, 650, 390);
		frmEnrollmentform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEnrollmentform.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(230, 27, 81, 14);
		frmEnrollmentform.getContentPane().add(lblNewLabel);
		
		fname = new JTextField();
		fname.setBounds(228, 52, 119, 20);
		frmEnrollmentform.getContentPane().add(fname);
		fname.setColumns(10);
		
		mname = new JTextField();
		mname.setColumns(10);
		mname.setBounds(357, 52, 127, 20);
		frmEnrollmentform.getContentPane().add(mname);
		
		lname = new JTextField();
		lname.setColumns(10);
		lname.setBounds(494, 52, 112, 20);
		frmEnrollmentform.getContentPane().add(lname);
		
		lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(238, 83, 61, 14);
		frmEnrollmentform.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Middle Name");
		lblNewLabel_2.setBounds(367, 83, 61, 14);
		frmEnrollmentform.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Last Name");
		lblNewLabel_3.setBounds(504, 83, 61, 14);
		frmEnrollmentform.getContentPane().add(lblNewLabel_3);
		
		lblBirthdate = new JLabel("Birthdate");
		lblBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBirthdate.setBounds(330, 108, 81, 14);
		frmEnrollmentform.getContentPane().add(lblBirthdate);
		
		JLabel lblNewLabel_5 = new JLabel("Age");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(231, 108, 46, 20);
		frmEnrollmentform.getContentPane().add(lblNewLabel_5);
		
		List <Integer> ages = new ArrayList<Integer>();
		 for (int i =5; i < 100; i++) {
			 ages.add(i);
		 }
		JComboBox age = new JComboBox(ages.toArray());
		age.setBounds(230, 131, 81, 20);
		frmEnrollmentform.getContentPane().add(age);
		
		DatePicker bdate = new DatePicker();
		bdate.setBounds(330, 131, 139, 20);
		frmEnrollmentform.getContentPane().add(bdate);
		
		JLabel lblBirthdate_1 = new JLabel("Gender");
		lblBirthdate_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBirthdate_1.setBounds(484, 108, 81, 14);
		frmEnrollmentform.getContentPane().add(lblBirthdate_1);
		
		JComboBox gender = new JComboBox();
		gender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Prefer not to say"}));
		gender.setBounds(487, 131, 119, 22);
		frmEnrollmentform.getContentPane().add(gender);
	
		
		lblAddress = new JLabel("Mobile number");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(230, 174, 100, 14);
		frmEnrollmentform.getContentPane().add(lblAddress);
		
		pnumber = new JTextField();
		pnumber.setColumns(10);
		pnumber.setBounds(230, 202, 152, 20);
		frmEnrollmentform.getContentPane().add(pnumber);
		((AbstractDocument)pnumber.getDocument()).setDocumentFilter(new DocumentFilter() {
			  public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
			        fb.insertString(offset, string.replaceAll("[^0-9]", ""), attr);
			      }
			      
			      public void replace(FilterBypass fb, int offset, int length, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
			        fb.replace(offset, length, string.replaceAll("[^0-9]", ""), attr);
			      }
			    });
		
		lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailAddress.setBounds(410, 174, 100, 14);
		frmEnrollmentform.getContentPane().add(lblEmailAddress);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(410, 202, 196, 20);
		frmEnrollmentform.getContentPane().add(email);
		
		lblChoosebStrand = new JLabel("Choosen strand");
		lblChoosebStrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChoosebStrand.setBounds(230, 233, 100, 14);
		frmEnrollmentform.getContentPane().add(lblChoosebStrand);
		
		strand = new JComboBox();
		strand.setModel(new DefaultComboBoxModel(new String[] {"TVL-ICT", "STEM", "ABM", "HUMSS", "GAS", "TVL-HE"}));
		strand.setBounds(230, 258, 152, 22);
		frmEnrollmentform.getContentPane().add(strand);
		
		btnNewButton = new JButton("Submit Enrollment");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Fname = fname.getText();
				String Mname = mname.getText();
				String Lname = lname.getText();
				String Age = age.getSelectedItem().toString();
				String Bdate = bdate.getDateStringOrEmptyString();
				String Gender = gender.getSelectedItem().toString();
				String Pnumber = pnumber.getText();
				String Email = email.getText();
				String Strand = strand.getSelectedItem().toString();
				try {
					String sql = ("INSERT INTO studentinfo(fname,mname,lname,age,bdate,gender,pnumber,email,strand) VALUES (?,?,?,?,?,?,?,?,?)");
					PreparedStatement preparedstatement = con.prepareStatement(sql);
					preparedstatement.setString(1, Fname);
					preparedstatement.setString(2, Mname);
					preparedstatement.setString(3, Lname);
					preparedstatement.setString(4,Age);
					preparedstatement.setString(5, Bdate);
					preparedstatement.setString(6,Gender);
					preparedstatement.setString(7,Pnumber);
					preparedstatement.setString(8,Email);
					preparedstatement.setString(9,Strand);
					preparedstatement.executeUpdate();
					con.commit();
					JOptionPane.showMessageDialog(null, "You are successfully Enrolled","Enrolled Succesfully",JOptionPane.INFORMATION_MESSAGE);
					
					fname.setText(""); 
					lname.setText(""); 
					mname.setText(""); 
					email.setText("");
					pnumber.setText("");
					gender.setSelectedIndex(0);
					strand.setSelectedIndex(0);
					bdate.setDate(null);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		
		});
		btnNewButton.setBounds(485, 295, 139, 23);
		frmEnrollmentform.getContentPane().add(btnNewButton);
		
	
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Ralp\\Pictures\\download.png"));
		lblNewLabel_4.setBounds(29, 83, 139, 139);
		frmEnrollmentform.getContentPane().add(lblNewLabel_4);
		
		JLabel lblEnrollmentSystem = new JLabel("Enrollment System");
		lblEnrollmentSystem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEnrollmentSystem.setBounds(10, 11, 185, 30);
		frmEnrollmentform.getContentPane().add(lblEnrollmentSystem);
		
		JMenuBar menuBar = new JMenuBar();
		frmEnrollmentform.setJMenuBar(menuBar);

		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);

		JMenuItem loginMenuItem = new JMenuItem("Login");
		menu.add(loginMenuItem);

		
		loginMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		       new LoginForm().setVisible(true);
		    }
		});
	 
	  }
	
}
