module pk.edu.pl.productdesktopapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires static lombok;
    requires spring.core;
    requires spring.context;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.data.commons;
    requires spring.webmvc;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens pk.edu.pl.productdesktopapp to spring.core;
    opens pk.edu.pl.productdesktopapp.view.product;
    exports pk.edu.pl.productdesktopapp;
    opens pk.edu.pl.productdesktopapp.config;
    opens pk.edu.pl.productdesktopapp.model.product;

    exports pk.edu.pl.productdesktopapp.service;
    exports pk.edu.pl.productdesktopapp.model.factories;
    exports pk.edu.pl.productdesktopapp.viewmodel;
    exports pk.edu.pl.productdesktopapp.view.product;
    exports pk.edu.pl.productdesktopapp.view;
    exports pk.edu.pl.productdesktopapp.core;
    exports pk.edu.pl.productdesktopapp.model.menu;
    exports pk.edu.pl.productdesktopapp.model.product;
    exports pk.edu.pl.productdesktopapp.model.history;
    exports pk.edu.pl.productdesktopapp.service.product;
    exports pk.edu.pl.productdesktopapp.view.menu;
    exports pk.edu.pl.productdesktopapp.view.mode;
//    opens pk.edu.pl.productdesktopapp.view.menu;
    exports pk.edu.pl.productdesktopapp.viewmodel.menu;
    exports pk.edu.pl.productdesktopapp.viewmodel.product;
}










