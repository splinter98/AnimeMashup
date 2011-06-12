package animegwt.client;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AnimeGWT implements EntryPoint {
	SimpleComboBox<String> sort;
	public void onModuleLoad() {
		
		final AnimeList list = new AnimeList();
		final ListView<ModelData> lView = new ListView<ModelData>();
		
		final TextField<String> user = new TextField<String>();
		
		Button btnUser = new Button("Get List", new SelectionListener<ButtonEvent>() {  
		      public void componentSelected(ButtonEvent ce) {  
		          list.getlist(user.getValue()); 
		        }
		}); 
			      
	      sort = new SimpleComboBox<String>();
	      sort.setTriggerAction(TriggerAction.ALL);  
	      sort.setEditable(false);  
	      sort.setForceSelection(true);  
	      sort.setWidth(100);  
	      sort.add("All");  
	      sort.add("Watched");  
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
		
		ToolBar tb = new ToolBar();
		
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
		
		ContentPanel cp = new ContentPanel(); 
		cp.setBodyBorder(false); 
		cp.setHeading("Anime List for Splinter98");
		cp.setButtonAlign(HorizontalAlignment.CENTER); 
		cp.setLayout(new FitLayout()); 
		cp.setHeight(920);
		cp.setAutoWidth(true);
		
		cp.setTopComponent(tb);
		cp.add(lView);

		RootPanel.get().add(cp);
		
	}

	protected void filter() {
		
	}

}