# Java EE Sandbox

## Requirements
* WildFly Full 10.1.0.Final
* MySQL (Optional)

## Installation
Configure the H2 database datasource:
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

Create the MySQL database(Optional):
```
mysql> create database javaee_sandbox;
```

Config. the mysql datasource:
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

Add the driver:
```
<driver name="mysql" module="com.mysql">
    <datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlDataSource</datasource-class>
</driver>
```

Extract modules.zip at Wildfly modules folder

## Usage
```http://localhost:8080/javaee-sandbox/livros/form.xhtml```
