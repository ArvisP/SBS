import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.NumberFormat;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;

public class ContentPanel extends JPanel {
	
	
	public JList<Object> list; // Add <String> if fail
	private Person user;
	public DefaultListModel<String> model = new DefaultListModel<String>();
	public Sale[] Sales;
	private int[] filtered;
	public JTextField searchField;
	private GridBagLayout layout;
	private GridBagConstraints c = new GridBagConstraints();
	public JButton searchButton;
	public JComboBox <String>sortBy;
	public JComboBox <String>filterBy;
	private Object[] panels; // REMOVE IF FAIL
	private String[] sortByOptions = {"Sort By:","Price", "Rating"};
	private String[] filterByOptions = {"Show All", "Items Only", "Services Only", "Auctions Only"};
	
	public ContentPanel(Connect conn, int width, int height, Person u){
		user = u;
		list = new JList<Object>();
		list.setCellRenderer(new ListingCells());
		initialItems(conn);
		list.setListData(panels);
		list.setVisibleRowCount(20);
		layout = new GridBagLayout();
		setLayout(layout);
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		// Panel for components
		JPanel comp = new JPanel();
		comp.setLayout(new FlowLayout());
		comp.setBounds(0, 0, 700, 50);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=7;
		// ---------- FilterBy Combo --------
		filterBy = new JComboBox<String>(filterByOptions);
		comp.add(filterBy, FlowLayout.LEFT);
		add(comp,c);
		// -------- sortBy Combo -------------
		sortBy = new JComboBox<String>(sortByOptions);
		comp.add(sortBy, FlowLayout.LEFT);
		// ------ Search Button --------------
		searchButton = new JButton("Search");
		comp.add(searchButton, FlowLayout.LEFT);
		// ------ Search Field --------------
		searchField = new JTextField(20);
		comp.add(searchField, FlowLayout.LEFT);
		
		// ---------JList---------------------
		c.gridy=1;
		c.gridx=0;
		c.gridwidth = 5;
		JScrollPane scroll = new JScrollPane(list);
		scroll.setMinimumSize(new Dimension(700, 650));
		add(scroll,c); 
		// -----------------------------------
	/*	
		list.addListSelectionListener(
				new ListSelectionListener(){
					@Override
					public void valueChanged(ListSelectionEvent evt) {
						 buyingGUI = new Buying(conn, user, sale, width, height, this);
					}
					
				}
					
				);
		*/
		
		
		
		
		sortBy.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						String selected = sortBy.getSelectedItem().toString();
						switch(selected){
							case "Price" : 
								sortByPrice();
								displaySales(Sales.length);
								break;
							case "Rating" :
								sortByRating();
								displaySales(Sales.length);
								break;
						}
					}
					
				}
					
				);
		
		filterBy.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						String selected = filterBy.getSelectedItem().toString();
						switch(selected){
							case "Items Only" : 
								filterItems();
								displaySales(Sales.length);
								break;
							case "Services Only" :
								filterServices();
								displaySales(Sales.length);
								break;
							case "Auctions Only" :
								filterAuctions();
								displaySales(Sales.length);
								break;
							case "Show All" :
								showAll();
								displaySales(Sales.length);
								break;
						}
					}
				}
					
				);
		
		searchButton.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						String query = searchField.getText();
						filterBy.setSelectedIndex(0);
						sortBy.setSelectedIndex(0);
						model.clear();
						if(query.equals("")) initialItems(conn);
						else{
							int amount = conn.rsAmount(query);
							if(amount == 0){
								model.addElement("No listings match your criteria.");
								setPanels();
							}
							else{
								Sales = new Sale[amount];
								Sales = conn.search(query, amount);
								showAll();
								displaySales(amount);
							}
						}
						
					}
				}
				);
	}
	
	public void initialItems(Connect conn){
		int i;
		Sales = new Sale[40];
		Sales = conn.ini_rs();
		NumberFormat nformat = NumberFormat.getCurrencyInstance();
		nformat.setGroupingUsed(true);
		String title;
		if(Sales[0] != null){
			for(i = 0; i<40; i++){
				if(Sales[i] != null){
					String price = nformat.format(Sales[i].getPrice());
					String new_price = nformat.format(Sales[i].getPrice()*0.9);
					if(Sales[i].isAuction()){
						if(user.isVIP()){
							title = "<html>AUCTION: "+Sales[i].getTitle()+" - Highest bid: "+ nformat.format(Sales[i].getHighBid()) +
									" | Asking price: <strike>"+nformat.format(Sales[i].getPrice())+"</strike> | VIP Price: "+nformat.format(Sales[i].getPrice()*0.9) +"<br>"+
									" Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
						}
						else{
							title = "<html>AUCTION: "+Sales[i].getTitle()+" - Highest bid: "+ nformat.format(Sales[i].getHighBid()) +
									" | Asking price: "+nformat.format(Sales[i].getPrice())+"<br>"+
									" Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
						}
					}
					else{
						if(Sales[i].getCategory().equals("Item")){
							if(user.isVIP()){
								title = "<html>ITEM SALE: "+Sales[i].getTitle()+" - <strike>Price: "+ price +"</strike> | VIP Price: " + new_price +
										"<br> Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
							}
							else{
								title = "<html>ITEM SALE: "+Sales[i].getTitle()+" - Price: "+ price +"<br>"+
										" Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
							}
						}
						else{
							if(user.isVIP()){
								title = "<html>SERVICE SALE: "+Sales[i].getTitle()+" - <strike>Price: "+ price +"</strike> | VIP Price: " + new_price +
										"<br> Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
							}
							else{
								title = "<html>SERVICE SALE: "+Sales[i].getTitle()+" - Price: "+ price +"<br>"+
										" Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
							}
						}
					}
					model.addElement(title);
				}
				else{
					model.addElement("");
				} 
			}
		}
		else
			model.addElement("No listings match your criteria.");
		showAll();
		setPanels();
		
	}
	
	public void displaySales(int amount){
		int i;
		String title;
		NumberFormat nformat = NumberFormat.getCurrencyInstance();
		nformat.setGroupingUsed(true);
		for(i = 0; i<amount; i++){
			if(filtered[i] == 0 && Sales[i]!=null){
				String price = nformat.format(Sales[i].getPrice());
				String new_price = nformat.format(Sales[i].getPrice()*0.9);
				if(Sales[i].isAuction()){
					if(user.isVIP()){
						title = "<html>AUCTION: "+Sales[i].getTitle()+" - Highest bid: "+ nformat.format(Sales[i].getHighBid()) +
								" | Asking price: <strike>"+nformat.format(Sales[i].getPrice())+"</strike> | VIP Price: "+nformat.format(Sales[i].getPrice()*0.9) +"<br>"+
								" Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
					}
					else{
						title = "<html>AUCTION: "+Sales[i].getTitle()+" - Highest bid: "+ nformat.format(Sales[i].getHighBid()) +
								" | Asking price: "+nformat.format(Sales[i].getPrice())+"<br>"+
								" Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
					}
				}
				else{
					if(Sales[i].getCategory().equals("Item")){
						if(user.isVIP()){
							title = "<html>ITEM SALE: "+Sales[i].getTitle()+" - <strike>Price: "+ price +"</strike> | VIP Price: " + new_price +
									"<br> Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
						}
						else{
							title = "<html>ITEM SALE: "+Sales[i].getTitle()+" - Price: "+ price +"<br>"+
									" Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
						}
					}
					else{
						if(user.isVIP()){
							title = "<html>SERVICE SALE: "+Sales[i].getTitle()+" - <strike>Price: "+ price +"</strike>"+" - VIP Price: " + new_price +
									"<br> Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
						}
						else{
							title = "<html>SERVICE SALE: "+Sales[i].getTitle()+" - Price: "+ price +"<br>"+
									" Seller: "+ Sales[i].getSeller().getUsername() +" | Rating: "+ Sales[i].getSeller().getRating()+"</html>";
						}
					}
				}
				model.addElement(title);
			}
		}
		while(model.size() < 20){
			model.addElement("");
		}
		
		setPanels();
	}
	
	public void sortByPrice(){
		if(!model.getElementAt(0).equals("No listings match your criteria.") && Sales.length>1){
			model.clear();
			Sale temp;
	        for (int i = 1; i < Sales.length; i++) {
	            for(int j = i ; j > 0 ; j--){
	            	if(Sales[j]!=null){
		                if(Sales[j].getPrice() < Sales[j-1].getPrice()){
		                    temp = Sales[j];
		                    Sales[j] = Sales[j-1];
		                    Sales[j-1] = temp;
		                }
	            	}
	            	else break;
	            }
	        }
		}
	}
	
	public void sortByRating(){
		if(!model.getElementAt(0).equals("No listings match your criteria.") && Sales.length>1){
			model.clear();
			Sale temp;
	        for (int i = 1; i < Sales.length; i++) {
	            for(int j = i ; j > 0 ; j--){
	            	if(Sales[j]!=null){
		                if(Sales[j].getSeller().getRating() > Sales[j-1].getSeller().getRating()){
		                    temp = Sales[j];
		                    Sales[j] = Sales[j-1];
		                    Sales[j-1] = temp;
		                }
	            	}
	            	else break;
	            }
	        }
		}
	}
	
	public void filterItems(){
		if(!model.getElementAt(0).equals("No listings match your criteria.")){
			int i;
			for (i=0; i<Sales.length; ++i){
				if(Sales[i]!=null){
					if(!Sales[i].getCategory().equals("Item"))
						filtered[i] = 1;
					else
						filtered[i] = 0;
				}
				
			}
		}
	}
	
	public void filterServices(){
		if(!model.getElementAt(0).equals("No listings match your criteria.")){
			int i;
			for (i=0; i<Sales.length; ++i){
				if(Sales[i]!=null){
					if(!Sales[i].getCategory().equals("Service"))
						filtered[i] = 1;
					else
						filtered[i] = 0;
				}
			}
		}
	}
	
	public void filterAuctions(){
		if(!model.getElementAt(0).equals("No listings match your criteria.")){
			int i;
			for (i=0; i<Sales.length; ++i){
				if(Sales[i]!=null){
					if(!Sales[i].isAuction())
						filtered[i] = 1;
					else
						filtered[i] = 0;
				}
			}
		}
	}
	
	public void showAll(){
			filtered = new int[Sales.length];
			int i;
			for (i=0; i<Sales.length; ++i){
				filtered[i] = 0;
			}
		
	}
	
	public void setPanels(){
		ImageIcon img;
		JLabel label;
		JPanel panel;
		int i;
		if(model.getElementAt(0).equals("No listings match your criteria."))
		{
			panels = new Object[1];
			img = new ImageIcon("images/no_image.png");
			Image icon = img.getImage();
			Image newimg = icon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			img = new ImageIcon(newimg);
			label = new JLabel(model.getElementAt(0), JLabel.CENTER);
			panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			panel.setPreferredSize(new Dimension(650,50));
			panel.add(label);
			panels[0] = panel;
		}
		else{
			panels = new Object[Sales.length];
			for(i=0; i<Sales.length; ++i){
				if(filtered[i]==0 && Sales[i]!=null){
					try{
		    			if(Sales[i].getImage().equals("n/a")){
		        			img = new ImageIcon("images/no_image.png");
		        			Image icon = img.getImage();
		        			Image newimg = icon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		        			img = new ImageIcon(newimg);
		    			}
		        		else{
		        			img = new ImageIcon("images/"+Sales[i].getImage());
		        			Image icon = img.getImage();
		        			Image newimg = icon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		        			img = new ImageIcon(newimg);
		        		}
		    			label = new JLabel(model.getElementAt(i),img, JLabel.LEFT);
		    			panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		    			panel.add(label);
		    			panels[i] = panel;
		    		}
		    		catch(Exception ex){
		    			img = new ImageIcon("images/no_image.png");
		    			Image icon = img.getImage();
		    			Image newimg = icon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		    			img = new ImageIcon(newimg);
		    			label = new JLabel(model.getElementAt(i),img, JLabel.LEFT);
		    			panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		    			panel.add(label);
		    			panels[i] = panel;
		    		}
				}
			}
		}
		
		list.setListData(panels);
	}

	
}
