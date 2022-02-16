FROM ubuntu

RUN apt-get update 
RUN apt-get -y dist-upgrade
RUN apt-get -y install default-jdk

Run javac Project2_Backend_amplifire-mamba.java

CMD["java", "Project2_Backend_amplifire-mamba"]