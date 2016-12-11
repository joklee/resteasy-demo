package de.triona.jonaskleemann.resteasydemo.data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author jkleemann
 *
 */
@ApplicationScoped
public class EntityManagerProducer {

	@PersistenceContext
	private EntityManager em;

	@Produces
	public EntityManager em() {
		return em;
	}

}
