package animegwt.client;

import animegwt.client.viewers.AnimeListViewer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AnimeGWT implements EntryPoint {
	
	public void onModuleLoad() {
		String username = com.google.gwt.user.client.Window.Location.getParameter("user");
		if (username != null) {
		RootPanel.get().add(new AnimeListViewer(username));
		} else {
		RootPanel.get().add(new AnimeListViewer());	
		}
		
	}


}