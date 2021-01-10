#!/bin/sh

echo "I'm in docker image"

cd $HOME

java -agentlib:jdwp=transport=dt_socket,address=8092,server=y,suspend=n -jar ./invoice-1.0.0-SNAPSHOT.jar

echo "Invoice Service module successfully deployed!!"