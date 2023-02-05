package com.til_generator.springboot_generate_til_using_chatgpt.dto;



public class BoardResponse {

    private int record_Id;
    private String record_Title;
    private String record_Author;
    private String record_Date;
    private int record_Views;

    public BoardResponse(int record_Id, String record_Title, String record_Author, String record_Date, int record_Views) {
        this.record_Id = record_Id;
        this.record_Title = record_Title;
        this.record_Author = record_Author;
        this.record_Date = record_Date;
        this.record_Views = record_Views;
    }

    public BoardResponse() {
    }

    public int getRecord_Id() {
        return record_Id;
    }

    public void setRecord_Id(int record_Id) {
        this.record_Id = record_Id;
    }

    public String getRecord_Title() {
        return record_Title;
    }

    public void setRecord_Title(String record_Title) {
        this.record_Title = record_Title;
    }

    public String getRecord_Author() {
        return record_Author;
    }

    public void setRecord_Author(String record_Author) {
        this.record_Author = record_Author;
    }

    public String getRecord_Date() {
        return record_Date;
    }

    public void setRecord_Date(String record_Date) {
        this.record_Date = record_Date;
    }

    public int getRecord_Views() {
        return record_Views;
    }

    public void setRecord_Views(int record_Views) {
        this.record_Views = record_Views;
    }
}
