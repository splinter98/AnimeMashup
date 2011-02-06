package com.animemashup.test.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.JSOHelper;

public class AnimeDS extends XJSONDataSource {
	//YQL string
    final static String YQL_URL = "http://query.yahooapis.com/v1/public/yql";
    final static String YQL_QUERY = "select anime from json where url=";
    	
    //URL strings for MAL API
    final static String BASE_URL = "http://mal-api.com";
    final static String URL_ANIMELIST = BASE_URL + "/animelist/"; private static AnimeDS instance = null;  
	  
    public static AnimeDS getInstance() {  
        if (instance == null) {  
            instance = new AnimeDS("splinter98");  
        }  
        return instance;  
    }  
    
    public AnimeDS(String User) {
    	setDataURL(YQL_URL);
        setRecordXPath("query/results/json/anime");
      //  yahooDS.setCacheAllData(false);
        setAutoCacheAllData(true);
        setCanMultiSort(false);
        setAttribute("dataFetchMode", "local", true);
        
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
        
//        DataSourceLinkField fullImage = new DataSourceLinkField("link", "Full Image");
//        fullImage.setValueXPath("Url");
//        fullImage.setAttribute("target", "_blank");

        addField(id);
        addField(thumbnail);
        addField(title);
        addField(watchedStatus);
    }
    
    private static String getQuery(String User) {
    	return YQL_QUERY+'"'+URL_ANIMELIST+User+'"';
    }
    
    //This method is called to transform the criteria into a a format that can be used by YQL
    @SuppressWarnings("unchecked")
	@Override  
    protected Object transformRequest(DSRequest dsRequest) {  
    	if (!dsRequest.getOperationType().equals(DSOperationType.FETCH))
    		return super.transformRequest(dsRequest);
    	Criteria c = dsRequest.getCriteria();
    	HashMap<String, String> hm = (HashMap<String, String>) c.getValues();
    	Iterator<Map.Entry<String, String>> i = (Iterator<Map.Entry<String, String>>) hm.entrySet().iterator();
    	String where = getQuery("splinter98");
    	while(i.hasNext()) {
    		Map.Entry me = (Map.Entry)i.next();
    		if (me.getKey().equals("__gwt_ObjectId"))
    			continue;
    		where = where + " and anime."+me.getKey()+"=\""+me.getValue()+'"';
    	}
    	c.addCriteria("q", where);
    	c.addCriteria("format", "json");
    	
    	return super.transformRequest(dsRequest);  
    } 
}
