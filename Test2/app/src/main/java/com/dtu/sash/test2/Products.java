package com.dtu.sash.test2;

/**
 * Created by hp pc on 24-01-2016.
 */
public class Products {
    private int _id;
    private String _name;
    private String _pno;
    private String _entity;
    private String _region;

    public Products(int id,String name, String pno,String entity, String region) {
        this._id=id;
        this._name = name;
        this._pno=pno;
        this._entity = entity;
        this._region = region;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public void set_pno(String pno){this._pno=pno;}
    public void set_entity(String entity) {
        this._entity = entity;
    }

    public void set_rate(String region) {
        this._region = region;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_pno() {
        return _pno;
    }

    public String get_region() {
        return _region;
    }

    public String get_entity() {
        return _entity;
    }
}
