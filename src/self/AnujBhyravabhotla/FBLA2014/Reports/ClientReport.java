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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;

import self.AnujBhyravabhotla.FBLA2014.Data.Animal;
import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;
import self.AnujBhyravabhotla.FBLA2014.GraphicInterface.ReportScreen;

public class ClientReport {
	/**The main window frame*/
	private JFrame frmFurryFriendsAnimal;
	/**The table that displays the data for the inventory-wide cost report.  This table displays general information for each animal*/
	private JTable table;
	/**Because this generates reports for each animal, the reportingAnimal field keeps track of the particular animal whose report
	 * is displayed*/
	private Animal reportingAnimal;
	/**The scrollpane that contains the data table.  This enables the user to scroll through the data if there is enough displayed
	 * to make for the need to scroll.*/
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblSelectAnAnimal;
	/**Because this generates reports for each animal, this dropdown box allows the user to select an animal from the inventory.
	 * Animals are displayed by name.*/
	private JComboBox animalSelector;
	private JPanel panel_1;
	private JButton btnPrint;
	private JButton btnDone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientReport window = new ClientReport();
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
	public ClientReport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try{
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
			
			final ArrayList<Animal> animals = new ArrayList<Animal>(Inventory.generalInventory.values());
			ArrayList<String> animalNames = new ArrayList<String>();
			for(Animal a:animals)
				animalNames.add(a.getName());
			
			panel_1 = new JPanel();
			frmFurryFriendsAnimal.getContentPane().add(panel_1, BorderLayout.SOUTH);
			
			btnPrint = new JButton("Print");
			btnPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						table.print(PrintMode.FIT_WIDTH,new MessageFormat("Furry Friends Animal Shelter"),new MessageFormat("Client Report for "+reportingAnimal.getName()));
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnPrint.setToolTipText("Click here to print the current report");
			panel_1.add(btnPrint);
			
			btnDone = new JButton("Done");
			btnDone.setToolTipText("Click here to close this window and return to the main screen");
			panel_1.add(btnDone);
			btnDone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ReportScreen.main(null);
					frmFurryFriendsAnimal.dispose();
				}
			});
		
			table = new JTable();
			frmFurryFriendsAnimal.getContentPane().add(table,BorderLayout.CENTER);
			
			scrollPane = new JScrollPane(table);
			frmFurryFriendsAnimal.getContentPane().add(scrollPane,BorderLayout.CENTER);
			
			panel = new JPanel();
			frmFurryFriendsAnimal.getContentPane().add(panel, BorderLayout.NORTH);
			
			lblSelectAnAnimal = new JLabel("Select an animal from the dropdown box: ");
			panel.add(lblSelectAnAnimal);
			animalSelector = new JComboBox(animalNames.toArray());
			animalSelector.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(Animal a:animals){
						if(animalSelector.getSelectedItem().equals(a.getName()))
							reportingAnimal = a;
					}
					final String[] columnNames=new String[]{"",""};
					try{
						if(reportingAnimal!=null&&reportingAnimal.getType().equalsIgnoreCase("Dog"))
							table.setModel(new DefaultTableModel(
									Inventory.dogInventory.get(reportingAnimal.caseNumber).getClientReport(),columnNames));
						if(reportingAnimal!=null&&reportingAnimal.getType().equalsIgnoreCase("Cat"))
							table.setModel(new DefaultTableModel(
									Inventory.catInventory.get(reportingAnimal.caseNumber).getClientReport(),columnNames));
						if(reportingAnimal!=null&&reportingAnimal.getType().equalsIgnoreCase("Other"))
							table.setModel(new DefaultTableModel(
									Inventory.otherInventory.get(reportingAnimal.caseNumber).getClientReport(),columnNames));
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			});
			panel.add(animalSelector);
			
			reportingAnimal = null;
			for(Animal a:animals){
				if(animalSelector.getSelectedItem().equals(a.getName()))
					reportingAnimal = a;
			}
			final String[] columnNames=new String[]{"",""};
			if(reportingAnimal!=null&&reportingAnimal.getType().equalsIgnoreCase("Dog"))
				table.setModel(new DefaultTableModel((Inventory.dogInventory.get(reportingAnimal.caseNumber)).getClientReport(),
						columnNames));
			if(reportingAnimal!=null&&reportingAnimal.getType().equalsIgnoreCase("Cat"))
				table.setModel(new DefaultTableModel((Inventory.catInventory.get(reportingAnimal.caseNumber)).getClientReport(),
						columnNames));
			if(reportingAnimal!=null&&reportingAnimal.getType().equalsIgnoreCase("Other"))
				table.setModel(new DefaultTableModel((Inventory.catInventory.get(reportingAnimal.caseNumber)).getClientReport(),
						columnNames));
		}catch(Exception e){
			JOptionPane.showMessageDialog(frmFurryFriendsAnimal, "The Inventory is Empty");
			frmFurryFriendsAnimal.dispose();
			return;
		}
	}

}
