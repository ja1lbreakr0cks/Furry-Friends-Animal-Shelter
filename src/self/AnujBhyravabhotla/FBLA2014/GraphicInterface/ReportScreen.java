package self.AnujBhyravabhotla.FBLA2014.GraphicInterface;

import java.awt.EventQueue;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;
import self.AnujBhyravabhotla.FBLA2014.Reports.ClientReport;
import self.AnujBhyravabhotla.FBLA2014.Reports.GenderReport;
import self.AnujBhyravabhotla.FBLA2014.Reports.IndividualAnimalCostReport;
import self.AnujBhyravabhotla.FBLA2014.Reports.InventoryReport;
import self.AnujBhyravabhotla.FBLA2014.Reports.InventoryWideCostReport;
/**
 * FBLA Desktop Application Programming Project 2014-2015.  
 * Allows the user to view reports for the shelter. 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Graphical Interface.
 * */
public class ReportScreen {

	private JFrame frmFurryFriendsAnimal;
	private final JLabel label = new JLabel("Furry Friends Animal Shelter: Records");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportScreen window = new ReportScreen();
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
	public ReportScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFurryFriendsAnimal = new JFrame();
		frmFurryFriendsAnimal.setTitle("Furry Friends Animal Shelter: Records System");
		frmFurryFriendsAnimal.setBounds(100, 100, 501, 338);
		frmFurryFriendsAnimal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmFurryFriendsAnimal.getContentPane().setLayout(new GridLayout(3, 0, 0, 0));
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
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		frmFurryFriendsAnimal.getContentPane().add(label);
		
		JLabel lblChooseAReport = new JLabel("Choose an option below");
		lblChooseAReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAReport.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmFurryFriendsAnimal.getContentPane().add(lblChooseAReport);
		
		JPanel panel = new JPanel();
		frmFurryFriendsAnimal.getContentPane().add(panel);
		
		JButton btnClientReport = new JButton("View Client Report");
		btnClientReport.setToolTipText("Given to a client upon adoption. The report contains the following information:\r\n\u2022 Animal name\r\n\u2022 Animal age (numeric field only)\r\n\u2022 Date of birth (if known)\r\n\u2022 Micro-chipped information\r\n\u2022 Breed\r\n\u2022 Weight (numeric field)\r\n\u2022 Hair color\r\n\u2022 Type of hair (long/short)");
		btnClientReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientReport.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		panel.add(btnClientReport);
		
		JButton btnGenderReport = new JButton("View Gender Report");
		btnGenderReport.setToolTipText("This report includes the following information:\r\n\u2022 Spayed/Neutered information\r\n\u2022 Vaccination information");
		btnGenderReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenderReport.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		panel.add(btnGenderReport);
		
		JButton btnIndividualCostReports = new JButton("View Individual Cost Reports");
		btnIndividualCostReports.setToolTipText("This report allows the owner/manager to pull up any animal in the shelter and find a detailed list of all the expenses for the animal.");
		btnIndividualCostReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IndividualAnimalCostReport.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		panel.add(btnIndividualCostReports);
		
		JButton btnInventoryReport = new JButton("View Inventory Report");
		btnInventoryReport.setToolTipText("This report lists each animal in the shelter and its location.");
		btnInventoryReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryReport.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		panel.add(btnInventoryReport);
		
		JButton btnTotalExpenseReport = new JButton("View Total Expense Report");
		btnTotalExpenseReport.setToolTipText("This report allows the owner/manager the opportunity to list total expenses for all the animals in the shelter.");
		btnTotalExpenseReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryWideCostReport.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		panel.add(btnTotalExpenseReport);
		
		JButton btnGoBackTo = new JButton("Go Back to the Main Menu");
		btnGoBackTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainScreen.main(null);
				frmFurryFriendsAnimal.dispose();
			}
		});
		panel.add(btnGoBackTo);
	}

}
