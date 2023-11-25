package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Bug;
import org.acme.repositories.BugRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/api/bugs")
@Transactional
public class BugResource {

    @Inject
    BugRepository bugRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bug> getBugs() {
        return bugRepository
                .listAll();
    }

    @Transactional
    @POST
    public Bug insert(Bug bug) {
        bugRepository.persist(bug);
        return bug;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bug getById(@PathParam("id") Long id) {
        return bugRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("No existe bug con el ID " + id));
    }

    @DELETE
    @Path("/{id}")
    public String deleteById(@PathParam("id") Long id) {
        return (bugRepository.deleteById(id))
                ? "El bug con ID " + id + " se ha borrado exitosamente"
                : "No se ha encontrado el bug con ID " + id;
    }

    @PUT
    @Path("/{id}")
    public Bug updateBug(@PathParam("id") Long id, Bug bug) {
        var updatedBug = bugRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("No existe bug con el ID " + id));

        updatedBug.setClosedAt(bug.getClosedAt());
        updatedBug.setDescription(bug.getDescription());
        updatedBug.setFixed(bug.getFixed());
        updatedBug.setTitle(bug.getTitle());
        bugRepository.persist(updatedBug);

        return updatedBug;
    }
}
