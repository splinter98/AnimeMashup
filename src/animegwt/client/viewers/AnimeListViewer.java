package animegwt.client.viewers;

import animegwt.client.data.AnimeList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ContainerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class AnimeListViewer extends ContentPanel {

	private SimpleComboBox<String> sort;
	private ListView<ModelData> lView;
	private AnimeList list;
	private TextField<String> user;
	private Button btnUser;
	private ToolBar tb;
	
	public AnimeListViewer() {
		list = new AnimeList();
		lView = new ListView<ModelData>();
		user = new TextField<String>();
		
		btnUser = new Button("Get List", new SelectionListener<ButtonEvent>() {  
		      public void componentSelected(ButtonEvent ce) {  
		          list.get(user.getValue()); 
		        }
		}); 
			      
	      sort = new SimpleComboBox<String>();
	      sort.setTriggerAction(TriggerAction.ALL);  
	      sort.setEditable(false);  
	      sort.setForceSelection(true);  
	      sort.setWidth(100);  
	      sort.add("All");  
	      sort.add("Watching");  
	      sort.add("Completed");  
	      sort.add("Plan to Watch");  
	      sort.add("On-Hold");  
	      sort.add("Dropped");  
	      sort.setSimpleValue("All");  
	      sort.addListener(Events.Select, new Listener<FieldEvent>() {  
	        public void handleEvent(FieldEvent be) {
	        	list.getStore().clearFilters();
	        if( sort.getSimpleValue() != "All")
	          list.getStore().filter("watched_status", sort.getSimpleValue());
	        }  
	      });  
		
		tb = new ToolBar();
		
		tb.add(new LabelToolItem("User:"));
		tb.add(user);
		tb.add(btnUser);
	    tb.add(new SeparatorToolItem());   
	    tb.add(sort);
		

		lView.setStore(list.getStore());
		lView.setTemplate(AnimeList.getTemplate());
		lView.setItemSelector("div.thumb-wrap");  
		lView.setLoadingText("Contacting MyAnimeList...");
		lView.setAutoWidth(true);
		
		setBodyBorder(false); 
		setHeading("Please Enter a Username");
		setButtonAlign(HorizontalAlignment.CENTER); 
		setLayout(new FitLayout()); 
		setHeight(920);
		setAutoWidth(true);
		
	
		lView.addListener(Events.OnDoubleClick,
				new Listener<ListViewEvent<ModelData>>() {
					@Override
					public void handleEvent(ListViewEvent<ModelData> lve) {
						String id = lve.getModel().get("id").toString();
						Window wdw = new Window();
						wdw.setWidth(460);
						wdw.setAutoHeight(true);
						wdw.setAutoHide(true);
						wdw.setModal(true);
						
						wdw.addListener(Events.Add, new Listener<ContainerEvent<Window,AnimeViewer>>(){

							@Override
							public void handleEvent(
									ContainerEvent<Window, AnimeViewer> be) {
								be.getContainer().show();
							}});
						
						wdw.add(new AnimeViewer(id));
					}
        		});
		
		setTopComponent(tb); 
		add(lView);
	}
	
	public AnimeListViewer(String username) {
			this();
			list.get(username);
			setHeading("Anime List for: "+username);
	}
	
}
