package com.cignex.rahul.springbootbatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private JdbcTemplate jdbcTemplate;

	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {

		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

			List<Person> result = jdbcTemplate.query("SELECT first_name, last_name FROM people",
					new RowMapper<Person>() {

						@Override
						public Person mapRow(ResultSet arg0, int arg1) throws SQLException {
							return new Person(arg0.getString(1), arg0.getString(2));
						}
					});

			for (Person person : result) {
				System.out.println("Person: " + person);
			}
		}

	}

}
