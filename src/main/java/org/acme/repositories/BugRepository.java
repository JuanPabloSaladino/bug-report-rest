package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Bug;

@ApplicationScoped
public class BugRepository implements PanacheRepository<Bug> {
}
