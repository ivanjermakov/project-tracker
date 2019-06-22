# Project tracker
Project and issue tracking management system

## Web pages
### Feed
![](https://pp.userapi.com/c853420/v853420139/6768b/E7j5qxRAhY8.jpg)
### Project
![](https://pp.userapi.com/c853420/v853420139/67695/tlM7O_v7ays.jpg)
### Task
![](https://pp.userapi.com/c853420/v853420139/6769f/UdPwwRVwiao.jpg)
## Host Server
### Requirements
    JDK 8 or higher
    Gradle 4.10.2 or higher
    PostgreSQL 11 or higher
    
### Setup
1. `git clone https://github.com/ivanjermakov/project-tracker.git`
2. Execute `database.sql` on previously created PSQL database.
3. Fill next properties in `application.properties`:

        spring.datasource.url=
        spring.datasource.username=
        spring.datasource.password=

4. `gradle build`

### Run
Web application will start on `:8080` port.
    
    gradle bootRun

## Host Web client
### Requirements
    node.js

### Setup
1. Go to `web/project-tracker` folder
2. Run `npm install`

### Run
1. Go to `web/project-tracker` folder
2. Run `npm start`

Additional scripts can be found at `package.json`
