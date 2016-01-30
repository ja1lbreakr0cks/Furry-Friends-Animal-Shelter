package self.AnujBhyravabhotla.FBLA2014.GraphicInterface;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import self.AnujBhyravabhotla.FBLA2014.Data.Animal;
import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;
import self.AnujBhyravabhotla.FBLA2014.Reports.ClientReport;
import self.AnujBhyravabhotla.FBLA2014.Reports.GenderReport;
import self.AnujBhyravabhotla.FBLA2014.Reports.IndividualAnimalCostReport;
import self.AnujBhyravabhotla.FBLA2014.Reports.InventoryReport;
import self.AnujBhyravabhotla.FBLA2014.Reports.InventoryWideCostReport;
/**
 * FBLA Desktop Application Programming Project 2014-2015.  
 * Allows the user to search for an animal in the record. 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Graphical Interface.
 * */
public class Search {

	private JFrame frmFurryFriendsAnimal;
	private JTextField caseNumTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search window = new Search();
					window.frmFurryFriendsAnimal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 *Populates the hashmap with the animal objects that contain the data for each animal in the shelter, then initializes
	 *the application.
	 *At this window, the user can search for an animal by case number or create a new entry.  If the user searches for a case 
	 *number that is not in the inventory, the screen will advance to the General Inventory Form where the user can then enter the 
	 *information for a new entry to be assigned to that case number.*/
	public Search() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFurryFriendsAnimal = new JFrame();
		frmFurryFriendsAnimal.setTitle("Furry Friends Animal Shelter: Records System");
		frmFurryFriendsAnimal.setBounds(100, 100, 450, 300);
		frmFurryFriendsAnimal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmFurryFriendsAnimal.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		frmFurryFriendsAnimal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to exit the program?");
				if(confirm==JOptionPane.YES_OPTION){
					try{
						Inventory.mapDataToCSV();
					}catch(IOException e){ 
						e.printStackTrace();
					}
					System.exit(0);
				}
			}
		});
		
		JPanel intropanel = new JPanel();
		frmFurryFriendsAnimal.getContentPane().add(intropanel);
		intropanel.setLayout(new GridLayout(3, 0, 0, 0));
		intropanel.setVisible(true);
		
		JLabel lblIntroMessage = new JLabel("Furry Friends Animal Shelter: Records");
		lblIntroMessage.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblIntroMessage.setHorizontalAlignment(SwingConstants.CENTER);
		intropanel.add(lblIntroMessage);
		
		JLabel lblCaseinstructionmessage = new JLabel("<html>Enter the case number for the animal you want to search in the text box below.  Then hit Search.</html>");
		lblCaseinstructionmessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCaseinstructionmessage.setHorizontalAlignment(SwingConstants.CENTER);
		intropanel.add(lblCaseinstructionmessage);
		
		JPanel panel_1 = new JPanel();
		intropanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCaseEntryLabel = new JLabel("Case Number:");
		lblCaseEntryLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_1.add(lblCaseEntryLabel);
		
		caseNumTextField = new JTextField();
		caseNumTextField.setToolTipText("Enter a number");
		panel_1.add(caseNumTextField);
		caseNumTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setToolTipText("Search the record for an animal with this case number");
		panel_1.add(btnSearch);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainScreen.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		btnGoBack.setToolTipText("Return to the previous screen");
		panel_1.add(btnGoBack);
		
		/*JButton btnNewEntry = new JButton("New Entry");
		btnNewEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=1;i<=Inventory.generalInventory.size()+1;i++){
					if(Inventory.generalInventory.get(i)==null) GeneralInventoryForm.caseNumberGlobal=i;
				}
				System.out.println(GeneralInventoryForm.caseNumberGlobal);
				if(GeneralInventoryForm.caseNumberGlobal==0) GeneralInventoryForm.caseNumberGlobal=Inventory.generalInventory.size();
				GeneralInventoryForm.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		panel_1.add(btnNewEntry);*/
		
		/*JMenuBar menuBar = new JMenuBar();
		frmFurryFriendsAnimal.setJMenuBar(menuBar);
		
		JMenuItem mntmGenerateInventoryReport = new JMenuItem("<html>Inventory<br>Report</html>");
		mntmGenerateInventoryReport.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmGenerateInventoryReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InventoryReport.main(null);
			}
		});
		menuBar.add(mntmGenerateInventoryReport);
		
		JMenuItem mntmGenerateTotalExpenseReport = new JMenuItem("<html>Expenses<br>Report</html>");
		mntmGenerateTotalExpenseReport.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmGenerateTotalExpenseReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryWideCostReport.main(null);
			}
		});
		menuBar.add(mntmGenerateTotalExpenseReport);
		
		JMenuItem mntmGenerateIndividualCost = new JMenuItem("<html>Cost<br>Reports</html>");
		mntmGenerateIndividualCost.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmGenerateIndividualCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					IndividualAnimalCostReport.main(null);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "The Inventory is Empty");
					return;
				}
			}
		});
		menuBar.add(mntmGenerateIndividualCost);
		
		JMenuItem mntmClientReports = new JMenuItem("<html>Client<br>Reports</html>");
		mntmClientReports.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmClientReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ClientReport.main(null);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "The Inventory is Empty");
					return;
				}
			}
		});
		menuBar.add(mntmClientReports);
		
		JMenuItem mntmGenderReport = new JMenuItem("<html>Gender<br>Report</html>");
		mntmGenderReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GenderReport.main(null);
			}
		});
		mntmGenderReport.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		menuBar.add(mntmGenderReport);*/
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(caseNumTextField.getText()==null||caseNumTextField.getText().equals(""))
						JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Please enter a case number");
					else{
						int caseNum=Integer.parseInt(caseNumTextField.getText());
						Animal data = Inventory.generalInventory.get(new Integer(caseNum));
						System.out.println(data);
						if(data==null){
							GeneralInventoryForm.caseNumberGlobal=caseNum;
							GeneralInventoryForm.animal=null;
							DogInventoryForm.searchedDog=null;
							CatInventoryForm.searchedCat=null;
							OtherInventoryForm.searchedOther=null;
							JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "No matching animal was found.");
						}
						else{
							int confirm = JOptionPane.showConfirmDialog(frmFurryFriendsAnimal,"A matching animal was found."
									+ "  Would you like to review the data for this animal?");
							if(confirm==JOptionPane.YES_OPTION){
								DogInventoryForm.dog=null;
								CatInventoryForm.cat=null;
								OtherInventoryForm.other=null;
								GeneralInventoryForm.animal=data;
								if(data.getType().equalsIgnoreCase("Dog")) DogInventoryForm.searchedDog=Inventory.dogInventory.get(new Integer(data.caseNumber));
								else if(data.getType().equalsIgnoreCase("Cat")) CatInventoryForm.searchedCat=Inventory.catInventory.get(new Integer(data.caseNumber));
								else OtherInventoryForm.searchedOther=Inventory.otherInventory.get(data.caseNumber);
								GeneralInventoryForm.main(null);
								frmFurryFriendsAnimal.dispose();
							}
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Input string contains invalid characters");
				}
			}
		});
	}

}
