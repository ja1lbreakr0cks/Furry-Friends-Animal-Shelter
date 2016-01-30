package self.AnujBhyravabhotla.FBLA2014.GraphicInterface;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import self.AnujBhyravabhotla.FBLA2014.Data.Animal;
import self.AnujBhyravabhotla.FBLA2014.Data.Dog;
import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;

import com.toedter.calendar.JDateChooser;
/**
 * FBLA Desktop Application Programming Project 2014-2015.  
 * Allows the user to enter specific information for an animal if that animal is a dog. 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Graphical Interface.
 * */
public class DogInventoryForm {
	/**The window frame*/
	private JFrame frmFurryFriendsAnimal;
	/**The case number for the animal who's data was entered in the general inventory form.
	 * This was a redundancy measure to make sure all the data was matched to the right animal.*/
	public static int caseNumber;
	/**The animal who's data was entered in the general inventory form and saved over.*/
	public static Animal dog;
	/**If the user entered a case number in the first screen and it returned an animal that was a dog, that data is represented
	 * by this variable*/
	public static Dog searchedDog;
	/**A group of radio buttons that allow the user to select the gender of this dog.*/
	private final ButtonGroup genderSelector = new ButtonGroup();
	/*Dropdown boxes for selecting values.  The first one draws values from the file DOGBREEDS.csv.  The second one can either select 
	 * "Positive" or "Negative"*/
	private JComboBox breedcomboBox;
	private JComboBox resultComboBox;
	/*Checkboxes for selecting values.  If the first two are selected, their corresponding date fields become locked.  If the last
	 * three are selected, their corresponding date fields become unlocked*/
	private JCheckBox chckbxSpayedNeutered;
	private JCheckBox chckbxNotFleaTested;
	private JCheckBox chckbxRabiesVaccinated;
	private JCheckBox chckbxDistemperVaccinated;
	private JCheckBox chckbxBordetellaVaccinated;
	/**Returns to the general inventory screen.  Any data entered in this form
	 * is purged.*/
	private JButton btnGoBack;
	/**Saves this entry into the hashmap, then returns to the first screen.*/
	private JButton btnSave;
	private JDateChooser spayNeuterDateChooser;
	private JDateChooser fleaTestDateChooser;
	private JDateChooser rabiesVaccineDateChooser;
	private JDateChooser heartwormTestDateChooser;
	private JDateChooser heartwormTreatmentDateChooser;
	private JDateChooser heartwormRetestDateChooser;
	private JDateChooser bordetelaVaccineDateChooser;
	private JDateChooser distemperVaccineDateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DogInventoryForm window = new DogInventoryForm();
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
	public DogInventoryForm() throws IOException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException{
		frmFurryFriendsAnimal = new JFrame();
		frmFurryFriendsAnimal.setTitle("Furry Friends Animal Shelter: Records System");
		frmFurryFriendsAnimal.setBounds(100, 100, 480, 706);
		frmFurryFriendsAnimal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmFurryFriendsAnimal.getContentPane().setLayout(gridBagLayout);
		frmFurryFriendsAnimal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to exit the program?");
				int caseNum=0;
				if(dog!=null) caseNum=dog.caseNumber;
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
		
		JLabel lblEnterTheInformation = new JLabel("Enter the information for this dog in the form below");
		lblEnterTheInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheInformation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEnterTheInformation = new GridBagConstraints();
		gbc_lblEnterTheInformation.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterTheInformation.gridwidth = 2;
		gbc_lblEnterTheInformation.gridx = 0;
		gbc_lblEnterTheInformation.gridy = 0;
		frmFurryFriendsAnimal.getContentPane().add(lblEnterTheInformation, gbc_lblEnterTheInformation);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Breed", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frmFurryFriendsAnimal.getContentPane().add(panel, gbc_panel);
		
		JLabel lblSelectFromThe = new JLabel("Select from the dropdown box: ");
		lblSelectFromThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSelectFromThe);
		
		breedcomboBox = new JComboBox(Inventory.getDogBreeds());
		breedcomboBox.setToolTipText("Choose a breed from the options");
		panel.add(breedcomboBox);
		if(searchedDog!=null){
			for(String s:Inventory.getDogBreeds()){
				if(s.contains(searchedDog.getBreed()+"-")){
					breedcomboBox.setSelectedItem(s);
					//breedcomboBox.setEditable(false);
				}
			}
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gender information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 2;
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
		
		final JRadioButton rdbtnMale = new JRadioButton("Male");
		genderSelector.add(rdbtnMale);
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_rdbtnMale = new GridBagConstraints();
		gbc_rdbtnMale.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMale.gridx = 0;
		gbc_rdbtnMale.gridy = 0;
		panel_1.add(rdbtnMale, gbc_rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		genderSelector.add(rdbtnFemale);
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_rdbtnFemale = new GridBagConstraints();
		gbc_rdbtnFemale.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFemale.gridwidth = 2;
		gbc_rdbtnFemale.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFemale.gridx = 1;
		gbc_rdbtnFemale.gridy = 0;
		panel_1.add(rdbtnFemale, gbc_rdbtnFemale);
		if(searchedDog!=null&&searchedDog.isMale()) rdbtnMale.setSelected(true);
		else rdbtnFemale.setSelected(true);
		
		JLabel lblProcedureDate = new JLabel("Date for Spay/Neuter procedure : ");
		lblProcedureDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblProcedureDate = new GridBagConstraints();
		gbc_lblProcedureDate.anchor = GridBagConstraints.EAST;
		gbc_lblProcedureDate.gridwidth = 7;
		gbc_lblProcedureDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblProcedureDate.gridx = 0;
		gbc_lblProcedureDate.gridy = 1;
		panel_1.add(lblProcedureDate, gbc_lblProcedureDate);
		
		
		spayNeuterDateChooser = new JDateChooser();
		GridBagConstraints gbc_spayNeuterDateChooser = new GridBagConstraints();
		gbc_spayNeuterDateChooser.gridwidth = 5;
		gbc_spayNeuterDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_spayNeuterDateChooser.fill = GridBagConstraints.BOTH;
		gbc_spayNeuterDateChooser.gridx = 7;
		gbc_spayNeuterDateChooser.gridy = 1;
		spayNeuterDateChooser.setMinSelectableDate(new Date());
		panel_1.add(spayNeuterDateChooser, gbc_spayNeuterDateChooser);
		if(searchedDog!=null&&!searchedDog.isSpayNeutered()){
			spayNeuterDateChooser.setCalendar(searchedDog.getSpayNeuterDate());
			spayNeuterDateChooser.setMinSelectableDate(searchedDog.getArrivalDate().getTime());
			//spayNeuterDateChooser.setEditable(false);
		}
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.insets = new Insets(0, 0, 5, 0);
		gbc_lblOr.gridwidth = 12;
		gbc_lblOr.gridx = 0;
		gbc_lblOr.gridy = 2;
		panel_1.add(lblOr, gbc_lblOr);
		
		chckbxSpayedNeutered = new JCheckBox("Check here if the dog has already been spayed/neutered");
		chckbxSpayedNeutered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxSpayedNeutered.isSelected()) {
					spayNeuterDateChooser.setCalendar(null);
					spayNeuterDateChooser.setEnabled(false);
				}
				else{
					spayNeuterDateChooser.setEnabled(true);
					if(searchedDog!=null){
						spayNeuterDateChooser.setCalendar(searchedDog.getSpayNeuterDate());
						spayNeuterDateChooser.setMinSelectableDate(searchedDog.getArrivalDate().getTime());
					}
					else if(dog!=null){
						spayNeuterDateChooser.setCalendar(null);
						spayNeuterDateChooser.setMinSelectableDate(dog.getArrivalDate().getTime());	
					}
				}
			}
		});
		chckbxSpayedNeutered.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_chckbxSpayedNeutered = new GridBagConstraints();
		gbc_chckbxSpayedNeutered.gridwidth = 12;
		gbc_chckbxSpayedNeutered.gridx = 0;
		gbc_chckbxSpayedNeutered.gridy = 3;
		panel_1.add(chckbxSpayedNeutered, gbc_chckbxSpayedNeutered);
		if(searchedDog!=null&&searchedDog.isSpayNeutered()){
			chckbxSpayedNeutered.setSelected(true);
			spayNeuterDateChooser.setEnabled(false);
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Flea Testing information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridwidth = 2;
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
		
		JLabel lblDateOfFirst = new JLabel("Date of first treament for fleas : ");
		lblDateOfFirst.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDateOfFirst = new GridBagConstraints();
		gbc_lblDateOfFirst.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfFirst.anchor = GridBagConstraints.EAST;
		gbc_lblDateOfFirst.gridwidth = 9;
		gbc_lblDateOfFirst.gridx = 0;
		gbc_lblDateOfFirst.gridy = 0;
		panel_2.add(lblDateOfFirst, gbc_lblDateOfFirst);
		
		fleaTestDateChooser = new JDateChooser();
		GridBagConstraints gbc_fleaTestDateChooser = new GridBagConstraints();
		gbc_fleaTestDateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_fleaTestDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fleaTestDateChooser.gridx = 9;
		gbc_fleaTestDateChooser.gridy = 0;
		if(dog!=null){
			fleaTestDateChooser.setMinSelectableDate(dog.getBirthdate().getTime());
			fleaTestDateChooser.setMaxSelectableDate(dog.getArrivalDate().getTime());
		}
		else if(searchedDog!=null){
			fleaTestDateChooser.setMinSelectableDate(searchedDog.getBirthdate().getTime());
			fleaTestDateChooser.setMaxSelectableDate(searchedDog.getArrivalDate().getTime());
		}
		fleaTestDateChooser.setMaxSelectableDate(new Date());
		panel_2.add(fleaTestDateChooser, gbc_fleaTestDateChooser);
		if(searchedDog!=null&&searchedDog.isFleaTested()) fleaTestDateChooser.setCalendar(searchedDog.getDateFirstFleaTreatment());
		
		JLabel lblOr_1 = new JLabel("OR");
		lblOr_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblOr_1 = new GridBagConstraints();
		gbc_lblOr_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblOr_1.gridwidth = 10;
		gbc_lblOr_1.gridx = 0;
		gbc_lblOr_1.gridy = 1;
		panel_2.add(lblOr_1, gbc_lblOr_1);
		
		chckbxNotFleaTested = new JCheckBox("Check here if this dog hasn't been tested for fleas");
		chckbxNotFleaTested.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNotFleaTested.isSelected()){ 
					fleaTestDateChooser.setCalendar(null);
					fleaTestDateChooser.setEnabled(false);
				}
				else{
					fleaTestDateChooser.setEnabled(true);
					if(dog!=null){
						fleaTestDateChooser.setMinSelectableDate(dog.getBirthdate().getTime());
						fleaTestDateChooser.setMaxSelectableDate(dog.getArrivalDate().getTime());
					}
					else if(searchedDog!=null){
						fleaTestDateChooser.setMinSelectableDate(searchedDog.getBirthdate().getTime());
						fleaTestDateChooser.setMaxSelectableDate(searchedDog.getArrivalDate().getTime());
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
		if(searchedDog!=null&&!searchedDog.isFleaTested()){
			chckbxNotFleaTested.setSelected(true);
			fleaTestDateChooser.setCalendar(null);
			fleaTestDateChooser.setEnabled(false);
		}
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Heartworm Test Information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		frmFurryFriendsAnimal.getContentPane().add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblDateOfHeartworm = new JLabel("Date of Heartworm Test:");
		lblDateOfHeartworm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDateOfHeartworm = new GridBagConstraints();
		gbc_lblDateOfHeartworm.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfHeartworm.anchor = GridBagConstraints.EAST;
		gbc_lblDateOfHeartworm.gridwidth = 7;
		gbc_lblDateOfHeartworm.gridx = 0;
		gbc_lblDateOfHeartworm.gridy = 0;
		panel_3.add(lblDateOfHeartworm, gbc_lblDateOfHeartworm);
		
		heartwormTestDateChooser = new JDateChooser();
		heartwormTestDateChooser.setToolTipText("This field is required");
		if(dog!=null){
			heartwormTestDateChooser.setMaxSelectableDate(dog.getArrivalDate().getTime());
			heartwormTestDateChooser.setMinSelectableDate(dog.getBirthdate().getTime());
		}
		else if(searchedDog!=null){
			heartwormTestDateChooser.setCalendar(searchedDog.getHeartwormTestDate());
			heartwormTestDateChooser.setMaxSelectableDate(searchedDog.getArrivalDate().getTime());
			heartwormTestDateChooser.setMinSelectableDate(searchedDog.getBirthdate().getTime());
		}
		GridBagConstraints gbc_heartwormTestDateChooser = new GridBagConstraints();
		gbc_heartwormTestDateChooser.gridwidth = 5;
		gbc_heartwormTestDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_heartwormTestDateChooser.fill = GridBagConstraints.BOTH;
		gbc_heartwormTestDateChooser.gridx = 7;
		gbc_heartwormTestDateChooser.gridy = 0;
		panel_3.add(heartwormTestDateChooser, gbc_heartwormTestDateChooser);
		
		
		JLabel lblResult = new JLabel("Result: ");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblResult = new GridBagConstraints();
		gbc_lblResult.anchor = GridBagConstraints.EAST;
		gbc_lblResult.gridwidth = 7;
		gbc_lblResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult.gridx = 0;
		gbc_lblResult.gridy = 1;
		panel_3.add(lblResult, gbc_lblResult);
		
		resultComboBox = new JComboBox(new String[]{"","Positive","Negative"});
		GridBagConstraints gbc_resultComboBox = new GridBagConstraints();
		gbc_resultComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_resultComboBox.gridwidth = 5;
		gbc_resultComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_resultComboBox.gridx = 7;
		gbc_resultComboBox.gridy = 1;
		panel_3.add(resultComboBox, gbc_resultComboBox);
		if(searchedDog!=null&&searchedDog.hasHeartworm()){
			resultComboBox.setSelectedItem("Positive");
			//resultComboBox.setEditable(false);
		}
		if(searchedDog!=null&&!searchedDog.hasHeartworm()){
			resultComboBox.setSelectedItem("Negative");
			//resultComboBox.setEditable(false);
		}
		
		final JPanel heartwormTreatmentPanel = new JPanel();
		heartwormTreatmentPanel.setVisible(false);
		heartwormTreatmentPanel.setBorder(new TitledBorder(null, "Heartworm Treatment Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_heartwormTreatmentPanel = new GridBagConstraints();
		gbc_heartwormTreatmentPanel.gridwidth = 14;
		gbc_heartwormTreatmentPanel.fill = GridBagConstraints.BOTH;
		gbc_heartwormTreatmentPanel.gridx = 0;
		gbc_heartwormTreatmentPanel.gridy = 2;
		panel_3.add(heartwormTreatmentPanel, gbc_heartwormTreatmentPanel);
		GridBagLayout gbl_heartwormTreatmentPanel = new GridBagLayout();
		gbl_heartwormTreatmentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_heartwormTreatmentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_heartwormTreatmentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_heartwormTreatmentPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		heartwormTreatmentPanel.setLayout(gbl_heartwormTreatmentPanel);
		if(searchedDog!=null&&searchedDog.hasHeartworm()) heartwormTreatmentPanel.setVisible(true);
		if(searchedDog!=null&&!searchedDog.hasHeartworm()) heartwormTreatmentPanel.setVisible(false);
		
		JLabel lblDateOfFirst_1 = new JLabel("Date of first treatment: ");
		lblDateOfFirst_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDateOfFirst_1 = new GridBagConstraints();
		gbc_lblDateOfFirst_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfFirst_1.anchor = GridBagConstraints.EAST;
		gbc_lblDateOfFirst_1.gridwidth = 7;
		gbc_lblDateOfFirst_1.gridx = 0;
		gbc_lblDateOfFirst_1.gridy = 0;
		heartwormTreatmentPanel.add(lblDateOfFirst_1, gbc_lblDateOfFirst_1);
		if(searchedDog!=null&&searchedDog.hasHeartworm()){
			heartwormTreatmentDateChooser.setCalendar(searchedDog.getHeartwormMedicationDate());
			//heartwormTreatmentDateChooser.setEnabled(false);
		}
		
		heartwormTreatmentDateChooser = new JDateChooser();
		GridBagConstraints gbc_heartwormTreatmentDateChooser = new GridBagConstraints();
		gbc_heartwormTreatmentDateChooser.gridwidth = 3;
		gbc_heartwormTreatmentDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_heartwormTreatmentDateChooser.fill = GridBagConstraints.BOTH;
		gbc_heartwormTreatmentDateChooser.gridx = 7;
		gbc_heartwormTreatmentDateChooser.gridy = 0;
		heartwormTreatmentPanel.add(heartwormTreatmentDateChooser, gbc_heartwormTreatmentDateChooser);
		
		JLabel lblRetestDatemmddyyyy = new JLabel("Retest Date: ");
		lblRetestDatemmddyyyy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblRetestDatemmddyyyy = new GridBagConstraints();
		gbc_lblRetestDatemmddyyyy.anchor = GridBagConstraints.EAST;
		gbc_lblRetestDatemmddyyyy.gridwidth = 7;
		gbc_lblRetestDatemmddyyyy.insets = new Insets(0, 0, 0, 5);
		gbc_lblRetestDatemmddyyyy.gridx = 0;
		gbc_lblRetestDatemmddyyyy.gridy = 1;
		heartwormTreatmentPanel.add(lblRetestDatemmddyyyy, gbc_lblRetestDatemmddyyyy);
		
		heartwormRetestDateChooser = new JDateChooser();
		GridBagConstraints gbc_heartwormRetestDateChooser = new GridBagConstraints();
		gbc_heartwormRetestDateChooser.gridwidth = 3;
		gbc_heartwormRetestDateChooser.insets = new Insets(0, 0, 0, 5);
		gbc_heartwormRetestDateChooser.fill = GridBagConstraints.BOTH;
		gbc_heartwormRetestDateChooser.gridx = 7;
		gbc_heartwormRetestDateChooser.gridy = 1;
		heartwormTreatmentPanel.add(heartwormRetestDateChooser, gbc_heartwormRetestDateChooser);
		if(searchedDog!=null&&searchedDog.hasHeartworm()){
			heartwormRetestDateChooser.setCalendar(searchedDog.getHeartwormRetestDate());
			//heartwormRetestDateChooser.setEditable(false);
		}
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vaccination Information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.gridwidth = 2;
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 5;
		frmFurryFriendsAnimal.getContentPane().add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblForEachVaccine = new JLabel("<html>For each vaccine, check off the box if this dog received it and enter<br>the date in the textbox next to it.</html>");
		lblForEachVaccine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblForEachVaccine = new GridBagConstraints();
		gbc_lblForEachVaccine.insets = new Insets(0, 0, 5, 0);
		gbc_lblForEachVaccine.gridwidth = 14;
		gbc_lblForEachVaccine.gridx = 0;
		gbc_lblForEachVaccine.gridy = 0;
		panel_4.add(lblForEachVaccine, gbc_lblForEachVaccine);
		
		chckbxRabiesVaccinated = new JCheckBox("Rabies");
		GridBagConstraints gbc_chckbxRabiesVaccinated = new GridBagConstraints();
		gbc_chckbxRabiesVaccinated.anchor = GridBagConstraints.WEST;
		gbc_chckbxRabiesVaccinated.gridwidth = 5;
		gbc_chckbxRabiesVaccinated.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxRabiesVaccinated.gridx = 0;
		gbc_chckbxRabiesVaccinated.gridy = 1;
		panel_4.add(chckbxRabiesVaccinated, gbc_chckbxRabiesVaccinated);
		
		rabiesVaccineDateChooser = new JDateChooser();
		rabiesVaccineDateChooser.setEnabled(false);
		GridBagConstraints gbc_rabiesVaccineDateChooser = new GridBagConstraints();
		gbc_rabiesVaccineDateChooser.gridwidth = 5;
		gbc_rabiesVaccineDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_rabiesVaccineDateChooser.fill = GridBagConstraints.BOTH;
		gbc_rabiesVaccineDateChooser.gridx = 5;
		gbc_rabiesVaccineDateChooser.gridy = 1;
		panel_4.add(rabiesVaccineDateChooser, gbc_rabiesVaccineDateChooser);
		
		chckbxDistemperVaccinated = new JCheckBox("Distemper");
		GridBagConstraints gbc_chckbxDistemperVaccinated = new GridBagConstraints();
		gbc_chckbxDistemperVaccinated.anchor = GridBagConstraints.WEST;
		gbc_chckbxDistemperVaccinated.gridwidth = 5;
		gbc_chckbxDistemperVaccinated.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDistemperVaccinated.gridx = 0;
		gbc_chckbxDistemperVaccinated.gridy = 2;
		panel_4.add(chckbxDistemperVaccinated, gbc_chckbxDistemperVaccinated);
		
		distemperVaccineDateChooser = new JDateChooser();
		distemperVaccineDateChooser.setEnabled(false);
		GridBagConstraints gbc_distemperVaccineDateChooser = new GridBagConstraints();
		gbc_distemperVaccineDateChooser.gridwidth = 5;
		gbc_distemperVaccineDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_distemperVaccineDateChooser.fill = GridBagConstraints.BOTH;
		gbc_distemperVaccineDateChooser.gridx = 5;
		gbc_distemperVaccineDateChooser.gridy = 2;
		panel_4.add(distemperVaccineDateChooser, gbc_distemperVaccineDateChooser);
		
		chckbxBordetellaVaccinated = new JCheckBox("Bordetella");
		GridBagConstraints gbc_chckbxBordetellaVaccinated = new GridBagConstraints();
		gbc_chckbxBordetellaVaccinated.anchor = GridBagConstraints.WEST;
		gbc_chckbxBordetellaVaccinated.gridwidth = 5;
		gbc_chckbxBordetellaVaccinated.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxBordetellaVaccinated.gridx = 0;
		gbc_chckbxBordetellaVaccinated.gridy = 3;
		panel_4.add(chckbxBordetellaVaccinated, gbc_chckbxBordetellaVaccinated);
		
		bordetelaVaccineDateChooser = new JDateChooser();
		bordetelaVaccineDateChooser.setEnabled(false);
		GridBagConstraints gbc_bordetelaVaccineDateChooser = new GridBagConstraints();
		gbc_bordetelaVaccineDateChooser.gridwidth = 5;
		gbc_bordetelaVaccineDateChooser.insets = new Insets(0, 0, 0, 5);
		gbc_bordetelaVaccineDateChooser.fill = GridBagConstraints.BOTH;
		gbc_bordetelaVaccineDateChooser.gridx = 5;
		gbc_bordetelaVaccineDateChooser.gridy = 3;
		panel_4.add(bordetelaVaccineDateChooser, gbc_bordetelaVaccineDateChooser);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridwidth = 2;
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 6;
		frmFurryFriendsAnimal.getContentPane().add(panel_5, gbc_panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setToolTipText("Click to return to the General Inventory Form.");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to return to the previous"
						+ " screen?  All data you have entered in this form will be lost.");
				if(confirm==JOptionPane.YES_OPTION){
					GeneralInventoryForm.animal=dog;
					System.out.println(GeneralInventoryForm.animal);
					GeneralInventoryForm.main(null);
					frmFurryFriendsAnimal.dispose();
				}
			}
		});
		panel_5.add(btnGoBack);
		
		btnSave = new JButton("Save");
		btnSave.setToolTipText("Click to save this entry and return to the main screen");
		panel_5.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(searchedDog==null){
					int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to save?");
					if(confirm==JOptionPane.YES_OPTION){
						boolean heartWormPositive = resultComboBox.getSelectedItem().equals("Positive");
						try {
							Dog entry=new Dog(dog,Integer.parseInt(((String) breedcomboBox.getSelectedItem()).substring(0,((String) breedcomboBox.getSelectedItem()).indexOf("-"))),
									rdbtnMale.isSelected(),chckbxSpayedNeutered.isSelected(),spayNeuterDateChooser.getCalendar(),!chckbxNotFleaTested.isSelected(),
									fleaTestDateChooser.getCalendar(),heartWormPositive,heartwormTestDateChooser.getCalendar(),
									heartwormTreatmentDateChooser.getCalendar(),heartwormRetestDateChooser.getCalendar(),chckbxRabiesVaccinated.isSelected(),
									rabiesVaccineDateChooser.getCalendar(),chckbxDistemperVaccinated.isSelected(),distemperVaccineDateChooser.getCalendar(),
									chckbxBordetellaVaccinated.isSelected(),bordetelaVaccineDateChooser.getCalendar());
							Inventory.writeToCSV(entry.toStringArray(), "DOG_INVENTORY.csv");
							Inventory.writeToCSV(dog.toStringArray(),"GENERAL_INVENTORY.csv");
							Inventory.dogInventory.put(entry.caseNumber, entry);
							Inventory.generalInventory.put(dog.caseNumber, dog);
							dog=null;
							JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Data was successfully saved");
							MainScreen.main(null);
							frmFurryFriendsAnimal.dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
									+ "  Make sure that a file called DOG_INVENTORY.csv exists in the same directory as this"
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
					int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to continue?");
					if(confirm==JOptionPane.YES_OPTION){
						boolean heartWormPositive = resultComboBox.getSelectedItem().equals("Positive");
						try{
							Dog entry=new Dog(dog,Integer.parseInt(((String) breedcomboBox.getSelectedItem()).substring(0,((String) breedcomboBox.getSelectedItem()).indexOf("-"))),
									rdbtnMale.isSelected(),chckbxSpayedNeutered.isSelected(),spayNeuterDateChooser.getCalendar(),!chckbxNotFleaTested.isSelected(),
									fleaTestDateChooser.getCalendar(),heartWormPositive,heartwormTestDateChooser.getCalendar(),
									heartwormTreatmentDateChooser.getCalendar(),heartwormRetestDateChooser.getCalendar(),chckbxRabiesVaccinated.isSelected(),
									rabiesVaccineDateChooser.getCalendar(),chckbxDistemperVaccinated.isSelected(),distemperVaccineDateChooser.getCalendar(),
									chckbxBordetellaVaccinated.isSelected(),bordetelaVaccineDateChooser.getCalendar());
								if(!entry.equals(searchedDog)){
									int confirmSave=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Would you like to save your data and return to the main screen?");
									if(confirmSave==JOptionPane.YES_OPTION){
										try {
											Inventory.generalInventory.put(dog.caseNumber,dog);
											Inventory.dogInventory.put(entry.caseNumber, entry);
											Inventory.catInventory.remove(entry.caseNumber);
											Inventory.otherInventory.remove(entry.caseNumber);
											Inventory.mapDataToCSV();
											JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Data was successfully saved");
											searchedDog=null;
											MainScreen.main(null);
											frmFurryFriendsAnimal.dispose();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
													+ "  Make sure that a file called DOG_INVENTORY.csv exists in the same directory as this"
													+ " application's main executable file.", "Error", JOptionPane.ERROR_MESSAGE);
											e1.printStackTrace();
										}
									}
								}else{
									searchedDog=null;
									MainScreen.main(null);
									frmFurryFriendsAnimal.dispose();
								}
						} catch(NumberFormatException ex){
							JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
									+ "  Make sure the Age field, the Chip ID field, and the Cage"
									+ " Number field contain numbers only", "Error", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					}
				}
			}
		});
		
		if(searchedDog!=null&&searchedDog.gotRabiesVaccine()){
			chckbxRabiesVaccinated.setSelected(true);
			rabiesVaccineDateChooser.setEnabled(true);
			rabiesVaccineDateChooser.setCalendar(searchedDog.getRabiesVaccineDate());
			rabiesVaccineDateChooser.setMinSelectableDate(searchedDog.getBirthdate().getTime());
			rabiesVaccineDateChooser.setMaxSelectableDate(searchedDog.getArrivalDate().getTime());
		}
		if(searchedDog!=null&&searchedDog.gotDistemperVaccine()){
			chckbxDistemperVaccinated.setSelected(true);
			distemperVaccineDateChooser.setEnabled(true);
			distemperVaccineDateChooser.setCalendar(searchedDog.getDistemperVaccineDate());
			distemperVaccineDateChooser.setMinSelectableDate(searchedDog.getBirthdate().getTime());
			distemperVaccineDateChooser.setMaxSelectableDate(searchedDog.getArrivalDate().getTime());
		} 
		if(searchedDog!=null&&searchedDog.gotBordetellaVaccine()){
			chckbxBordetellaVaccinated.setSelected(true);
			bordetelaVaccineDateChooser.setEnabled(true);
			bordetelaVaccineDateChooser.setCalendar(searchedDog.getBordetelaVaccineDate());
			bordetelaVaccineDateChooser.setMinSelectableDate(searchedDog.getBirthdate().getTime());
			bordetelaVaccineDateChooser.setMaxSelectableDate(searchedDog.getArrivalDate().getTime());
		} 
		
		resultComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(resultComboBox.getSelectedItem().equals("Positive")){
					heartwormTreatmentPanel.setVisible(true);
					if(searchedDog!=null){
						heartwormTreatmentDateChooser.setMinSelectableDate(searchedDog.getHeartwormTestDate().getTime());
						heartwormRetestDateChooser.setMinSelectableDate(searchedDog.getHeartwormMedicationDate().getTime());
					}else{
						heartwormTreatmentDateChooser.setMinSelectableDate(heartwormTestDateChooser.getDate());
						heartwormRetestDateChooser.setMinSelectableDate(heartwormTreatmentDateChooser.getDate());
					}
				}
				else heartwormTreatmentPanel.setVisible(false);
			}
		});
		chckbxRabiesVaccinated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(chckbxRabiesVaccinated.isSelected()){
					rabiesVaccineDateChooser.setEnabled(true);
					if(dog!=null){
						rabiesVaccineDateChooser.setMinSelectableDate(dog.getBirthdate().getTime());
						rabiesVaccineDateChooser.setMaxSelectableDate(dog.getArrivalDate().getTime());
					}
					else if(searchedDog!=null){
						rabiesVaccineDateChooser.setMinSelectableDate(searchedDog.getBirthdate().getTime());
						rabiesVaccineDateChooser.setMaxSelectableDate(searchedDog.getArrivalDate().getTime());
						rabiesVaccineDateChooser.setCalendar(searchedDog.getRabiesVaccineDate());
					}
				}
				else{
					rabiesVaccineDateChooser.setCalendar(null);
					rabiesVaccineDateChooser.setEnabled(false);
				}
			}
		});
		chckbxDistemperVaccinated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(chckbxDistemperVaccinated.isSelected()){
					distemperVaccineDateChooser.setEnabled(true);
					if(dog!=null){
						distemperVaccineDateChooser.setMinSelectableDate(dog.getBirthdate().getTime());
						distemperVaccineDateChooser.setMaxSelectableDate(dog.getArrivalDate().getTime());
					}
					else if(searchedDog!=null){
						distemperVaccineDateChooser.setMinSelectableDate(searchedDog.getBirthdate().getTime());
						distemperVaccineDateChooser.setMaxSelectableDate(searchedDog.getArrivalDate().getTime());
						distemperVaccineDateChooser.setCalendar(searchedDog.getDistemperVaccineDate());
					}
				}
				else{
					distemperVaccineDateChooser.setEnabled(false);	
					distemperVaccineDateChooser.setCalendar(null);
				}
			}
		});
		chckbxBordetellaVaccinated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(chckbxBordetellaVaccinated.isSelected()){
					bordetelaVaccineDateChooser.setEnabled(true);
					if(dog!=null){
						bordetelaVaccineDateChooser.setMinSelectableDate(dog.getBirthdate().getTime());
						bordetelaVaccineDateChooser.setMaxSelectableDate(dog.getArrivalDate().getTime());
					}
					else if(searchedDog!=null){
						bordetelaVaccineDateChooser.setMinSelectableDate(searchedDog.getBirthdate().getTime());
						bordetelaVaccineDateChooser.setMaxSelectableDate(searchedDog.getArrivalDate().getTime());
						bordetelaVaccineDateChooser.setCalendar(searchedDog.getBordetelaVaccineDate());
					}
				}
				else{
					bordetelaVaccineDateChooser.setEnabled(false);
					bordetelaVaccineDateChooser.setCalendar(null);
				}
			}
		});
	}

}
