package in.jayanth.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jayanth.model.Records;

public interface RecordsRepository extends JpaRepository<Records, Serializable> {

}
