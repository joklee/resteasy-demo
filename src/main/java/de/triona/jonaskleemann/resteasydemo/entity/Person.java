package de.triona.jonaskleemann.resteasydemo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.resteasy.links.RESTServiceDiscovery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@XmlRootElement
@Data
@EqualsAndHashCode(callSuper = true, of = {})
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@NamedQueries({ @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p") })
public class Person extends BaseEntity {

	@XmlElement
	String firstname;

	@XmlElement
	String lastname;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "person_parents")
	@XmlTransient
	@OrderBy(value = "id")
	Set<Person> parentList = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "person_children")
	@XmlTransient
	@OrderBy(value = "id")
	Set<Person> childrenList = new HashSet<>();

	public void addChildren(Person children) {
		childrenList.add(children);
		children.getParentList().add(this);
	}

	public Person(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	@XmlElementRef
	@Transient
	RESTServiceDiscovery rest;

}
