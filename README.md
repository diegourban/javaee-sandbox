# Java EE Sandbox

Java EE Sandbox using JPA, JTA, CDI and JAX-RS

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



## Usage

```http://localhost:8080/javaee-sandbox/index.xhtml``` - home page

```http://localhost:8080/javaee-sandbox/livros/form.xhtml``` - book form

```http://localhost:8080/javaee-sandbox/livros/livros.xhtml``` - book list
