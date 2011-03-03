package com.jgk.springrecipes.mvc.release.service.impl;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jgk.springrecipes.mvc.release.domain.ReleaseUser;
import com.jgk.springrecipes.mvc.release.service.ProjectService;
import com.jgk.springrecipes.mvc.release.service.ReleaseService;
import com.jgk.springrecipes.mvc.release.service.ReleaseUserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/com/jgk/springrecipes/mvc/release/service/impl/ProjectReleaseServicesIntegrationTest-config.xml")
public class ProjectReleaseServicesIntegrationTest {
	
	@Inject ReleaseService releaseService;
	@Inject ReleaseUserService releaseUserService;
	@Inject ProjectService projectService;
	
	@Before
	public void populateDatabase() {
		checkAllThere();
		System.out.println("Populate Databases");
		ReleaseUser user = ReleaseUser.createUser("fakeuser", "fakepassword", Boolean.TRUE);
		releaseUserService.saveReleaseUser(user);
	}
	public void checkAllThere() {
		assertNotNull(releaseService);
		assertNotNull(releaseUserService);
		assertNotNull(projectService);
	}
	@Test
	public void basic() {
//		System.out.println(releaseService);
		assertEquals(1,releaseUserService.findAllReleaseUsers().size());
		
	}

}
