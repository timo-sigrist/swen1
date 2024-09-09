package ch.zhaw.swen1.forum.domain;

import javax.naming.AuthenticationException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the whole forum with its list of topics and users
 */
public class Forum {
    private final Clock clock;
    private final List<User> users = new ArrayList<>();
    private final List<Topic> topics = new ArrayList<>();

    public Forum(Clock clock) {
        super();
        this.clock = clock;
    }

    /**
     * Returns the list of users. Just for tests.
     *
     * @return
     */
    protected List<User> getUsers() {
        return users;
    }

    /**
     * Returns the list of topics. Just for tests.
     *
     * @return
     */
    protected List<Topic> getTopics() {
        return topics;
    }

    /**
     * Returns the topic with the specified name or null if there is no such topic.
     */
    protected Topic getTopicForName(String name) {
        for (Topic topic : topics) {
            if (topic.getName().equals(name)) {
                return topic;
            }
        }
        return null;
    }

    protected User getUserForName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @return total contributions in the whole Forum
     */
    protected int getNbrOfContributions() {
        int totalContributions = 0;
        for (Topic topic : getTopics()) {
            for (Discussion dicussion : topic.getDiscussions()) {
                totalContributions += dicussion.getContributions().size();
            }
        }
        return totalContributions;
    }

    /**
     * Add discussion to topic with given user credentials
     *
     * @param userName       User who owns the discussion
     * @param passwordHash   Pass for given user
     * @param topicName      Name of the topic
     * @param discussionName Name of the new discussion
     * @throws DiscussionDuplicateException When discussion already exists
     * @throws AuthenticationException      When user credentials couldn't be verified
     */
    protected void addNewDiscussion(String userName, byte[] passwordHash,
                                    String topicName, String discussionName)
            throws DiscussionDuplicateException, AuthenticationException {
        if (getUserForName(userName).testPassword(passwordHash)) {
            getTopicForName(topicName).addDiscussion(discussionName);
        } else {
            throw new AuthenticationException("Couldn't authenticate user \"" + userName + "\" with given credentials.");
        }
    }

}

