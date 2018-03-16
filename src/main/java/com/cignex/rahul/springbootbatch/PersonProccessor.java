package com.cignex.rahul.springbootbatch;

import org.springframework.batch.item.ItemProcessor;


public class PersonProccessor implements ItemProcessor<Person, Person> {

	@Override
	public Person process(Person person) throws Exception {

		Person transformedPerson = new Person(person.getFirstName(), person.getLastName());

		System.out.println("TransformedPerson ===>  " + transformedPerson);

		return transformedPerson;
	}

}
