package com.animemashup.test.client;

import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

public class AnimeDS extends XJSONDataSource {
  	
    //URL strings for MAL API
    final static String BASE_URL = "http://mal-api.com";
    final static String URL_ANIMELIST = BASE_URL + "/animelist/"; 
    private static AnimeDS instance = null;  
    
	  
    public static AnimeDS getInstance() {  
        if (instance == null) {  
            instance = new AnimeDS("splinter98");  
        }  
        return instance;  
    }
    
    public static AnimeDS changeInstance(String user) {   
            instance = new AnimeDS(user);  
        return instance;  
    }
    
    public AnimeDS(String User) {
    	setDataURL(URL_ANIMELIST + User);
        setRecordXPath("anime");
      //  yahooDS.setCacheAllData(false);
        setAutoCacheAllData(true);
        setCanMultiSort(true);
        //setAttribute("dataFetchMode", "local", true);
        
        DataSourceImageField thumbnail = new DataSourceImageField("Thumbnail", "Thumbnail");
        thumbnail.setWidth(150);
        thumbnail.setImageHeight(200);
        thumbnail.setValueXPath("image_url");
        
        DataSourceIntegerField id = new DataSourceIntegerField("id");
        id.setValueXPath("id");
        id.setAttribute("hidden", true);
        id.setRequired(true);
        id.setPrimaryKey(true);

        DataSourceField title = new DataSourceField("title", FieldType.TEXT);
        DataSourceTextField watchedStatus = new DataSourceTextField("watched_status", "Watched Status");
        watchedStatus.setValueMap("watching", "completed", "on-hold", "dropped", "plan to watch");
        watchedStatus.setCanSortClientOnly(true);
        
//        DataSourceLinkField fullImage = new DataSourceLinkField("link", "Full Image");
//        fullImage.setValueXPath("Url");
//        fullImage.setAttribute("target", "_blank");

        addField(id);
        addField(thumbnail);
        addField(title);
        addField(watchedStatus);
    }
    
    
    //This method is called to transform the criteria into a a format that can be used by YQL
    @Override  
    protected Object transformRequest(DSRequest dsRequest) {  
//    	if (!dsRequest.getOperationType().equals(DSOperationType.FETCH))
//    		return super.transformRequest(dsRequest);
//    	Criteria c = dsRequest.getCriteria();
//    	HashMap<String, String> hm = (HashMap<String, String>) c.getValues();
//    	Iterator<Map.Entry<String, String>> i = (Iterator<Map.Entry<String, String>>) hm.entrySet().iterator();
//    	String where = getQuery("splinter98");
//    	while(i.hasNext()) {
//    		Map.Entry me = (Map.Entry)i.next();
//    		if (me.getKey().equals("__gwt_ObjectId"))
//    			continue;
//    		where = where + " and anime."+me.getKey()+"=\""+me.getValue()+'"';
//    	}
//    	c.addCriteria("q", where);
//    	c.addCriteria("format", "json");
//    	
    	return super.transformRequest(dsRequest);  
    } 
}
