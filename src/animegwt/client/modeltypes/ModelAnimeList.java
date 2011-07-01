package animegwt.client.modeltypes;

import com.extjs.gxt.ui.client.data.DataField;
import com.extjs.gxt.ui.client.data.ModelType;

public class ModelAnimeList extends ModelType {

	public ModelAnimeList() {
		super();
		setRoot("anime");  
	    addField("image_url");  
	    addField("type");  
	    addField("score");  
	    addField("listed_anime_id");  
	    addField("watched_episodes");  
	    addField("watched_status");  
	    addField("title");
	    DataField id = new DataField("id");
	    id.setType(Integer.class);
	    addField(id);  
	    addField("episodes");  
	    
//	    DataField datefield = new DataField("lastpost");  
//	    datefield.setType(Date.class);  
//	    datefield.setFormat("timestamp");  
//	    type.addField(datefield); 
	}

}
