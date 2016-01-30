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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import self.AnujBhyravabhotla.FBLA2014.Data.Animal;
import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;
import self.AnujBhyravabhotla.FBLA2014.GraphicInterface.ReportScreen;

public class InventoryReport {
	/**The main window frame*/
	private JFrame frmFurryFriendsAnimal;
	/**The main data table.  This data table has three columns.  The first lists the case numbers for each animal.  The second lists
	 * each animal's name.  The third lists each animal's cage number.*/
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
					InventoryReport window = new InventoryReport();
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
	public InventoryReport() {
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
		
			ArrayList<Animal> animals = new ArrayList<Animal>(Inventory.generalInventory.values());
			String[] columnNames=new String[]{"Case Number", "Name", "Cage Number"};
			ArrayList<String[]> animalData = new ArrayList<String[]>();
			for(Animal s:animals)
				animalData.add(new String[]{s.caseNumber+"",s.getName(),s.getCageNumber()+""});
			String[][] columnData = new String[animals.size()][3];
			for(int i=0;i<columnData.length;i++)
				columnData[i]=animalData.get(i);
			TableModel data = new DefaultTableModel(columnData,columnNames);
			table = new JTable(data);
			frmFurryFriendsAnimal.getContentPane().add(table, BorderLayout.CENTER);
			scrollPane = new JScrollPane(table);
			frmFurryFriendsAnimal.getContentPane().add(scrollPane,BorderLayout.CENTER);
			
			panel = new JPanel();
			frmFurryFriendsAnimal.getContentPane().add(panel, BorderLayout.SOUTH);
			
			btnPrint = new JButton("Print");
			btnPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						table.print(PrintMode.FIT_WIDTH,new MessageFormat("Furry Friends Animal Shelter"),
								new MessageFormat("Inventory Report"));
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnPrint.setToolTipText("Click here to print the current report");
			panel.add(btnPrint);
			
			btnDone = new JButton("Done");
			btnDone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ReportScreen.main(null);
					frmFurryFriendsAnimal.dispose();
				}
			});
			btnDone.setToolTipText("Click here to close this window and return to the main screen");
			panel.add(btnDone);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
