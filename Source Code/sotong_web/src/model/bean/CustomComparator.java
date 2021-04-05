package model.bean;

import java.util.Comparator;

import dao.story.StoryViewVO;

public class CustomComparator implements Comparator<StoryViewVO> {
   public int compare(StoryViewVO o1, StoryViewVO o2) {
        return o2.getStoryModifyDate().compareTo(o1.getStoryModifyDate());
    }
}