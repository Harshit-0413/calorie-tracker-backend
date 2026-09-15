package com.calorietracker.dto;

import java.util.List;

public class ClaudeRequest {


    private String model;
    private int max_tokens;
    private List<ClaudeMessage> messages;

    public ClaudeRequest(){

    }
    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model= model;
    }
    public int getMax_tokens(){
        return max_tokens;
    }
    public void setMax_tokens(int max_tokens){
        this.max_tokens = max_tokens;
    }
    public List<ClaudeMessage> getMessages(){
        return messages;
    }

    public void setMessages(List<ClaudeMessage> messages){
        this.messages = messages;
    }
}
