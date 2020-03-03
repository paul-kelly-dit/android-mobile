package com.example.myapplication;

public class Task
{
    int _id;  
    String _name;  
    String _description;
    String _status;

    public Task(){   }

    public Task(String name, String description, String status){
        this._name = name;
        this._description= _description;
        this._status= status;
    }
  

    public int getID(){  
        return this._id;  
    }  
  
    public void setID(int id){  
        this._id = id;  
    }

    public void setDescription(String description)
    {
        this._description = description;

    }

    public void setStatus(String status)
    {
        this._status = status;

    }

    public String getName()
    {

        return this._name;  
    }

    public String getDescription()
    {

        return this._description;
    }

    public String getStatus()
    {

        return this._status;
    }

    public void setName(String name){  
        this._name = name;  
    }  
  
    }