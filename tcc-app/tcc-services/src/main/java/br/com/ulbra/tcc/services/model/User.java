package br.com.ulbra.tcc.services.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The User Entity
 * 
 * @author Paulo Pacheco
 *
 */
@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id", nullable = false)
	private BigDecimal userId;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="email")
	private String email;

	public User() {
	}

	/**
	 * Gets the userId attribute
	 *
	 * @return the userId
	 */
	public BigDecimal getUserId() {
		return userId;
	}

	/**
	 * Sets the userId attribute
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	/**
	 * Gets the name attribute
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name attribute
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email attribute
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email attribute
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}