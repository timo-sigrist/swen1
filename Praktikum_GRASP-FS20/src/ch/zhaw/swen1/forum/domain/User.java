package ch.zhaw.swen1.forum.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a user that participates in this forum.
 *
 */
public class User {
	private final String name;
	private byte[] passwordHash;
	private final List<Contribution> contributions = new ArrayList<Contribution>();
	
	public User(String name, byte[] passwordHash) {
		super();
		this.name = name;
		this.passwordHash = passwordHash;
	}

	public String getName() {
		return name;
	}

	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	public boolean testPassword(byte[] hash){
		return Arrays.equals(passwordHash, hash);
	}

	public List<Contribution> getContributions() {
		return contributions;
	}
	
	public void addContribution(Contribution contribution){
		contributions.add(contribution);
	}
	
	public int getNbrOfContributions() {
		return contributions.size();
	}
}