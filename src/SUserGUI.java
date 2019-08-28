import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

public class SUserGUI extends JPanel {
	private int w;
	private int h;
	private Person SU;
	private JPanel tasks; // Todo panel
	private JPanel list; // UserList panel
	private JPanel comp; // Complaints panel
	private JPanel bot; // Log Panel
	private BorderLayout layout;
	// -----------------------
	private JPopupMenu popup = new JPopupMenu();
	// -----------------------
	// -----------------------
	private String[] log_col = {"Time", "Event"};
	private String[] u_col = {"Username", "DOB", "Age", "Type", "Email", "Amount",
							  "Rating", "Flags", "Active", "VIP", "Times rated", "Times Suspended", "Lowest Rater"};
	private String[] duty;
	private String[] complaints;
	private Object[][] Data;
	private Object[][] Users;
	private JTable log_table;
	private JTable user_table;
	private JList <String> comp_list;
	private JList <String> Todo;
	private JScrollPane list_scroll;
	private JScrollPane task_scroll;
	private JScrollPane comp_scroll;
	private JScrollPane log_scroll;
	private JTabbedPane tabs = new JTabbedPane();
	
	public SUserGUI(Connect conn, int width, int height, Person su){
		SU = su;
		w = width;
		h = height;
		layout = new BorderLayout();
		setLayout(layout);
		setVisible(true);
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		tabs = new JTabbedPane();
		// Panel Initialization
		setupPanels();
		// Setup the 3 tables
		setLogTable(conn);
		setUserTable(conn);
		setComplaints(conn);
		setTasks(conn);
		setTabs();

		
		JMenuItem suspend = new JMenuItem("Suspend");
        JMenuItem reinstate = new JMenuItem("Reinstate");
        JMenuItem promote = new JMenuItem("Promote");
        JMenuItem demote = new JMenuItem("Demote");
        
        popup.add(reinstate);
        popup.add(promote);
        popup.add(demote);
        popup.add(suspend);
		
		

		suspend.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(user_table.getSelectedRow()>=0){
					list_scroll.setVisible(false);
					log_scroll.setVisible(false);
					int row = user_table.getSelectedRow();
					conn.suspend(row+1);
					conn.refresh();
					
					conn.log("SU "+ SU.getUsername() + " has suspended " + Users[row][0]+".");
					setLogTable(conn);
					// Users row = row;
					// suspend row = row+1;
					setUserTable(conn);
					tabs.removeAll();
					setTabs();
					tabs.setSelectedIndex(1);
				}
				
			}
		}
		);
		
		reinstate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(user_table.getSelectedRow()>=0){
					list_scroll.setVisible(false);
					log_scroll.setVisible(false);
					int row = user_table.getSelectedRow();
					conn.reinstate(row+1);
					conn.refresh();
					conn.log("SU "+ SU.getUsername() + " has reinstated " + Users[row][0]+".");
					setLogTable(conn);
						// Users row = row;
						// suspend row = row+1;
					setUserTable(conn);
					tabs.removeAll();
					setTabs();
					tabs.setSelectedIndex(1);
				}
			}
		}
		);
		
		promote.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(user_table.getSelectedRow()>=0){
					list_scroll.setVisible(false);
					log_scroll.setVisible(false);
					int row = user_table.getSelectedRow();
					conn.promote(row+1);
					conn.refresh();
					conn.log("SU "+ SU.getUsername() + " has approved " + Users[row][0]+"'s application to be promoted.");
					setLogTable(conn);
						// Users row = row;
						// suspend row = row+1;
					setUserTable(conn);
					tabs.removeAll();
					setTabs();
					tabs.setSelectedIndex(1);
				}
			}
		}
		);
		
		demote.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(user_table.getSelectedRow()>=0){
					list_scroll.setVisible(false);
					log_scroll.setVisible(false);
					int row = user_table.getSelectedRow();
					conn.demote(row+1);
					conn.refresh();
					conn.log("SU "+ SU.getUsername() + " has demoted " + Users[row][0]+".");
					setLogTable(conn);
						// Users row = row;
						// suspend row = row+1;
					setUserTable(conn);
					tabs.removeAll();
					setTabs();
					tabs.setSelectedIndex(1);
				}
			}
		}
		);
	}
	

	public void setUserTable(Connect conn){
		Users = conn.userList();
		user_table = new JTable(Users,u_col);
		list_scroll = new JScrollPane(user_table);
		Dimension d = user_table.getPreferredSize();
		d.width = 700;
		d.height = 450;
		list_scroll.setPreferredSize(d);
		user_table.setComponentPopupMenu(popup);
		list.add(list_scroll);
	
	}
	
	public void setComplaints(Connect conn){
		complaints = conn.displayComplaints();
		comp_list = new JList(complaints);
		comp_list.setFixedCellHeight(30);
		Dimension d = comp_list.getPreferredSize();
		d.width = 700;
		d.height = 450;
		comp_scroll = new JScrollPane(comp_list);
		comp_scroll.setPreferredSize(d);
		comp.add(comp_scroll);
		
		/*
		comp_list.addListSelectionListener(new ListSelectionListener() {
		    @Override
			public void valueChanged(ListSelectionEvent e)
		    {
		    	  if (!e.getValueIsAdjusting()) 
		    	  {
		    		  	  Todo.setVisible(false);
			    		  log_scroll.setVisible(false);
			    	      System.out.println(Todo.getSelectedIndex());
			    		  conn.SUReviewed(Todo.getSelectedIndex(), SU.getUsername(), "Complaint");
			    		  Todo.clearSelection();
			    		  conn.refresh();
			    		  tabs.removeAll();
			    		  setComplaints(conn);
			    		  setTasks(conn);
			    		  setLogTable(conn);
						  setTabs();
						  tabs.setSelectedIndex(2);
		    		  
		    	  }
		    }
		}); */
	}
	
	public void setupPanels(){
		tasks = new JPanel();
		list = new JPanel();
		bot = new JPanel();
		comp = new JPanel();
		tasks.setLayout(new GridBagLayout());
		tasks.setBounds(0, 0, w, 100);
		list.setPreferredSize(new Dimension(w, h/2));
		list.setLayout(new FlowLayout());
	
		bot.setPreferredSize(new Dimension(w, h/4));
		bot.setBorder(BorderFactory.createTitledBorder("Event Log"));
		add(bot,BorderLayout.SOUTH);
	}
	
	public void setTasks(Connect conn){
		duty = conn.displayTasks();
		Todo = new JList<String>(duty);
		Todo.setFixedCellHeight(30);
		Dimension d = Todo.getPreferredSize();
		d.width = 700;
		d.height = 450;
		task_scroll = new JScrollPane(Todo);
		task_scroll.setPreferredSize(d);
		tasks.add(task_scroll);
		
		Todo.addListSelectionListener(new ListSelectionListener() {
		    @Override
			public void valueChanged(ListSelectionEvent e)
		    {
		    	  if (!e.getValueIsAdjusting()) 
		    	  {
		    		  tasks.removeAll();
		    		  log_scroll.setVisible(false);
		    		  conn.SUReviewed(Todo.getSelectedIndex(), SU.getUsername(), "Task");
		    		  conn.refresh();
		    		  tabs.removeAll();
		    		  setTasks(conn);
		    		  setLogTable(conn);
					  setTabs();
					  tabs.setSelectedIndex(0);
		    		  
		    	  }
		    }
		}); 
	}
	
	public void setLogTable(Connect conn){
		Data = conn.setLog();
		log_table = new JTable(Data, log_col);
		// Set column width
		TableColumn column = null;
		column = log_table.getColumnModel().getColumn(0);
		column.setPreferredWidth(150);
		column = log_table.getColumnModel().getColumn(1);
		column.setPreferredWidth(550);
		// -----------------------
		log_scroll = new JScrollPane(log_table);
		Dimension d = log_table.getPreferredSize();
		d.width = 700;
		d.height = 160;
		log_scroll.setPreferredSize(d);
		bot.add(log_scroll);
	}
	
	public void setTabs(){
		tabs.addTab("To do", tasks);
		tabs.addTab("User List", list);
		tabs.addTab("Complaints", comp);
		add(tabs);
	}
	
	
}
