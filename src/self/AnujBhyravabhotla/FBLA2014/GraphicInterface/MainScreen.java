package self.AnujBhyravabhotla.FBLA2014.GraphicInterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * FBLA Desktop Application Programming Project 2014-2015.  
 * This is the first screen for the program.  From here, the 
 * user can choose to create an entry, search for an entry, or view reports. 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Graphical Interface.
 * */
public class MainScreen {

	private JFrame frmFurryFriendsAnimal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
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
	public MainScreen() {
		try {
			GeneralInventoryForm.animal=null;
			if(Inventory.generalInventory.isEmpty()) Inventory.populateInventoryHashMaps();
			System.out.println(Inventory.generalInventory);
			System.out.println(Inventory.dogInventory);
			System.out.println(Inventory.catInventory);
			System.out.println(Inventory.otherInventory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "Couldnt' save the animal."
					+ "  Make sure that files called GENERAL_INVENTORY.csv, DOG_INVENTORY.csv, CAT_INVENTORY.csv, and "
					+ "OTHER_INVENTORY.csv exist in the same directory as this"
					+ " application's main executable file.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
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
		frmFurryFriendsAnimal.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label = new JLabel("Furry Friends Animal Shelter: Records");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmFurryFriendsAnimal.getContentPane().add(label);
		
		JLabel lblWelcomeToThe = new JLabel("<html>Welcome to the program!  Choose an option using the buttons below</html>");
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmFurryFriendsAnimal.getContentPane().add(lblWelcomeToThe);
		
		JPanel panel = new JPanel();
		frmFurryFriendsAnimal.getContentPane().add(panel);
		
		JButton btnNewEntry = new JButton("Create New Entry");
		btnNewEntry.setToolTipText("Click here to start entering information for a new animal admitted to the shelter.");
		btnNewEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=1;i<=Inventory.generalInventory.size()+1;i++){
					if(Inventory.generalInventory.get(i)==null) GeneralInventoryForm.caseNumberGlobal=i;
				}
				System.out.println(GeneralInventoryForm.caseNumberGlobal);
				if(GeneralInventoryForm.caseNumberGlobal==0) GeneralInventoryForm.caseNumberGlobal=Inventory.generalInventory.size();
				GeneralInventoryForm.animal=null;
				DogInventoryForm.dog=null;
				DogInventoryForm.searchedDog=null;
				CatInventoryForm.cat=null;
				CatInventoryForm.searchedCat=null;
				OtherInventoryForm.other=null;
				OtherInventoryForm.searchedOther=null;
				GeneralInventoryForm.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		panel.add(btnNewEntry);
		
		JButton btnSearch = new JButton("Search for an existing entry");
		btnSearch.setToolTipText("Click here to search for an animal in the shelter.");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralInventoryForm.animal=null;
				DogInventoryForm.dog=null;
				DogInventoryForm.searchedDog=null;
				CatInventoryForm.cat=null;
				CatInventoryForm.searchedCat=null;
				OtherInventoryForm.other=null;
				OtherInventoryForm.searchedOther=null;
				Search.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		panel.add(btnSearch);
		
		JButton btnViewReports = new JButton("View Reports");
		btnViewReports.setToolTipText("Click here to view the reports for this shelter.");
		btnViewReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportScreen.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		if(Inventory.generalInventory.isEmpty()) btnViewReports.setVisible(false);
		panel.add(btnViewReports);
		frmFurryFriendsAnimal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirm=JOptionPane.showConfirmDialog(frmFurryFriendsAnimal, "Are you sure you want to exit the program?");
				if(confirm==JOptionPane.YES_OPTION){
					try{
						Inventory.mapDataToCSV();
						System.exit(0);
					}catch(IOException e){ 
						e.printStackTrace();
					}
				}
			}
		});
	}

}
