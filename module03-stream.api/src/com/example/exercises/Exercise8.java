package com.example.exercises;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@SuppressWarnings("unused")
public class Exercise8 {
	private static final MovieService movieService = InMemoryMovieService.getInstance();
	private static final Predicate<Movie> MOVIE_AFTER_70 = movie -> movie.getYear() >= 1970;
	private static final Predicate<Movie> MOVIE_BEFORE_80 = movie -> movie.getYear() < 1980;
	private static final Predicate<Movie> MOVIE_IN_70 = movie -> movie.getYear() >= 1970 && movie.getYear() < 1980;
	
	public static void main(String[] args) {
		// Group the movies by the year in 70s and list them
		Collection<Movie> movies = movieService.findAllMovies();
		Map<Integer,List<Movie>> moviesByYear = 
		movies.stream()
		      //.filter(MOVIE_AFTER_70.and(MOVIE_BEFORE_80))
		      .filter(MOVIE_IN_70)
		      .collect(Collectors.groupingBy(Movie::getYear));
		moviesByYear.forEach((year,moviesThatYear)->{
			System.err.println(year);
			moviesThatYear.forEach(System.err::println);
		});
	}

}
