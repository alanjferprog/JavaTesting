package com.example.platzi.movies.data;

import com.example.platzi.movies.model.Movie;
import com.example.platzi.movies.model.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class MovieRepositoryIntegrationTest 
{
    MovieRepositoryJdbc movieRepositoryJdbc;
    DriverManagerDataSource dataSource;


    @BeforeEach
    void setUp() throws SQLException {
        //  Conectando a base de datos en memoria (Gracias a la libreria h2)
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");
        //  Ejecuto un script para crear la tabla de peliculas
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));
        //  Creo el jdb template que necesita el repositorio para conectarse a la base de datos
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        movieRepositoryJdbc = new MovieRepositoryJdbc(jdbcTemplate);

    }

    @Test
    void load_all_movies() throws SQLException {

        //  Recupero las peliculas
        Collection<Movie> allMovies = movieRepositoryJdbc.findAll();

        assertThat(allMovies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION) ,
                new Movie(2, "Memento", 113, Genre.THRILLER) ,
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }

    @Test
    void load_movie_by_id()
    {
        Movie movie = movieRepositoryJdbc.findById(2);

        assertThat(movie, is(new Movie(2, "Memento", 113, Genre.THRILLER)));
    }

    @Test
    void save_or_update()
    {

        Movie movie= new Movie("Super 8", 112, Genre.THRILLER);
        movieRepositoryJdbc.saveOrUpdate(movie);
        Movie movieExpected = movieRepositoryJdbc.findById(4);

        assertThat(movieExpected, is(new Movie(4, "Super 8", 112, Genre.THRILLER)));
    }

    @AfterEach
    void tearDown() throws SQLException {

        //  Luego de ejecutar cada test, borro los datos de la base de datos en memoria.
        final Statement s= dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files");
    }
}