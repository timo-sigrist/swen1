package ch.zhaw.swen1.forum.domain;

public class DiscussionDuplicateException extends Exception {
    public DiscussionDuplicateException(String errorMessage) {
        super(errorMessage);
    }
}
