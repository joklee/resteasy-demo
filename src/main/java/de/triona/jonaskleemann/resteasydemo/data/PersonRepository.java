package de.triona.jonaskleemann.resteasydemo.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.triona.jonaskleemann.resteasydemo.entity.Person;

@RequestScoped
public class PersonRepository {

	@Inject
	EntityManager entityManager;

	public List<Person> getPersonList() {
		return entityManager.createNamedQuery("Person.findAll", Person.class).getResultList();
	}

	@Transactional
	public Person save(Person person) {
		return entityManager.merge(person);
	}

	@Transactional
	public void init() {

		Person bart = save(new Person("Bart", "Simpson"));
		Person lisa = save(new Person("Lisa", "Simpson"));
		Person maggie = save(new Person("Maggie", "Simpson"));
		Person ling = save(new Person("Ling", "Bouvier"));

		Person homer = save(new Person("Homer Jay", "Simpson"));
		Person marge = save(new Person("Marge", "Simpson"));

		Person selam = save(new Person("Selma", "Bouvier"));
		Person patty = save(new Person("Patty", "Bouvier"));

		Person abraham = save(new Person("Abraham J.", "Simpson"));
		Person mona = save(new Person("Mona", "Simpson"));

		Person jacquline = save(new Person("Jacquline","Bouvier"));
		Person clancy = save(new Person("Clancy","Bouvier"));

		abraham.addChildren(homer);
		mona.addChildren(homer);

		jacquline.addChildren(marge);
		jacquline.addChildren(selam);
		jacquline.addChildren(patty);
		clancy.addChildren(marge);
		clancy.addChildren(selam);
		clancy.addChildren(patty);

		homer.addChildren(bart);
		homer.addChildren(lisa);
		homer.addChildren(maggie);

		marge.addChildren(bart);
		marge.addChildren(lisa);
		maggie.addChildren(maggie);

		selam.addChildren(ling);

	}

	public Person findBy(Long id) {
		return entityManager.find(Person.class, id);
	}
}
