package com.cdx.cdxlearningmaterials.repository;

import com.cdx.cdxlearningmaterials.repository.dao.Pos;
import com.cdx.cdxlearningmaterials.repository.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosRepository extends JpaRepository<Pos, Long> {
    List<Pos> findAllByName(String name);

}
