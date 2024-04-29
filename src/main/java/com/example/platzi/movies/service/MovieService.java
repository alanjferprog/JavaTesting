package com.example.platzi.movies.service;

import com.example.platzi.movies.data.MovieRepository;
import com.example.platzi.movies.model.Genre;
import com.example.platzi.movies.model.Movie;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie findMovieById(Integer id)
    {
        Movie movieById = movieRepository.findById(id);

        return movieById;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre)
    {
        Collection<Movie> moviesByGenre = movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());

        return moviesByGenre;
    }

    public Collection<Movie> findMoviesByLength(Integer time)
    {
        if(time < 0)
            throw new IllegalArgumentException("El tiempo negativo o zero no es valido.") ;

        Collection<Movie> moviesByTime = movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <= time).collect(Collectors.toList());

        return moviesByTime;
    }

    public Collection<Movie> findMoviesByName(String name)
    {
        String movieName= name.toLowerCase();

        Collection<Movie> moviesByName = movieRepository.findAll().stream()
                .filter(movie -> movie.getName().toLowerCase().contains(movieName)).collect(Collectors.toList());

        return moviesByName;
    }

    public Collection<Movie> findMoviesByTemplate(Movie movieSelected)
    {
        Collection<Movie> moviesToReturn = null;
        Integer id= movieSelected.getId();

        if (id != null ) {
            moviesToReturn.add(movieSelected); }
        else {
            moviesToReturn = movieRepository.findAll();

            String nameMovie = movieSelected.getName().toLowerCase();
            if (nameMovie != null) {
                moviesToReturn = moviesToReturn.stream()
                        .filter(movie -> movie.getName().toLowerCase().contains(nameMovie)).collect(Collectors.toList());
            }

            Integer time = movieSelected.getMinutes();
            if (time != null) {
                moviesToReturn = moviesToReturn.stream()
                        .filter(movie -> movie.getMinutes() <= time).collect(Collectors.toList());
            }

            Genre genre = movieSelected.getGenre();
            if (genre != null) {
                moviesToReturn = moviesToReturn.stream()
                        .filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());
            }
        }

        return moviesToReturn;
    }
}