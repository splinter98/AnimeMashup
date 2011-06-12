package animegwt.client;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.JsonLoadResultReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.ScriptTagProxy;
import com.extjs.gxt.ui.client.store.ListStore;

public class AnimeList {
	final String url_prefix = "http://mal-api.com/animelist/"; 
	String url;
	ScriptTagProxy<PagingLoadResult<ModelData>> proxy;
	ModelType type; 
	JsonLoadResultReader<ListLoadResult<ModelData>> reader;
	final BaseListLoader<ListLoadResult<ModelData>> loader;
	ListStore<ModelData> store;
	
	public AnimeList() {

    proxy = new ScriptTagProxy<PagingLoadResult<ModelData>>(url);  
  
    type = new ModelType();
    //Change
    type.setRoot("anime");  
    type.addField("image_url");  
    type.addField("type");  
    type.addField("score");  
    type.addField("listed_anime_id");  
    type.addField("watched_episodes");  
    type.addField("watched_status");  
    type.addField("title");  
    type.addField("id");  
    type.addField("episodes");  
  
//    DataField datefield = new DataField("lastpost");  
//    datefield.setType(Date.class);  
//    datefield.setFormat("timestamp");  
//    type.addField(datefield);  
  
	reader = new JsonLoadResultReader<ListLoadResult<ModelData>>(type);  
    
	loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
	
    store = new ListStore<ModelData>(loader); 
	}
	
	public BaseListLoader<ListLoadResult<ModelData>> getLoader() {
		return loader;
	}
	
	public ListStore<ModelData> getStore() {
		return store;
	}
	
	public String getURL() {
		return url;
	}
	
	public native static String getTemplate() /*-{ 
    return ['<tpl for=".">', 
    '<div class="thumb-wrap {watched_status}" style="border: 1px solid #DDDDDD;float:left;margin:4px 0 4px  4px; padding:9px;width:150px;height:260px;text-align:center">',
    '<img style="width:140px;height:200px;padding-left:5px;" src="{image_url}">', 
    '<p style="overflow:hidden;width:150px;height:18px;" class="title">{title}</p>', 
    '<p class="watched_status">{watched_status}</p>',
    '<p class="episode_count">{watched_episodes}/{episodes}</p>', 
    '</div>', 
    '</tpl>', 
    ''].join(""); 
	}-*/;

	public void getlist(String username) {
		url = url_prefix+username;
	    proxy.setUrl(url);  
	    loader.load();
	} 
	
}
