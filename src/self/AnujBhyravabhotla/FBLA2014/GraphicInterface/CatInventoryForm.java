package self.AnujBhyravabhotla.FBLA2014.GraphicInterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import self.AnujBhyravabhotla.FBLA2014.Data.Animal;
import self.AnujBhyravabhotla.FBLA2014.Data.Cat;
import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;

import com.toedter.calendar.JDateChooser;
/**
 * FBLA Desktop Application Programming Project 2014-2015.  
 * Allows the user to enter specific information about an animal if that animal is a cat. 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Graphical Interface.
 * */
public class CatInventoryForm {
	/**The main window frame*/
	private JFrame frmFurryFriendsAnimal;
	/*Radio button groups.  The first one listed lets the owner select what type of declawing procedure should be performed if the 
	 * cat wasn't already declawed.  The second one listed lets the user select the cat's gender.  The third one selected lets
	 * the user select whether the cat was already declawed in the first case.*/
	private final ButtonGroup declawProcedureSelector = new ButtonGroup();
	private final ButtonGroup genderSelector = new ButtonGroup();
	private final ButtonGroup declawedSelector = new ButtonGroup();
	/**The animal representation of the data entered in the General Inventory form.*/
	public static Animal cat;
	/**If the user entered a case number in the first screen and it returned an animal that was a cat, this variable contains that
	 * result.*/
	public static Cat searchedCat;
	/*Checkboxes to be selected if the cat was already spayed/neutered, not flea tested, or given a rabies vaccination.
	 * If the check box for already spayed/neutered or not flea tested is selected, their date text fields are locked
	 * If the rabies vaccination checkbox is selected, the corresponding date field becomes unlocked.*/
	private JCheckBox chckbxAlreadySpayedNeutered;
	private JCheckBox chckbxRabies;
	private JCheckBox chckbxNotFleaTested;
	/*Dropdown menus for selecting values.  The first listed lets the user select the cat's feline leukemia result from either 
	 * "Positive" or "Negative".*/
	private JComboBox leukemiaResultBox;
	private JComboBox breedcomboBox;
	/**Saves all the data in this form as an object to the hashmap, then returns to the first screen.*/
	private JButton btnSave;
	/**Returns to the previous screen.  The data in that form is immutable, and all data entered in this form is purged.*/
	private JButton btnGoBack;
	private JDateChooser spayNeuterDateChooser;
	private JDateChooser fleaTestDateChooser;
	private JDateChooser felineLeukemiaDateChooser;
	private JDateChooser rabiesVaccineDateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CatInventoryForm window = new CatInventoryForm();
					window.frmFurryFriendsAnimal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public CatInventoryForm() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frmFurryFriendsAnimal = new JFrame();
		frmFurryFriendsAnimal.setTitle("Furry Friends Animal Shelter: Records System");
		frmFurryFriendsAnimal.setBounds(100, 100, 500, 675);
		frmFurryFriendsAnimal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmFurryFriendsAnimal.getContentPane().setLayout(gridBagLayout);
		frmFurryFriendsAnimal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to exit the program?");
				int caseNum=0;
				if(cat!=null) caseNum=cat.caseNumber;
				if(confirm==JOptionPane.YES_OPTION){
					try{
						Inventory.mapDataToCSV();
						System.exit(0);
					}catch(IOException e){ 
						e.printStackTrace();
					}catch(NullPointerException e){
						JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "The data in the previous form cannot be kept in the inventory."
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
		
		JLabel lblFillOutThe = new JLabel("Fill out the information for this cat in the form below");
		lblFillOutThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFillOutThe = new GridBagConstraints();
		gbc_lblFillOutThe.insets = new Insets(0, 0, 5, 0);
		gbc_lblFillOutThe.gridwidth = 16;
		gbc_lblFillOutThe.gridx = 0;
		gbc_lblFillOutThe.gridy = 0;
		frmFurryFriendsAnimal.getContentPane().add(lblFillOutThe, gbc_lblFillOutThe);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Breed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 16;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frmFurryFriendsAnimal.getContentPane().add(panel, gbc_panel);
		
		JLabel lblSelectFromThe = new JLabel("Select from the dropdown box:");
		lblSelectFromThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSelectFromThe);
		
		breedcomboBox = new JComboBox(Inventory.getCatBreeds());
		panel.add(breedcomboBox);
		if(searchedCat!=null){
			for(String s:Inventory.getCatBreeds()){
				if(s.contains(searchedCat.getBreed()+"")){
					breedcomboBox.setSelectedItem(s);
//					breedcomboBox.setEditable(false);
				}
			}
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gender information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 16;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		frmFurryFriendsAnimal.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		final JRadioButton rdbtnMaleCat = new JRadioButton("Male");
		genderSelector.add(rdbtnMaleCat);
		rdbtnMaleCat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_rdbtnMaleCat = new GridBagConstraints();
		gbc_rdbtnMaleCat.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMaleCat.gridx = 0;
		gbc_rdbtnMaleCat.gridy = 0;
		panel_1.add(rdbtnMaleCat, gbc_rdbtnMaleCat);
		
		JRadioButton rdbtnFemaleCat = new JRadioButton("Female");
		genderSelector.add(rdbtnFemaleCat);
		rdbtnFemaleCat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_rdbtnFemaleCat = new GridBagConstraints();
		gbc_rdbtnFemaleCat.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFemaleCat.gridwidth = 2;
		gbc_rdbtnFemaleCat.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFemaleCat.gridx = 1;
		gbc_rdbtnFemaleCat.gridy = 0;
		panel_1.add(rdbtnFemaleCat, gbc_rdbtnFemaleCat);
		if(searchedCat!=null&&searchedCat.isMale()) rdbtnMaleCat.setSelected(true);
		if(searchedCat!=null&&!searchedCat.isMale()) rdbtnFemaleCat.setSelected(true);
		
		JLabel label = new JLabel("Date for Spay/Neuter procedure : ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridwidth = 7;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel_1.add(label, gbc_label);
		
		spayNeuterDateChooser = new JDateChooser();
		if(cat!=null) spayNeuterDateChooser.setMinSelectableDate(cat.getArrivalDate().getTime());
		if(searchedCat!=null) spayNeuterDateChooser.setMinSelectableDate(searchedCat.getArrivalDate().getTime());
		GridBagConstraints gbc_spayNeuterDateChooser = new GridBagConstraints();
		gbc_spayNeuterDateChooser.gridwidth = 5;
		gbc_spayNeuterDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_spayNeuterDateChooser.fill = GridBagConstraints.BOTH;
		gbc_spayNeuterDateChooser.gridx = 7;
		gbc_spayNeuterDateChooser.gridy = 1;
		panel_1.add(spayNeuterDateChooser, gbc_spayNeuterDateChooser);
		
		JLabel label_1 = new JLabel("OR");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.gridwidth = 12;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		panel_1.add(label_1, gbc_label_1);
		
		chckbxAlreadySpayedNeutered = new JCheckBox("Check here if the cat has already been spayed/neutered");
		chckbxAlreadySpayedNeutered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAlreadySpayedNeutered.isSelected()){
					spayNeuterDateChooser.setCalendar(null);
					spayNeuterDateChooser.setEnabled(false);
				}
				else{
					spayNeuterDateChooser.setEnabled(true);
					if(cat!=null) spayNeuterDateChooser.setMinSelectableDate(cat.getArrivalDate().getTime());
					if(searchedCat!=null){
						spayNeuterDateChooser.setMinSelectableDate(searchedCat.getArrivalDate().getTime());
						spayNeuterDateChooser.setCalendar(searchedCat.getSpayNeuterDate());
					}
				}
			}
		});
		
		chckbxAlreadySpayedNeutered.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_chckbxAlreadySpayedNeutered = new GridBagConstraints();
		gbc_chckbxAlreadySpayedNeutered.anchor = GridBagConstraints.WEST;
		gbc_chckbxAlreadySpayedNeutered.gridwidth = 12;
		gbc_chckbxAlreadySpayedNeutered.gridx = 0;
		gbc_chckbxAlreadySpayedNeutered.gridy = 3;
		panel_1.add(chckbxAlreadySpayedNeutered, gbc_chckbxAlreadySpayedNeutered);
		if(searchedCat!=null&&searchedCat.isSpayNeutered()){
			spayNeuterDateChooser.setEnabled(false);
			chckbxAlreadySpayedNeutered.setSelected(true);
		}
		if(searchedCat!=null&&!searchedCat.isSpayNeutered()){
			chckbxAlreadySpayedNeutered.setSelected(false);
			spayNeuterDateChooser.setMinSelectableDate(searchedCat.getArrivalDate().getTime());
			spayNeuterDateChooser.setCalendar(searchedCat.getSpayNeuterDate());
			//spayNeuterDateChooser.setEditable(false);
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Flea Testing information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridwidth = 16;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		frmFurryFriendsAnimal.getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel label_2 = new JLabel("Date of first treament for fleas : ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.gridwidth = 9;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 0;
		panel_2.add(label_2, gbc_label_2);
		
		fleaTestDateChooser = new JDateChooser();
		if(cat!=null){
			fleaTestDateChooser.setMinSelectableDate(cat.getBirthdate().getTime());
			fleaTestDateChooser.setMaxSelectableDate(cat.getArrivalDate().getTime());
		}
		else if(searchedCat!=null){
			fleaTestDateChooser.setMinSelectableDate(searchedCat.getBirthdate().getTime());
			fleaTestDateChooser.setMaxSelectableDate(searchedCat.getArrivalDate().getTime());
		}
		GridBagConstraints gbc_fleaTestDateChooser = new GridBagConstraints();
		gbc_fleaTestDateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_fleaTestDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fleaTestDateChooser.gridx = 9;
		gbc_fleaTestDateChooser.gridy = 0;
		panel_2.add(fleaTestDateChooser, gbc_fleaTestDateChooser);
		
		JLabel label_3 = new JLabel("OR");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.gridwidth = 10;
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 1;
		panel_2.add(label_3, gbc_label_3);
		
		chckbxNotFleaTested = new JCheckBox("Check here if this cat hasn't been tested for fleas");
		chckbxNotFleaTested.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNotFleaTested.isSelected()){
					fleaTestDateChooser.setCalendar(null);
					fleaTestDateChooser.setEnabled(false);
				}
				else{ 
					fleaTestDateChooser.setEnabled(true);
					if(cat!=null){
						fleaTestDateChooser.setMinSelectableDate(cat.getBirthdate().getTime());
						fleaTestDateChooser.setMaxSelectableDate(cat.getArrivalDate().getTime());
					}
					else if(searchedCat!=null){
						fleaTestDateChooser.setMinSelectableDate(searchedCat.getBirthdate().getTime());
						fleaTestDateChooser.setMaxSelectableDate(searchedCat.getArrivalDate().getTime());
					}
				}
			}
		});
		
		chckbxNotFleaTested.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_chckbxNotFleaTested = new GridBagConstraints();
		gbc_chckbxNotFleaTested.gridwidth = 10;
		gbc_chckbxNotFleaTested.gridx = 0;
		gbc_chckbxNotFleaTested.gridy = 2;
		panel_2.add(chckbxNotFleaTested, gbc_chckbxNotFleaTested);
		if(searchedCat!=null&&searchedCat.isFleaTested()){
			fleaTestDateChooser.setCalendar(searchedCat.getDateFirstFleaTreatment());
			fleaTestDateChooser.setMinSelectableDate(searchedCat.getBirthdate().getTime());
			fleaTestDateChooser.setMaxSelectableDate(searchedCat.getArrivalDate().getTime());
			//fleaTestDateChooser.setEditable(false);
		}
		if(searchedCat!=null&&!searchedCat.isFleaTested()){
			fleaTestDateChooser.setEnabled(false);
			chckbxNotFleaTested.setSelected(true);
		}
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Declawed Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridwidth = 16;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		frmFurryFriendsAnimal.getContentPane().add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		JLabel lblDeclawed = new JLabel("Declawed?");
		lblDeclawed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblDeclawed);
		
		final JRadioButton rdbtnDeclawed = new JRadioButton("Yes");
		declawedSelector.add(rdbtnDeclawed);
		rdbtnDeclawed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(rdbtnDeclawed);
		
		final JRadioButton rdbtnNotDeclawed = new JRadioButton("No");
		declawedSelector.add(rdbtnNotDeclawed);
		rdbtnNotDeclawed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(rdbtnNotDeclawed);
		
		final JPanel declawProcedurePanel = new JPanel();
		declawProcedurePanel.setVisible(false);
		panel_3.add(declawProcedurePanel);
		
		JLabel lblDeclawingProcedure = new JLabel("If no, select declawing procedure: ");
		lblDeclawingProcedure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		declawProcedurePanel.add(lblDeclawingProcedure);
		
		final JRadioButton rdbtnTwo = new JRadioButton("Two");
		rdbtnTwo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		declawProcedureSelector.add(rdbtnTwo);
		declawProcedurePanel.add(rdbtnTwo);
		
		JRadioButton rdbtnFour = new JRadioButton("Four");
		rdbtnFour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		declawProcedureSelector.add(rdbtnFour);
		declawProcedurePanel.add(rdbtnFour);
		if(searchedCat!=null&&searchedCat.isDeclawed()) rdbtnDeclawed.setSelected(true);
		if(searchedCat!=null&&!searchedCat.isDeclawed()&&searchedCat.wantsTwoClawsRemoval()){
			rdbtnNotDeclawed.setSelected(true);
			rdbtnTwo.setSelected(true);
		}
		if(searchedCat!=null&&!searchedCat.isDeclawed()&&!searchedCat.wantsTwoClawsRemoval()){
			rdbtnNotDeclawed.setSelected(true);
			rdbtnFour.setSelected(true);
		}
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Feline Leukemia Testing Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.gridwidth = 16;
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 5;
		frmFurryFriendsAnimal.getContentPane().add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblTestDate = new JLabel("Test Date : ");
		lblTestDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTestDate = new GridBagConstraints();
		gbc_lblTestDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblTestDate.gridwidth = 8;
		gbc_lblTestDate.anchor = GridBagConstraints.EAST;
		gbc_lblTestDate.gridx = 0;
		gbc_lblTestDate.gridy = 0;
		panel_5.add(lblTestDate, gbc_lblTestDate);
		
		
		felineLeukemiaDateChooser = new JDateChooser();
		felineLeukemiaDateChooser.setToolTipText("This field is required");
		GridBagConstraints gbc_felineLeukemiaDateChooser = new GridBagConstraints();
		gbc_felineLeukemiaDateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_felineLeukemiaDateChooser.fill = GridBagConstraints.BOTH;
		gbc_felineLeukemiaDateChooser.gridx = 8;
		gbc_felineLeukemiaDateChooser.gridy = 0;
		panel_5.add(felineLeukemiaDateChooser, gbc_felineLeukemiaDateChooser);
		if(searchedCat!=null){
			felineLeukemiaDateChooser.setCalendar(searchedCat.getFelineLeukemiaTestDate());
			felineLeukemiaDateChooser.setMinSelectableDate(searchedCat.getBirthdate().getTime());
			felineLeukemiaDateChooser.setMaxSelectableDate(searchedCat.getArrivalDate().getTime());
			//felineLeukemiaDateChooser.setEnabled(false);
		}
		else if(cat!=null){
			felineLeukemiaDateChooser.setMinSelectableDate(cat.getBirthdate().getTime());
			felineLeukemiaDateChooser.setMaxSelectableDate(cat.getArrivalDate().getTime());
		}
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblResult = new GridBagConstraints();
		gbc_lblResult.anchor = GridBagConstraints.EAST;
		gbc_lblResult.gridwidth = 8;
		gbc_lblResult.insets = new Insets(0, 0, 0, 5);
		gbc_lblResult.gridx = 0;
		gbc_lblResult.gridy = 1;
		panel_5.add(lblResult, gbc_lblResult);
		
		leukemiaResultBox = new JComboBox(new String[]{"","Positive","Negative"});
		GridBagConstraints gbc_leukemiaResultBox = new GridBagConstraints();
		gbc_leukemiaResultBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_leukemiaResultBox.gridx = 8;
		gbc_leukemiaResultBox.gridy = 1;
		panel_5.add(leukemiaResultBox, gbc_leukemiaResultBox);
		if(searchedCat!=null&&searchedCat.hasFelineLeukemia()) leukemiaResultBox.setSelectedItem("Positive");
		if(searchedCat!=null&&!searchedCat.hasFelineLeukemia()) leukemiaResultBox.setSelectedItem("Negative");
//		if(searchedCat!=null) leukemiaResultBox.setEditable(false);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Vaccination information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.gridwidth = 16;
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 6;
		frmFurryFriendsAnimal.getContentPane().add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblForEachVaccine = new JLabel("<html>For each vaccine listed below, check off if the cat had it administered and enter the date it was given.</html>");
		lblForEachVaccine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblForEachVaccine = new GridBagConstraints();
		gbc_lblForEachVaccine.insets = new Insets(0, 0, 5, 0);
		gbc_lblForEachVaccine.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblForEachVaccine.gridwidth = 16;
		gbc_lblForEachVaccine.gridx = 0;
		gbc_lblForEachVaccine.gridy = 0;
		panel_6.add(lblForEachVaccine, gbc_lblForEachVaccine);
		
		chckbxRabies = new JCheckBox("Rabies");
		chckbxRabies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_chckbxRabies = new GridBagConstraints();
		gbc_chckbxRabies.anchor = GridBagConstraints.EAST;
		gbc_chckbxRabies.gridwidth = 3;
		gbc_chckbxRabies.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxRabies.gridx = 0;
		gbc_chckbxRabies.gridy = 1;
		panel_6.add(chckbxRabies, gbc_chckbxRabies);
		
		rabiesVaccineDateChooser = new JDateChooser();
		GridBagConstraints gbc_rabiesVaccineDateChooser = new GridBagConstraints();
		gbc_rabiesVaccineDateChooser.gridwidth = 9;
		gbc_rabiesVaccineDateChooser.insets = new Insets(0, 0, 0, 5);
		gbc_rabiesVaccineDateChooser.fill = GridBagConstraints.BOTH;
		gbc_rabiesVaccineDateChooser.gridx = 3;
		gbc_rabiesVaccineDateChooser.gridy = 1;
		panel_6.add(rabiesVaccineDateChooser, gbc_rabiesVaccineDateChooser);
		if(searchedCat!=null&&searchedCat.hasRabiesVaccine()){
			chckbxRabies.setSelected(true);
			//rabiesVaccineDateChooser.setEnabled(false);
			rabiesVaccineDateChooser.setCalendar(searchedCat.getRabiesVaccineDate());
			rabiesVaccineDateChooser.setMinSelectableDate(searchedCat.getBirthdate().getTime());
			rabiesVaccineDateChooser.setMaxSelectableDate(searchedCat.getArrivalDate().getTime());
		}
		else{
			chckbxRabies.setSelected(false);
			rabiesVaccineDateChooser.setEnabled(false);
			rabiesVaccineDateChooser.setCalendar(null);
		}
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.gridwidth = 16;
		gbc_panel_7.insets = new Insets(0, 0, 0, 5);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 7;
		frmFurryFriendsAnimal.getContentPane().add(panel_7, gbc_panel_7);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setToolTipText("Return to the General Inventory Form");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to return to the previous"
						+ "screen?  You will lose all information you have entered in this form.");
				if(confirm==JOptionPane.YES_OPTION){
					GeneralInventoryForm.animal=cat;
					System.out.println(GeneralInventoryForm.animal);
					GeneralInventoryForm.main(null);
					frmFurryFriendsAnimal.dispose();
				}
			}
		});
		panel_7.add(btnGoBack);
		
		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save this animal to the record");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(searchedCat==null){
					int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to save?");
					if(confirm==JOptionPane.YES_OPTION){
							try {
								Cat entry=new Cat(cat,Integer.parseInt(((String) breedcomboBox.getSelectedItem()).substring(0,((String) breedcomboBox.getSelectedItem()).indexOf("-"))),
										rdbtnMaleCat.isSelected(),chckbxAlreadySpayedNeutered.isSelected(),spayNeuterDateChooser.getCalendar(),!chckbxNotFleaTested.isSelected(),
										fleaTestDateChooser.getCalendar(),rdbtnDeclawed.isSelected(),rdbtnTwo.isSelected(),
										((String)leukemiaResultBox.getSelectedItem()).equals("Positive"),felineLeukemiaDateChooser.getCalendar(),chckbxRabies.isSelected(),
										rabiesVaccineDateChooser.getCalendar());
								Inventory.writeToCSV(entry.toStringArray(), "DOG_INVENTORY.csv");
								Inventory.writeToCSV(cat.toStringArray(),"GENERAL_INVENTORY.csv");
								Inventory.catInventory.put(entry.caseNumber, entry);
								Inventory.generalInventory.put(cat.caseNumber, cat);
								JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Data was successfully saved");
								MainScreen.main(null);
								frmFurryFriendsAnimal.dispose();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
										+ "  Make sure that a file called CAT_INVENTORY.csv exists in the same directory as this"
										+ " application's main executable file.", "Error", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							} catch(NumberFormatException ex){
								JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
										+ "  Make sure the Age field, the Chip ID field, and the Cage"
										+ " Number field contain numbers only", "Error", JOptionPane.ERROR_MESSAGE);
								ex.printStackTrace();
							}
					}
				}else{
					int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to save?");
					if(confirm==JOptionPane.YES_OPTION){
						try{
							Cat entry=new Cat(cat,Integer.parseInt(((String) breedcomboBox.getSelectedItem()).substring(0,((String) breedcomboBox.getSelectedItem()).indexOf("-"))),
									rdbtnMaleCat.isSelected(),chckbxAlreadySpayedNeutered.isSelected(),spayNeuterDateChooser.getCalendar(),!chckbxNotFleaTested.isSelected(),
									fleaTestDateChooser.getCalendar(),rdbtnDeclawed.isSelected(),rdbtnTwo.isSelected(),
									((String)leukemiaResultBox.getSelectedItem()).equals("Positive"),felineLeukemiaDateChooser.getCalendar(),chckbxRabies.isSelected(),
									rabiesVaccineDateChooser.getCalendar());
								if(!entry.equals(searchedCat)){
									int confirmSave=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Would you like to save your data and return to the main screen?");
									if(confirmSave==JOptionPane.YES_OPTION){
										try {
											Inventory.catInventory.put(entry.caseNumber, entry);
											Inventory.dogInventory.remove(entry.caseNumber);
											Inventory.otherInventory.remove(entry.caseNumber);
											Inventory.mapDataToCSV();
											JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Data was successfully saved");
											searchedCat=null;
											MainScreen.main(null);
											frmFurryFriendsAnimal.dispose();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
													+ "  Make sure that a file called CAT_INVENTORY.csv exists in the same directory as this"
													+ " application's main executable file.", "Error", JOptionPane.ERROR_MESSAGE);
											e1.printStackTrace();
										}
									}
								}
						}catch(NumberFormatException ex){
							JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
									+ "  Make sure the Age field, the Chip ID field, and the Cage"
									+ " Number field contain numbers only", "Error", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					}
				}
			}
		});
		panel_7.add(btnSave);
		chckbxRabies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxRabies.isSelected()){
					rabiesVaccineDateChooser.setEnabled(true);
					if(cat!=null){
						rabiesVaccineDateChooser.setMinSelectableDate(cat.getBirthdate().getTime());
						rabiesVaccineDateChooser.setMaxSelectableDate(cat.getArrivalDate().getTime());
					}
					else if(searchedCat!=null){
						rabiesVaccineDateChooser.setMinSelectableDate(searchedCat.getBirthdate().getTime());
						rabiesVaccineDateChooser.setMaxSelectableDate(searchedCat.getArrivalDate().getTime());
						rabiesVaccineDateChooser.setCalendar(searchedCat.getRabiesVaccineDate());
					}
				}
				else{
					rabiesVaccineDateChooser.setCalendar(null);
					rabiesVaccineDateChooser.setEnabled(false);
				}
			}
		});
		rdbtnNotDeclawed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNotDeclawed.isSelected()) declawProcedurePanel.setVisible(true);
				else declawProcedurePanel.setVisible(false);
			}
		});
		rdbtnDeclawed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNotDeclawed.isSelected()) declawProcedurePanel.setVisible(true);
				else declawProcedurePanel.setVisible(false);
			}
		});
	}

}
