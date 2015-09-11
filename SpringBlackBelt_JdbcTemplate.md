## [JdbcTemplate](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html) 3 questions ##
### State whether `JdbcTemplate` throws checked or unchecked exceptions ###
  * unchecked `DataAccessException`
### State whether you throw checked or unchecked exceptions in the callback methods ###
### Know when to use [SimpleJdbcTemplate](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/jdbc/core/simple/SimpleJdbcTemplate.html) and `JdbcTemplate` ###
  * `SimpleJdbcTemplate` class wraps the classic `JdbcTemplate` and leverages Java 5 language features such as varargs and autoboxing.
### Write code using `JdbcTemplate` to write select and update queries ###
### Write code using `JdbcTemplate` to pass parameters to a query ###
### Know when to use each of the following callbacks and write code using them: ###
  * `RowMapper`
    * Usage: get an object
```
Username username = jdbcTemplate.queryForObject(
 "select username, password, password_reminder, interim_name, person_id, user_type from username where username = ?",
 new Object[]{usernameName},
 new UsernameRowMapper()
);		
return username;

```
    * `RowMapper`
```
public class UsernameRowMapper implements RowMapper<Username> {

	@Override
    public Username mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Username user = new Username();
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setPersonId(rs.getString("person_id"));
        user.setInterimName(rs.getString("interim_name"));
        user.setUserType(rs.getString("user_type"));
        user.setPasswordReminder(rs.getString("password_reminder"));
        return user;
    }

}
```
  * `RowCallbackHandler`
```
	private List<Username> getUsernameObjectsWithRollCallbackHandler() {
		final List<Username> u = new ArrayList<Username>();
		String sql = "select username, password, password_reminder, interim_name, person_id, user_type from username ";
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
		    	Username user = new Username();
		        user.setUsername(rs.getString("username"));
		        user.setPassword(rs.getString("password"));
		        user.setPersonId(rs.getString("person_id"));
		        user.setInterimName(rs.getString("interim_name"));
		        user.setUserType(rs.getString("user_type"));
		        user.setPasswordReminder(rs.getString("password_reminder"));
				u.add(user);
			}
		});
		return u;
	}

```
  * `ResultSetExtractor`
    * Usage:
```
	private List<Username> getUsernameObjectsWithResultSetExtractor() {
		String sql = "select username, password, password_reminder, interim_name, person_id, user_type from username ";
		return jdbcTemplate.query(sql, new UsernameResultSetExtractor());
		
	}

```
    * Implementation:
```

public class UsernameResultSetExtractor implements ResultSetExtractor<List<Username>> {

	@Override
	public List<Username> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		List<Username> users = new ArrayList<Username>();
		while(rs.next()) {
			Username user = new Username();
	        user.setUsername(rs.getString("username"));
	        user.setPassword(rs.getString("password"));
	        user.setPersonId(rs.getString("person_id"));
	        user.setInterimName(rs.getString("interim_name"));
	        user.setUserType(rs.getString("user_type"));
	        user.setPasswordReminder(rs.getString("password_reminder"));
	        users.add(user);
		}
		return users;
	}

}
```