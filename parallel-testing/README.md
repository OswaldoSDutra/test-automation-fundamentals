# Selenium parallel testing

### Reference articles
* https://www.lambdatest.com/blog/webdrivermanager-in-selenium/
* https://www.baeldung.com/maven-junit-parallel-tests
* https://qaautomation.expert/2022/07/13/how-to-parameterize-tests-in-junit4/
  
### Configuration steps
1. Add junit
  
`<dependency>
<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>4.12</version>
<scope>test</scope>
</dependency>`
  
2. Add surfire
  
`<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-surefire-plugin</artifactId>
<version>2.21.0</version>
</plugin>`
  
3. Add selenium-java
  
`<dependency>
<groupId>org.seleniumhq.selenium</groupId>
<artifactId>selenium-java</artifactId>
<version>4.11.0</version>
</dependency>`
  
4. Add WebDriver manager
  
`<dependency>
<groupId>io.github.bonigarcia</groupId>
<artifactId>webdrivermanager</artifactId>
<version>5.5.2</version>
<scope>test</scope>
</dependency>`