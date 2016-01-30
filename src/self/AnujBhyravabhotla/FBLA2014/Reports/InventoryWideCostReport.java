package self.AnujBhyravabhotla.FBLA2014.Reports;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;

import self.AnujBhyravabhotla.FBLA2014.Data.Animal;
import self.AnujBhyravabhotla.FBLA2014.Data.Cat;
import self.AnujBhyravabhotla.FBLA2014.Data.Dog;
import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;
import self.AnujBhyravabhotla.FBLA2014.GraphicInterface.MainScreen;
import self.AnujBhyravabhotla.FBLA2014.GraphicInterface.ReportScreen;

public class InventoryWideCostReport {
	/**The main window frame*/
	private JFrame frmFurryFriendsAnimal;
	/**The table that displays the data for the inventory-wide cost report.  This table has three columns.  The first displays the 
	 * animal's case number.  The second displays the animal's name. The third displays the total cost for the animal's stay.*/
	private JTable table;
	/**The scrollpane that contains the data table.  This enables the user to scroll through the data if there is enough displayed
	 * to make for the need to scroll.*/
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnPrint;
	private JButton btnDone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryWideCostReport window = new InventoryWideCostReport();
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
	public InventoryWideCostReport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try{
			NumberFormat fmt = NumberFormat.getCurrencyInstance();
			frmFurryFriendsAnimal = new JFrame();
			frmFurryFriendsAnimal.setTitle("Furry Friends Animal Shelter");
			frmFurryFriendsAnimal.setBounds(100, 100, 450, 300);
			frmFurryFriendsAnimal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
			
			String[] columnNames = new String[]{"Case Number","Name","Total Cost for stay"}; 
			ArrayList<Animal> animals=new ArrayList<Animal>(Inventory.generalInventory.values());
			String[][] data = new String[Inventory.generalInventory.size()][3];
			for(int i=0;i<data.length;i++){
				if(animals.get(i).getType().equalsIgnoreCase("Dog")){
					data[i]=new String[]{animals.get(i).caseNumber+"",animals.get(i).getName(),fmt.format(Inventory.dogInventory.get(animals.get(i).caseNumber).calculateCost())};
				}
				if(animals.get(i).getType().equalsIgnoreCase("Cat")){
					data[i]=new String[]{animals.get(i).caseNumber+"",animals.get(i).getName(),fmt.format(Inventory.catInventory.get(animals.get(i).caseNumber).calculateCost())};
				}
				if(animals.get(i).getType().equalsIgnoreCase("Other")){
					data[i]=new String[]{animals.get(i).caseNumber+"",animals.get(i).getName(),fmt.format(animals.get(i).calculateCost())};
				}
			}
			table = new JTable(new DefaultTableModel(data,columnNames));
			frmFurryFriendsAnimal.getContentPane().add(table, BorderLayout.CENTER);
			scrollPane = new JScrollPane(table);
			frmFurryFriendsAnimal.getContentPane().add(scrollPane,BorderLayout.CENTER);
			
			panel = new JPanel();
			frmFurryFriendsAnimal.getContentPane().add(panel, BorderLayout.SOUTH);
			
			btnPrint = new JButton("Print");
			btnPrint.setToolTipText("Click here to print the current report");
			btnPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						table.print(PrintMode.FIT_WIDTH,new MessageFormat("Furry Friends Animal Shelter"),
								new MessageFormat("Inventory Wide Expense Report"));
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(btnPrint);
			
			btnDone = new JButton("Done");
			btnDone.setToolTipText("Click here to close this window and return to the main screen");
			panel.add(btnDone);
			btnDone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ReportScreen.main(null);
					frmFurryFriendsAnimal.dispose();
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
