package com.tradingcards.elements.binder;

import com.tradingcards.elements.collection.CollectionModel;

public class BinderController {

    private CollectionModel sharedCollection;
    private BinderModel model;
    private BinderView view;

    public BinderController(CollectionModel sharedCollection, BinderModel model, BinderView view) {
        this.sharedCollection = sharedCollection;
        this.model = model;
        this.view = view;
    }

    public void addBinder() {
        BinderModel binder = new BinderModel();

        String name = view.setBinderName();

        sharedCollection.setBinderCollection(binder, name);
    }
}
