package com.calorietracker.dto;

import java.util.List;

public class ClaudeResponse {

    private List<Content> content;

    public List<Content> getContent(){
        return content;
    }

    public void setContent(List<Content> content){
        this.content = content;
    }

    public static class Content{
        private String type;
        private String text;

        public String getType(){
            return type;
        }

        public void setType(String type){
            this.type = type;
        }

        public String getText(){
            return text;
        }
        public void setText(String text){
            this.text = text;
        }
    }
}
