package de.triona.jonaskleemann.resteasydemo.rest;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;
import org.jboss.resteasy.links.LinkResources;

import com.arjuna.ats.jta.exceptions.NotImplementedException;

import de.triona.jonaskleemann.resteasydemo.data.PersonRepository;
import de.triona.jonaskleemann.resteasydemo.entity.Person;

@ApplicationScoped
@Path("/person")
public class PersonRestService {

	@Inject
	PersonRepository personRepository;

	@PostConstruct
	private void init(){
		personRepository.init();
	}
	
	@AddLinks
	@LinkResources({ @LinkResource(value = Person.class) })
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Person> getPersonList() {
		return personRepository.getPersonList();
	}

	@AddLinks
	@LinkResources({ @LinkResource(value = Person.class) })
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Person getPerson(@PathParam("id") Long id) {
		return personRepository.findBy(id);
	}

	@AddLinks
	@LinkResources({ @LinkResource(value = Person.class, rel = "children") })
	@Path("/{id}/children")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Set<Person> getChildren(@PathParam("id") Long id) {
		return personRepository.findBy(id).getChildrenList();
	}

	@AddLinks
	@LinkResources({ @LinkResource(value = Person.class, rel = "parents") })
	@Path("/{id}/parents")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Set<Person> getParents(@PathParam("id") Long id) {
		return personRepository.findBy(id).getParentList();
	}

	@AddLinks
	@LinkResources({ @LinkResource(value = Person.class, rel = "delete") })
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Person deletePerson(@PathParam("id") Long id) throws NotImplementedException {
		throw new NotImplementedException("Not implement");
	}
}
