#!/bin/bash
for CLASS in {1..1500}
do
  export CLASS
  echo Generating "src/main/java/MyClass$CLASS.java"
  envsubst < "GenClasHead.txt" > "src/main/java/MyClass$CLASS.java"

  for PROC in {1..20}
  do
    export PROC
    envsubst < "GenProc.txt" >> "src/main/java/MyClass$CLASS.java"
  done

  envsubst < "GenClasFoot.txt" >> "src/main/java/MyClass$CLASS.java"
done
