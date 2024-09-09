package ch.zhaw.swen1.forum.domain;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a topic for discussions.
 */
public class Topic {
    private String name;
    private String description;
    private final List<Discussion> discussions = new ArrayList<Discussion>();

    public Topic(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Discussion> getDiscussions() {
        return discussions;
    }

    /**
     * Returns the topic with the specified name or null if there is no such topic.
     */
    protected Discussion getDiscussionForName(String name) {
        for (Discussion discussion : discussions) {
            if (discussion.getName().equals(name)) {
                return discussion;
            }
        }
        return null;
    }

    /**
     * Add new discussion if it does not already exists
     * @param name Name for the Discussion to add
     * @throws DiscussionDuplicateException
     */
    protected void addDiscussion(String name) throws DiscussionDuplicateException {
        if (checkIfNoDuplicate(name)) {
            discussions.add(new Discussion(name));
        }
    }

    /**
     * Check for duplicate discussions
     * @param name Discussion name
     * @return true if no duplicate has been found, else false
     * @throws DiscussionDuplicateException
     */
    private boolean checkIfNoDuplicate(String name) throws DiscussionDuplicateException {
        if (getDiscussionForName(name) != null) {
            throw new DiscussionDuplicateException("Discussion with name \"" + name + "\" already exists.");
        } else {
            return true;
        }
    }

}