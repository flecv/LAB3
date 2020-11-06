package com.example.demo.Services.Classes.Repository;

import com.example.demo.Services.Classes.DirectorClass.Director;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepo extends CrudRepository<Director, String>
{
    Director getDirectorByName(String id);
}
