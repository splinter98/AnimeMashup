package animegwt.client.data;

import animegwt.client.modeltypes.ModelAnimeList;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.JsonLoadResultReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ScriptTagProxy;
import com.extjs.gxt.ui.client.store.ListStore;

public class AnimeList extends GenericData<ListLoadResult<ModelData>> {
	private final static String url_prefix = "http://mal-api.com/animelist/"; 
	private ListStore<ModelData> store;
	
	public AnimeList() { 
     super(url_prefix,
    		new BaseListLoader<ListLoadResult<ModelData>>(
    				new ScriptTagProxy<ListLoadResult<ModelData>>(""),
    				new JsonLoadResultReader<ListLoadResult<ModelData>>(new ModelAnimeList())
    			)
    		);
     store = new ListStore<ModelData>((BaseListLoader<ListLoadResult<ModelData>>) getLoader());
	}


	public ListStore<ModelData> getStore() {
		return store;
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

	
}
