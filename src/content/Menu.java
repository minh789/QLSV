package content;

/**
 * 
 * @author Lê Huỳnh Minh
 * 
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Window.Type;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;



public class Menu extends JFrame {

	static Menu frame;
	private JPanel contentPane;
	private JTextField mssvTextField;
	private JTextField NameTextField;
	//private StudentManager s = new StudentManager();
	private JTextField GPAtextField;
	private JTextArea AddressTextArea;
	private JTextField birthYearTextField;
	private JTable stTable;
	
	private Connection con;
	private PreparedStatement pst;
	private Statement stm;
	private ResultSet rs;
	
	/*private String [] columnNames = new String [] {
            "ID", "Name", "Age", "Address", "GPA"};*/
   // private Object data = new Object [][] {};
    
    //private String[] nganh = {"Công Nghệ Thông Tin","Kỹ thuật viễn thông","Kinh Tế","Kế Toán","Điện - Điện Tử", "Công Trình Xây Dựng", "Kết Cấu Xây Dựng", "Cơ Khí Ôtô", "Giao Thông Công Trình", "Nhiệt Điện", "Kỹ Thuật Điện", "Quản trị kinh doanh", "Kế toán TH và GT", "Kết cấu xây dựng", "Tự động hoá", "Kinh tế XD công trình GT", "Kinh tế vận tải du lịch", "Địa kỹ thuật công trình GT", "Kỹ thuật xây dựng Cầu hầm", "Cơ khí giao thông công chính", "Quản trị Logistics", "Kinh tế bưu chính viễn thông", "Quản trị doanh nghiệp XD"};
	private final static JComboBox<String> comboBox = new JComboBox<String>();
	
	private StudentDAO std;
	private  TableRowSorter<TableModel> rowSorter;
	private DefaultTableModel tbl;
	
	static SQLconnector sqlc = new SQLconnector();
	private final JLabel genderLabel = new JLabel("Giới tính:  ");
	private final JRadioButton rdbtnFemaleButton = new JRadioButton("Nữ");
	private final JRadioButton rdbtnMaleButton = new JRadioButton("Nam");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private static DecimalFormat df = new DecimalFormat("0.0");
	private final JTextField idSearchField = new JTextField();
	
	private final JButton Addbtn = new JButton("Thêm");
	private final JButton Updatebtn = new JButton("Sửa");
	private final JButton Deletebtn = new JButton("Xóa");
	
	private final JButton ClearField = new JButton("Refresh");
	private final JButton searchBtn = new JButton("Tìm kiếm");
	private final JLabel SearchLabel = new JLabel("Tìm kiếm theo: ");
	private final JLabel clickRefreshbtn = new JLabel("Bấm Refresh để quay lại chế độ xem bình thường");
	private final JTextField nameSearchField = new JTextField();
	private final JLabel FilteredTitle = new JLabel("FilteredTitle");
	private final JComboBox GenderBox = new JComboBox();
	private final JComboBox SearchByCriteriaJCB = new JComboBox();
	private final JButton hideSearchBtn = new JButton("Hủy");
	private final JMenu FeatureMenu = new JMenu("Chức năng");
	private final JMenuItem xeploai = new JMenuItem("Xếp loại");
	
	private final JToolBar toolBar = new JToolBar();
	private final JLabel RankLabel = new JLabel("Xếp loại: ");
	private final JComboBox RankTitleJCB = new JComboBox();
	private final JMenuItem sortMenuItem = new JMenuItem("Sắp xếp");
	private final JMenuItem thongke = new JMenuItem("Thống kê");
	private final JToolBar toolBar2 = new JToolBar();
	private final JComboBox listingJCB = new JComboBox();
	private final JButton btnX2 = new JButton("X");
	private final JLabel ListingLabel = new JLabel("Thống kê: ");
	private final JLabel classIdLabel = new JLabel("Mã Lớp:  ");
	private final JTextField ClassIdtextField = new JTextField();
	private final JToolBar toolBar3 = new JToolBar();
	private final JLabel SortingLabel = new JLabel("Sắp xếp: ");
	private final JComboBox SortingJCB = new JComboBox();
	private final JButton btnX3 = new JButton("X");
	private final JComboBox OrderJCB = new JComboBox();
	private final JMenuItem MoreClassesMNIT = new JMenuItem("Quản lý lớp");
	private final JLabel logoLabel = new JLabel("New label");
	private final JLabel logoLabel_1 = new JLabel("New label");
	//private int searchbtnFlag=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public Menu() {
		
		//tableUpdate();
		initGUI();
		tableUpdate();
		ClassUpdate();
	}
	
	
	private void initGUI() {
		setTitle("TRÌNH QUẢN LÝ SINH VIÊN MENU - 1.0 Early build");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Set OptionPane.messageFont's specified size as DEFAULT
		UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
		UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 20));
		
		
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1, 1);
		contentPane.add(layeredPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		file.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(file);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportExcel(stTable);
			}
		});
		saveMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		file.add(saveMenuItem);
		
		JMenuItem showlinkMenuItem = new JMenuItem("Contact");
		showlinkMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JLabel mess = new JLabel("<html>Lê Huỳnh Minh 5951071059@st.utc2.edu.vn✔ <br/> Đào Khải Minh 5951071058@st.utc2.edu.vn <br/> Khuất Lê Thành Luân 5951071054@st.utc2.edu.vn</html");
				mess.setFont(new Font("Arial", Font.BOLD, 18));
				JOptionPane.showMessageDialog(frame, mess, "Contact", JOptionPane.PLAIN_MESSAGE);
			}
		});
		showlinkMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		file.add(showlinkMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		file.add(exitMenuItem);
		FeatureMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		menuBar.add(FeatureMenu);
		xeploai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toolBar.setVisible(true);
				toolBar.setEnabled(true);
				RankTitleJCB.setSelectedIndex(0);
				toolBar2.setVisible(false);
				toolBar3.setVisible(false);
			}
		});
		xeploai.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		FeatureMenu.add(xeploai);
		sortMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toolBar.setVisible(false);
				toolBar2.setVisible(false);
				toolBar3.setVisible(true);
				
			}
		});
		sortMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		FeatureMenu.add(sortMenuItem);
		thongke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar2.setVisible(true);
				listingJCB.setSelectedIndex(0);
				toolBar.setVisible(false);
				toolBar3.setVisible(false);
			}
		});
		thongke.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		FeatureMenu.add(thongke);
		MoreClassesMNIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classform cf = new Classform();
				cf.setVisible(true);
			}
		});
		MoreClassesMNIT.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		FeatureMenu.add(MoreClassesMNIT);
		menuBar.add(clickRefreshbtn);
		
		clickRefreshbtn.setVisible(false);
		clickRefreshbtn.setBackground(Color.BLACK);
		clickRefreshbtn.setForeground(Color.RED);
		clickRefreshbtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		getContentPane().setLayout(null);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getItemCount()>0)
				{
					String c = comboBox.getSelectedItem().toString();
				
					//System.out.print(c);
					try {
						Connection con = sqlc.getConnection();
						Statement stm = con.createStatement();
						ResultSet rs = stm.executeQuery("SELECT * FROM Classes");
						//
						while (rs.next()) {
							String ClassName = rs.getString("ClassName").toString();
							if(c.equals(ClassName))
							{
								ClassIdtextField.setText(rs.getString("ClassId").toString());
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					sqlc.closeConnection();
				}
			}
		});
		
		//comboBox.setModel(new DefaultComboBoxModel(lop));
		
		
		
		//String[] nganh = {"Công Nghệ Thông Tin","Kỹ thuật viễn thông","Kinh Tế","Kế Toán","Điện - Điện Tử", "Công Trình Xây Dựng", "Kết Cấu Xây Dựng", "Cơ Khí Ôtô", "Giao Thông", "Nhiệt Điện", "Kỹ Thuật Điện"};
		//JComboBox comboBox = new JComboBox(nganh);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		comboBox.setBounds(174, 257, 318, 29);
		contentPane.add(comboBox);
		
		
		//Nhập sinh viên/ Thêm sinh viên
		Addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Student st= new Student(Integer.parseInt(mssvTextField.getText()),NameTextField.getText(),AddressTextArea.getText(),(String)comboBox.getSelectedItem(),Integer.parseInt(birthYearTextField.getText()),Double.parseDouble(GPAtextField.getText()));
				//DefaultTableModel model = (DefaultTableModel)table.getModel();
				try {
						if(Integer.parseInt(birthYearTextField.getText())>2002  || Integer.parseInt(birthYearTextField.getText())<1970)
						{
							throw new Exception("birthyear");
						}
						if(Double.parseDouble(GPAtextField.getText())>10)
						{
							throw new Exception("gpa");
						}
						if((Integer.parseInt(birthYearTextField.getText())<=2002 || Integer.parseInt(birthYearTextField.getText())>=1970) && Double.parseDouble(GPAtextField.getText())<=10)
						{
							StudentDAO std = new StudentDAO();
							Student st= new Student(mssvTextField.getText(),NameTextField.getText(),buttonGroup.getSelection().getActionCommand(),AddressTextArea.getText(),ClassIdtextField.getText(),Integer.parseInt(birthYearTextField.getText()),Double.parseDouble(GPAtextField.getText()));
							//System.out.println(Integer.parseInt(mssvTextField.getText())+NameTextField.getText()+AddressTextArea.getText()+(String)comboBox.getSelectedItem()+Integer.parseInt(birthYearTextField.getText())+Double.parseDouble(GPAtextField.getText()));
							
							std.save(st);
							tbl.setRowCount(0);
							tableUpdate();
							JLabel mess2 = new JLabel("Đã thêm 1 sinh viên");
							mess2.setFont(new Font("Arial", Font.BOLD, 18));
							JOptionPane.showMessageDialog(null, mess2);
						}
						
							
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						if(e1.getMessage().equals("birthyear"))
						{
							JLabel mess = new JLabel("Năm sinh phải nằm trong đoạn [1970,2002]");
							mess.setFont(new Font("Arial", Font.BOLD, 18));
							JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
						}
						if(e1.getMessage().equals("gpa"))
						{
							JLabel mess = new JLabel("Điểm phải bé hơn hoặc bằng 10");
							mess.setFont(new Font("Arial", Font.BOLD, 18));
							JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
						}
						if(e1.getMessage().equals("For input string: \"\""))
						{
							JLabel mess = new JLabel("Thông tin chưa được nhập đầy đủ");
							mess.setFont(new Font("Arial", Font.BOLD, 18));
							JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
						}
						
						else
						{
							JLabel mess = new JLabel("Thông tin nhập không đúng cách. Cụ thể lỗi: "+e1.getMessage());
							mess.setFont(new Font("Arial", Font.BOLD, 18));
							JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
						}
					}
			}
		});
		Addbtn.setBounds(39, 573, 114, 45);
		Addbtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		contentPane.add(Addbtn);
		
		JLabel mssvLabel = new JLabel("MSSV: ");
		mssvLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		mssvLabel.setBounds(63, 90, 90, 45);
		contentPane.add(mssvLabel);
		
		JLabel nameLabel = new JLabel("H\u1ECD & T\u00EAn:  ");
		nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		nameLabel.setBounds(29, 145, 123, 45);
		contentPane.add(nameLabel);
		
		JLabel classLabel = new JLabel("L\u1EDBp:  ");
		classLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		classLabel.setBounds(88, 245, 64, 45);
		contentPane.add(classLabel);
		
		mssvTextField = new JTextField(20);
		mssvTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if((Character.isDigit(k)) || (Character.isLetter(k)))
				{
					
					if(mssvTextField.getText().length()<20)
					{
						mssvTextField.setEditable(true);
					}
					else
					{
						mssvTextField.setEditable(false);
					}
				}
				else
				{
					if((k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE)
					{
							
						mssvTextField.setEditable(true);
					}
					else
					{
						eve.consume();
					}
				}
				
				
				
			}
		});
		mssvTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		mssvTextField.setBounds(173, 100, 157, 26);
		contentPane.add(mssvTextField);
		mssvTextField.setColumns(10);
		
		NameTextField = new JTextField(50);
		NameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if(Character.isLetter(k))
				{
					
					if(NameTextField.getText().length()<20)
					{
						NameTextField.setEditable(true);
					}
					else
					{
						NameTextField.setEditable(false);
					}
				}
				else
				{
					if((k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE || k==KeyEvent.VK_SPACE)
					{
							
						NameTextField.setEditable(true);
					}
					else
					{
						eve.consume(); //Tiêu thụ sự kiện này => Không xử lý nó
					}
				}
			}
		});
		NameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		NameTextField.setColumns(10);
		NameTextField.setBounds(174, 148, 260, 36);
		contentPane.add(NameTextField);
		
		JLabel gpaLabel = new JLabel("GPA:  ");
		gpaLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		gpaLabel.setBounds(82, 333, 71, 45);
		contentPane.add(gpaLabel);
		
		GPAtextField = new JTextField(3);
		GPAtextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if((Character.isDigit(k))  || k==KeyEvent.VK_PERIOD)
				{
					if(GPAtextField.getText().length()<3)
					{
						GPAtextField.setEditable(true);
					}
					else
					{
						GPAtextField.setEditable(false);
					}
				}
				else
				{
					if((k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE)
					{
						
						GPAtextField.setEditable(true);
					}
					else
					{
						eve.consume();
					}
				}
			}
		});
		GPAtextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GPAtextField.setColumns(10);
		GPAtextField.setBounds(174, 340, 64, 26);
		contentPane.add(GPAtextField);
		
		JLabel addressLabel = new JLabel("\u0110\u1ECBa ch\u1EC9: ");
		addressLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		addressLabel.setBounds(63, 388, 90, 45);
		contentPane.add(addressLabel);
		
		
		
		
		AddressTextArea = new JTextArea();
		AddressTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if((Character.isDigit(k)) || (Character.isLetter(k)) || (k==KeyEvent.VK_COMMA) || (k==KeyEvent.VK_SLASH))
				{
					
					if(AddressTextArea.getText().length()<50)
					{
						AddressTextArea.setEditable(true);
					}
					else
					{
						AddressTextArea.setEditable(false);
					}
				}
				else
				{
					if((k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE || k==KeyEvent.VK_SPACE )
					{
							
						AddressTextArea.setEditable(true);
					}
					else
					{
						eve.consume();
					}
				}
			}
		});
		//AddressTextArea.setWrapStyleWord(true);
		AddressTextArea.setLineWrap(true);
		AddressTextArea.setRows(5);
		AddressTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		AddressTextArea.setBounds(173, 402, 288, 100);
		contentPane.add(AddressTextArea);
		
		
		
		JLabel birthYearLabel = new JLabel("Năm Sinh: ");
		birthYearLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		birthYearLabel.setBounds(39, 501, 114, 45);
		contentPane.add(birthYearLabel);
		
		birthYearTextField = new JTextField();
		birthYearTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if((Character.isDigit(k)))
				{
					if(birthYearTextField.getText().length()<4)
					{
						birthYearTextField.setEditable(true);
					}
					else
					{
						birthYearTextField.setEditable(false);
					}
				}
				else
				{
					if((k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE)
					{
						birthYearTextField.setEditable(true);
					}
					else
					{
						eve.consume();
					}
				}
				
			}
		});
		birthYearTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		birthYearTextField.setColumns(10);
		birthYearTextField.setBounds(172, 512, 124, 26);
		contentPane.add(birthYearTextField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(502, 103, 1402, 811);
		contentPane.add(scrollPane);
		
		stTable = new JTable();
		stTable.setBorder(new SoftBevelBorder(BevelBorder.RAISED, SystemColor.info, SystemColor.activeCaption, null, null));
		stTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = stTable.getSelectedRow();
				TableModel model = stTable.getModel();
				String mssv=(model.getValueAt(row, 0).toString());
				String ten=(model.getValueAt(row, 1).toString());
				String gt=(model.getValueAt(row, 2).toString());
				String dc=(model.getValueAt(row, 3).toString());
				String lop=(model.getValueAt(row, 4).toString());
				String ns=(model.getValueAt(row, 5).toString());
				String gpa=(df.format(model.getValueAt(row, 6)).toString());
	
				mssvTextField.setText(mssv);
				mssvTextField.setEditable(false);
				NameTextField.setText(ten);
				if(gt.equals("Nữ"))
				{
					rdbtnFemaleButton.setSelected(true);
				}
				if(gt.equals("Nam"))
				{
					rdbtnMaleButton.setSelected(true);
				}
				AddressTextArea.setText(dc);
				ClassIdtextField.setText(lop);
				birthYearTextField.setText(ns);
				GPAtextField.setText(gpa);
				
				Updatebtn.setEnabled(true);
				Deletebtn.setEnabled(true);
				Addbtn.setEnabled(false);
				
				//int i;
				//String c = comboBox.getSelectedItem().toString();
				//System.out.print(c);
				try {
					Connection con = sqlc.getConnection();
					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery("SELECT * FROM Classes");
					//
					while (rs.next()) {
						String ClassId = rs.getString("ClassId").toString();
						if(lop.equals(ClassId))
						{
							comboBox.setSelectedItem(rs.getString("ClassName").toString());
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				sqlc.closeConnection();
			}
		});
		//stTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		stTable.setRowSelectionAllowed(true);
		JTableHeader tableHeader = stTable.getTableHeader();
		tableHeader.setFont(new Font("Times New Roman", Font.BOLD, 24));
		stTable.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		stTable.setRowHeight(30);
		stTable.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"MSSV", "Họ & Tên", "Giới tính", "Địa chỉ", "Lớp", "Năm sinh", "GPA"}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Byte.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		stTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		stTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		stTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		stTable.getColumnModel().getColumn(3).setPreferredWidth(300);
		stTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		stTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		stTable.getColumnModel().getColumn(6).setPreferredWidth(50);
		//stTable.getColumnModel().getColumn(5).setPreferredWidth(15);
		scrollPane.setViewportView(stTable);
		
		
		
		//cập nhật/ sửa sinh viên
		Updatebtn.setEnabled(false);
		Updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
						if(Integer.parseInt(birthYearTextField.getText())>2002  || Integer.parseInt(birthYearTextField.getText())<1970)
						{
							throw new Exception("birthyear");
						}
						if(Double.parseDouble(GPAtextField.getText())>10)
						{
							throw new Exception("gpa");
						}
						if((Integer.parseInt(birthYearTextField.getText())<=2002 || Integer.parseInt(birthYearTextField.getText())>=1970) && Double.parseDouble(GPAtextField.getText())<=10)
						{
							StudentDAO std = new StudentDAO();
							Student st= new Student(mssvTextField.getText(),NameTextField.getText(),buttonGroup.getSelection().getActionCommand(),AddressTextArea.getText(),ClassIdtextField.getText(),Integer.parseInt(birthYearTextField.getText()),Double.parseDouble(GPAtextField.getText()));
							//System.out.println(Integer.parseInt(mssvTextField.getText())+NameTextField.getText()+AddressTextArea.getText()+(String)comboBox.getSelectedItem()+Integer.parseInt(birthYearTextField.getText())+Double.parseDouble(GPAtextField.getText()));
							
							std.update(st);
							tbl.setRowCount(0);
							tableUpdate();
							JLabel mess = new JLabel("Đã cập nhật 1 sinh viên");
							mess.setFont(new Font("Arial", Font.BOLD, 18));
							JOptionPane.showMessageDialog(null, mess);
						}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1.getMessage().equals("birthyear"))
					{
						JLabel mess = new JLabel("Năm sinh phải nằm trong đoạn [1970,2002]");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					if(e1.getMessage().equals("gpa"))
					{
						JLabel mess = new JLabel("Điểm phải bé hơn hoặc bằng 10");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					if(e1.getMessage().equals("For input string: \"\""))
					{
						JLabel mess = new JLabel("Chưa chọn sinh viên để sửa");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					
					else
					{
						JLabel mess = new JLabel("Thông tin nhập không đúng cách. Cụ thể lỗi: "+e1.getMessage());
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		Updatebtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Updatebtn.setBounds(177, 573, 114, 45);
		contentPane.add(Updatebtn);
		
		
		//xóa sinh viên
		Deletebtn.setEnabled(false);
		Deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					StudentDAO std = new StudentDAO();
					int row = stTable.getSelectedRow();
					String value=(stTable.getModel().getValueAt(row, 0).toString());
					
					int answer = JOptionPane.showConfirmDialog(frame, "Bạn có chắc là muốn xóa sinh viên này không?", "CẢNH BÁO", JOptionPane.YES_NO_OPTION);
					
					if (answer == JOptionPane.YES_OPTION) 
					{
						
						//String query="DELETE FROM STUDENTS WHERE ID="+value;
						//String value=mssvTextField.getText();
						std.delete(value);
						JLabel mess = new JLabel("Đã xóa 1 sinh viên");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess);
						functionReset();
						
					} else if(answer == JOptionPane.NO_OPTION) {
					  	// do something else
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1.toString().contains("java.lang.ArrayIndexOutOfBoundsException"))
					{
						JLabel mess = new JLabel("Chưa chọn sinh viên để xóa");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					if(e1.toString().contains("Null"))
					{
						JLabel mess = new JLabel("Chưa chọn sinh viên để xóa");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						JLabel mess = new JLabel("Xóa không đúng cách. Cụ thể lỗi: "+e1);
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					//JOptionPane.showMessageDialog(frame, e1, "LỖI", JOptionPane.PLAIN_MESSAGE);

				}
				
			}
		});
		Deletebtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Deletebtn.setBounds(319, 573, 114, 45);
		contentPane.add(Deletebtn);
		genderLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		genderLabel.setBounds(39, 200, 113, 45);
		
		contentPane.add(genderLabel);
		buttonGroup.add(rdbtnFemaleButton);
		rdbtnFemaleButton.setSelected(true);
		rdbtnFemaleButton.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		rdbtnFemaleButton.setBounds(252, 212, 64, 21);
		
		contentPane.add(rdbtnFemaleButton);
		buttonGroup.add(rdbtnMaleButton);
		rdbtnMaleButton.setSelected(true);
		rdbtnMaleButton.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		rdbtnMaleButton.setBounds(179, 212, 71, 21);
		
		contentPane.add(rdbtnMaleButton);
		
		rdbtnMaleButton.setActionCommand("Nam");
		rdbtnFemaleButton.setActionCommand("Nữ");
		
		idSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				/*String query=idSearchField.getText().toLowerCase();
				
				TableRowSorter<DefaultTableModel> t = new TableRowSorter<DefaultTableModel>(tbl);
				stTable.setRowSorter(t);
				t.setRowFilter(RowFilter.regexFilter(query));*/
				//tbl.setRowCount(0); //reset table
				
				//reset textFields
				mssvTextField.setText("");
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				
				try {
					Connection con = sqlc.getConnection();
					PreparedStatement pst = con.prepareStatement("SELECT * FROM Students, Classes WHERE Students.ClassId=Classes.ClassId AND ID=?");
					pst.setString(1, idSearchField.getText());
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						String ID = rs.getString("ID");
						
						String name = rs.getString("Name");
						
						String gender = rs.getString("Gender");
						
						String address = rs.getString("Address");
						
						String lop = rs.getString("ClassId");
						
						String birthYear = rs.getString("birthYear");
						
						String gpa = rs.getString("GPA");
						
						mssvTextField.setText(ID);
						mssvTextField.setEditable(false);
						NameTextField.setText(name);
						if(gender.equals("Nữ"))
						{
							rdbtnFemaleButton.setSelected(true);
						}
						if(gender.equals("Nam"))
						{
							rdbtnMaleButton.setSelected(true);
						}
						AddressTextArea.setText(address);
						ClassIdtextField.setText(lop);;
						birthYearTextField.setText(birthYear);
						GPAtextField.setText(gpa);
						comboBox.setSelectedItem(rs.getString("ClassName").toString());
						
						
						//result.add(new Student(ID, Name, Gender, Address, Class, BirthYear, GPA));
					}
					
					
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
				sqlc.closeConnection();
				
				tbl.setRowCount(0); //reset table
				tableUpdateFiltered("SELECT * FROM Students WHERE Id LIKE N'%"+idSearchField.getText()+"%'"); //update table based on filter
				Updatebtn.setEnabled(true);
				Deletebtn.setEnabled(true);
			}
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if((Character.isDigit(k)) || (Character.isLetter(k)))
				{
					
					if(mssvTextField.getText().length()<20)
					{
						mssvTextField.setEditable(true);
					}
					else
					{
						mssvTextField.setEditable(false);
					}
				}
				else
				{
					if((k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE)
					{
							
						mssvTextField.setEditable(true);
					}
					else
					{
						eve.consume();
					}
				}
			}
		});
		idSearchField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		idSearchField.setColumns(10);
		idSearchField.setBounds(648, 66, 235, 36);
		
	
		contentPane.add(idSearchField);
		ClearField.setBackground(new Color(204, 0, 0));
		ClearField.setForeground(Color.WHITE);
		FilteredTitle.setVisible(false);
		ClearField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				functionReset();
			}
		});
		ClearField.setFont(new Font("Times New Roman", Font.BOLD, 24));
		ClearField.setBounds(319, 512, 114, 45);
		SearchLabel.setToolTipText("Refresh chương trình về trạng thái ban đầu");
		
		SearchLabel.setVisible(false);
		idSearchField.setVisible(false);
		nameSearchField.setVisible(false);
		GenderBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = GenderBox.getSelectedItem().toString();
				tbl.setRowCount(0); //reset table
				tableUpdateFiltered("SELECT * FROM Students WHERE Gender=N'"+selected+"'"); //update table based on filter
			}
		});
		GenderBox.setVisible(false);
		
		contentPane.add(ClearField);
		SearchLabel.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		SearchLabel.setBounds(893, 65, 157, 36);
		
		contentPane.add(SearchLabel);
		nameSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//tbl.setRowCount(0); //reset table
				
				//reset textFields
				mssvTextField.setText("");
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				
				
				
				tbl.setRowCount(0); //reset table
				tableUpdateFiltered("SELECT * FROM Students WHERE Name LIKE N'%"+nameSearchField.getText()+"%'"); //update table based on filter
			}
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if(Character.isLetter(k))
				{
					
					if(NameTextField.getText().length()<20)
					{
						NameTextField.setEditable(true);
					}
					else
					{
						NameTextField.setEditable(false);
					}
				}
				else
				{
					if((k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE || k==KeyEvent.VK_SPACE)
					{
							
						NameTextField.setEditable(true);
					}
					else
					{
						eve.consume(); //Tiêu thụ sự kiện này => Không xử lý nó
					}
				}
			}
		});
		nameSearchField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		nameSearchField.setColumns(10);
		nameSearchField.setBounds(648, 66, 235, 36);
		
		contentPane.add(nameSearchField);
		FilteredTitle.setBackground(Color.WHITE);
		FilteredTitle.setForeground(Color.BLUE);
		FilteredTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
		FilteredTitle.setBounds(1014, 10, 457, 45);
		
		contentPane.add(FilteredTitle);
		
	
		GenderBox.setModel(new DefaultComboBoxModel(new String[] {"Chưa chọn", "Nam", "Nữ"}));
		GenderBox.setToolTipText("Chọn giới tính để lọc sinh viên theo giới tính đó");
		GenderBox.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GenderBox.setBounds(648, 65, 135, 37);
		
		contentPane.add(GenderBox);
		
		//ấn tìm kiếm
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchLabel.setVisible(true);
				idSearchField.setVisible(true);
				tbl.setRowCount(0);
				tableUpdate();
				
				FilteredTitle.setVisible(false);
				SearchByCriteriaJCB.setVisible(true);
				SearchByCriteriaJCB.setSelectedIndex(0);
				
				mssvTextField.setText("");
				mssvTextField.setEditable(false);
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				searchBtn.setVisible(false);
				clickRefreshbtn.setVisible(false);
				Updatebtn.setEnabled(false);
				Deletebtn.setEnabled(false);
				Addbtn.setEnabled(false);
				
				hideSearchBtn.setVisible(true);
			}
		});
		searchBtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		searchBtn.setBounds(507, 66, 135, 36);
		contentPane.add(searchBtn);
		SearchByCriteriaJCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = SearchByCriteriaJCB.getSelectedItem().toString();
				if(selected.equals("MSSV"))
				{
					idSearchField.setVisible(true);
					nameSearchField.setVisible(false);
					GenderBox.setVisible(false);
				}
				if(selected.equals("Họ & Tên"))
				{
					idSearchField.setVisible(false);
					nameSearchField.setVisible(true);
					GenderBox.setVisible(false);
				}
				if(selected.equals("Giới Tính"))
				{
					idSearchField.setVisible(false);
					nameSearchField.setVisible(false);
					GenderBox.setVisible(true);
				}
				mssvTextField.setText("");
				mssvTextField.setEditable(false);
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				idSearchField.setText("");
				nameSearchField.setText("");
				GenderBox.setSelectedIndex(0);
				tbl.setRowCount(0);
				tableUpdate();
			}
		});
		SearchByCriteriaJCB.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		SearchByCriteriaJCB.setModel(new DefaultComboBoxModel(new String[] {"MSSV", "Họ & Tên", "Giới Tính"}));
		SearchByCriteriaJCB.setBounds(1042, 66, 135, 36);
		SearchByCriteriaJCB.setVisible(false);
		
		contentPane.add(SearchByCriteriaJCB);
		
		//ấn hủy tìm kiếm
		hideSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mssvTextField.setText("");
				mssvTextField.setEditable(true);
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				idSearchField.setText("");
				tbl.setRowCount(0);
				tableUpdate();
				clickRefreshbtn.setVisible(false);
				FilteredTitle.setVisible(false);
				SearchLabel.setVisible(false);
				idSearchField.setVisible(false);
				nameSearchField.setVisible(false);
				GenderBox.setVisible(false);
				SearchByCriteriaJCB.setVisible(false);
				
				Updatebtn.setEnabled(false);
				Deletebtn.setEnabled(false);
				Addbtn.setEnabled(true);
				
				hideSearchBtn.setVisible(false);
				searchBtn.setVisible(true);
			}
		});
		hideSearchBtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		hideSearchBtn.setBounds(507, 66, 135, 36);
		contentPane.add(hideSearchBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 565, 419, 8);
		contentPane.add(separator);
		toolBar.setBackground(SystemColor.activeCaption);
		toolBar.setBounds(0, 0, 296, 45);
		toolBar.setVisible(false);
		contentPane.add(toolBar);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar.setVisible(false);
				toolBar.setEnabled(false);
			}
		});
		
		
		RankTitleJCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionReset();
				tbl.setRowCount(0);
				mssvTextField.setEditable(false);
				String rank = RankTitleJCB.getSelectedItem().toString();
				if(rank.equals("Xuất sắc"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=9"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.BLUE);
					FilteredTitle.setText("Danh sách Sinh viên được Học bổng");
				}
				if(rank.equals("Giỏi"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=8 AND GPA<9"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.CYAN);
					FilteredTitle.setText("Danh sách Sinh viên Giỏi");
				}
				if(rank.equals("Khá"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=7 AND GPA<8"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.GREEN);
					FilteredTitle.setText("Danh sách Sinh viên Khá");
				}
				if(rank.equals("Trung Bình"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=5 AND GPA<7"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.ORANGE);
					FilteredTitle.setText("Danh sách Sinh viên Trung Bình");
				}
				if(rank.equals("Yếu"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA<5"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.BLACK);
					FilteredTitle.setText("Danh sách Sinh viên Yếu");
				}
				if(rank.equals("Tất cả"))
				{
					tableUpdateFiltered("SELECT * FROM Students"); //update table based on filter
					clickRefreshbtn.setVisible(false); //show the tip
					FilteredTitle.setVisible(false);
				}
			}
		});
		RankLabel.setForeground(SystemColor.desktop);
		toolBar.add(RankLabel);
		RankLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		RankTitleJCB.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		RankTitleJCB.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Xuất sắc", "Giỏi", "Khá", "Trung Bình", "Yếu"}));
		toolBar.add(RankTitleJCB);
		toolBar.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnX.setBackground(Color.RED);
		
		toolBar2.setBackground(SystemColor.info);
		toolBar2.setBounds(319, 0, 440, 45);
		toolBar2.setVisible(false);
		
		contentPane.add(toolBar2);
		toolBar2.add(ListingLabel);
		ListingLabel.setBackground(SystemColor.menuText);
		ListingLabel.setForeground(SystemColor.menuText);
		ListingLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		listingJCB.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				String select = listingJCB.getSelectedItem().toString();
				if(select.equals("Tỉ lệ nam : nữ"))
				{
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE Gender=N'Nam'");
					int malecount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE Gender=N'Nữ'");
					int femalecount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdate();
					DefaultPieDataset pieDataset = new DefaultPieDataset();
					pieDataset.setValue("Nam", malecount);
					pieDataset.setValue("Nữ", femalecount);
					JFreeChart chart = ChartFactory.createPieChart("Tỉ lệ Nam : Nữ", pieDataset, true, true, true);
					ChartFrame frame = new ChartFrame("Biểu đồ tròn Tỉ lệ Nam : Nữ", chart);
					frame.setVisible(true);
					frame.setSize(600, 800);
				}
				if(select.equals("Ngành theo số sinh viên"))
				{
					int i=0;
					DefaultPieDataset pieDataset = new DefaultPieDataset();
					try {
						Connection con = sqlc.getConnection();
						Statement stm = con.createStatement();
						ResultSet rs = stm.executeQuery("SELECT * FROM Students, Classes WHERE Students.ClassId=Classes.ClassId");
						int count=0;
						while (rs.next()) {
							//String ClassId = rs.getString("ClassId").toString();
							
							tbl.setRowCount(0);
							tableUpdateFiltered("SELECT * FROM Students WHERE ClassId=N'"+rs.getString("ClassId").toString()+"'");
							if(stTable.getRowCount()==0)
							{
								count=0;
							}
							if(stTable.getRowCount()!=0)
							{
								count=stTable.getRowCount();
							}	
							i++;
							pieDataset.setValue(rs.getString("ClassName"), count);
							//System.out.println(rs.getString("ClassName")+" "+count);
							count=0;
						}
						tbl.setRowCount(0);
						tableUpdate();
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					sqlc.closeConnection();
					
					
					
					JFreeChart chart = ChartFactory.createPieChart("Tỉ lệ Ngành theo số sinh viên", pieDataset, true, true, true);					
					ChartFrame frame = new ChartFrame("Biểu đồ tròn Ngành theo số sinh viên", chart);
					frame.setVisible(true);
					frame.setSize(600, 800);
					
				}
				if(select.equals("Xếp loại"))
				{
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA<5");
					int weakcount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=5 AND GPA<7");
					int avgcount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=7 AND GPA<8");
					int closecount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=8 AND GPA<9");
					int goodcount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=9");
					int outstandingcount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdate();
					DefaultPieDataset pieDataset = new DefaultPieDataset();
					pieDataset.setValue("Yếu (<5)", weakcount);
					pieDataset.setValue("Trung Bình (>=5)", avgcount);
					pieDataset.setValue("Khá (>=7)", closecount);
					pieDataset.setValue("Giỏi (>=8)", goodcount);
					pieDataset.setValue("Xuất sắc (>=9)", outstandingcount);
					JFreeChart chart = ChartFactory.createPieChart("Tỉ lệ Xếp loại", pieDataset, true, true, true);
					ChartFrame frame = new ChartFrame("Biểu đồ tròn Tỉ Xếp loại", chart);
					frame.setVisible(true);
					frame.setSize(600, 800);
					
				}
				if(select.equals("Điểm"))
				{ 
					int i;
					int[] count = new int[100];
					for(i=0;i<11;i++)
					{
						int j=i+1;
						tbl.setRowCount(0);
						tableUpdateFiltered("SELECT * FROM Students WHERE GPA>="+i+" AND GPA <"+j);
						if(stTable.getRowCount()==0)
						{
							count[i]=0;
						}
						if(stTable.getRowCount()!=0)
						{
							count[i]=stTable.getRowCount();
						}
					}
					
					tbl.setRowCount(0);
					tableUpdate();
					
					DefaultCategoryDataset Dataset = new DefaultCategoryDataset();
					for(i=0;i<11;i++)
					{
						Dataset.setValue(count[i], Integer.toString(i), Integer.toString(i));
					}
					/*for(i=0;i<11;i++)
					{
						System.out.print(count[i]+" điểm "+i+" ");
					}*/
					
					JFreeChart chart = ChartFactory.createBarChart3D("Thống kê điểm" , "Loại điểm", "Số lượng", Dataset, PlotOrientation.VERTICAL, true, true, true);
					ChartFrame frame = new ChartFrame("Sơ đồ cột Tỉ lệ Điểm", chart);
					frame.setVisible(true);
					frame.setSize(600, 800);
					
				}
				
			}
		});
		listingJCB.setModel(new DefaultComboBoxModel(new String[] {"None", "Ngành theo số sinh viên", "Tỉ lệ nam : nữ", "Điểm", "Xếp loại"}));
		listingJCB.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		toolBar2.add(listingJCB);
		btnX2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar2.setVisible(false);
			}
		});
		btnX2.setForeground(Color.WHITE);
		btnX2.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnX2.setBackground(Color.RED);
		
		toolBar2.add(btnX2);
		classIdLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		classIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		classIdLabel.setBounds(39, 294, 113, 36);
		
		contentPane.add(classIdLabel);
		
		ClassIdtextField.setText("CNTT");
		ClassIdtextField.setEditable(false);
		ClassIdtextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		ClassIdtextField.setColumns(10);
		ClassIdtextField.setBounds(174, 301, 157, 26);
		
		contentPane.add(ClassIdtextField);
		toolBar3.setBackground(SystemColor.textHighlight);
		toolBar3.setBounds(0, 43, 478, 45);
		toolBar3.setVisible(false);
		
		contentPane.add(toolBar3);
		toolBar3.add(SortingLabel);
		SortingLabel.setForeground(SystemColor.desktop);
		SortingLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		SortingLabel.setBackground(Color.BLACK);
		SortingJCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionReset();
				tbl.setRowCount(0);
				mssvTextField.setEditable(false);
				String select = SortingJCB.getSelectedItem().toString();
				String citeria = OrderJCB.getSelectedItem().toString();
				if(select.equals("MSSV") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Id ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("MSSV") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Id DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Họ & Tên") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Name ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Họ & Tên") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Name DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Giới Tính") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Gender ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Giới Tính") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Gender DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Lớp") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY ClassId ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Lớp") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY ClassId DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Điểm") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY GPA ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Điểm") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY GPA DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Năm sinh") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY BirthYear ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Năm sinh") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY BirthYear DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
			}
		});
		SortingJCB.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		SortingJCB.setModel(new DefaultComboBoxModel(new String[] {"MSSV", "Họ & Tên", "Giới Tính", "Lớp", "Năm sinh", "Điểm"}));
		toolBar3.add(SortingJCB);
		btnX3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar3.setVisible(false);
				toolBar3.setEnabled(false);
			}
		});
		OrderJCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionReset();
				tbl.setRowCount(0);
				mssvTextField.setEditable(false);
				String select = SortingJCB.getSelectedItem().toString();
				String citeria = OrderJCB.getSelectedItem().toString();
				if(select.equals("MSSV") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Id ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("MSSV") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Id DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Họ & Tên") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Name ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Họ & Tên") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Name DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Giới Tính") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Gender ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Giới Tính") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY Gender DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Lớp") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY ClassId ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Lớp") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY ClassId DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Điểm") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY GPA ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Điểm") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY GPA DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Năm sinh") && citeria.equals("Tăng dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY BirthYear ASC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
				if(select.equals("Năm sinh") && citeria.equals("Giảm dần"))
				{
					tableUpdateFiltered("SELECT * FROM Students ORDER BY BirthYear DESC"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
				}
			}
		});
		OrderJCB.setModel(new DefaultComboBoxModel(new String[] {"Tăng dần", "Giảm dần"}));
		toolBar3.add(OrderJCB);
		OrderJCB.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnX3.setForeground(Color.WHITE);
		btnX3.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnX3.setBackground(Color.RED);
		
		toolBar3.add(btnX3);
		logoLabel.setIcon(new ImageIcon(Menu.class.getResource("/img/logoUTC2.png")));
		logoLabel.setBounds(39, 640, 141, 140);
		
		contentPane.add(logoLabel);
		logoLabel_1.setIcon(new ImageIcon(Menu.class.getResource("/img/unnamed.png")));
		logoLabel_1.setBounds(174, 620, 244, 256);
		
		contentPane.add(logoLabel_1);
		hideSearchBtn.setVisible(false);
		
		//System.out.println(buttonGroup.getSelection().getActionCommand());
	}
	public String getIDTextField() {
		return mssvTextField.getText();
	}
	public String getNameTextField() {
		return NameTextField.getText();
	}
	public String getGPAtextField() {
		return GPAtextField.getText();
	}
	public String getClassTextField() {
		return ((String) comboBox.getSelectedItem());
	}
	public String getAddressTextArea() {
		return AddressTextArea.getText();
	}
	public String getBirthYearTextField() {
		return birthYearTextField.getText();
	}
	
/*	public List<Student> getAll() {
		List<Student> result = new ArrayList<Student>();
		
		try {
			Connection con = sqlc.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Students");
			//
			while (rs.next()) {
				String ID = rs.getString("ID");
				String Name = rs.getString("Name");
				String Gender = rs.getString("Gender");
				String Address = rs.getString("Address");
				String ClassId = rs.getString("ClassId");
				int BirthYear = rs.getInt("BirthYear");
				float GPA = rs.getFloat("GPA");
				
				result.add(new Student(ID, Name, Gender, Address, ClassId, BirthYear, GPA));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlc.closeConnection();
		return result;
	}*/
	
	public void tableUpdate() //Update table's datas
	{
		StudentDAO std=new StudentDAO();
		List<Student> stl = std.getAll();
		tbl = (DefaultTableModel)stTable.getModel();
		rowSorter=new TableRowSorter<TableModel>(stTable.getModel());
		stTable.setRowSorter(rowSorter);
		Object[] row = new Object[7];
		for(int i=0;i<stl.size();i++)
		{
			row[0]=stl.get(i).getId();
			row[1]=stl.get(i).getName();
			row[2]=stl.get(i).getGender();
			row[3]=stl.get(i).getAddress();
			row[4]=stl.get(i).getClassId();
			row[5]=stl.get(i).getAge();
			row[6]=stl.get(i).getGpa();
			tbl.addRow(row);
		}
	}
	
	
	public List<Student> getFilteredInfo(String t) {
		List<Student> result = new ArrayList<Student>();
		
		try {
			Connection con = sqlc.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(t);
			while (rs.next()) {
				String ID = rs.getString("ID");
				String Name = rs.getString("Name");
				String Gender = rs.getString("Gender");
				String Address = rs.getString("Address");
				String ClassId = rs.getString("ClassId");
				int BirthYear = rs.getInt("BirthYear");
				float GPA = rs.getFloat("GPA");
				
				result.add(new Student(ID, Name, Gender, Address, ClassId, BirthYear, GPA));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlc.closeConnection();
		return result;
	}
	public void tableUpdateFiltered(String t) //Update table's datas
	{
		List<Student> stl = getFilteredInfo(t);
		tbl = (DefaultTableModel)stTable.getModel();
		Object[] row = new Object[7];
		for(int i=0;i<stl.size();i++)
		{
			row[0]=stl.get(i).getId();
			row[1]=stl.get(i).getName();
			row[2]=stl.get(i).getGender();
			row[3]=stl.get(i).getAddress();
			row[4]=stl.get(i).getClassId();
			row[5]=stl.get(i).getAge();
			row[6]=stl.get(i).getGpa();
			tbl.addRow(row);
		}
	}
	
	public void functionReset()
	{
		mssvTextField.setText("");
		mssvTextField.setEditable(true);
		NameTextField.setText("");
		rdbtnFemaleButton.setSelected(false);
		rdbtnMaleButton.setSelected(false);
		AddressTextArea.setText("");
		comboBox.setSelectedIndex(0);
		birthYearTextField.setText("");
		GPAtextField.setText("");
		idSearchField.setText("");
		tbl.setRowCount(0);
		tableUpdate();
		clickRefreshbtn.setVisible(false);
		FilteredTitle.setVisible(false);
		SearchLabel.setVisible(false);
		idSearchField.setVisible(false);
		nameSearchField.setVisible(false);
		GenderBox.setVisible(false);
		SearchByCriteriaJCB.setVisible(false);
		hideSearchBtn.setVisible(false);
		searchBtn.setVisible(true);
		
		Updatebtn.setEnabled(false);
		Deletebtn.setEnabled(false);
		Addbtn.setEnabled(true);
		ClassUpdate();
	}
	
	public void exportExcel(JTable table) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		int i = chooser.showSaveDialog(chooser);
		if (i == JFileChooser.APPROVE_OPTION) 
		{
			File file = chooser.getSelectedFile();
			try {
					//FileWriter out = new FileWriter(file + ".xls");
					//BufferedWriter bwrite = new BufferedWriter(out);
					
					FileOutputStream fileOutputStream = new FileOutputStream(file + ".txt");
		            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
		            BufferedWriter bw = new BufferedWriter(writer);
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					// ten Cot
					for (int j = 0; j < table.getColumnCount(); j++) {
						bw.write(model.getColumnName(j) + "\t");
					}
					bw.write("\n");
					// Lay du lieu dong
					for (int j = 0; j < table.getRowCount(); j++) 
					{
						for (int k = 0; k < table.getColumnCount(); k++) 
						{
							bw.write(model.getValueAt(j, k) + "\t");
						}
						bw.write("\n");
					}
					bw.close();
					JOptionPane.showMessageDialog(null, "Lưu file thành công!");
				} catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, "Lỗi khi lưu file! Cụ thể "+e2.getMessage());
				}
		}
	}
	
	
	public static void ClassUpdate() //Update class datas
	{
		System.out.println("ClassUpdate");
		if(comboBox.getItemCount()>0)
		{
			comboBox.removeAllItems();
		}
		try {
			Connection con = sqlc.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Classes");
			while (rs.next()) {
				comboBox.addItem(rs.getString("ClassName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlc.closeConnection();
	}
}