package animegwt.client.viewers;

import animegwt.client.data.AnimeInfo;

import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.JavaScriptObject;

public class AnimeViewer extends Html {
	private AnimeInfo anime;
	private XTemplate tpl;
	public AnimeViewer() {
		anime = new AnimeInfo();
		tpl = XTemplate.create(AnimeInfo.getTemplate());
		
		anime.getLoader().addLoadListener(new LoadListener(){
			@Override
			public void loaderLoad(LoadEvent le) {
				JavaScriptObject jsobj = le.getData();
				String html = tpl.applyTemplate(jsobj);
				setHtml(html);
			}

			@Override
			public void loaderLoadException(LoadEvent le) {
				ModelData md = le.getData();
				Info.display("Error", "An Error Occured while loading the Anime: "+md.get("title"));
			}
		});
			      
		setAutoWidth(true);		
	}
	
	public AnimeViewer(String animeId) {
		this();
		anime.get(animeId);
	}
	
	public void setXTemplate(XTemplate xtpl) {
		tpl = xtpl;
	}
	
	public void setXTemplate(String html) {
		tpl = XTemplate.create(html);
	}

}
