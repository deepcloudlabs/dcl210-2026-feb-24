package com.example.exercises;

import java.util.Comparator;
import java.util.Objects;

import com.example.dao.CityDao;
import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise4 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();
	private static final CityDao cityDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the highest populated capital city
        countryDao.findAllCountries()   // Collection<Country> 
                  .stream()             // Stream<Country>
                  .map(Country::getCapital) // Stream<Integer>
                  //.map(cityId -> cityDao.findCityById(cityId)) // Stream<City>
                  .map(cityDao::findCityById) // Stream<City>
                  //.filter(city -> Objects.nonNull(city)) // Stream<City>
                  //.filter(city -> city != null) // Stream<City>
                  .filter(Objects::nonNull) // Stream<City>
                  .max(Comparator.comparing(City::getPopulation))
                  //.ifPresent( capital -> System.out.println(capital));
                  .ifPresent( System.out::println);
	}

}
