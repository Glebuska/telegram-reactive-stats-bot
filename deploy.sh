#!/bin/bash

Modules=('api' 'bot' 'storage')
mvn clean package

for i in "${Modules[@]}"; do
  cd $i
  mvn appengine:deploy
  cd ..
done
