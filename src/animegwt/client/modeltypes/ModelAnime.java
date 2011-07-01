package animegwt.client.modeltypes;

import com.extjs.gxt.ui.client.data.ModelType;

public class ModelAnime extends ModelType {
	public ModelAnime() {
		super();
		setRoot("anime");  
	    addField("id");
	    addField("title");
//	    addField("other_titles");
//	    addField("synonyms");
//	    addField("english");
//	    addField("japanese");
	    addField("rank");
	    addField("popularity_rank");
	    addField("image_url");
	    addField("type");
	    addField("episodes");
	    addField("status");
	    addField("classification");
	    addField("members_score");
	    addField("members_count");
	    addField("favorited_count");
	    addField("synopsis");
//	    addField("genres");
//	    addField("tags");
//	    addField("manga_adaptations");
//	    addField("manga_adaptations.manga_id");
//	    addField("manga_adaptations.title");
//	    addField("manga_adaptations.url");
//	    addField("prequels");
//	    addField("prequels.anime_id");
//	    addField("prequels.title");
//	    addField("prequels.url");
//	    addField("sequels");
//	    addField("sequels.anime_id");
//	    addField("sequels.title");
//	    addField("sequels.url");
//	    addField("side_stories");
//	    addField("side_stories.anime_id");
//	    addField("side_stories.title");
//	    addField("side_stories.url");
	  
//	    DataField datefield = new DataField("lastpost");  
//	    datefield.setType(Date.class);  
//	    datefield.setFormat("timestamp");  
//	    addField(datefield);  
	}
}
