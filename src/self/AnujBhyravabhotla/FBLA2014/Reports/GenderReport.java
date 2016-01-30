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

import self.AnujBhyravabhotla.FBLA2014.Data.Animal;
import self.AnujBhyravabhotla.FBLA2014.Data.Inventory;
import self.AnujBhyravabhotla.FBLA2014.GraphicInterface.ReportScreen;

public class GenderReport {
	/**The main window frame*/
	private JFrame frmFurryFriendsAnimal;
	/**The table that displays the data for the inventory-wide cost report.  This table has four columns.  The first displays the 
	 * animal's name.  The second displays the animal's gender. The third displays whether the animal was spayed/neutered.  The
	 * fourth displays all the vaccines the animal has received.*/
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
					GenderReport window = new GenderReport();
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
	public GenderReport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try{
			frmFurryFriendsAnimal = new JFrame();
			frmFurryFriendsAnimal.setTitle("Furry Friends Animal Shelter");
			frmFurryFriendsAnimal.setBounds(100, 100, 720, 640);
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
			String[] columnNames=new String[]{"Name","Gender","Spayed/Neutered?","Vaccines Had"};
			String[][] tableData=new String[animals.size()][4];
			for(int i=0;i<tableData.length;i++){
				if(animals.get(i).getType().equalsIgnoreCase("Dog"))
					tableData[i]=new String[]{animals.get(i).getName(),(Inventory.dogInventory.get(animals.get(i).caseNumber)).gender(),(Inventory.dogInventory.get(animals.get(i).caseNumber)).getSpayedNeuteredStatus(),(Inventory.dogInventory.get(animals.get(i).caseNumber)).getAllVaccines()};
				if(animals.get(i).getType().equalsIgnoreCase("Cat"))
					tableData[i]=new String[]{animals.get(i).getName(),(Inventory.catInventory.get(animals.get(i).caseNumber)).gender(),(Inventory.catInventory.get(animals.get(i).caseNumber)).getSpayedNeuteredStatus(),(Inventory.catInventory.get(animals.get(i).caseNumber)).getAllVaccines()};
				if(animals.get(i).getType().equalsIgnoreCase("Other"))
					tableData[i]=new String[]{animals.get(i).getName(),"Unkown","Not Applicable",(Inventory.otherInventory.get(animals.get(i).caseNumber)).getVaccines()};
			}
			table = new JTable(new DefaultTableModel(tableData,columnNames));
			frmFurryFriendsAnimal.getContentPane().add(table, BorderLayout.CENTER);
			scrollPane = new JScrollPane(table);
			frmFurryFriendsAnimal.getContentPane().add(scrollPane,BorderLayout.CENTER);
			
			panel = new JPanel();
			frmFurryFriendsAnimal.getContentPane().add(panel, BorderLayout.SOUTH);
			
			btnPrint = new JButton("Print");
			btnPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						table.print(PrintMode.FIT_WIDTH, new MessageFormat("Furry Friends Animal Shelter"),
								new MessageFormat("Gender Report"));
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnPrint.setToolTipText("Click here to print the current report");
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
