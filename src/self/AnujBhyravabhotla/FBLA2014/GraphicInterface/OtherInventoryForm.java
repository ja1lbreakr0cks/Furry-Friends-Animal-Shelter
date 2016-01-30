package self.AnujBhyravabhotla.FBLA2014.GraphicInterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import self.AnujBhyravabhotla.FBLA2014.Data.Animal;
import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;
import self.AnujBhyravabhotla.FBLA2014.Data.Other;
/**
 * FBLA Desktop Application Programming Project 2014-2015.  
 * Allows the user to enter specific information for an animal if 
 * the animal is neither a dog nor a cat. 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Graphical Interface.
 * */
public class OtherInventoryForm {
	/**The main window frame*/
	private JFrame frmFurryFriendsAnimal;
	/**The animal object created from the data in the general inventory form*/
	public static Animal other;
	/**If the user entered a case number in the first screen and the searched returned an animal object*/
	public static Other searchedOther;
	/*General description fields.  The first listed is the most specific.  The second is a numerical field.  The description and 
	 * vaccines pane are the true general description panes where the user can enter any relevant data*/
	private JTextField txtTypeField;
	private JTextField txtWeightField;
	/**Returns to the general inventory form.  The data in that form is immutable and any data entered in this form is purged.*/
	private JButton btnGoBack;
	/**Saves the data in this form as an object to the hashmap, then returns to the first
	 * screen.*/
	private JButton btnSave;
	private TextArea textAreaVaccines;
	private TextArea textAreaGeneralDescription;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtherInventoryForm window = new OtherInventoryForm();
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
	public OtherInventoryForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFurryFriendsAnimal = new JFrame();
		frmFurryFriendsAnimal.setTitle("Furry Friends Animal Shelter: Records System");
		frmFurryFriendsAnimal.setBounds(100, 100, 734, 547);
		frmFurryFriendsAnimal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmFurryFriendsAnimal.getContentPane().setLayout(gridBagLayout);
		frmFurryFriendsAnimal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to exit the program?");
				int caseNum=0;
				if(other!=null) caseNum=other.caseNumber;
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
		
		JLabel lblEnterTheInformation = new JLabel("Enter the information for this animal in the form below");
		lblEnterTheInformation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEnterTheInformation = new GridBagConstraints();
		gbc_lblEnterTheInformation.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterTheInformation.gridwidth = 19;
		gbc_lblEnterTheInformation.gridx = 0;
		gbc_lblEnterTheInformation.gridy = 0;
		frmFurryFriendsAnimal.getContentPane().add(lblEnterTheInformation, gbc_lblEnterTheInformation);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 19;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frmFurryFriendsAnimal.getContentPane().add(panel, gbc_panel);
		
		JLabel lblType = new JLabel("Type: ");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblType);
		
		txtTypeField = new JTextField();
		panel.add(txtTypeField);
		txtTypeField.setColumns(10);
		if(searchedOther!=null){
			txtTypeField.setText(searchedOther.getOtherType());
//			txtTypeField.setEditable(false);
		}
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 19;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		frmFurryFriendsAnimal.getContentPane().add(panel_1, gbc_panel_1);
		
		JLabel lblWeight = new JLabel("Weight: ");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblWeight);
		
		txtWeightField = new JTextField();
		panel_1.add(txtWeightField);
		txtWeightField.setColumns(10);
		if(searchedOther!=null){
			txtWeightField.setText(searchedOther.getWeight()+"");
//			txtWeightField.setEditable(false);
		}
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridwidth = 19;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		frmFurryFriendsAnimal.getContentPane().add(panel_2, gbc_panel_2);
		
		JLabel lblDescribeThisAnimal = new JLabel("Describe this animal: ");
		lblDescribeThisAnimal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblDescribeThisAnimal);
		
		textAreaGeneralDescription = new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		panel_2.add(textAreaGeneralDescription);
		if(searchedOther!=null){
			textAreaGeneralDescription.setText(searchedOther.getDescription().replace("/", ","));
			//textArea.setEditable(false);
		}
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridwidth = 19;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		frmFurryFriendsAnimal.getContentPane().add(panel_3, gbc_panel_3);
		
		JLabel lblListAllVaccines = new JLabel("List all vaccines this animal has received: ");
		lblListAllVaccines.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblListAllVaccines);
		
		textAreaVaccines = new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		panel_3.add(textAreaVaccines);
		if(searchedOther!=null){
			textAreaVaccines.setText(searchedOther.getVaccines().replace("/", ","));
			//txtpnVaccinesPane.setEditable(false);
		}
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 19;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 5;
		frmFurryFriendsAnimal.getContentPane().add(panel_4, gbc_panel_4);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setToolTipText("Go back to the General Inventory form");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to return to the previous"
						+ "screen?  You will lose all information you have entered in this form.");
				if(confirm==JOptionPane.YES_OPTION){
					GeneralInventoryForm.animal=other;
					GeneralInventoryForm.main(null);
					frmFurryFriendsAnimal.dispose();
				}
			}
		});
		panel_4.add(btnGoBack);
		
		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save this animal to the records");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(searchedOther==null){
					int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to save?");
					if(confirm==JOptionPane.YES_OPTION){
						Other data = new Other(other,txtTypeField.getText(),Integer.parseInt(txtWeightField.getText()),
								textAreaGeneralDescription.getText().replace(',', '/'),textAreaVaccines.getText().replace(',', '/'));						
						try {
							Inventory.writeToCSV(data.toStringArray(), "DOG_INVENTORY.csv");
							Inventory.writeToCSV(other.toStringArray(),"GENERAL_INVENTORY.csv");
							Inventory.otherInventory.put(data.caseNumber, data);
							Inventory.generalInventory.put(other.caseNumber, other);
							JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Data was successfully saved");
							MainScreen.main(null);
							frmFurryFriendsAnimal.dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
									+ "  Make sure that a file called OTHER_INVENTORY.csv exists in the same directory as this"
									+ " application's main executable file.", "Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					}
				}else{
					int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to save?");
					if(confirm==JOptionPane.YES_OPTION){
						try{
							Other data = new Other(other,txtTypeField.getText(),Integer.parseInt(txtWeightField.getText()),
									textAreaGeneralDescription.getText().replace(',', '/'),textAreaVaccines.getText().replace(',', '/'));
								if(!data.equals(searchedOther)){
									try {
										Inventory.otherInventory.put(data.caseNumber, data);
										Inventory.catInventory.remove(data.caseNumber);
										Inventory.dogInventory.remove(data.caseNumber);
										JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Data was successfully saved");
										searchedOther=null;
										MainScreen.main(null);
										frmFurryFriendsAnimal.dispose();
										Inventory.mapDataToCSV();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
												+ "  Make sure that a file called OTHER_INVENTORY.csv exists in the same directory as this"
												+ " application's main executable file.", "Error", JOptionPane.ERROR_MESSAGE);
										e1.printStackTrace();
									}
								}
						}catch(NumberFormatException ex){
							JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldn't save the animal."
									+ "  Make sure the Weight field contains numbers only", "Error", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					}
				}
			}
		});
		panel_4.add(btnSave);
	}

}
