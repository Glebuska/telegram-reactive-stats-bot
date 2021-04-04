#!/bin/bash

Modules=('api' 'bot' 'storage')
mvn clean install

for i in "${Modules[@]}"; do
  cd $i
  eb deploy
  cd ..
done
