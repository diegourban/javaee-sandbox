# Java EE Sandbox

Java EE Sandbox using JPA, JTA, CDI, JAX-RS, JMS and JAAS

## Requirements
* WildFly Full 10.1.0.Final
* H2 or MySQL

## Installation
### Configure H2 database:
Add the following datasouce at jboss configuration:

```
<datasource jndi-name="java:jboss/datasources/JavaeeSandboxDS" pool-name="JavaeeSandboxDS">
	<connection-url>jdbc:h2:file:C:\Users\diego\javaee_sandbox;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
	<driver>h2</driver>
	<security>
		<user-name>sa</user-name>
		<password>sa</password>
	</security>
</datasource>
```

To access H2 database:

```
java -jar C:\Users\diego\wildfly\...\h2-X.X.X.jar
```


### Configure MySQL database:
Create the database:

```
mysql> create database javaee_sandbox;
```

Add the following datasouce at jboss configuration:

```
<datasource jndi-name="java:jboss/datasources/JavaeeSandboxDS" pool-name="JavaeeSandboxDS">
    <connection-url>jdbc:mysql://localhost:3306/javaee_sandbox</connection-url>
    <connection-property name="DatabaseName">
    	javaee_sandbox
    </connection-property>
    <driver>mysql</driver>
    <pool>
    	<min-pool-size>10</min-pool-size>
    	<max-pool-size>20</max-pool-size>
    </pool>
    <security>
        <user-name>root</user-name>
        <password>root</password>
    </security>
</datasource>
```

Extract modules.zip at Wildfly modules folder and add the following driver:

```
<driver name="mysql" module="com.mysql">
    <datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlDataSource</datasource-class>
</driver>
```

### Configure Mail-Session:
```
<mail-session name="gmail" jndi-name="java:jboss/mail/gmail">
    <smtp-server outbound-socket-binding-ref="mail-smtp-gmail" ssl="true" username="{email}" password="{password}"/>
</mail-session>
```

```
<outbound-socket-binding name="mail-smtp-gmail">
    <remote-destination host="smtp.gmail.com" port="465"/>
</outbound-socket-binding>
```

Google > My Account > Sign-in & Security: Allow less secure apps

### Insert admin user:

```
INSERT INTO SYSTEMUSER (EMAIL, SENHA) VALUES('admin@email.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
ISNERT INTO SYSTEMROLE VALUES ('ADMIN');
INSERT INTO SYSTEMUSER_SYSTEMROLE VALUES (1, 'ADMIN');
```

### Configure Security Domain:

```
<security-domain name="database-login" cache-type="default">
	<authentication>
		<login-module flag="required" code="Database">
			<module-option name="dsJndiName" value="java:jboss/datasources/JavaeeSandboxDS"/>
			<module-option name="principalQuery" value="SELECT SENHA FROM SYSTEMUSER WHERE EMAIL = ?"/>
			<module-option name="rolesQuery" value="SELECT UR.ROLES_NAME, 'Roles' FROM SYSTEMUSER_SYSTEMROLE UR INNER JOIN SYSTEMUSER SU ON UR.SYSTEMUSER_ID = SU.ID WHERE SU.EMAIL = ?"/>
			<module-option name="hashAlgorithm" value="SHA-256"/>
			<module-option name="hashEncoding" value="base64"/>
		</login-module>
	</authentication>
</security-domain>
```


## Usage

```http://localhost:8080/javaee-sandbox/index.xhtml``` - home page

```http://localhost:8080/javaee-sandbox/livros/form.xhtml``` - book form

```http://localhost:8080/javaee-sandbox/livros/livros.xhtml``` - book list
