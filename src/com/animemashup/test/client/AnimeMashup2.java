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

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class AnimeMashup2 implements EntryPoint {
	
	final TileGrid tileGrid = new TileGrid();
	
    public void onModuleLoad() {

        Canvas canvas = new Canvas();

        final AnimeDS animeList = AnimeDS.getInstance();
        animeList.setAutoCacheAllData(true);
        
        
        tileGrid.setTop(130);
        tileGrid.setWidth(960);
        tileGrid.setHeight(800);
        tileGrid.setTileWidth(150);
        tileGrid.setTileHeight(250);
        tileGrid.setCanReorderTiles(true);
        tileGrid.setShowAllRecords(true);
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
        
        //tileGrid.sortByProperty("watched_status", true);

        final DynamicForm form = new DynamicForm();

        ButtonItem watchingBtn = new ButtonItem();
        watchingBtn.setTitle("Watching");
        watchingBtn.setStartRow(false);
        watchingBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Filter(new Criteria("watched_status", "watching"));
            }
        });
        
        ButtonItem completedBtn = new ButtonItem();
        completedBtn.setTitle("Completed");
        completedBtn.setStartRow(false);
        completedBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Filter(new Criteria("watched_status", "completed"));
            }
        });
        
        ButtonItem planToWatchBtn = new ButtonItem();
        planToWatchBtn.setTitle("Plan to Watch");
        planToWatchBtn.setStartRow(false);
        planToWatchBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Filter(new Criteria("watched_status", "plan to watch"));
            }
        });
        
        ButtonItem onHoldBtn = new ButtonItem();
        onHoldBtn.setTitle("On Hold");
        onHoldBtn.setStartRow(false);
        onHoldBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Filter(new Criteria("watched_status", "on-hold"));
            }
        });     
        
        ButtonItem droppedBtn = new ButtonItem();
        droppedBtn.setTitle("dropped");
        droppedBtn.setStartRow(false);
        droppedBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Filter(new Criteria("watched_status", "Dropped"));
            }
        });
        
        form.setItems(watchingBtn, completedBtn, planToWatchBtn, onHoldBtn, droppedBtn);
        
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
