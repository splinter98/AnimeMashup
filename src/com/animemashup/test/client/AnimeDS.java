package com.animemashup.test.client;

import com.google.gwt.http.client.URL;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

public class AnimeDS extends XJSONDataSource {
	private static AnimeDS instance = null;  
	  
    public static AnimeDS getInstance() {  
        if (instance == null) {  
            instance = new AnimeDS("splinter98");  
        }  
        return instance;  
    }  
    
    public AnimeDS(String User) {
    	setDataURL(getURL("splinter98"));
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
    
    private static String getURL(String User) {
    	//YQL string
    	final String YQL_URL = "http://query.yahooapis.com/v1/public/yql?q=";
    	final String YQL_QUERY = "select * from json where url=";
    	
    	//URL strings for MAL API
    	final String BASE_URL = "http://mal-api.com";
    	final String URL_ANIMELIST = BASE_URL + "/animelist/"; 
    	
    	return YQL_URL + URL.encode(YQL_QUERY+'"'+URL_ANIMELIST+User+'"')+"&format=json";
    }
}
