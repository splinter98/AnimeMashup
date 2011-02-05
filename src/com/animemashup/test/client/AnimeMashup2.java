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
	
    public void onModuleLoad() {

        Canvas canvas = new Canvas();

        final AnimeDS animeList = AnimeDS.getInstance();

        final TileGrid tileGrid = new TileGrid();
        tileGrid.setTop(120);
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
        tileGrid.fetchData();
        tileGrid.sortByProperty("watched_status", true);

        final DynamicForm form = new DynamicForm();

        ButtonItem button = new ButtonItem();
        button.setTitle("Load");
        button.setStartRow(false);
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                tileGrid.fetchData(new Criteria("watched_status", "completed"));
            }
        });

        form.setItems(button);
        
        canvas.addChild(form);
        canvas.addChild(tileGrid);
        canvas.draw();
    }

}
