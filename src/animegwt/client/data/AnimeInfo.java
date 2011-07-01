package animegwt.client.data;

import com.extjs.gxt.ui.client.data.BaseLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ScriptTagProxy;

public class AnimeInfo extends GenericData<ModelData> {
	private final static String url_prefix = "http://mal-api.com/anime/"; 
	
	public AnimeInfo() {
		super(url_prefix, 
				new BaseLoader<ModelData>(new ScriptTagProxy<ModelData>(""))
			);
	
	}
	
	public native static String getTemplate() /*-{ 
    return ['<tpl for=".">',
    '<div class="thumb-wrap" style=padding:5px;">',
    '<img style="width:140px;height:200px;padding:5px;float:left" src="{image_url}">', 
    '<p style="font-weight:bold" class="title">Title: {title}</p>', 
    '<p class="synopsis"><span style="font-weight:bold">Synopsis:</span> {synopsis}</p>', 
    '</div>',  
    '</tpl>'].join(""); 
	}-*/;
	
}
