# Protractor basics
## Tutorial
* https://www.testim.io/blog/protractor-testing/

## Install & config
``` npm install -g protractor ```  
``` protractor --version ```  
  
Git ignore  
https://github.com/github/gitignore/blob/main/Global/JetBrains.gitignore  
https://github.com/github/gitignore/blob/main/Node.gitignore  

## Running
``` webdriver-manager update (updates selenium server) ```  
``` webdriver-manager start (runs selenium server) ```  

Obs.: It is possible to drive the browser without Selenium server using the ``` directConnect: true ``` option in the config object.

``` protractor conf.js ```   
