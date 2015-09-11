# Acegi #
## web.xml ##
```
  <!-- Define Acegi Filter Chain Proxy -->
  <filter>      
	<filter-name>Acegi Filter Chain Proxy</filter-name>       
		<filter-class>org.acegisecurity.util.FilterToBeanProxy</filter-class>       
		<init-param>          
			<param-name>targetClass</param-name>          
			<param-value>org.acegisecurity.util.FilterChainProxy</param-value>      
		</init-param>  
  </filter>  
  
  <!-- Filter Acegi servlet -->
  <filter-mapping>    
	<filter-name>Acegi Filter Chain Proxy</filter-name>    
	<url-pattern>/*</url-pattern>  
  </filter-mapping> 

```
## Config ##
```
<!-- Acegi bean definitions begin --> 
	<bean id="filterChainProxy"
		class="org.acegisecurity.util.FilterChainProxy"> 
		<property name="filterInvocationDefinitionSource"> 
			<value> 
				CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
				PATTERN_TYPE_APACHE_ANT
				/**=httpSessionContextIntegrationFilter,logoutFilter,authenticationProcessingFilter,securityContextHolderAwareRequestFilter,rememberMeProcessingFilter,anonymousProcessingFilter,exceptionTranslationFilter,filterInvocationInterceptor
			</value> 
		</property> 
	</bean> 
 
	<bean id="httpSessionContextIntegrationFilter"
		class="org.acegisecurity.context.HttpSessionContextIntegrationFilter" /> 
 
	<bean id="logoutFilter"
		class="org.acegisecurity.ui.logout.LogoutFilter"> 
		<constructor-arg value="/" /> 
		<constructor-arg> 
			<list> 
				<ref bean="rememberMeServices" /> 
				<bean
					class="org.acegisecurity.ui.logout.SecurityContextLogoutHandler" /> 
			</list> 
		</constructor-arg> 
	</bean> 
 
	<bean id="authenticationProcessingFilter"
		class="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter"> 
		<property name="authenticationManager"
			ref="authenticationManager" /> 
		<property name="authenticationFailureUrl"
			value="/anonymous/logon.jsp?error" /> 
		<property name="defaultTargetUrl" value="/welcome.do" /> 
		<property name="filterProcessesUrl"
			value="/j_acegi_security_check" /> 
		<property name="rememberMeServices" ref="rememberMeServices" /> 
	</bean> 
 
	<bean id="securityContextHolderAwareRequestFilter"
		class="org.acegisecurity.wrapper.SecurityContextHolderAwareRequestFilter" /> 
 
	<bean id="rememberMeProcessingFilter"
		class="org.acegisecurity.ui.rememberme.RememberMeProcessingFilter"> 
		<property name="authenticationManager"
			ref="authenticationManager" /> 
		<property name="rememberMeServices" ref="rememberMeServices" /> 
	</bean> 
 
	<bean id="anonymousProcessingFilter"
		class="org.acegisecurity.providers.anonymous.AnonymousProcessingFilter"> 
		<property name="key" value="changeThis" /> 
		<property name="userAttribute"
			value="anonymousUser,ROLE_ANONYMOUS" /> 
	</bean> 
 
	<bean id="exceptionTranslationFilter"
		class="org.acegisecurity.ui.ExceptionTranslationFilter"> 
		<property name="authenticationEntryPoint"> 
			<bean
				class="org.acegisecurity.ui.webapp.AuthenticationProcessingFilterEntryPoint"> 
				<property name="loginFormUrl" value="/anonymous/logon.jsp" /> 
				<property name="forceHttps" value="false" /> 
			</bean> 
		</property> 
		<property name="accessDeniedHandler"> 
			<bean
				class="org.acegisecurity.ui.AccessDeniedHandlerImpl"> 
				<property name="errorPage" value="/unauth_patient_warning.jsp" /> 
			</bean> 
		</property> 
	</bean> 
	<bean id="filterInvocationInterceptor"
		class="org.acegisecurity.intercept.web.FilterSecurityInterceptor"> 
		<property name="authenticationManager"
			ref="authenticationManager" /> 
		<property name="accessDecisionManager"> 
			<bean class="org.acegisecurity.vote.AffirmativeBased"> 
				<property name="allowIfAllAbstainDecisions"
					value="false" /> 
				<property name="decisionVoters"> 
					<list> 
						<bean class="org.acegisecurity.vote.RoleVoter" /> 
						<bean
							class="org.acegisecurity.vote.AuthenticatedVoter" /> 
					</list> 
				</property> 
			</bean> 
		</property> 
		<property name="objectDefinitionSource"> 
			<value> 
				CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
				PATTERN_TYPE_APACHE_ANT
				/anonymouspatientregistration.do=IS_AUTHENTICATED_ANONYMOUSLY
				/*/displayquestion.do=IS_AUTHENTICATED_ANONYMOUSLY
				/util/favs/**=IS_AUTHENTICATED_ANONYMOUSLY
				/util/open/**=IS_AUTHENTICATED_ANONYMOUSLY
				/anonymous/**=IS_AUTHENTICATED_ANONYMOUSLY
				/common/**=IS_AUTHENTICATED_ANONYMOUSLY
				/style/**=IS_AUTHENTICATED_ANONYMOUSLY
				/images/**=IS_AUTHENTICATED_ANONYMOUSLY
				/js/**=IS_AUTHENTICATED_ANONYMOUSLY
				/spiderplotservlet/**=IS_AUTHENTICATED_ANONYMOUSLY
				/rest/**=IS_AUTHENTICATED_ANONYMOUSLY
				/*.resthttpdo=IS_AUTHENTICATED_ANONYMOUSLY
				/admin/**=ROLE_AD,ROLE_AAD
				/patientportal/**=ROLE_PT
				/**=ROLE_PR,ROLE_PT,ROLE_ST,ROLE_AD,ROLE_AAD
			</value> 
		</property> 
	</bean> 
	<bean id="rememberMeServices"
		class="org.acegisecurity.ui.rememberme.TokenBasedRememberMeServices"> 
		<property name="userDetailsService" ref="userDetailsService" /> 
		<property name="tokenValiditySeconds" value="1800"></property> 
		<property name="key" value="changeThis" /> 
	</bean> 
	<bean id="authenticationManager"
		class="org.acegisecurity.providers.ProviderManager"> 
		<property name="providers"> 
			<list> 
				<ref local="daoAuthenticationProvider" /> 
				<bean
					class="org.acegisecurity.providers.anonymous.AnonymousAuthenticationProvider"> 
					<property name="key" value="changeThis" /> 
				</bean> 
				<bean
					class="org.acegisecurity.providers.rememberme.RememberMeAuthenticationProvider"> 
					<property name="key" value="changeThis" /> 
				</bean> 
			</list> 
		</property> 
	</bean> 
	<bean id="daoAuthenticationProvider"
		class="org.acegisecurity.providers.dao.DaoAuthenticationProvider"> 
		<property name="userDetailsService" ref="userDetailsService" /> 
		<property name="userCache"> 
			<bean
				class="org.acegisecurity.providers.dao.cache.EhCacheBasedUserCache"> 
				<property name="cache"> 
					<bean
						class="org.springframework.cache.ehcache.EhCacheFactoryBean"> 
						<property name="cacheManager"> 
							<bean
								class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean" /> 
						</property> 
						<property name="cacheName" value="userCache" /> 
					</bean> 
				</property> 
			</bean> 
		</property> 
	</bean> 
	<bean id="userDetailsService"
		class="org.acegisecurity.userdetails.jdbc.JdbcDaoImpl"> 
		<property name="dataSource"> 
			<ref bean="genericDataSource"/> 
		</property> 
		<property name="usersByUsernameQuery"> 
			<value> 
				select username, password, 1 from username where username = ?
			</value> 
		</property> 
		<property name="authoritiesByUsernameQuery"> 
			<value> 
				select username, 'ROLE_' || user_type as authority from username where username = ?
			</value> 
		</property> 
	</bean> 
	<bean id="loggerListener"
		class="org.acegisecurity.event.authentication.LoggerListener" /> 
<!-- Acegi bean definitions end --> 
 
```