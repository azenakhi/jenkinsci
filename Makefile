start:
      docker run -p 8080:8080 -p 50000:50000 -v $(pwd)/scripts:/var/jenkins_home/init.groovy.d -v $(pwd)/jenkins_home:/var/jenkins_home jenkins/jenkins:lts
