package com.example.platzi.movies.data;


import com.example.platzi.movies.model.Movie;

import java.util.Collection;

public interface MovieRepository {

    Movie findById(Integer id);
    Collection<Movie> findAll();
    void saveOrUpdate(Movie movie);
}
