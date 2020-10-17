#!/bin/bash
mvn -DrunCucumberTests test
rm csci310.db
mvn flyway:migrate
mvn test

