package self.AnujBhyravabhotla.FBLA2014.GraphicInterface;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import self.AnujBhyravabhotla.FBLA2014.Data.Animal;
import self.AnujBhyravabhotla.FBLA2014.Data.Cat;
import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;
import self.AnujBhyravabhotla.FBLA2014.Data.Other;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;
/**
 * FBLA Desktop Application Programming Project 2014-2015.  
 * Allows the user to enter general information about an animal. 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Graphical Interface.
 * */
public class GeneralInventoryForm {
	/**The main application frame*/
	private JFrame frmFurryFriendsAnimal;
	/**The case number assigned to this animal*/
	public static int caseNumberGlobal;
	/**The animal that gets created from the data entered into this form.
	 * If the user entered a case number in the first screen and it returned an animal, it is assigned to this variable*/
	public static Animal animal;
	/**A group of radio buttons that let the user decide whether the animal is a dog, a cat, or neither (other).*/
	private final ButtonGroup typeSelector = new ButtonGroup();
	/*The rest of these fields are text fields to input the name, date of birth, arrival date, age, micro-chip information, relinquishing party, and cage number 
	 * for new arrivals.*/
	private JTextField txtNamefield;
	private JTextField txtAgefield;
	private JTextField txtChipid;
	private JTextField textChipOwner;
	private JTextField txtRelinquishingParty;
	private JTextField txtCagenumber;
	/**Advances the application from this form to the specialized forms.  The data is first stored in an animal object before the screen advances*/
	private JButton btnContinue;
	/**Returns the application to the search window.  Any data entered in the form is purged.*/
	private JButton btnGoBack;
	private JDateChooser birthdateChooser;
	private JDateChooser arrivaldateChooser;
	private JDateChooser microchippeddateChooser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralInventoryForm window = new GeneralInventoryForm();
					window.frmFurryFriendsAnimal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GeneralInventoryForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFurryFriendsAnimal = new JFrame();
		frmFurryFriendsAnimal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int caseNum=0;
				if(animal!=null) caseNum=animal.caseNumber;
				int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to exit the program?");
				if(confirm==JOptionPane.YES_OPTION){
					try{
						Inventory.mapDataToCSV();
						System.exit(0);
					}catch(IOException e){ 
						e.printStackTrace();
					}catch(NullPointerException e){
						JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "The data in this form cannot be kept in the inventory."
								+ "  It will be deleted from the record.");
						Inventory.generalInventory.remove(caseNum);
						try {
							Inventory.mapDataToCSV();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.exit(0);
					}
				}
			}
		});
		frmFurryFriendsAnimal.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFurryFriendsAnimal.setTitle("Furry Friends Animal Shelter: Records System");
		frmFurryFriendsAnimal.setBounds(100, 100, 500, 525);
		frmFurryFriendsAnimal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmFurryFriendsAnimal.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Fill out the information for the animal in the form below, then hit \"Continue\"");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frmFurryFriendsAnimal.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		frmFurryFriendsAnimal.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtNamefield = new JTextField();
		txtNamefield.setToolTipText("This field is required");
		GridBagConstraints gbc_txtNamefield = new GridBagConstraints();
		gbc_txtNamefield.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNamefield.insets = new Insets(0, 0, 5, 0);
		gbc_txtNamefield.gridx = 1;
		gbc_txtNamefield.gridy = 1;
		frmFurryFriendsAnimal.getContentPane().add(txtNamefield, gbc_txtNamefield);
		if(animal!=null){
			txtNamefield.setText(animal.getName());
			//txtNamefield.setEditable(false);
		}
		txtNamefield.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Animal Type", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		frmFurryFriendsAnimal.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JRadioButton rdbtnDog = new JRadioButton("Dog");
		rdbtnDog.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeSelector.add(rdbtnDog);
		panel.add(rdbtnDog);
		if(animal!=null&&animal.getType().equalsIgnoreCase("Dog")) rdbtnDog.setSelected(true);
		
		final JRadioButton rdbtnCat = new JRadioButton("Cat");
		rdbtnCat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeSelector.add(rdbtnCat);
		panel.add(rdbtnCat);
		if(animal!=null&&animal.getType().equalsIgnoreCase("Cat")) rdbtnCat.setSelected(true);
		
		final JRadioButton rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeSelector.add(rdbtnOther);
		panel.add(rdbtnOther);
		if(animal!=null&&animal.getType().equalsIgnoreCase("Other")) rdbtnOther.setSelected(true);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridwidth = 3;
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 3;
		frmFurryFriendsAnimal.getContentPane().add(panel_5, gbc_panel_5);
		panel_5.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_5.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{124, 82, 149, 0, 0};
		gbl_panel_2.rowHeights = new int[]{20, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDateOfBirth = new GridBagConstraints();
		gbc_lblDateOfBirth.anchor = GridBagConstraints.EAST;
		gbc_lblDateOfBirth.fill = GridBagConstraints.VERTICAL;
		gbc_lblDateOfBirth.insets = new Insets(0, 0, 0, 5);
		gbc_lblDateOfBirth.gridx = 1;
		gbc_lblDateOfBirth.gridy = 0;
		panel_2.add(lblDateOfBirth, gbc_lblDateOfBirth);
		
		
		birthdateChooser = new JDateChooser();
		birthdateChooser.setToolTipText("This field is required");
		birthdateChooser.setMaxSelectableDate(new Date());
		GridBagConstraints gbc_birthdateChooser = new GridBagConstraints();
		gbc_birthdateChooser.gridwidth = 2;
		gbc_birthdateChooser.anchor = GridBagConstraints.NORTH;
		gbc_birthdateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_birthdateChooser.gridx = 2;
		gbc_birthdateChooser.gridy = 0;
		panel_2.add(birthdateChooser, gbc_birthdateChooser);
		birthdateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				GregorianCalendar today = new GregorianCalendar();
				if(birthdateChooser.getCalendar()!=null)
					txtAgefield.setText(today.get(today.YEAR)-birthdateChooser.getCalendar().get(birthdateChooser.getCalendar().YEAR)+"");
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_5.add(panel_3);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("91px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("91px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("154px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("139px"),},
			new RowSpec[] {
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("25px"),}));
		
		JLabel lblDateOfArrival = new JLabel("Date of Arrival:");
		panel_3.add(lblDateOfArrival, "4, 2, right, center");
		lblDateOfArrival.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		arrivaldateChooser = new JDateChooser();
		arrivaldateChooser.setToolTipText("This field is required\r\nDefault: Leave unchanged");
		arrivaldateChooser.setCalendar(new GregorianCalendar());
		arrivaldateChooser.setMaxSelectableDate(new Date());
		if(birthdateChooser.getCalendar()!=null) arrivaldateChooser.setMinSelectableDate(birthdateChooser.getCalendar().getTime());
		panel_3.add(arrivaldateChooser, "6, 2, 3, 1, fill, center");
		
		/*JButton btnUseTodaysDate = new JButton("Use Today's Date");
		panel_3.add(btnUseTodaysDate, "10, 2, left, center");
		btnUseTodaysDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUseTodaysDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arrivaldateChooser.setCalendar(new GregorianCalendar());
			}
		});*/
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("183px"),
				ColumnSpec.decode("28px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),}));
		
		JLabel lblAge = new JLabel("Age:");
		panel_4.add(lblAge, "2, 2, left, center");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtAgefield = new JTextField();
		panel_4.add(txtAgefield, "4, 2, 3, 1, left, top");
		txtAgefield.setVisible(true);
		txtAgefield.setColumns(10);
		if(animal!=null){
			birthdateChooser.setCalendar(animal.getBirthdate());
		}
		if(animal!=null){
			arrivaldateChooser.setCalendar(animal.getArrivalDate());
		}
		if(animal!=null){
			txtAgefield.setText(animal.getAge()+"");
			//txtAgefield.setEditable(false);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Microchip information", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 4;
		frmFurryFriendsAnimal.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		final JCheckBox chckbxMicrochipped = new JCheckBox("This animal has been microchipped");
		chckbxMicrochipped.setToolTipText("Check if the animal was microchipped");
		chckbxMicrochipped.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_chckbxMicrochipped = new GridBagConstraints();
		gbc_chckbxMicrochipped.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxMicrochipped.gridx = 0;
		gbc_chckbxMicrochipped.gridy = 0;
		panel_1.add(chckbxMicrochipped, gbc_chckbxMicrochipped);
		if(animal!=null&&animal.isMicroChipped()) chckbxMicrochipped.setSelected(true);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 1;
		panel_1.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{261, 261, 0};
		gbl_panel_6.rowHeights = new int[]{20, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblDateOfContact = new JLabel("Date of Contact (mm/dd/yyyy): ");
		lblDateOfContact.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDateOfContact = new GridBagConstraints();
		gbc_lblDateOfContact.fill = GridBagConstraints.BOTH;
		gbc_lblDateOfContact.insets = new Insets(0, 0, 0, 5);
		gbc_lblDateOfContact.gridx = 0;
		gbc_lblDateOfContact.gridy = 0;
		panel_6.add(lblDateOfContact, gbc_lblDateOfContact);
		lblDateOfContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		microchippeddateChooser = new JDateChooser();
		microchippeddateChooser.setToolTipText("This field is required");
		GridBagConstraints gbc_microchippeddateChooser = new GridBagConstraints();
		gbc_microchippeddateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_microchippeddateChooser.gridx = 1;
		gbc_microchippeddateChooser.gridy = 0;
		panel_6.add(microchippeddateChooser, gbc_microchippeddateChooser);
		if(animal!=null){
			microchippeddateChooser.setCalendar(animal.getMicroChipDate());
		}
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 2;
		panel_1.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblChipIdNumber = new JLabel("Chip ID number: ");
		panel_7.add(lblChipIdNumber);
		lblChipIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtChipid = new JTextField();
		txtChipid.setToolTipText("Enter a number or a series of numbers.  This field is required.");
		panel_7.add(txtChipid);
		txtChipid.setColumns(10);
		if(animal!=null){
			txtChipid.setText(animal.getMicrochipID()+"");
			//txtChipid.setEditable(false);
		}
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 3;
		panel_1.add(panel_8, gbc_panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JLabel lblOwner = new JLabel("Owner: ");
		panel_8.add(lblOwner);
		lblOwner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textChipOwner = new JTextField();
		textChipOwner.setToolTipText("If the animal was microchipped, this field is required.");
		panel_8.add(textChipOwner);
		textChipOwner.setColumns(10);
		textChipOwner.setVisible(false);
		
		lblOwner.setVisible(false);
		if(animal!=null&&chckbxMicrochipped.isSelected()){
			lblOwner.setVisible(true);
			textChipOwner.setVisible(true);
			textChipOwner.setText(animal.getOwner());
			//textChipOwner.setEditable(false);
		}
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridwidth = 3;
		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 5;
		frmFurryFriendsAnimal.getContentPane().add(panel_9, gbc_panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblRelinquishingParty = new JLabel("Relinquishing Party: ");
		panel_9.add(lblRelinquishingParty);
		lblRelinquishingParty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label = new JLabel("");
		panel_9.add(label);
		
		JLabel label_1 = new JLabel("");
		panel_9.add(label_1);
		
		JLabel label_2 = new JLabel("");
		panel_9.add(label_2);
		
		txtRelinquishingParty = new JTextField();
		txtRelinquishingParty.setToolTipText("This field is required.");
		panel_9.add(txtRelinquishingParty);
		txtRelinquishingParty.setColumns(10);
		
		JLabel label_3 = new JLabel("");
		panel_9.add(label_3);
		if(animal!=null) {
			txtRelinquishingParty.setText(animal.getRelinquishingParty());
			//txtRelinquishingParty.setEditable(false);
		}
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridwidth = 3;
		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 6;
		frmFurryFriendsAnimal.getContentPane().add(panel_10, gbc_panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCageNumber = new JLabel("Cage Number: ");
		panel_10.add(lblCageNumber);
		lblCageNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtCagenumber = new JTextField();
		panel_10.add(txtCagenumber);
		txtCagenumber.setColumns(10);
		txtCagenumber.setText(caseNumberGlobal+"");
		if(animal!=null) {
			txtCagenumber.setText(animal.getCageNumber()+"");
			//txtCagenumber.setEditable(false);
		}
		
		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridwidth = 3;
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 7;
		frmFurryFriendsAnimal.getContentPane().add(panel_11, gbc_panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setToolTipText("Click to return to the main screen. ");
		panel_11.add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to go back?"
						+ "You will lose all information you have entered in this form.");
				if(confirm==JOptionPane.YES_OPTION){
					try{
						Inventory.mapDataToCSV();
						MainScreen.main(null);
						frmFurryFriendsAnimal.dispose();
					}catch(NullPointerException ex){
						JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "The data in this form cannot be kept in the inventory."
								+ "  It will be deleted from the record.");
						Inventory.generalInventory.remove(caseNumberGlobal);
						try {
							Inventory.mapDataToCSV();
							MainScreen.main(null);
							frmFurryFriendsAnimal.dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}catch(IOException ex){
						ex.printStackTrace();
					}
				}
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnContinue = new JButton("Continue");
		btnContinue.setToolTipText("Click to advance to the next screen");
		panel_11.add(btnContinue);
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String owner = textChipOwner.getText();
				if(owner==null||owner.equals("")) owner = "Furry Friends Animal Shelter";
				if(animal==null){
					int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to continue?");
					if(confirm==JOptionPane.YES_OPTION){
						if(rdbtnDog.isSelected()){
							try {
								animal = new Animal(caseNumberGlobal,txtNamefield.getText(),"Dog",Integer.parseInt(txtAgefield.getText()),
										(GregorianCalendar)birthdateChooser.getCalendar(),(GregorianCalendar)arrivaldateChooser.getCalendar(),chckbxMicrochipped.isSelected(),Long.parseLong(txtChipid.getText()),
										(GregorianCalendar)microchippeddateChooser.getCalendar(),owner,txtRelinquishingParty.getText(),Integer.parseInt(txtCagenumber.getText()));
								DogInventoryForm.dog=animal;
								System.out.println(DogInventoryForm.dog);
								DogInventoryForm.main(null);
								frmFurryFriendsAnimal.dispose();
							} catch(NumberFormatException ex){
								JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
										+ "  Make sure the Age field, the Chip ID field, and the Cage Number field contain numbers only", "Error", JOptionPane.ERROR_MESSAGE);
								ex.printStackTrace();
							}
						}
						if(rdbtnCat.isSelected()){
							try {
								animal = new Animal(caseNumberGlobal,txtNamefield.getText(),"Cat",Integer.parseInt(txtAgefield.getText()),
										(GregorianCalendar)birthdateChooser.getCalendar(),(GregorianCalendar)arrivaldateChooser.getCalendar(),chckbxMicrochipped.isSelected(),Long.parseLong(txtChipid.getText()),
										(GregorianCalendar)microchippeddateChooser.getCalendar(),owner,txtRelinquishingParty.getText(),Integer.parseInt(txtCagenumber.getText()));
								Inventory.generalInventory.put(animal.caseNumber, animal);
								CatInventoryForm.main(null);
								frmFurryFriendsAnimal.dispose();
							} catch(NumberFormatException ex){
								JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
										+ "  Make sure the Age field, the Chip ID field, and the Cage Number field contain numbers only", "Error", JOptionPane.ERROR_MESSAGE);
								ex.printStackTrace();
							}
						}
						if(rdbtnOther.isSelected()){
							try {
								animal = new Animal(caseNumberGlobal,txtNamefield.getText(),"Other",Integer.parseInt(txtAgefield.getText()),
										(GregorianCalendar)birthdateChooser.getCalendar(),(GregorianCalendar)arrivaldateChooser.getCalendar(),chckbxMicrochipped.isSelected(),Long.parseLong(txtChipid.getText()),
										(GregorianCalendar)microchippeddateChooser.getCalendar(),owner,txtRelinquishingParty.getText(),Integer.parseInt(txtCagenumber.getText()));
								OtherInventoryForm.other=animal;
								OtherInventoryForm.main(null);
								frmFurryFriendsAnimal.dispose();
							} catch(NumberFormatException ex){
								JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
										+ "  Make sure the Age field, the Chip ID field, and the Cage Number field contain numbers only", "Error", JOptionPane.ERROR_MESSAGE);
								ex.printStackTrace();
							}
						}
					}
				}else{
					int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to continue?");
					if(confirm==JOptionPane.YES_OPTION){
						if(animal.getType().equalsIgnoreCase("Dog")){
							try{
								Animal entry = new Animal(animal.caseNumber,txtNamefield.getText(),"Dog",Integer.parseInt(txtAgefield.getText()),
										(GregorianCalendar)birthdateChooser.getCalendar(),(GregorianCalendar)arrivaldateChooser.getCalendar(),chckbxMicrochipped.isSelected(),Long.parseLong(txtChipid.getText()),
										(GregorianCalendar)microchippeddateChooser.getCalendar(),owner,txtRelinquishingParty.getText(),Integer.parseInt(txtCagenumber.getText()));
								if(!Inventory.generalInventory.containsValue(entry)){
									DogInventoryForm.searchedDog=Inventory.dogInventory.get(animal.caseNumber);
									DogInventoryForm.dog=entry;
									DogInventoryForm.caseNumber=animal.caseNumber;
									System.out.println(DogInventoryForm.searchedDog);
									DogInventoryForm.main(null);
									frmFurryFriendsAnimal.dispose();
								}else{
									DogInventoryForm.searchedDog=Inventory.dogInventory.get(animal.caseNumber);
									if(DogInventoryForm.searchedDog==null) DogInventoryForm.dog=entry;
									System.out.println(DogInventoryForm.searchedDog);
									DogInventoryForm.main(null);
									frmFurryFriendsAnimal.dispose();
								}
							} catch(NumberFormatException ex){
								JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
										+ "  Make sure the Age field, the Chip ID field, and the Cage Number field contain numbers only", "Error", JOptionPane.ERROR_MESSAGE);
								ex.printStackTrace();
							}
						}
						if(animal.getType().equalsIgnoreCase("Cat")){
							try{
								Animal entry =new Animal(animal.caseNumber,txtNamefield.getText(),"Cat",Integer.parseInt(txtAgefield.getText()),
										(GregorianCalendar)birthdateChooser.getCalendar(),(GregorianCalendar)arrivaldateChooser.getCalendar(),chckbxMicrochipped.isSelected(),Long.parseLong(txtChipid.getText()),
										(GregorianCalendar)microchippeddateChooser.getCalendar(),owner,txtRelinquishingParty.getText(),Integer.parseInt(txtCagenumber.getText()));
								if(!Inventory.generalInventory.containsValue(entry)){
									CatInventoryForm.searchedCat=Inventory.catInventory.get(animal.caseNumber);
									CatInventoryForm.cat=entry;
									CatInventoryForm.main(null);
									frmFurryFriendsAnimal.dispose();
								}else{
									CatInventoryForm.searchedCat=Inventory.catInventory.get(animal.caseNumber);
									if(CatInventoryForm.searchedCat==null) CatInventoryForm.cat=entry;
									System.out.println(DogInventoryForm.searchedDog);
									CatInventoryForm.main(null);
									frmFurryFriendsAnimal.dispose();
								}
							} catch(NumberFormatException ex){
								JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
										+ "  Make sure the Age field, the Chip ID field, and the Cage"
										+ " Number field contain numbers only", "Error", JOptionPane.ERROR_MESSAGE);
								ex.printStackTrace();
							}
						}
						if(animal.getType().equalsIgnoreCase("Other")){
							try{
								Animal entry = new Animal(animal.caseNumber,txtNamefield.getText(),"Other",Integer.parseInt(txtAgefield.getText()),
										(GregorianCalendar)birthdateChooser.getCalendar(),(GregorianCalendar)arrivaldateChooser.getCalendar(),chckbxMicrochipped.isSelected(),Long.parseLong(txtChipid.getText()),
										(GregorianCalendar)microchippeddateChooser.getCalendar(),owner,txtRelinquishingParty.getText(),Integer.parseInt(txtCagenumber.getText()));
								if(!Inventory.generalInventory.containsValue(entry)){
									OtherInventoryForm.searchedOther=Inventory.otherInventory.get(animal.caseNumber);
									OtherInventoryForm.other=entry;
									OtherInventoryForm.main(null);
									frmFurryFriendsAnimal.dispose();
								}else{
									OtherInventoryForm.searchedOther=Inventory.otherInventory.get(animal.caseNumber);
									if(OtherInventoryForm.searchedOther==null) OtherInventoryForm.other=entry;
									OtherInventoryForm.main(null);
									frmFurryFriendsAnimal.dispose();
								}
							}catch(NumberFormatException ex){
								JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
										+ "  Make sure the Age field, the Chip ID field, and the Cage Number field contain numbers only", "Error", JOptionPane.ERROR_MESSAGE);
								ex.printStackTrace();
							}
							}
						}
						
					}
				}
			
		});
		if(chckbxMicrochipped.isSelected()){
			lblOwner.setVisible(true);
			textChipOwner.setVisible(true);
			microchippeddateChooser.setMaxSelectableDate(new Date());
			if(birthdateChooser.getCalendar()!=null) microchippeddateChooser.setMinSelectableDate(birthdateChooser.getCalendar().getTime());
		}
		else{
			lblOwner.setVisible(false);
			textChipOwner.setVisible(false);
			microchippeddateChooser.setMinSelectableDate(new Date());
			microchippeddateChooser.setMaxSelectableDate(null);
		}
		chckbxMicrochipped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxMicrochipped.isSelected()){
					lblOwner.setVisible(true);
					textChipOwner.setVisible(true);
					microchippeddateChooser.setMaxSelectableDate(new Date());
					if(birthdateChooser.getCalendar()!=null) microchippeddateChooser.setMinSelectableDate(birthdateChooser.getCalendar().getTime());
				}
				else{
					lblOwner.setVisible(false);
					textChipOwner.setVisible(false);
					microchippeddateChooser.setMinSelectableDate(new Date());
					microchippeddateChooser.setMaxSelectableDate(null);
				}
			}
		});
		
	}

}
