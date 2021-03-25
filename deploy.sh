#!/bin/bash

Modules=('api' 'bot' 'storage')

for i in "${Modules[@]}"; do
  cd $i
  mvn clean install
  eb deploy
  cd ..
  done

