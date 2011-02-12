package com.animemashup.test.client;

/*
 * Smart GWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * Smart GWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  Smart GWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.data.AdvancedCriteria;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.OperatorId;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.ItemChangedEvent;
import com.smartgwt.client.widgets.form.events.ItemChangedHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class AnimeMashup2 implements EntryPoint {
	
	final TileGrid tileGrid = new TileGrid();
	
    public void onModuleLoad() {

        Canvas canvas = new Canvas();

        final AnimeDS animeList = AnimeDS.getInstance();
        animeList.setAutoCacheAllData(true);
        
        
        tileGrid.setTop(50);
        tileGrid.setWidth(960);
        tileGrid.setHeight(800);
        tileGrid.setTileWidth(150);
        tileGrid.setTileHeight(250);
        tileGrid.setCanReorderTiles(true);
        tileGrid.setShowAllRecords(false);
        tileGrid.setDataSource(animeList);



        DetailViewerField thumbnailField = new DetailViewerField("Thumbnail");
        thumbnailField.setType("image");
        DetailViewerField titleField = new DetailViewerField("title");
        DetailViewerField watchedStatusField = new DetailViewerField("watched_status");  
//        viewerWatchedStatus.setCellStyleHandler(new CellStyleHandler() {  
//            public String execute(Object value, DetailViewerField field, Record record) {  
//                if("completed".equals(value)) {  
//                    return "completed";  
//                } else if ("plan to watch".equals(value)) {  
//                    return "plantowatch";  
//                } else if ("watching".equals(value)) {  
//                    return "watching";  
//                } else if ("on-hold".equals(value)) {  
//                    return "onhold";                
//                } else if ("dropped".equals(value)) {  
//                        return "dropped";  
//                } else {  
//                    return null;  
//                }  
//            } 
//        });  
        tileGrid.setFields(thumbnailField, titleField, watchedStatusField);
                
        //tileGrid.getResultSet().setUseClientFiltering(true);
        tileGrid.fetchData();
        
        //tileGrid.sortByProperty("title", true);

        final DynamicForm form = new DynamicForm();
        form.setNumCols(10);
        
        final CheckboxItem watchingChk = new CheckboxItem();  
        watchingChk.setTitle("Watching");
        
        final CheckboxItem completedChk = new CheckboxItem();  
        completedChk.setTitle("Completed");
        
        final CheckboxItem planToWatchChk = new CheckboxItem();  
        planToWatchChk.setTitle("Plan to Watch");
        
        final CheckboxItem onholdChk = new CheckboxItem();  
        onholdChk.setTitle("On-Hold");
        
        final CheckboxItem droppedChk = new CheckboxItem();  
        droppedChk.setTitle("Dropped");
        
        form.addItemChangedHandler(new ItemChangedHandler() {  
        	public void onItemChanged(ItemChangedEvent event) {
        		Criteria c = new Criteria();
        		ArrayList<String> valuesAL = new ArrayList<String>();
        		String[] valuesArray;
        		//Check each CheckBox value and if true add to the ArrayList
	        	if (watchingChk.getValueAsBoolean()) valuesAL.add("watching");
	        	if (completedChk.getValueAsBoolean()) valuesAL.add("completed");
	        	if (planToWatchChk.getValueAsBoolean()) valuesAL.add("plan to watch");
	        	if (onholdChk.getValueAsBoolean()) valuesAL.add("on-hold");
	        	if (droppedChk.getValueAsBoolean()) valuesAL.add("dropped");
	        	//If any checkboxes are ticked then add Criteria
	        	//This is done to allow no checkboxes = show all data
	        	if (valuesAL.size() > 0) {
		        	valuesArray = new String[valuesAL.size()];
		        	valuesAL.toArray(valuesArray);
		        	c.addCriteria("watched_status", valuesArray);
	        	}
	        	//Send to Filter
	        	Filter(c);
	        }  
	    });  


        
        form.setItems(watchingChk, completedChk, planToWatchChk, onholdChk, droppedChk);
        canvas.addChild(form);
        canvas.addChild(tileGrid);
        canvas.draw();
    }

	public void Filter(Criteria c) {
		tileGrid.getResultSet().setCriteria(c);
		tileGrid.getResultSet().filterLocalData();
		//tg.redraw();
	}
}
