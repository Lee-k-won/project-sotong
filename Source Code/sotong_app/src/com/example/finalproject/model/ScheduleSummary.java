package com.example.finalproject.model;

public class ScheduleSummary{ 
   private String imageUri;
   private String schedule;
   public ScheduleSummary() {
   
   } 
   public ScheduleSummary(String schedule) {
      
      this.schedule = schedule;
   }
   public ScheduleSummary(String imageUri, String schedule) {
      super();
      this.imageUri = imageUri;
      this.schedule = schedule;
   }
   public String getImageUri() {
      return imageUri;
   }
   public void setImageUri(String imageUri) {
      this.imageUri = imageUri;
   }
   public String getSchedule() {
      return schedule;
   }
   public void setSchedule(String schedule) {
      this.schedule = schedule;
   } 
}
