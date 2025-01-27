package ch.zhaw.swen1.forum.domain;

import org.junit.Before;
import org.junit.Test;

import javax.naming.AuthenticationException;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.Assert.*;

/**
 * Combined tests for forum domain logic. Because domain classes are quite simple,
 * only integration tests have been written.
 */
public class ForumTest {
    private Forum forum;
    private User user;
    private Topic topic1;
    private Discussion discussion1;
    private Contribution contribution1;
    private Instant reference;

    @Before
    public void setUp() throws Exception {
        reference = Instant.ofEpochSecond(1000000);
        // use a clock which returns always the same time, so testing is easy
        forum = new Forum(Clock.fixed(reference, ZoneId.systemDefault()));
        // user
        user = new User("User", new byte[]{0});
        forum.getUsers().add(user);
        // topic
        topic1 = new Topic("Topic1", "Description Topic1");
        forum.getTopics().add(topic1);
        // discussion
        discussion1 = new Discussion("Discussion1");
        forum.getTopicForName("Topic1").getDiscussions().add(discussion1);
        // contribution
        contribution1 = new Contribution("Contribution1 content", user, Instant.ofEpochSecond(1000000));
        forum.getTopicForName("Topic1").getDiscussionForName("Discussion1").getContributions().add(contribution1);
    }

    @Test
    public void userTest() {
        byte[] password = new byte[]{0};
        String userName = "User";
        User user = forum.getUserForName(userName);

        assertEquals(1, forum.getUsers().size());
        assertTrue(user.testPassword(password));
    }

    @Test
    public void topicsTest() {
        assertEquals(1, forum.getTopics().size());
        assertEquals(topic1.getName(), forum.getTopicForName("Topic1").getName());
    }

    @Test
    public void discussionsTest() {
        assertEquals(1, forum.getTopicForName("Topic1").getDiscussions().size());
        assertEquals(discussion1.getName(), forum.getTopicForName("Topic1").getDiscussionForName("Discussion1").getName());
    }

    @Test
    public void contributionsTest() {
        assertEquals(1, forum.getTopicForName("Topic1").getDiscussionForName("Discussion1").getContributions().size());
    }

    @Test
    public void nbrOfContributionsTest() {
        assertEquals(1, forum.getNbrOfContributions());
    }

    @Test
    public void addNewDiscussionSuccessTest() {
        String newDiscussionName = "Discussion2";
        byte[] userPass = new byte[]{0};
        try {
            forum.addNewDiscussion(user.getName(), userPass, "Topic1", newDiscussionName);
        } catch (DiscussionDuplicateException | AuthenticationException e) {
            fail("Exception has been thrown.");
        }
    }

    @Test(expected = DiscussionDuplicateException.class)
    public void addNewDiscussionFailTest() throws AuthenticationException, DiscussionDuplicateException {
        String newDiscussionName = "Discussion2";
        byte[] userPass = new byte[]{0};
        forum.addNewDiscussion(user.getName(), userPass, "Topic1", newDiscussionName);
        forum.addNewDiscussion(user.getName(), userPass, "Topic1", newDiscussionName);
    }

    @Test(expected = AuthenticationException.class)
    public void addNewDiscussionUserFailTest() throws AuthenticationException, DiscussionDuplicateException {
        String newDiscussionName = "Discussion2";
        byte[] userPass = new byte[]{5};
        forum.addNewDiscussion(user.getName(), userPass, "Topic1", newDiscussionName);
    }
}