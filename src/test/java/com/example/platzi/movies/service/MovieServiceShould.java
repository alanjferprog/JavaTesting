package com.example.platzi.movies.service;

import com.example.platzi.movies.data.MovieRepository;
import com.example.platzi.movies.model.Genre;
import com.example.platzi.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovieServiceShould
{
    private MovieRepository movieRepository;
    private MovieService movieService;

    @BeforeEach
    void setUp()
    {
        movieRepository = Mockito.mock(MovieRepository.class);

        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION),
                        new Movie(2, "Memento", 113, Genre.THRILLER),
                        new Movie(3, "There's Something About Marty", 119, Genre.COMEDY),
                        new Movie(4, "Super 8", 112, Genre.THRILLER),
                        new Movie(5, "Scream", 111, Genre.HORROR),
                        new Movie(6, "Home Alone", 103, Genre.COMEDY),
                        new Movie(7, "Matrix", 136, Genre.ACTION),
                        new Movie(8, "Superman", 126, Genre.ACTION),
                        new Movie(9, "Super red", 156, Genre.THRILLER)
                )
        );

        movieService = new MovieService(movieRepository);
    }

    @Test
    void return_movies_by_genre()
    {
        Collection<Movie> movies = movieService.findMoviesByGenre(Genre.COMEDY);

        List<Integer> moviesIds = movies.stream().map(movie -> movie.getId()).collect(Collectors.toList());

        assertThat( moviesIds, is(Arrays.asList(3,6)));
    }

    @Test
    void return_movies_by_name()
    {
        Collection<Movie> movies = movieService.findMoviesByName("super");

        List<Integer> moviesIds = movies.stream().map(movie -> movie.getId()).collect(Collectors.toList());

        assertThat( moviesIds, is(Arrays.asList(4,8,9)));
    }

    @Test
    void return_movies_by_length()
    {

        Collection<Movie> movies = movieService.findMoviesByLength(120);

        List<Integer> moviesIds = movies.stream().map(movie -> movie.getId()).collect(Collectors.toList());

        assertThat( moviesIds, is(Arrays.asList(2,3,4,5,6)));
    }

    @Test
    public void exception_by_negative_length(){

           assertThrows(IllegalArgumentException.class, () -> movieService.findMoviesByLength(-1));
    }


    @Test
    void return_movie_by_minutes_and_genre()
    {
        Movie movieSelected = new Movie("", 120, Genre.THRILLER);

        Collection<Movie> movies = movieService.findMoviesByTemplate(movieSelected);

        List<Integer> moviesIds = movies.stream().map(movie -> movie.getId()).collect(Collectors.toList());

        assertThat( moviesIds, is(Arrays.asList(2,4)));
    }

    @Test
    void return_movie_by_name_and_minutes()
    {
        Movie movieSelected = new Movie("Super", 126, null);

        Collection<Movie> movies = movieService.findMoviesByTemplate(movieSelected);

        List<Integer> moviesIds = movies.stream().map(movie -> movie.getId()).collect(Collectors.toList());

        assertThat( moviesIds, is(Arrays.asList(4,8)));
    }

    @Test
    void return_movie_by_name_and_genre()
    {
        Movie movieSelected = new Movie("Super", null, Genre.THRILLER);

        Collection<Movie> movies = movieService.findMoviesByTemplate(movieSelected);

        List<Integer> moviesIds = movies.stream().map(movie -> movie.getId()).collect(Collectors.toList());

        assertThat( moviesIds, is(Arrays.asList(4,9)));
    }

    @Test
    void return_movie_by_id()
    {
        Movie movie = movieService.findMovieById(5);

        assertThat( movie, is(new Movie(5, "Scream", 111, Genre.HORROR)));
    }
}